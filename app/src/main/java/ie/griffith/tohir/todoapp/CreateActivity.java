package ie.griffith.tohir.todoapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    // private fields of the class
    private EditText et_title;
    private EditText et_task;
    private TestDBOpenHelper tdb;
    private SQLiteDatabase sdb;

    public void init(){
        et_task = (EditText) findViewById(R.id.et_task);
        et_title = (EditText) findViewById(R.id.et_title);

        // get access to sqlite DB
        tdb = new TestDBOpenHelper(this, "todo.db", null, 1);
        sdb = tdb.getWritableDatabase();

        // set action listener
        et_task.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionid, KeyEvent keyEvent) {
                if (actionid == EditorInfo.IME_ACTION_DONE) {

                    String date_created = new SimpleDateFormat("yyyy-dd-dd", Locale.getDefault()).format(new Date());

                    // save task to database
                    ContentValues cv = new ContentValues();
                    cv.put("TITLE", et_title.getText().toString());
                    cv.put("TASK", et_task.getText().toString());
                    cv.put("DATE_CREATED", date_created);
                    sdb.insert("todo", null, cv);

                    Log.i("CreateActivity", "new task entered" + et_task.getText());

                    // return back to main activity
                    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        init();
    }


    public void saveTask(View view) {
        Log.i("CreateActivity", "new task entered" + et_task.getText());
    }
}
