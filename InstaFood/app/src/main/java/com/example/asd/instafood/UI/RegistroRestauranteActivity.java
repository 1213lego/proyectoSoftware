package com.example.asd.instafood.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RegistroRestauranteViewModel;
import com.example.asd.instafood.db.models.TipoComida;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegistroRestauranteActivity extends AppCompatActivity {

    // Constantes
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private Spinner spinner;
    private ArrayList<String> comidas;
    private Button btnExaminar;
    private ImageView imgRestaurante;
    private String mPath;

    //TODO: AGREGAR CONEXIÓN A LA DB.

    private RegistroRestauranteViewModel registroRestauranteViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);
        comidas = new ArrayList();
        registroRestauranteViewModel=ViewModelProviders.of(this).get(RegistroRestauranteViewModel.class);
        spinner = (Spinner) findViewById(R.id.listaRestaurantes);
        comidas.add("Seleccione");
        registroRestauranteViewModel.darLiveDataTiposComida().observe(this, new Observer<List<TipoComida>>()
        {
            @Override
            public void onChanged(@Nullable List<TipoComida> tipoComidas)
            {
                for(int i=0; i<tipoComidas.size(); i++)
                {
                    comidas.add(tipoComidas.get(i).toString());
                }
            }
        });
        //TODO: REALIZAR LA CONSULTA EN LA TABLA TIPOCOMIDAS Y CARGARLA EN EL ARRAYLIST
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item, comidas);
        spinner.setAdapter(adaptador);

        btnExaminar = (Button) findViewById(R.id.btnExaminar);
        imgRestaurante = (ImageView) findViewById(R.id.imgRestaurante);

        btnExaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistroRestauranteActivity.this);
        builder.setTitle("Elige una opción:");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == PHOTO_CODE)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgRestaurante.setImageBitmap(bitmap);
                galleryAddPic();

            }
            else if(requestCode == SELECT_PICTURE)
            {
                Uri imageUri = data.getData();
                imgRestaurante.setImageURI(imageUri);
            }
        }
    }

    private void galleryAddPic()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Long timestamp = System.currentTimeMillis() / 1000;
        String imageName = timestamp.toString() + ".jpg";

        mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                + File.separator + imageName;
        File f = new File(mPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void openMap(View view) {
        if (view.getId() == R.id.btnUbicarRestaurante)
        {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            //TODO: ABRIR LA VISTA DE RESTAURANTE QUE PERMITA AL USUARIO UBICAR UN PUNTERO EN EL MAPA Y RECOGER SU DIRECCION.
        }
    }
    public void openRegistroPlato(View view)
    {
        if(view.getId() == R.id.btnGuardar)
        {
            Intent intent=new Intent(this,RegistroPlatoActivity.class);
            startActivity(intent);
        }
    }





}
