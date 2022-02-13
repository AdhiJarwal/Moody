package com.example.moody.ButtonsActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moody.AdaptersPackage.CommonAdapter;
import com.example.moody.MainActivity;
import com.example.moody.RegisterActivity;
import com.example.moody.SelectMoodActivity;
import com.example.moody.TitleDescriptionActivity;
import com.example.moody.TtileDescGetterSetter;
import com.example.moody.databinding.ActivityHappyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HappyActivity extends AppCompatActivity {

    ActivityHappyBinding binding;
    String userNameAndPass;
    FirebaseDatabase database;
    ArrayList<TtileDescGetterSetter>List ;
    CommonAdapter commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHappyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SelectMoodActivity.Happy_remove="Happy";

        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();

        if(!RegisterActivity.usernamePlusPass1.equals("Sakshi"))
        {
            userNameAndPass = RegisterActivity.usernamePlusPass1;
        }

        if(!MainActivity.loginUserId.equals("Adhiraj"))
        {
            userNameAndPass = MainActivity.loginUserId;
        }


        //List.add(new TtileDescGetterSetter("Title","Description"));
        List = new ArrayList<>();

        database.getReference()
                .child("Data")
                .child(userNameAndPass)
                .child(userNameAndPass + "Happy")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        List.clear();
                       for(DataSnapshot ds: snapshot.getChildren())
                       {
                           TtileDescGetterSetter t=ds.getValue(TtileDescGetterSetter.class);
                           List.add(t);
                       }

                       commonAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });

        //this is to scroll the activity up and down
        commonAdapter=new CommonAdapter(List,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewHappy.setLayoutManager(linearLayoutManager);
        // binding.recyclerViewHappy.setAdapter(adapterHappy);
        binding.recyclerViewHappy.setAdapter(commonAdapter);


         binding.add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent i = new Intent(HappyActivity.this, TitleDescriptionActivity.class);
                 i.putExtra("CurrentMood","Happy");
                 startActivity(i);

             }
         });

    }
}