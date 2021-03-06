package com.example.aser.blinds;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class FavriouteLocation extends AppCompatActivity {

    SharedPreferences readData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favrioute_location);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);
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
            startActivity( new Intent(this,MainActivity.class));
        }
        if((keyCode==KeyEvent.KEYCODE_VOLUME_UP))
        {
            makeCall();
        }
        return true;
    }

}