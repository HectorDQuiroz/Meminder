package com.example.meminder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Main extends RecyclerView.Adapter<Adapter_Main.Itemviewdapter>{
    List<Person> item;
    public Adapter_Main(List<Person> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public Itemviewdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Itemviewdapter holder = new Itemviewdapter(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Itemviewdapter holder, int position) {
        Person p = item.get(position);
        holder.name_Sub_Card.setText(p.getNameS());
        holder.name_Plan_Card.setText(p.getNameP());
//        holder.imd_Dollar_Card.setImageResource(p.getImd_Dollar());
        holder.cantidad_Card.setText(p.getCantidad());
        holder.cv.setBackgroundColor(Color.parseColor(p.getColor()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(holder.itemView.getContext(), detalles_Card.class);
                add.putExtra("nameS", p);
                holder.itemView.getContext().startActivity(add);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public static class Itemviewdapter extends RecyclerView.ViewHolder {
        CardView cv;
        String nS;
        TextView name_Sub_Card;
        TextView name_Plan_Card;
//        ImageView imd_Dollar_Card;
        TextView cantidad_Card;
        public Itemviewdapter(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_Sub);
            name_Sub_Card = (TextView) itemView.findViewById(R.id.txt_name_Card);
            name_Plan_Card = (TextView) itemView.findViewById(R.id.txt_plan_Card);
//            imd_Dollar_Card = (ImageView) itemView.findViewById(R.id.imgv_IconDollar);
            cantidad_Card = (TextView) itemView.findViewById(R.id.txt_cantidad_Card);
        }
    }

}
