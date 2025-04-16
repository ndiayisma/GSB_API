package com.example.gsb_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_api.Model.Praticien;

import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.PraticienViewHolder> {
    // This class is used to adapt the Praticien data for display in a RecyclerView or similar component.
    // It would typically extend RecyclerView.Adapter and implement necessary methods like onCreateViewHolder, onBindViewHolder, etc.
    // For now, it's just a placeholder.

    private List<Praticien> praticiens;

    public PraticienAdapter(List<Praticien> praticiens) {
        this.praticiens = praticiens;
    }

    @NonNull
    @Override
    public PraticienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.praticien_list, parent, false);
        return new PraticienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PraticienViewHolder holder, int position) {
        Praticien praticien = praticiens.get(position);
        holder.nom.setText(praticien.getNom());
        holder.bind(praticien);
    }

    @Override
    public int getItemCount() {
        return praticiens.size();
    }

    public static class PraticienViewHolder extends RecyclerView.ViewHolder {
        TextView nom;

        public PraticienViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.Praticien);
        }

        public void bind(Praticien praticien) {
            // Bind the data from the Praticien object to the views here
        }
    }

}
