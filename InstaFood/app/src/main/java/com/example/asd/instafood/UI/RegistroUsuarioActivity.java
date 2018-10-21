package com.example.asd.instafood.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asd.instafood.R;

public class RegistroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }

    public void createUser(View view)
    {
        if(view.getId()==R.id.btnRegistar)
        {

        }
    }
}
