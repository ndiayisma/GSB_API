package com.example.gsb_api;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praticiens_details);

        // Récupérer le praticien depuis l'Intent
        Praticien praticien = (Praticien) getIntent().getSerializableExtra("praticien");

        if (praticien != null) {
            // Associer les données du praticien aux TextViews
            TextView nomTextView = findViewById(R.id.praticien_nom);
            TextView adresseTextView = findViewById(R.id.praticien_adresse);
            TextView telTextView = findViewById(R.id.praticien_tel);
            TextView visitesTextView = findViewById(R.id.praticien_visites);

            nomTextView.setText(praticien.getNom() + " " + praticien.getPrenom());
            adresseTextView.setText(praticien.getCodePostal() + " " + praticien.getVille() + " " + praticien.getRue());
            telTextView.setText(praticien.getTel());
            visitesTextView.setText("Nombre de visites : " + praticien.getVisites().size());

            // Initialiser le RecyclerView
            recyclerView = findViewById(R.id.recycler_visites);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            // Récupérer les visites du praticien
            fetchVisites(praticien.get_id());
        }

        visites = new ArrayList<>();
        adapter = new VisiteAdapter(visites);
        recyclerView.setAdapter(adapter);

    }

    private void fetchVisites(String praticienId) {
            GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<List<Visite>> call = service.getVisitesByPraticien(praticienId);

        call.enqueue(new Callback<List<Visite>>() {
            @Override
            public void onResponse(Call<List<Visite>> call2, Response<List<Visite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visites.clear();
                    visites.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.d("Praticiens", "Liste des praticiens récupérée avec succès");
                } else {
                    Log.e("MainActivity", "Échec de la récupération des praticiens : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Visite>> call, Throwable t) {
                Log.e("MainActivity", "Network error: " + t.getMessage());
            }
            });

    }
}
