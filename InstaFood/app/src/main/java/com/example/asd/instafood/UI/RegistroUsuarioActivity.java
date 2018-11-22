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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.LoginActivityViewModel;
import com.example.asd.instafood.ViewModels.RegistroRestauranteViewModel;
import com.example.asd.instafood.ViewModels.RegistroUsuarioViewModel;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Estados;
import com.example.asd.instafood.db.models.TipoUsuario;
import com.example.asd.instafood.db.models.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

public class RegistroUsuarioActivity extends AppCompatActivity {
    // Constantes
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private Button btnExaminar;
    private String mPath;
    private ImageView imgUsuario;
    private EditText txtEmail;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtpasword;
    private RadioButton rbTipoUsuario;
    private RegistroUsuarioViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        viewModel=ViewModelProviders.of(this).get(RegistroUsuarioViewModel.class);
        btnExaminar = (Button) findViewById(R.id.btnExaminar);
        imgUsuario = (ImageView) findViewById(R.id.imgUsuario);
        txtNombre=findViewById(R.id.txtNombre);
        txtApellido=findViewById(R.id.txtApellido);
        txtEmail=findViewById(R.id.txtEmail);
        txtpasword=findViewById(R.id.txtPassword);
        rbTipoUsuario=findViewById(R.id.rbAnunciante);
        btnExaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsuarioActivity.this);
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
    private boolean validar()
    {
        if(TextUtils.isEmpty(txtEmail.getText()) || TextUtils.isEmpty(txtNombre.getText())
                || TextUtils.isEmpty(txtApellido.getText()) || TextUtils.isEmpty(txtApellido.getText()))
        {
            return false;
        }
        return true;
    }
    private void openCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_CODE);
    }
    public void createUser(View view)
    {
        if(view.getId()==R.id.btnRegistar)
        {
            if(validar())
            {
                Bitmap bitmap = ((BitmapDrawable) imgUsuario.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();
                char tipo= rbTipoUsuario.isChecked() ? TipoUsuario.USUARIO_ANUNCIANTE : TipoUsuario.USUARIO_NORMAL;
                Usuario user= new Usuario(txtEmail.getText().toString(),txtNombre.getText().toString(), txtApellido.getText().toString()
                        ,txtpasword.getText().toString(),Estados.ESTADO_ACTIVO,tipo,imageInByte);
                viewModel.ingresar(user);
                if(tipo==TipoUsuario.USUARIO_ANUNCIANTE)
                {
                    Anunciante anunciante= new Anunciante(txtEmail.getText().toString(),5000,new Date());
                    viewModel.ingresar(anunciante);
                }
                Toast.makeText(this, "Se ha registrado satisfatoriamente", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(this,LoginActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Verificar los campos", Toast.LENGTH_SHORT).show();
            }
        }
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
                imgUsuario.setImageBitmap(bitmap);
                galleryAddPic();

            }
            else if(requestCode == SELECT_PICTURE)
            {
                Uri imageUri = data.getData();
                imgUsuario.setImageURI(imageUri);
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
}
