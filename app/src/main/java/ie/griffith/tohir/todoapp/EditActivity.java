package ie.griffith.tohir.todoapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class for editing of tasks
 */

public class EditActivity extends AppCompatActivity {

    private TestDBOpenHelper tdb;
    private SQLiteDatabase sdb;
    private EditText et_title;
    private EditText et_task;
    private String taskId;

    /**
     * Oncreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // get access to sqlite DB
        tdb = new TestDBOpenHelper(this, "todo.db", null, 1);
        sdb = tdb.getWritableDatabase();

        Intent intent = getIntent();
        taskId = intent.getExtras().getString("id");
        String title = intent.getExtras().getString("title");
        String note = intent.getExtras().getString("note");
        Log.i("EditActivity", "id = " + taskId + " title = " + title);

        et_title = (EditText) findViewById(R.id.et_title_edit);
        et_task = (EditText) findViewById(R.id.et_task_edit);

        // set content of edit text
        et_title.setText(title);
        et_task.setText(note);
    }


    /**
     * Method to update task in database
     * @param v
     */
    public void updateClickHandler(View v){
        ContentValues cv = new ContentValues();
        cv.put("TITLE", et_title.getText().toString());
        cv.put("TASK", et_task .getText().toString());

        sdb.update("todo", cv, 	"ID=?", new String[]{taskId});

        // return back to main activity
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);

        // Log to console
        Log.i("EditActivity", "new task updated");
    }
}
