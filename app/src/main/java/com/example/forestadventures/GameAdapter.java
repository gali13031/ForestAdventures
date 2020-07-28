package com.example.forestadventures;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

    private final Activity context;
    private final String[] scores;
    private final String[] names;

    static class ViewHolder {

        public TextView name;
        public TextView score;
        public ImageView image;
    }

    public GameAdapter(Activity context, String[] names, String[] scores) {

        this.context = context;
        this.scores = scores;
        this.names = names;
    }

    public int getCount() {
        return scores.length;
    }

    public Object getItem(int position) {
        return scores[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        //reuse views
        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.scoreplaces_layout, null);

            //Configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name_tv);
            viewHolder.score = (TextView) rowView.findViewById(R.id.scoreResult_tv);
            viewHolder.image =(ImageView) rowView.findViewById(R.id.icon_iv);
            rowView.setTag(viewHolder);
        }

        //fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = scores[position];
        String n = names[position];
        holder.name.setText(s);
        holder.score.setText(n);

        if(position == 0){
            holder.image.setImageResource(R.drawable.trophy_firstplace);
        } else if(position == 1){
            holder.image.setImageResource(R.drawable.trophy_secondplace);
        } else if(position == 2){
            holder.image.setImageResource(R.drawable.trophy_thirdplace);
        } else if(position == 3){
            holder.image.setImageResource(R.drawable.fourthplace);
        } else if(position == 4){
            holder.image.setImageResource(R.drawable.fivethplace);
        } else if(position == 5){
            holder.image.setImageResource(R.drawable.sixthplace);
        } else if(position == 6){
            holder.image.setImageResource(R.drawable.sevenplace);
        } else if(position == 7){
            holder.image.setImageResource(R.drawable.eigthplace);
        } else if(position == 8){
            holder.image.setImageResource(R.drawable.ninethplace);
        } else if(position == 9) {
            holder.image.setImageResource(R.drawable.tenplace);
        }
        return rowView;
    }
}
