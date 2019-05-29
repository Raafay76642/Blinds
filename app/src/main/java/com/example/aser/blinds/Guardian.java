package com.example.aser.blinds;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Guardian extends AppCompatActivity {

    SharedPreferences readData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);

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
    public void openEmergency_Number(View view)
    {
        Intent oEN = new Intent(this,Emergancy_Number.class);
        startActivity(oEN);
    }
    public void openSetLocation(View view)
    {
        Intent oSL = new Intent(this,SetLocation.class);
        startActivity(oSL);
    }
    public void dletenumber(View view)
    {

        SharedPreferences.Editor editor= getSharedPreferences("Dataguardian", MODE_PRIVATE).edit();

        String PhNo1 = "";
        String PhNo2 = "";

        editor.putString("spphno1",PhNo1);
        editor.putString("spphno2",PhNo2);
        editor.apply();
        Toast.makeText(Guardian.this, "Number deleted", Toast.LENGTH_LONG).show();
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){

        }
        if((keyCode==KeyEvent.KEYCODE_VOLUME_UP))
        {
            makeCall();
        }
        return true;
    }
}
