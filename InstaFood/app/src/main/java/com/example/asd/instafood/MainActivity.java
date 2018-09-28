package com.example.asd.instafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=(Button) findViewById(R.id.btnMap);
    }

    public void openMap(View view)
    {
        Intent intent=new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
