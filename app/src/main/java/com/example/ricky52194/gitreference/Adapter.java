package com.example.ricky52194.gitreference;

/**
 * Created by ricky52194 on 2/25/18.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by ricky52194 on 2/15/18.
 */

public class Adapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList mDataSource;

    public Adapter(Context context, ArrayList<GitReference> commands){
        mContext = context;
        mDataSource = commands;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.custom_row_view, viewGroup, false);

        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);
        TextView sectionView = rowView.findViewById(R.id.section);


        GitReference command = (GitReference) getItem(position);
        commandView.setText(command.getCommand());
        exampleView.setText(command.getExample());
        explanationView.setText(command.getExplanation());
        sectionView.setText(command.getSection());

        return rowView;
    }
}

