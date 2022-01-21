package com.example.meminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class seguridad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);
        getSupportActionBar().hide();
        String titulo = getString(R.string.titulo3);
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
    }

    public void OnClick(View view){
        if (view.getId() == R.id.button_back) {
            regresar();
        }else if(view.getId() == R.id.button2){
            ir_cambiarpass();
        }
    }

    public void regresar() {
        finish();
    }

    public void ir_cambiarpass(){
        Intent ir_cambiarpasss = new Intent(this, cambiarcontrasena.class);
        startActivity(ir_cambiarpasss);
    }
}