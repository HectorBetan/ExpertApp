package com.example.expertapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {
    private DatabaseReference myRef;
    Usuario user;
    String nombre_usuario, cel_usuario, email_usuario;
    TextView tv1, tv2, tv3;
    private SharedPreferences settings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        String user_email = settings.getString("user_email", "");
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("usuario").orderByChild("email").equalTo(user_email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    user = dataSnapshot.getValue(Usuario.class);
                    nombre_usuario = user.getNom();
                    email_usuario = user.getEmail();
                    cel_usuario = user.getCel();
                    tv1 = (TextView) findViewById(R.id.nombre_perfil);
                    tv2 = (TextView) findViewById(R.id.email_perfil);
                    tv3 = (TextView) findViewById(R.id.cel_perfil);
                    tv1.setText(nombre_usuario);
                    tv2.setText("E-mail: " + email_usuario);
                    tv3.setText("Cel: " + cel_usuario);
                }
                if (snapshot.getChildrenCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Error. Usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void onBackPressed(){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id == R.id.mis_servicios) {
            Intent newIntent = new Intent(this, MisServicios.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        else if(id == R.id.editar_perfil){
            Intent newIntent = new Intent(this, EditarPerfil.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        else if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected((menuItem));
    }
    public void goToMisServicios(View view) {
        Intent newIntent = new Intent(this, MisServicios.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
    public void goToEditarPerfil(View view) {
        Intent newIntent = new Intent(this, EditarPerfil.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
}