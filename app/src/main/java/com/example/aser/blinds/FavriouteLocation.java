package com.example.aser.blinds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import me.leolin.shortcutbadger.ShortcutBadger;

public class FavriouteLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favrioute_location);
        LinearLayout masjid = findViewById(R.id.masjid);
        LinearLayout school = findViewById(R.id.school);
        LinearLayout park = findViewById(R.id.park);
        LinearLayout home = findViewById(R.id.home);
        LinearLayout office = findViewById(R.id.Office);
        selectthis();
        masjid.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                masjid();
                return true;
            }
        });
        school.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                school();
                return true;

            }
        });
        park.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                park();
                return true;
            }
        });
        office.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                office();
                return true;
            }
        });
        home.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                home();
                return true;
            }
        });
    }



    public void school()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.gotoschool);
        player.start();

    }
    public void home()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.home);
        player.start();

    }
    public void masjid()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.masjid);
        player.start();

    }
    public void office()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.office);
        player.start();

    }
    public void park()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.park);
        player.start();

    }
    public void selectthis()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.selectedfavruoite);
        player.start();

    }



    public void openmasjid(View view)
    {
        String placeName = "Masjid";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openSchool(View view)
    {

        String placeName = "School";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openPark(View view)
    {

        String placeName = "Park";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openHome(View view)
    {

        String placeName = "Home";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openOffice(View view)
    {

        String placeName = "Office";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            startActivity( new Intent(this,MainActivity.class));
        }
        return true;
    }

}