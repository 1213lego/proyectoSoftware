package com.example.asd.instafood.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity
        (
                indices = @Index(value ={ "email"},unique = true)
        )
public class Usuario implements  IDto{
    @ColumnInfo(name = "email")
    @NonNull
    @PrimaryKey
    private String email;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido;

    private String contrasenia;
    @NonNull
    private char estado;
    @NonNull
    private char tipoUsuario;

    @Nullable
    @ColumnInfo(name = "imageArray",typeAffinity = ColumnInfo.BLOB)
    private byte [] imageArray;

    public Usuario(@NonNull String email, @NonNull String nombre, @NonNull String apellido,
                   String contrasenia, @NonNull char estado, @NonNull char tipoUsuario, @Nullable byte [] imageArray) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.imageArray = imageArray;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellido() {
        return apellido;
    }

    public void setApellido(@NonNull String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @NonNull
    public char getEstado() {
        return estado;
    }

    public void setEstado(@NonNull char estado) {
        this.estado = estado;
    }

    @NonNull
    public char getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(@NonNull char tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Nullable
    public byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(@Nullable byte[] imageArray) {
        this.imageArray = imageArray;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Email " + email+
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado=" + estado +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
