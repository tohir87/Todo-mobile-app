package ie.griffith.tohir.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomItem {
    // Constructor
    CustomItem(String name, long time){
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
        Date d = new Date(time);
        date = sdf.format(d);
    }

    // getter methods
    public String getName(){ return name; };
    public String getDate(){ return date; }

    // private fields of the class
    private String name;
    private String date;

}
