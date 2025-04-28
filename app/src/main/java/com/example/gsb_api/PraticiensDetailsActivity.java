package com.example.gsb_api;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_api.API.GSBServices;
import com.example.gsb_api.API.RetrofitClientInstance;
import com.example.gsb_api.Model.Praticien;
import com.example.gsb_api.Model.Visite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PraticiensDetailsActivity extends AppCompatActivity {

    private VisiteAdapter adapter;
    private RecyclerView recyclerView;
    private List<Visite> visites;
    private TextView emptyView;  // Pour afficher "aucune visite"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praticiens_details);

        // Récupérer le praticien depuis l'Intent
        Praticien praticien = (Praticien) getIntent().getSerializableExtra("praticien");

        // Initialiser les vues
        recyclerView = findViewById(R.id.recycler_visites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        visites = new ArrayList<>();
        adapter = new VisiteAdapter(visites);
        recyclerView.setAdapter(adapter);

        emptyView = findViewById(R.id.empty_view); // Assure-toi d'ajouter ça dans ton XML

        if (praticien != null) {
            // Associer les données du praticien aux TextViews
            TextView nomTextView = findViewById(R.id.praticien_nom);
            TextView adresseTextView = findViewById(R.id.praticien_adresse);
            TextView telTextView = findViewById(R.id.praticien_tel);
            TextView visitesTextView = findViewById(R.id.praticien_visites);

            nomTextView.setText(praticien.getNom() + " " + praticien.getPrenom());
            adresseTextView.setText(praticien.getCodePostal() + " " + praticien.getVille() + " " + praticien.getRue());
            telTextView.setText(praticien.getTel());

            // Récupérer l'ID du praticien
            String praticienId = praticien.get_id() != null ? praticien.get_id() : praticien.getPraticienId();

            if (praticienId != null) {
                Log.d("DEBUG", "Praticien ID utilisé pour les visites : " + praticienId);
                fetchVisites(praticienId, visitesTextView);
            } else {
                Log.e("DEBUG", "ID du praticien est null, impossible de récupérer les visites");
                Toast.makeText(this, "Erreur: ID du praticien introuvable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fetchVisites(String praticienId, TextView visitesTextView) {
        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<List<Visite>> call = service.getVisitesByPraticien(praticienId);

        call.enqueue(new Callback<List<Visite>>() {
            @Override
            public void onResponse(Call<List<Visite>> call, Response<List<Visite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visites.clear();
                    visites.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", "Nombre de visites récupérées : " + visites.size());

                    visitesTextView.setText("Nombre de visites : " + visites.size());

                    if (visites.isEmpty()) {
                        // Aucune visite
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("DEBUG", "Échec de la récupération des visites : " + response.code());
                    Toast.makeText(PraticiensDetailsActivity.this, "Erreur récupération visites", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Visite>> call, Throwable t) {
                Log.e("DEBUG", "Erreur réseau : " + t.getMessage());
                Toast.makeText(PraticiensDetailsActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
