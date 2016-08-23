package Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mrrobot.mynote.NotesDetails;
import com.example.mrrobot.mynote.R;

import java.io.Serializable;
import java.util.ArrayList;

import Data.MyNotes;

/**
 * Created by Mr.Robot on 8/4/2016.
 */
public class CustomViewListAdapter extends ArrayAdapter<MyNotes> {
    private int layoutR;
    private Activity activity;
    private ArrayList<MyNotes> noteList = new ArrayList<>();

    public CustomViewListAdapter(Activity act, int resource, ArrayList<MyNotes> data) {
        super(act, resource, data);
        layoutR  = resource;
        activity = act;
        noteList =data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public MyNotes getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public int getPosition(MyNotes item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;
        if ( row == null || (row.getTag() == null)){
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutR, null);
            holder = new ViewHolder();
            holder.Title = (TextView) row.findViewById(R.id.listname);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.myNotes = getItem(position);
        holder.Title.setText(holder.myNotes.getTitle());

        final ViewHolder finalV = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, NotesDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("userData", finalV.myNotes);
                i.putExtras(mBundle);
                activity.startActivity(i);

            }
        });



        return row;
    }

    public class ViewHolder {
        MyNotes myNotes;
        TextView Title;

    }
}
