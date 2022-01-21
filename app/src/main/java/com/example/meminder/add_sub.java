package com.example.meminder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class add_sub extends AppCompatActivity {

    private EditText cantidadTxt;
    private EditText nombreTxt;
    private EditText descripcionTxt;
    private EditText diasPagos;
    private EditText menuOp;
    private EditText choose_color;
    private EditText pagoTxt;
    private EditText tipo_pago;
    private Spinner opciones;
    private EditText fechaTxt;
    //Selector de fecha
    Calendar C = Calendar.getInstance();
    static final int DATE_ID = 0;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        getSupportActionBar().hide();
        String titulo = getString(R.string.titulo5);
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Declaracion variables
        cantidadTxt = (EditText) findViewById(R.id.cantidad_Add);
        nombreTxt = (EditText) findViewById(R.id.txt_Nombre);
        descripcionTxt = (EditText) findViewById(R.id.txt_Description);
        diasPagos = (EditText) findViewById(R.id.txt_dias);
        //

        tipo_pago = (EditText) findViewById(R.id.txt_tipo_pago);
        fechaTxt = (EditText) findViewById(R.id.txt_primer_Pago);
        choose_color = (EditText) findViewById(R.id.txt_choose_color);
        cantidadTxt.setPaintFlags(cantidadTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //Metodos
        spinner_Seleccion();
        selector_Action();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent add = new Intent(this, activity_panprincipal.class);
        startActivity(add);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
    }

    public void onCLick(View view) {
        if (view.getId() == R.id.btn_guardar) {
            validar_Datos();
        }
    }


    //Guardar los datos
    public void validar_Datos() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users/" + uid);
            String cantidad = cantidadTxt.getText().toString();
            cantidad = cantidad + ".00";
            String nameS = nombreTxt.getText().toString();
            String nameP = descripcionTxt.getText().toString();
            String dia = diasPagos.getText().toString();
            String periodo = (String) opciones.getSelectedItem().toString();
            String primerPago = fechaTxt.getText().toString();
            String color = choose_color.getText().toString();
            String tipoPago = tipo_pago.getText().toString();
            String proximoP = fechaTxt.getText().toString();
            datos_Add add = new datos_Add(cantidad, nameS, nameP, dia, periodo, primerPago, color, tipoPago, proximoP);

            myRef.child("item" + nameS).setValue(add);
        }
        finish();
    }

    //Declaracion de spinner
    public void spinner_Seleccion() {
        opciones = (Spinner) findViewById(R.id.spinner_periodo);
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapater);
    }
    //Comienza metodo para mostrar selector de fecha

    public void selector_Action() {
        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        fechaTxt = (EditText) findViewById(R.id.txt_primer_Pago);

        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void colocar_fecha() {
        fechaTxt.setText((mMonthIni + 1) + "/" + mDayIni + "/" + mYearIni + " ");
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }
        return null;
    }

    //termina metodo de seleccion de fecha
}
