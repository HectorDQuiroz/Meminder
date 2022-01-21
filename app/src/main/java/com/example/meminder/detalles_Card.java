package com.example.meminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class detalles_Card extends AppCompatActivity {

    private EditText txt_fac, txt_proxp, txt_primp, txt_pagt, txt_tipop;
    private TextView icon_notify, icon_delete, nameS, nameP, cantidad;
    private AlertDialog.Builder dialoogBuldier;
    private AlertDialog dialog;
    private CardView cardCV;
    private Button btn_color1, btn_color2, btn_color3, btn_color4, btn_color5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles__card);
        String titulo = getString(R.string.titulo6);
        getSupportActionBar().hide();
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
        variables_declar();//Metodo que declara las variables del xml

    }


    public void onClick(View view) {
        if (view.getId() == R.id.icon_delete) {
            advertencia();
        } else if (view.getId() == R.id.icon_notify) {
            Intent popup = new Intent(this, PopUp.class);
            startActivity(popup);
        }
    }

    List<datos_Add> item;

    public void variables_declar() {
        Person p_nameS = (Person) getIntent().getSerializableExtra("nameS");

        nameS = (TextView) findViewById(R.id.name_Card);
        nameP = (TextView) findViewById(R.id.name_SubCard);
        cantidad = (TextView) findViewById(R.id.cantidad_SubCar);
        cardCV = (CardView) findViewById(R.id.cardCV);
        txt_fac = (EditText) findViewById(R.id.txt_fac);
        txt_proxp = (EditText) findViewById(R.id.txt_proxp);
        txt_primp = (EditText) findViewById(R.id.txt_primp);
        txt_pagt = (EditText) findViewById(R.id.txt_pagt);
        txt_tipop = (EditText) findViewById(R.id.txt_tipop);

        //Buttons
        btn_color1 = (Button) findViewById(R.id.btn_color1);
        btn_color2 = (Button) findViewById(R.id.btn_color2);
        btn_color3 = (Button) findViewById(R.id.btn_color3);
        btn_color4 = (Button) findViewById(R.id.btn_color4);
        btn_color5 = (Button) findViewById(R.id.btn_color5);
        //Textview
        icon_notify = (TextView) findViewById(R.id.icon_notify);
        icon_delete = (TextView) findViewById(R.id.icon_delete);

        String nameS_S = p_nameS.getNameS();
        nameS.setText(p_nameS.getNameS());
        nameP.setText(p_nameS.getNameP());
        cantidad.setText(p_nameS.getCantidad());
        cardCV.setBackgroundColor(Color.parseColor(p_nameS.getColor()));
        btn_color1.setBackgroundColor(Color.parseColor(p_nameS.getColor()));
        btn_color2.setBackgroundColor(Color.parseColor(p_nameS.getColor()));
        btn_color3.setBackgroundColor(Color.parseColor(p_nameS.getColor()));
        btn_color4.setBackgroundColor(Color.parseColor(p_nameS.getColor()));
        btn_color5.setBackgroundColor(Color.parseColor(p_nameS.getColor()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users/" + uid);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot :
                            dataSnapshot.getChildren()) {
                        datos_Add add = snapshot.getValue(datos_Add.class);
                        txt_fac.setText(add.getPeriodo());
                        txt_proxp.setText(add.getProximoP());
                        txt_primp.setText(add.getPrimerPago());
                        txt_pagt.setText(add.getCantidad());
                        txt_tipop.setText(add.getTipoPago());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }


    public void Eliminar() {
        Person p_nameS = (Person) getIntent().getSerializableExtra("nameS");
        String nameSS = p_nameS.getNameS();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users/" + uid);
            myRef.child("item" + nameSS).removeValue();
            Toast.makeText(this, "Se elimino la card: " + nameSS, Toast.LENGTH_SHORT).show();
        }
    }

    public void advertencia() {
        icon_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(detalles_Card.this);
                alerta.setMessage(R.string.alerta_1)
                        .setCancelable(false)
                        .setPositiveButton(R.string.opcion_1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Eliminar();
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.opcion_2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle(R.string.titulo_alerta_1);
                titulo.show();
            }
        });
    }


}