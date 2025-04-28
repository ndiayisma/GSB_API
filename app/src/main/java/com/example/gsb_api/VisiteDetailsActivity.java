package com.example.gsb_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gsb_api.Model.Visite;
import com.example.gsb_api.Model.Praticien;
import com.example.gsb_api.Model.Visiteur;
import com.example.gsb_api.R;
import com.example.gsb_api.API.GSBServices;

import com.example.gsb_api.databinding.ActivityMainBinding;
import com.example.gsb_api.databinding.ActivityVisiteDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class VisiteDetailsActivity extends AppCompatActivity {

    private ActivityVisiteDetailsBinding binding;
    private TextView dateText, motifText, commentaireText, textVisiteur, textPraticien;
    private Button btnModifier, btnSupprimer;
    private Visite visite;
    private List<Visite> visite_list;
    private String token;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visite_details);

        dateText = findViewById(R.id.dateText);
        motifText = findViewById(R.id.motifText);
        commentaireText = findViewById(R.id.commentaireText);
        textVisiteur = findViewById(R.id.text_visiteur);
        textPraticien = findViewById(R.id.text_praticien);
        //btnModifier = findViewById(R.id.btn_modifier);
        //btnSupprimer = findViewById(R.id.btn_supprimer);

        Intent intent = getIntent();
        visite = (Visite) getIntent().getSerializableExtra("visite");

        if (visite != null) {
            this.visite = visite;
            dateText.setText(visite.getDateVisite().toString());
            motifText.setText(visite.getMotif());
            commentaireText.setText(visite.getCommentaire());
            textVisiteur.setText(visite.getVisiteur());
            textPraticien.setText(visite.getPraticien());

            if (visite.getPraticien() != null) {
                textPraticien.setText(visite.getPraticien());
            } else {
                textPraticien.setText("Praticien non disponible");
            }

            textVisiteur.setText(visite.getVisiteur());
        } else {
            Log.e("VisiteDetailsActivity", "Aucune visite trouvée dans l'intent");
        }


            // Logique pour afficher les détails de la visite




        //btnModifier.setOnClickListener(v -> {
        //    Intent intent = new Intent(VisiteDetailsActivity.this, ModifierVisiteActivity.class);
        //    intent.putExtra("visite", visite);
        //    startActivity(intent);
        //});
        //btnSupprimer.setOnClickListener(v -> {
        //    Intent intent = new Intent(VisiteDetailsActivity.this, SupprimerVisiteActivity.class);
        //    intent.putExtra("visite", visite);
        //    startActivity(intent);
        //});

    }



}