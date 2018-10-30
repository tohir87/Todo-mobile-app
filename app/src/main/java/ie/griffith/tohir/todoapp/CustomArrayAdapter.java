package ie.griffith.tohir.todoapp;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomArrayAdapter extends BaseAdapter {

    // constructor for class that takes reference to a context and an arraylist
    public CustomArrayAdapter(Context c, ArrayList<CustomItem> al){
        context = c;
        al_items = al;
    }

    // overriden method to construct a view for the listview
    public View getView(final int position, View convert_view, ViewGroup parent){
        ViewHolder holder;
        if(convert_view ==	null)	{
            holder =	new ViewHolder();
            //	get	access	to	the	layout	infaltor service
            LayoutInflater	inflator =	(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //	inflate	the	XML	custom	item	layout	into	a	view	to	which	we	can	add	data
            convert_view =	inflator.inflate(R.layout.custom_item_layout,	parent,	false);
            //	pull	all	the	items	from	the	XML	so	we	can	modify	them
//            holder.tv_id =	(TextView)	convert_view.findViewById(R.id.tv_id);
            holder.tv_title =	(TextView)	convert_view.findViewById(R.id.tv_title);
            holder.tv_note =	(TextView)	convert_view.findViewById(R.id.tv_note);

            // set event listeners for buttons
            holder.btn_delete = (Button) convert_view.findViewById(R.id.btn_delete);
//

            //	set	the	view	holder as	a	tag	on	this	convert	view	in	case	it	needs	to	be
            //	recycled
            convert_view.setTag(holder);
        }else{
            holder = (ViewHolder) convert_view.getTag();
        }

        // set all data on the fields before returning
//        holder.tv_id.setText((al_items.get(position).getId()));
        holder.tv_title.setText(al_items.get(position).getTitle());
        holder.tv_note.setText(al_items.get(position).getNote());


        // return constructed view
        return convert_view;
    }

    // overriden method that will tell the listview how many items of data that is to be displayed
    public int getCount(){ return al_items.size(); }

    // returns the rowid of the item at the given position.
    public long getItemId(int position){ return position; }

    // Overriden method that will return the item at a given position

    @Override
    public Object getItem(int position) {
        return al_items.get(position);
    }

    // private fields for class
    private Context context;
    private ArrayList<CustomItem> al_items;

    static class ViewHolder {
//        public TextView tv_id;
        public TextView tv_title;
        public TextView tv_note;
        public Button btn_delete;
        public Button btn_edit;
    }
}
