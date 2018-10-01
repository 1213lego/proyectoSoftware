package com.example.asd.instafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asd.instafood.models.DaoMaster;
import com.example.asd.instafood.models.DaoSession;
import com.example.asd.instafood.models.JoinRestaurantesWithRestaurantes;
import com.example.asd.instafood.models.JoinRestaurantesWithRestaurantesDao;
import com.example.asd.instafood.models.Plato;
import com.example.asd.instafood.models.PlatoDao;
import com.example.asd.instafood.models.Restaurantes;
import com.example.asd.instafood.models.RestaurantesDao;
import com.example.asd.instafood.models.Review;
import com.example.asd.instafood.models.ReviewDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;


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
