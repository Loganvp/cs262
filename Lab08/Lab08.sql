/* Logan VanProyen
 * Lab08
 *
 * Player(ID, emailAddress, name)
 * PlayerGame(saveID, gameID, playerID, score, cash, pieceLocation)
 * Game (ID, time)
 * SavedGameInfo(ID, assetType, location) 
 * 
 */

/* Exercise 8.1 */

--SELECT * FROM Game ORDER BY time DESC;

--SELECT * FROM Game WHERE time >= '10/28/2016';

--SELECT * FROM Player WHERE name != NULL;

--SELECT * FROM Player WHERE Player.ID = PlayerGame.playerID AND PlayerGame.score > 2000;

--SELECT * FROM Player WHERE emailAddress LIKE %gmail.com%;

/* Exercise 8.2 */

--SELECT PG.score FROM Player P , PlayerGame PG , WHERE P.ID = PG.playerID ORDER BY PG.score DESC;

--SELECT P.name FROM Player P , PlayerGame PG , Game G  WHERE P.ID = PG.playerID AND G.ID = PG.gameID AND G.time = '2006-06-28 13:20:00';

--It should do absolutely nothing because all the data is the same.

--Maybe if you wanted to see if one person did better on a game with more money or less money.

