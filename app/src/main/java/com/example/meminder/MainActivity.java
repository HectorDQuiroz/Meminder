package com.example.meminder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText txt_user;
    private EditText txt_pass;
    private TextInputLayout textInputLayout;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_pass = (EditText) findViewById(R.id.txt_pass);

        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

    }

    public void onCLick(View view) {
        if (view.getId() == R.id.btn_guardar) {
            validar();
//            iniciar();
        } else if (view.getId() == R.id.btn_registrarse) {
            registrar();
        }
    }

    public void validar() {
        String usuarios = txt_user.getText().toString().trim();
        String contrasena = txt_pass.getText().toString().trim();
        if (TextUtils.isEmpty(usuarios)) {
            txt_user.setError(getString(R.string.verficicacion));
            txt_user.requestFocus();
        } else if (TextUtils.isEmpty(contrasena)) {
            txt_pass.setError(getString(R.string.verficicacion));
            txt_pass.requestFocus();
        } else {
            iniciar();
        }
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    public void iniciar() {
        SharedPreferences preferences = getSharedPreferences(("credenciales"), Context.MODE_PRIVATE);

        String user = preferences.getString("user", "NO existe la info");
        String pass = preferences.getString("pass", "NO existe la info");

        String usuarios = txt_user.getText().toString().trim();
        String contrasena = txt_pass.getText().toString().trim();

//        Variables strings
        String mensaje_B = getString(R.string.mensaje_bievenida);
        String usuario_D = getString(R.string.usuario_Desconocido);
        mAuth.signInWithEmailAndPassword(usuarios, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            principal();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    public void registrar() {
        Intent registro = new Intent(this, sesion_Iniciada.class);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        startActivity(registro);
    }

    public void principal() {
        Intent home = new Intent(this, activity_panprincipal.class);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        startActivity(home);
    }

}