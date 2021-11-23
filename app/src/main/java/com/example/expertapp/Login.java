package com.example.expertapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    Boolean token = false;
    Usuario user;
    private EditText et1, et2;
    Button b1;
    TextView tregistro;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        b1 = (Button) findViewById(R.id.button2);
        et1 = (EditText) findViewById(R.id.usuario_login);
        et2 = (EditText) findViewById(R.id.password_login);
        tregistro = (TextView) findViewById(R.id.textView3);
        tregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getApplicationContext(), Registro.class);
                startActivity(newIntent);
                finish();
            }
        });

    }
    public void iniciarSesion(View view) {
        String usuario = et1.getText().toString();
        String clave = et2.getText().toString();
        if (!usuario.equals("") && !clave.equals("")) {

            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("usuario").orderByChild("email").equalTo(usuario).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        user = dataSnapshot.getValue(Usuario.class);
                        if(usuario.equals(user.getEmail()) &&
                                clave.equals(user.getPass())){
                                token = true;
                        }
                        else {
                            Toast.makeText(Login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                            et1.setText("");
                            et1.requestFocus();
                        }
                    }
                    if (snapshot.getChildrenCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
                        et1.setText("");
                        et1.requestFocus();
                        et2.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(this, "Por favor, ingrese los datos de usuario", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        if (token == true) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("token", true);
            editor.putString("user_email", usuario);
            editor.commit();
            Intent newIntent = new Intent(this, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        else{
            Toast.makeText(this, "El usuario no se pudo validar", Toast.LENGTH_SHORT).show();
        }
    }


}