package com.example.moody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.moody.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {

    ActivityAboutUsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.linkdinAdhiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse("https://linkedin.com/in/adjrwl"));
                startActivity(i3);
            }
        });

        binding.linkdinSakshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse("https://linkedin.com/in/sakshi-sawalikar-698452209"));
                startActivity(i3);
            }
        });
    }
}