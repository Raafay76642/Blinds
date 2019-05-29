package com.example.aser.blinds;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    SharedPreferences readData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);
        gotolocationbtn =(LinearLayout) findViewById(R.id.GotoLocation);
        otherlocationbtn = (LinearLayout) findViewById(R.id.OtherPlaces);
        MediaPlayer player = MediaPlayer.create(this,R.raw.main);
        player.start();
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
    public void gotofavrouiteLocations(View view)
    {
        Intent goGurdian = new Intent(this,FavriouteLocation.class);
        startActivity(goGurdian);
    }
    public void gotoothers(View view)
    {
        Intent goGurdian = new Intent(this,OtherPlaces.class);
        startActivity(goGurdian);
    }

    public void makeCall()
    {

        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        }else {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        String no1 = readData.getString("spphno1","");
        String no2 = readData.getString("spphno2","");
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+no1));
            this.startActivity(callIntent);
        }else{
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            startActivity( new Intent(this,FavriouteLocation.class));
        }
        if((keyCode==KeyEvent.KEYCODE_VOLUME_UP))
        {
           makeCall();
        }
        return true;
    }


}
