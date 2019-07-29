package com.icloud.pcehb.guardian.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.icloud.pcehb.guardian.R;
import com.icloud.pcehb.guardian.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView desc;
        private TextView date;
        private String dateFormatted;

        private ImageView photo;


        public ViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.article_title);
            author = itemView.findViewById(R.id.cat);
            desc = itemView.findViewById(R.id.article_desc);
            date = itemView.findViewById(R.id.date);
            photo = itemView.findViewById(R.id.photo);

            itemView.setOnClickListener(itemCLickListener);
        }

        public void setData(Result articles, int position) {
            title.setText(articles.getWebTitle());
            author.setText(articles.getFields().getByline());
            desc.setText(articles.getFields().getTrailText());

            //format date
            dateFormatted = articles.getWebPublicationDate();
            String[] dateFormattedPart = dateFormatted.split("T"); // yyyy/mm/dd
            String[] dateSplit = dateFormattedPart[0].split("-");
            String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
            int month = Integer.parseInt(dateSplit[1]);
            month = month - 1;
            dateFormatted = dateSplit[2] + " " + months[month] + " " + dateSplit[0];
            date.setText(dateFormatted);

            Picasso.get()
                    .load(articles.getFields().getThumbnail())
                    .placeholder(R.drawable.newspaper)
                    .into(photo);
            itemView.setTag(position);
        }
    }

    public List<Result> articles = new ArrayList<>();

    @Override
    public int getItemCount() {
        return articles.size();
    }


    private View.OnClickListener itemCLickListener;

    public FeedAdapter(View.OnClickListener clickListener) {
        super();

        this.itemCLickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_feed_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result article = articles.get(position);
        holder.setData(article, position);
    }

    //update data when refreshing articles
    public void updateData(List<Result> newsFeed) {
        this.articles = newsFeed;
        this.notifyDataSetChanged();
    }

    //add data when loading more articles
    public void addData(List<Result> newsFeed) {
        this.articles.addAll(newsFeed);
        this.notifyDataSetChanged();
    }
}