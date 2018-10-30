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
    private Button btn_add_task;
    private TestDBOpenHelper tdb;
    private SQLiteDatabase sdb;
    private ListView lv_mainlist;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter	caa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get access to sqlite DB
        tdb = new TestDBOpenHelper(this, "todo.db", null, 1);
        sdb = tdb.getWritableDatabase();

        // generte array list with some strings
        al_items = new ArrayList<CustomItem>();

        // create an adapter and set it on listview
        caa = new CustomArrayAdapter(this, al_items);


        btn_add_task = (Button) findViewById(R.id.btn_add_task);
        tv_date = (TextView) findViewById(R.id.tv_date);
        lv_mainlist =	(ListView)	findViewById(R.id.lv_mainlist);

        lv_mainlist.setAdapter(caa);



        init();


    }

    public void init(){

        String date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        tv_date.setText("Today " + date);
        Log.i("MainActivity", "onCreate: date set");

        updateListViewFromDB();

    }

    /**
     * Loads a new activity for adding new task
     * @param v View
     */
    public void createClickHandler(View v){
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        startActivity(intent);
    }

    /**
     * This method deletes an existing task
     * @param v
     */
    public void deleteClickHandler(View v){
        // get the position index
        int position = lv_mainlist.getPositionForView((View) v.getParent());

        // get object on the position
        al_items.get(position);
        Log.i("MainActivity", "Position = " + al_items.get(position).getId());

        // delete from DB
        String table_name = "todo";

        sdb.delete(table_name, "ID" + "=?", new String[]{al_items.get(position).getId()});

        al_items.remove(position);
        Toast.makeText(this, "Position = " + al_items.get(position).getId(), Toast.LENGTH_LONG).show();

        updateListViewFromDB();
    }

    public void updateListViewFromDB(){
        String table_name = "todo";
        String[] columns = {"ID", "TITLE", "TASK"};

        String where = null;
        String where_args[] = null;
        String group_by = null;
        String having = null;
        String order_by = null;

        Cursor c = sdb.query(table_name, columns, where, where_args, group_by, having, order_by);

        // display data on view

        String tasks = "";
        c.moveToFirst();
        for(int i =	0;	i <	c.getCount();	i++)	{
            al_items.add(new CustomItem(c.getInt(0), c.getString(1), c.getString(2)));
            tasks +=	c.getInt(0)	+	"	" +	c.getString(1) +	"	" +	c.getString(2)	+	"\n";
            c.moveToNext();
        }
        Log.i("MainActivity", tasks);

        if (caa == null) {
            caa = new CustomArrayAdapter(this, al_items);
            lv_mainlist.setAdapter(caa);
        } else {
            caa.notifyDataSetChanged();
        }

        c.close();
        sdb.close();
    }

}
