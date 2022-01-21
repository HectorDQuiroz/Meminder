package com.example.meminder;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meminder.controller.IComunicaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_arch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_arch extends Fragment {

    private BottomNavigationView bnv;
    private RecyclerView rv;
    private TextView txt_SubMenu;
    private CardView cv;
    IComunicaFragment interfacesComunicaFragments;
    Activity actividad;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_arch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_arch.
     */
    // TODO: Rename and change types and number of parameters
    public static home_arch newInstance(String param1, String param2) {
        home_arch fragment = new home_arch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    Adapter_Main adapter;
    List<Person> item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_home_arch, container, false);

        txt_SubMenu = (TextView) vista.findViewById(R.id.sub_menu);
        rv = (RecyclerView) vista.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        item = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("arch");
        adapter = new Adapter_Main(item);
        rv.setAdapter(adapter);
        //FireBase Implementacion

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                item.removeAll(item);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    Person per = snapshot.getValue(Person.class);
                    item.add(per);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return vista;
    }
}