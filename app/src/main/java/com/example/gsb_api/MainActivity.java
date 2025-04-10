package com.example.gsb_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gsb_api.API.GSBServices;
import com.example.gsb_api.API.RetrofitClientInstance;
import com.example.gsb_api.Model.Praticien;
import com.example.gsb_api.Model.Visiteur;
import com.example.gsb_api.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ActivityMainBinding binding;
    private Visiteur visiteur;
    private Praticien praticien;
    private List<Praticien> praticienList;
    private PraticienAdapter adapter;
    GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);


    /*private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new  ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == 1){
                Intent resultIntent = result.getData();
                if(resultIntent != null){
                    Log.d("Resultat", resultIntent.getSerializableExtra("monSejourRecupere").toString() );

                    //monSejour = (Sejour) resultIntent.getSerializableExtra("monSejourRecupere");
                    //kitVoyage.ajouterOption(monSejour);
                    //binding.textViewResultat.setText(kitVoyage.toString());
                }
            }

        }
    });*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les informations du visiteur
        Intent intent = getIntent();
        Visiteur visiteur = (Visiteur) intent.getSerializableExtra("visiteur");

        if (visiteur != null) {
            this.visiteur = visiteur;
            fetchVisiteurDetails(visiteur);
        } else {
            // Gérer le cas où les informations du visiteur ne sont pas disponibles
            Log.e("MainActivity", "Aucun visiteur trouvé dans l'intent");
        }

        if (praticien != null) {
            Log.d("MainActivity", "Praticien: " + praticien.getNom());
        } else {
            Log.e("MainActivity", "Aucun praticien trouvé");
        }

        praticienList = new ArrayList<>();
        adapter = new PraticienAdapter(praticienList);
        binding.recyclerPraticienList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerPraticienList.setAdapter(adapter);
    }

    private void fetchVisiteurDetails(Visiteur visiteur) {
        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<Visiteur> call = service.getVisiteurById(visiteur.getVisiteurId());

        call.enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Visiteur visiteur = response.body();
                    Log.d("Visiteur", "Nom: " + visiteur.getNom() + ", Prénom: " + visiteur.getPrenom());

                    TextView textView = findViewById(R.id.textView);
                    if (visiteur.getNom() != null && visiteur.getPrenom() != null) {
                        textView.setText("Bonjour " + visiteur.getNom() + " " + visiteur.getPrenom());
                    } else {
                        textView.setText("Bonjour");
                        Log.e("MainActivity", "Nom or Prénom is null");
                    }
                } else {
                    Log.e("API", "Failed to fetch visitor details: " + response.code());
                }
            }


            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                Log.e("API", "Network error: " + t.getMessage());
            }
        });

        private void fetchPraticiens(Praticien praticien) {
            GSBServices service2 = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
            Call<List<Praticien>> call2 = service.getAllPraticiens();

            call2.enqueue(new Callback<List<Praticien>>() {
                @Override
                public void onResponse(Call<List<Praticien>> call2, Response<List<Praticien>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        praticienList.clear();
                        praticienList.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e("MainActivity", "Failed to fetch practitioners: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Praticien>> call, Throwable t) {
                    Log.e("MainActivity", "Network error: " + t.getMessage());
                }
            });



    }
}