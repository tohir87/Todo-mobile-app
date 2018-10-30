package ie.griffith.tohir.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomItem {
    // Constructor

    /**
     * Class default constructor
     * @param todoId Task ID
     * @param title Task Title
     * @param note Task Note
     */
    CustomItem(int todoId, String title, String note){
        this.id = String.valueOf(todoId);
        this.title = title;
        this.note = note;
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
    }


    /**
     * Getter method that returns the ID of the task
     * @return
     */
    public String getId(){ return id; };

    /**
     * Getter method that returns the Title of the task
     * @return
     */
    public String getTitle(){ return title; };

    /**
     * Getter method that returns the note of the task
     * @return
     */
    public String getNote(){ return note; };

    // private fields of the class
    private String id;
    private String title;
    private String note;

}
