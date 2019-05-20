package com.example.aser.blinds;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Trace;
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
        String no1 = readData.getString("spphno1","");
        String no2 = readData.getString("spphno2","");

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", no1, null));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
           startActivity( new Intent(this,Guardian.class));
        }
        return true;
    }





}
