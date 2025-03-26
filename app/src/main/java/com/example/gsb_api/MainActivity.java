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

import com.example.gsb_api.Model.Visiteur;
import com.example.gsb_api.databinding.ActivityMainBinding;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ActivityMainBinding binding;
    private Visiteur visiteur;


    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new  ActivityResultCallback<ActivityResult>() {
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
    });
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les informations du visiteur
        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");

        // Utiliser les informations du visiteur (par exemple, les afficher dans un TextView)
        TextView textView = findViewById(R.id.textView);
        textView.setText("Bonjour " + nom + " " + prenom);
    }
}