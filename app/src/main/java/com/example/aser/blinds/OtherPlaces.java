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

public class OtherPlaces extends AppCompatActivity {

    SharedPreferences readData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_places);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);
        LinearLayout bank = findViewById(R.id.bank);
        LinearLayout resturant = findViewById(R.id.resturants);

        bank.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                bank();
                return true;
            }
        });
        resturant.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resturant();
                return true;

            }
        });
    }
    public  void bank()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.bank);
        player.start();


    }
    public  void resturant()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.resturant);
        player.start();


    }

    public void openbank(View view)
    {
        String placeName = "Bank";
        Intent intent = new Intent(this, Office.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openresturant(View view)
    {
        String placeName = "Resturant";
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
