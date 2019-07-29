package com.icloud.pcehb.guardian.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.icloud.pcehb.guardian.R;
import com.icloud.pcehb.guardian.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

//Shows article contents

public class DetailsActivity extends AppCompatActivity {

    private TextView title;
    private String date;
    private TextView desc;
    private ImageView photo;
    private TextView categories;
    private ProgressBar progressBar;
    private Boolean isFABOpen = false;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab;
    private Result data;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        getWindow().setEnterTransition(new Explode());

        //get article data sent as Extra
        final Intent intent = getIntent();
        data = (Result) intent.getSerializableExtra("article");


        setContentView(R.layout.activity_details);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        // show all fabs
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        // share article
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, data.getWebUrl());
                startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
            }
        });
        // open article in web
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent internet = new Intent(Intent.ACTION_VIEW);
                internet.setData(Uri.parse(data.getWebUrl()));
                startActivity(internet);
            }
        });

        updateData();
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        ViewCompat.animate(fab)
                .rotation(45.0F)
                .withLayer()
                .setDuration(300L)
                .start();
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        ViewCompat.animate(fab)
                .rotation(0.0F)
                .withLayer()
                .setDuration(300L)
                .start();

    }

    public void updateData(){
        title=(TextView) findViewById(R.id.article_title);
        desc=(TextView) findViewById(R.id.article_desc);
        photo=(ImageView) findViewById(R.id.toolbarImage);
        categories=(TextView) findViewById(R.id.categories);
        toolbar = findViewById(R.id.toolbar);

        //formats date
        date = data.getWebPublicationDate();
        String[] dateFormattedPart = date.split("T"); // yyyy/mm/dd
        String[] dateSplit = dateFormattedPart[0].split("-");
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        int month = Integer.parseInt(dateSplit[1]);
        month = month -1;
        date = dateSplit[2].toString() + " " + months[month] + " " + dateSplit[0].toString();

        toolbar.setTitle(date);

        title.setText(data.getWebTitle());
        desc.setText(data.getFields().getBodyText());
        categories.setText("Category: " + data.getSectionName());

        Picasso.get()
                .load(data.getFields().getThumbnail())
                .placeholder(R.drawable.newspaper)
                .into(photo);
    }

}
