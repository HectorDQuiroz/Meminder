package com.example.meminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class activity_panprincipal extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Person> data;
    private GridLayoutManager glm;
    private BottomNavigationView bnv;
    public Button btn_Fragment;
    private TextView txt_SubMenu;
    private RelativeLayout topmenu;
    //Paginas BottomNav

    private boolean flag = false;
    ;
    FragmentTransaction transaction;
    Fragment home_archF, home_F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panprincipal);

//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().hide();
        String titulo = getString(R.string.titulo);
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
        //Controles de navbar
        bnv = (BottomNavigationView) findViewById(R.id.bottom_Navogation);
        bnv.setBackground(null);
        bnv.getMenu().getItem(1).setEnabled(false);

        topmenu = (RelativeLayout) findViewById(R.id.topmenu);
        showSelectedFragment(new home_Fragment());
        txt_SubMenu = (TextView) findViewById(R.id.sub_menu);

        txt_SubMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity_panprincipal.this);
                alerta.setMessage(R.string.alerta_1)
                        .setCancelable(false)
                        .setPositiveButton(R.string.opcion_1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
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
                return false;
            }
        });

        home_archF = new home_arch();
        home_F = new home_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, home_F).commit();

        cambios_Fragment();
    }

    private void cambios_Fragment() {
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    showSelectedFragment(new home_Fragment());
                    topmenu.setVisibility(View.VISIBLE);
                    String titulo = getString(R.string.titulo);
                    Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
                }
                if (item.getItemId() == R.id.bnv_ajutes) {
                    showSelectedFragment(new ajustes_Fragment());
                    topmenu.setVisibility(View.INVISIBLE);
                    String titulo = getString(R.string.titulo2);
                    Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);

                }
                return true;
            }
        });
    }

    private void showSelectedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }


    public void onClick(View view) {

        transaction = getSupportFragmentManager().beginTransaction();
        if (view.getId() == R.id.floatingActionButton_Agregar) {
            add();
        } else if (view.getId() == R.id.card_Sub) {
//            detalles_Card();
        } else if (flag == false) {
            if (view.getId() == R.id.sub_menu) {
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container, home_archF);
//                transaction.addToBackStack(null);

                flag = true;
            }
        } else if (flag) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container, home_F);
//            transaction.addToBackStack(null);
            flag = false;
        }
        transaction.commit();
    }

    public void add() {
        Intent add = new Intent(this, add_sub.class);
        startActivity(add);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
    }

    public void detalles_Card() {
        Intent add = new Intent(this, detalles_Card.class);
        startActivity(add);
    }
}
