package ie.griffith.tohir.todoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
// import java utils
import java.util.*;

// import date lib
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_date;
    private TextView tv_task;
    private Button btn_add_task;
    private TestDBOpenHelper tdb;
    private SQLiteDatabase sdb;
//    private TextView tv_display;
//    private ListView lv_mainlist;
//    private EditText et_new_strings;
//    private ArrayList<CustomItem>	al_items;
//    private CustomArrayAdapter	caa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get access to sqlite DB
        tdb = new TestDBOpenHelper(this, "todo.db", null, 1);
        sdb = tdb.getWritableDatabase();

        init();


    }

    public void init(){
        btn_add_task = (Button) findViewById(R.id.btn_add_task);
        TextView tv_date = (TextView) findViewById(R.id.tv_date);


        String date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        tv_date.setText("Today " + date);
        Log.i("MainActivity", "onCreate: date set");

        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        String table_name = "todo";
        String[] columns = {"ID", "TITLE", "TASK"};

        String where = null;
        String where_args[] = null;
        String group_by = null;
        String having = null;
        String order_by = null;

        Cursor c = sdb.query(table_name, columns, where, where_args, group_by, having, order_by);

        // display data on view
        TextView tv = (TextView) findViewById(R.id.tv_task);
        String tasks = "";
        c.moveToFirst();
        for(int i =	0;	i <	c.getCount();	i++)	{
            tasks +=	c.getInt(0)	+	"	" +	c.getString(1) +	"	" +	c.getString(2)	+	"\n";
            c.moveToNext();
        }
        tv.setText(tasks);

    }






}
