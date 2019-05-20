package com.example.aser.blinds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SetLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
    }
    public void openmasjid(View view)
    {
        String placeName = "Masjid";
        Intent intent = new Intent(this, setLocationMap.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openSchool(View view)
    {

        String placeName = "School";
        Intent intent = new Intent(this, setLocationMap.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openPark(View view)
    {

        String placeName = "Park";
        Intent intent = new Intent(this, setLocationMap.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openHome(View view)
    {

        String placeName = "Home";
        Intent intent = new Intent(this, setLocationMap.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
    public void openOffice(View view)
    {

        String placeName = "Office";
        Intent intent = new Intent(this, setLocationMap.class);
        intent.putExtra("Place_NAME", placeName);
        startActivity(intent);
    }
}
