package com.example.asd.instafood.UI;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RegistroRestauranteViewModel;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.TipoComida;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegistroRestauranteActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_UBICATION= 1;
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
    private String email;
    private int id;
    private EditText txtNombre;
    private EditText txtTelContacto;
    private EditText txtDescripcion;
    private EditText txtDireccion;
    //TODO: AGREGAR CONEXIÓN A LA DB.

    private RegistroRestauranteViewModel registroRestauranteViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);
        email = getIntent().getStringExtra("Email");
        id = getIntent().getIntExtra("Id",-1);
        comidas = new ArrayList();
        registroRestauranteViewModel = ViewModelProviders.of(this).get(RegistroRestauranteViewModel.class);
        spinner = (Spinner) findViewById(R.id.listaRestaurantes);
        comidas.add("Seleccione");
        registroRestauranteViewModel.darLiveDataTiposComida().observe(this, new Observer<List<TipoComida>>() {
            @Override
            public void onChanged(@Nullable List<TipoComida> tipoComidas) {
                for (int i = 0; i < tipoComidas.size(); i++) {
                    comidas.add(tipoComidas.get(i).toString());
                }
            }
        });
        txtNombre = findViewById(R.id.txtNombre);
        txtTelContacto = findViewById(R.id.txtTelContacto);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtDireccion=findViewById(R.id.txtDireccion);

        //TODO: REALIZAR LA CONSULTA EN LA TABLA TIPOCOMIDAS Y CARGARLA EN EL ARRAYLIST
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, comidas);
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
                imgRestaurante.setImageBitmap(bitmap);
                galleryAddPic();

            } else if (requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                imgRestaurante.setImageURI(imageUri);
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

    public void openMap(View view) {
        if (view.getId() == R.id.btnUbicarRestaurante) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            //TODO: ABRIR LA VISTA DE RESTAURANTE QUE PERMITA AL USUARIO UBICAR UN PUNTERO EN EL MAPA Y RECOGER SU DIRECCION.
        }
    }

    public void GuardarRes(View view) {
        if (view.getId() == R.id.btnGuardar) {
            if (validar()) {
                Bitmap bitmap = ((BitmapDrawable) imgRestaurante.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();
                LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_UBICATION);
                    Toast.makeText(this, "No proporcionaste los permisos de ubicacion", Toast.LENGTH_SHORT).show();
                    return;
                }
                Location location = locationManager.getLastKnownLocation(locationManager
                        .getBestProvider(criteria, false));
                double latitude = location.getLatitude();
                double longitud = location.getLongitude();
                Restaurante restaurante= new Restaurante(id,spinner.getSelectedItemPosition(),txtNombre.getText().toString(),
                        txtTelContacto.getText().toString(),txtDescripcion.getText().toString(),latitude,longitud,txtDireccion.getText().toString(),imageInByte);
                registroRestauranteViewModel.ingresar(restaurante);
                Intent intent= new Intent();
                setResult(RESULT_OK,intent);
            }
            else
            {
                Intent intent=new Intent();
                setResult(RESULT_CANCELED,intent);
            }
        }
    }

    private boolean validar()
    {
        if(TextUtils.isEmpty(txtDescripcion.getText())|| TextUtils.isEmpty(txtNombre.getText()) ||
                TextUtils.isEmpty(txtTelContacto.getText())|| TextUtils.isEmpty(txtDireccion.getText()))
        {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(spinner.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "No seleccionaste el tipo de comida", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean permisosUbicacion()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }
        else
        {
            return true;
        }
    }


}
