package com.example.asd.instafood.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asd.instafood.R;


public class RegistroPlatoActivity extends AppCompatActivity {

    // Constantes
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private Button btnExaminar;
    private ImageView imgPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_plato);

        btnExaminar = (Button) findViewById(R.id.btnExaminar);
        imgPlato = (ImageView) findViewById(R.id.imgRestaurante);

        btnExaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistroPlatoActivity.this);
        builder.setTitle("Elige una opción:");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (option[which] == "Tomar foto") {
                    openCamera();
                } else if (option[which] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                } else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_CODE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgPlato.setImageBitmap(bitmap);

            } else if (requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                imgPlato.setImageURI(imageUri);
            }
        }
    }

    public void openRegistroPlato(View view)
    {
        if(view.getId() == R.id.btnAgregarPlato)
        {
            //todo agregar el plato a la db

            Intent intent=new Intent(this,RegistroPlatoActivity.class);
            startActivity(intent);
        }
    }
    public void openAnuncianteActivity(View view)
    {
        if(view.getId() == R.id.btnFinalizar)
        {
            //todo agregar el plato a la db
            Intent intent=new Intent(this,AnuncianteActivity.class);
            startActivity(intent);
        }
    }


}
