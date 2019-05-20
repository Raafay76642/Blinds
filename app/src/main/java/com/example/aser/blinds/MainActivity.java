package com.example.aser.blinds;

import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.content.Intent;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    LinearLayout gotolocationbtn,otherlocationbtn;
    int badgeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotolocationbtn =(LinearLayout) findViewById(R.id.GotoLocation);
        otherlocationbtn = (LinearLayout) findViewById(R.id.OtherPlaces);


        gotolocationbtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                gotolocationmp();
                return true;
            }
        });
        otherlocationbtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                otherplacesmp();
                return true;
            }
        });
    }

public void gotolocationmp()
{
    MediaPlayer player = MediaPlayer.create(this,R.raw.gotofavriotplaces);
    player.start();

}
    public void otherplacesmp()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.gotootherplaces);
        player.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mode,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.blind:

                Intent Bi = new Intent(this, MainActivity.class);
                startActivity(Bi);
                finish();
                return true;
            case R.id.Guardian:
                Intent Gi = new Intent(this, Guardian.class);
                startActivity(Gi);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void goGurdian(View view)
    {
        Intent goGurdian = new Intent(this,FavriouteLocation.class);
        startActivity(goGurdian);
    }
    public void not(View view)
    {
        badgeCount++;
        ShortcutBadger.applyCount(this, badgeCount); //for 1.1.4+
    }

}
