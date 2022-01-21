package com.example.meminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class cambiarcontrasena extends AppCompatActivity {

    private EditText edt_mail;
    private Button guardarnewpass, button_back;
    private boolean flag = false;
    String email = "";
    private ProgressDialog mDialog;

    private FirebaseAuth mAuth;
    private String alerta1, alerta2, alerta3, alerta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiarcontrasena);
        getSupportActionBar().hide();
        String titulo = getString(R.string.titulo4);
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);


        init();
        OnClick();


    }

    public void init() {
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        edt_mail = (EditText) findViewById(R.id.txt_oldpass);
        guardarnewpass = (Button) findViewById(R.id.guardarnewpass);
        button_back = (Button) findViewById(R.id.button_back);
        alerta1 = getString(R.string.alerta1);
        alerta2 = getString(R.string.alerta2);
        alerta3 = getString(R.string.alerta3);
        alerta4 = getString(R.string.alerta4);
    }

    public void OnClick() {
        guardarnewpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_mail.getText().toString().trim();
                if (!email.isEmpty()) {
                    mDialog.setMessage(alerta1);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();
                } else {
                    Toast.makeText(cambiarcontrasena.this, alerta2, Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
    }

    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(cambiarcontrasena.this, alerta3, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(cambiarcontrasena.this, alerta4, Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });
    }

    public void regresar() {
        finish();
    }
}