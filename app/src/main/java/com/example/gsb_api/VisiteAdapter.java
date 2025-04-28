package com.example.gsb_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_api.Model.Visite;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VisiteAdapter extends RecyclerView.Adapter<VisiteAdapter.VisiteViewHolder> {

    private List<Visite> visites;

    public VisiteAdapter(List<Visite> visites) {
        this.visites = visites;
    }

    @NonNull
    @Override
    public VisiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visite_list, parent, false);
        return new VisiteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisiteViewHolder holder, int position) {
        Visite visite = visites.get(position);

        // Formatage propre de la date
        String formattedDate = "";
        if (visite.getDateVisite() != null) {
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formattedDate = outputFormat.format(visite.getDateVisite());
        }
        holder.dateText.setText(formattedDate);

        holder.dateText.setText(formattedDate);

        // Gestion du motif
        if (visite.getMotif() != null) {
            holder.motifText.setText("Motif : " + visite.getMotif());
        } else {
            holder.motifText.setText("Motif : -");
        }

        holder.commentaireText.setText(visite.getCommentaire());

        // Gérer le clic sur une visite
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VisiteDetailsActivity.class);
            intent.putExtra("visite", visite);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return visites.size();
    }

    public static class VisiteViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, motifText, commentaireText;

        public VisiteViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            motifText = itemView.findViewById(R.id.motifText);
            commentaireText = itemView.findViewById(R.id.commentaireText);
        }
    }
}
