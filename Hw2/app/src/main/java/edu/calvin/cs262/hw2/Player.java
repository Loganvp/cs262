package edu.calvin.cs262.hw2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Players forecast object (POJO), one per day, based on openweathermap's RESTful API.
 * Based on Deitel's WeatherViewer app (chapter 17).
 *
 * @author deitel
 * @author kvlinden
 * @version spring, 2017
 *
 * @author Loganvp
 * @version 2016
 *
 */
public class Player {

    private int id;
    private String emailaddress, name;

    public Player(int id, String emailaddress, String name) {
        this.id = id;
        this.emailaddress = emailaddress;
        this.name =  name;
    }

    public int getID() {
        return id;
    }
    public String getEmailAddress() {
        return emailaddress;
    }
    public String getName() {
        return name;
    }
}
