-- @version Summer, 2015
-- edited by Logan for Fall 2016

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.

USE gameDatabase;

DROP TABLE IF EXISTS SavedGameInfo;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;

-- Create the schema.
CREATE TABLE Game (
	ID		INT PRIMARY KEY, 
	time	timestamp
	);

CREATE TABLE Player (
	ID				INT PRIMARY KEY, 
	emailAddress	NVARCHAR(50) NOT NULL,
	name			NVARCHAR(50)
	);
	
CREATE TABLE PlayerGame (
    saveID			INT IDENTITY(1,1) PRIMARY KEY,
	gameID			INT REFERENCES Game(ID), 
	playerID		INT REFERENCES Player(ID),
	score			INT,
	cash			INT,
	pieceLocation	INT
	);
	
CREATE TABLE SavedGameInfo(
	ID			INT REFERENCES PlayerGame(saveID),
	assetType	NVARCHAR(10),
	location	INT
	);
	
-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON SavedGameInfo TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player VALUES (1, 'me@calvin.edu', 'Logan');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 1, 0.00, 100, 1);
INSERT INTO PlayerGame VALUES (2, 1, 2, 0.00, 200, 3);
INSERT INTO PlayerGame VALUES (3, 1, 3, 2350.00, 300, 2);
INSERT INTO PlayerGame VALUES (4, 2, 1, 1000.00, 400, 2);
INSERT INTO PlayerGame VALUES (5, 2, 2, 0.00, 500, 3);
INSERT INTO PlayerGame VALUES (6, 2, 3, 500.00, 600, 1);
INSERT INTO PlayerGame VALUES (7, 3, 2, 0.00, 700, 3);
INSERT INTO PlayerGame VALUES (8, 3, 3, 5500.00, 800, 1);

INSERT INTO SavedGameInfo VALUES (1, 'house', 5);
INSERT INTO SavedGameInfo VALUES (2, 'house', 7);
INSERT INTO SavedGameInfo VALUES (3, 'house', 13);
INSERT INTO SavedGameInfo VALUES (1, 'hotel', 20);
INSERT INTO SavedGameInfo VALUES (2, 'hotel', 11);
INSERT INTO SavedGameInfo VALUES (3, 'hotel', 1);
INSERT INTO SavedGameInfo VALUES (3, 'property', 9);
INSERT INTO SavedGameInfo VALUES (3, 'property', 16);
INSERT INTO SavedGameInfo VALUES (3, 'property', 2);
INSERT INTO SavedGameInfo VALUES (1, 'property', 3);
