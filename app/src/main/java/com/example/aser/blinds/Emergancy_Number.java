package com.example.aser.blinds;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Trace;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Emergancy_Number extends AppCompatActivity {



    EditText phNo1,phNo2;
    SharedPreferences readData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergancy__number);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);
        phNo1 =(EditText)findViewById(R.id.ph_no_1);
        phNo2 =(EditText)findViewById(R.id.ph_no_2);
        String no1 = readData.getString("spphno1","");
        String no2 = readData.getString("spphno2","");
       phNo1.setText(no1);
        phNo2.setText(no2);
        uneditable();


    }


    public void uneditable()
    { phNo1.setEnabled(false);
        phNo2.setEnabled(false);
        phNo1.setTextColor(Color.parseColor("#000000"));
        phNo2.setTextColor(Color.parseColor("#000000"));

    }
    public void saveNo(View view)
    {
        SharedPreferences.Editor editor= getSharedPreferences("Dataguardian", MODE_PRIVATE).edit();
        phNo1 =(EditText)findViewById(R.id.ph_no_1);
        phNo2 =(EditText)findViewById(R.id.ph_no_2);
        String PhNo1 = phNo1.getText().toString();
        String PhNo2 = phNo2.getText().toString();
        phNo1.setEnabled(false);
        phNo2.setEnabled(false);
        phNo1.setTextColor(Color.parseColor("#000000"));
        phNo2.setTextColor(Color.parseColor("#000000"));
        editor.putString("spphno1",PhNo1);
        editor.putString("spphno2",PhNo2);
        editor.apply();
        Toast.makeText(Emergancy_Number.this, "Number Saved", Toast.LENGTH_LONG).show();

    }
    public void editable(View view)
    {
        phNo1.setEnabled(true);
        phNo2.setEnabled(true);
    }
    public void makeCall(View view)
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
           startActivity( new Intent(this,Guardian.class));
        }
        if((keyCode==KeyEvent.KEYCODE_VOLUME_UP))
        {
            phoneCall();
        }
        return true;
    }





}
