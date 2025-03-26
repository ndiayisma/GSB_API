package com.example.gsb_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb_api.API.GSBServices;
import com.example.gsb_api.API.RetrofitClientInstance;
import com.example.gsb_api.Model.Visiteur;
import com.example.gsb_api.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private Visiteur visiteur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.mainTextLogin.getText().toString();
                String password = binding.mainTextPassword.getText().toString();
                login(email, password);
            }
        });
    }

    private void login(String email, String password) {
        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Visiteur visiteur = new Visiteur(email, password);
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        Call<Visiteur> call = service.login(credentials);
        call.enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Visiteur visiteur = response.body();
                    // Handle successful login
                    Toast.makeText(LoginActivity.this, "Login succeeded", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    myIntent.putExtra("nom", visiteur.getNom());
                    myIntent.putExtra("prenom", visiteur.getPrenom());
                    startActivity(myIntent);
                } else {
                    // Handle login failure
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                // Handle network failure
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }

        });

        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}