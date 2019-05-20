package com.example.aser.blinds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Guardian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);

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
}
