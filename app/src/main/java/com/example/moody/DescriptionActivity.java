package com.example.moody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.moody.AdaptersPackage.CommonAdapter;
import com.example.moody.databinding.ActivityDescriptionBinding;
import com.example.moody.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DescriptionActivity extends AppCompatActivity
{
    ActivityDescriptionBinding binding;
    static String userNameAndPass;
    DatabaseReference databasereference;
    TtileDescGetterSetter td;
    static String mood_update;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        /*-----------------------------------------------------------------------------------------*/
        String TITLE=getIntent().getExtras().getString("Title");
        String DESCRIPTION=getIntent().getExtras().getString("Description");
        binding.title.setText(TITLE);
        binding.description.setText(DESCRIPTION);
        /*-----------------------------------------------------------------------------------------*/
        if(!RegisterActivity.usernamePlusPass1.equals("Sakshi"))
        {
            userNameAndPass = RegisterActivity.usernamePlusPass1;
        }

        if(!MainActivity.loginUserId.equals("Adhiraj"))
        {
            userNameAndPass = MainActivity.loginUserId;
        }
        /*-----------------------------------------------------------------------------------------*/
        if(SelectMoodActivity.Happy_remove.equals("Happy"))
        {
            mood_update=SelectMoodActivity.Happy_remove;
        }
        if(SelectMoodActivity.Sad_remove.equals("Sad"))
        {
            mood_update=SelectMoodActivity.Sad_remove;
        }
        if(SelectMoodActivity.Angry_remove.equals("Angry"))
        {
            mood_update=SelectMoodActivity.Angry_remove;
        }
        if(SelectMoodActivity.Other_remove.equals("Other"))
        {
            mood_update=SelectMoodActivity.Other_remove;
        }
        /*-----------------------------------------------------------------------------------------*/
        databasereference = FirebaseDatabase.getInstance().getReference().child("Data");
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(DescriptionActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                String title = binding.title.getText().toString();
                String des = binding.description.getText().toString();
                HashMap updated=new HashMap();
                updated.put("title",title);
                updated.put("description",des);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("Data").child(userNameAndPass).child(userNameAndPass+mood_update).orderByChild("description").equalTo(CommonAdapter.key);
                applesQuery.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        for (DataSnapshot appleSnapshot: snapshot.getChildren())
                        {
                            appleSnapshot.getRef().updateChildren(updated);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });



            }
        });


    }
}