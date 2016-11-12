package com.csun_sunlink.csuncareercenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by olgak on 11/7/16.
 */

public class HomePage extends AppCompatActivity{

    //Variables:


    //private final CURRENTUSER = getCurrentUser();
    /*this is data to test home page class*/
    private String userName = "Matt Ross";
    private String userDegree = "Computer Science";
    private String screenHeader;
    private String event1 = "Event1";
    private String date1 ="11/1/16";
    private String event2 = "Event2";
    private String date2 ="11/2/16";
    private String event3 = "Event3";
    private String date3 ="11/3/16";

    //Buttons:
    ImageButton prflButton;
    ImageButton srchButton;
    ImageButton careerButton;
    ImageButton rsrcButton;
    ImageButton sttngButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Get Curent User: currentUser = ParseUser.getCurrentUser(); + check if the user !null
        createHeader();
        TextView header = (TextView) findViewById(R.id.headerHomePage);
        header.setText(screenHeader);

        //Profile picture:
        //check if no profile picture on file use defualt:

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.defaultpicture);
        Bitmap circularBitmap = ProfileImageDrawable.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)findViewById(R.id.imageView);
        circularImageView.setImageBitmap(circularBitmap);

        //Main Menu Buttons:
        //Profile:
        prflButton =(ImageButton)findViewById(R.id.profile_button);
        prflButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(HomePage.this, ProfileActivity.class);
               // startActivity(intent);
            }
        });
        //Search:
        srchButton =(ImageButton)findViewById(R.id.search_button);
        srchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(HomePage.this, SearchActivity.class);
                // startActivity(intent);
            }
        });
        //Career Center:
        careerButton =(ImageButton)findViewById(R.id.careercenter_button);
        careerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(HomePage.this, CareerCenterActivity.class);
                // startActivity(intent);
            }
        });
        //Resources:
        rsrcButton = (ImageButton)findViewById(R.id.resources_button);
        rsrcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(HomePage.this, ResourcesActivity.class);
                // startActivity(intent);
            }
        });
        //Settings:
        sttngButton =(ImageButton)findViewById(R.id.settings_button);
        sttngButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(HomePage.this, SettingsActivity.class);
                // startActivity(intent);
            }
        });


    }

    public void createHeader() {
        this.screenHeader = this.userName + "\n" + this.userDegree;
    }
}
