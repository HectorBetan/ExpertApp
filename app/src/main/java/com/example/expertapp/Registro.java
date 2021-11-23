package com.example.expertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registro extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private EditText et1, et2, et3, et4;
    private Usuario usuario;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro);
        et1 = findViewById(R.id.nombre_main);
        et2 = findViewById(R.id.cel_usuario);
        et3 = findViewById(R.id.email_usuario);
        et4 = findViewById(R.id.password_usuario);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
    public void registrar(View view){
        String nom = et1.getText().toString();
        String cel = et2.getText().toString();
        String email = et3.getText().toString();
        String pass = et4.getText().toString();
        if (!nom.equals("") && !cel.equals("") && !email.equals("") && !pass.equals("")) {
            usuario = new Usuario(nom, cel, email, pass);
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child("usuario").push();
            myRef.setValue(usuario);
            Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("token", true);
            editor.putString("user_email", email);
            editor.commit();
            Intent newIntent = new Intent(this, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            newIntent.putExtra("email", usuario.getEmail() );
            startActivity(newIntent);
        } else
            Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
    }
}
