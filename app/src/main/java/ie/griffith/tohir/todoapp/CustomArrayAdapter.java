package ie.griffith.tohir.todoapp;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomArrayAdapter extends BaseAdapter {

    // constructor for class that takes reference to a context and an arraylist
    public CustomArrayAdapter(Context c, ArrayList<CustomItem> al){
        context = c;
        al_items = al;
    }

    // overriden method to construct a view for the listview
    public View getView(int position, View convert_view, ViewGroup parent){
        ViewHolder holder;
        if(convert_view ==	null)	{
            holder =	new ViewHolder();
            //	get	access	to	the	layout	infaltor service
            LayoutInflater	inflator =	(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //	inflate	the	XML	custom	item	layout	into	a	view	to	which	we	can	add	data
            convert_view =	inflator.inflate(R.layout.custom_item_layout,	parent,	false);
            //	pull	all	the	items	from	the	XML	so	we	can	modify	them
            holder.tv_name =	(TextView)	convert_view.findViewById(R.id.tv_name);
            holder.tv_date =	(TextView)	convert_view.findViewById(R.id.tv_date);
//            holder.iv_image =	(ImageView)	convert_view.findViewById(R.id.iv_image);
//	set	the	view	holder as	a	tag	on	this	convert	view	in	case	it	needs	to	be
//	recycled
            convert_view.setTag(holder);
        }else{
            holder = (ViewHolder) convert_view.getTag();
        }

        // set all data on the fields before returning
        holder.tv_name.setText(al_items.get(position).getName());
        holder.tv_date.setText(al_items.get(position).getDate());

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
        public TextView tv_name;
        public TextView tv_date;
        public TextView iv_image;
    }
}
