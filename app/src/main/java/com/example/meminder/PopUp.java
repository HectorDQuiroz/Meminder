package com.example.meminder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class PopUp extends AppCompatActivity {

    private EditText horaET;
    private Spinner opciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);
        opciones = (Spinner) findViewById(R.id.spinner_periodo);
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapater);
        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout((int) (ancho * 0.98), (int) (alto * 0.8));

        horaET = (EditText) findViewById(R.id.edt_hora);
        horaET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PopUp.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String MorP = "";
                        if (selectedHour > 12){
                            MorP = " p. m.";
                        } else {
                            MorP = " a. m.";
                        }
                        if (selectedHour < 10 && selectedMinute < 10) {
                            horaET.setText("0" + selectedHour + ":" + "0" + selectedMinute + MorP);
                        } else if (selectedMinute < 10) {
                            horaET.setText(selectedHour + ":" + "0" + selectedMinute + MorP);
                        } else if (selectedHour < 10) {
                            horaET.setText("0" + selectedHour + ":" + selectedMinute + MorP);
                        } else {
                            horaET.setText(+selectedHour + ":" + selectedMinute + MorP);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_aceptar) {
            finish();
        }
    }

}