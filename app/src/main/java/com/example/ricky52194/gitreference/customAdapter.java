package com.example.ricky52194.gitreference;

/**
 * Created by ricky52194 on 2/25/18.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class customAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GitReference> mDataSource;


    public customAdapter(Context context, ArrayList<GitReference> commands){
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
        if (position % 2 == 1) {
            rowView.setBackgroundResource(R.color.customBlue);
        } else {
            rowView.setBackgroundColor(Color.GRAY);
        }

        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);
        TextView sectionView = rowView.findViewById(R.id.section);


        GitReference command = (GitReference) getItem(position);

        String txt1 = mContext.getResources().getString(R.string.commandTag) + command.getCommand();
        commandView.setText(txt1);

        String txt2 = mContext.getResources().getString(R.string.exampleTag) + command.getExample();
        exampleView.setText(txt2);

        String txt3 = mContext.getResources().getString(R.string.explanationTag) + command.getExplanation();
        explanationView.setText(txt3);

        String txt4 = mContext.getResources().getString(R.string.sectionTag) + command.getSection();
        sectionView.setText(txt4);

        return rowView;
    }

}



