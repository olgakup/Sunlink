package com.csun_sunlink.csuncareercenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by olgak on 11/7/16.
 */

public class HomePage extends AppCompatActivity{

    //Variables:


    //private final CURRENTUSER = getCurrentUser();
    private String userName = "Matt Ross";
    private String userDegree = "Computer Science";
    private String screenHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Get Curent User: currentUser = ParseUser.getCurrentUser(); + check if the user !null
        createHeader();
        TextView header = (TextView) findViewById(R.id.headerHomePage);
        header.setText(screenHeader);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.profile);
        Bitmap circularBitmap = ProfileImageDrawable.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)findViewById(R.id.imageView);
        circularImageView.setImageBitmap(circularBitmap);


    }

    public void createHeader() {
        this.screenHeader = this.userName + "\n" + this.userDegree;
    }
}
