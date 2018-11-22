package com.example.asd.instafood.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RegistroPlatoViewModel;
import com.example.asd.instafood.db.models.Plato;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class RegistroPlatoActivity extends AppCompatActivity {

    // Constantes
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private String mPath;
    private Button btnExaminar;
    private ImageView imgPlato;
    private int idRestaurante;
    private EditText nombrePlato;
    private EditText descripcionPlato;
    private RegistroPlatoViewModel viewModel;
    private Button registrarPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_plato);
        viewModel=ViewModelProviders.of(this).get(RegistroPlatoViewModel.class);
        btnExaminar = (Button) findViewById(R.id.btnExaminar);
        imgPlato = (ImageView) findViewById(R.id.   imagenPlatoRegistro);
        idRestaurante=getIntent().getIntExtra("Id",-1);
        nombrePlato=findViewById(R.id.txtNombrePlato);
        descripcionPlato=findViewById(R.id.txtDescripcionPlato);
        registrarPlato=findViewById(R.id.btnFinalizar);
        registrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo agregar el plato a la db
                if(TextUtils.isEmpty(descripcionPlato.getText())|| TextUtils.isEmpty(nombrePlato.getText()))
                {
                    Toast.makeText(RegistroPlatoActivity.this, "No has llenado los campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Bitmap bitmap = ((BitmapDrawable) imgPlato.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();
                    Plato plato= new Plato(idRestaurante,nombrePlato.getText().toString(),descripcionPlato.getText().toString(),imageInByte);
                    viewModel.ingresar(plato);
                    Intent intent= new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
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
                galleryAddPic();

            } else if (requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                imgPlato.setImageURI(imageUri);
            }
        }
    }

    private void galleryAddPic() {
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
}
