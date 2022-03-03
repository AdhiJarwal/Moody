package com.example.moody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.moody.databinding.ActivityTitleDescriptionBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.moody.RegisterActivity;

public class TitleDescriptionActivity extends AppCompatActivity
{

    ActivityTitleDescriptionBinding binding;
    String userNameAndPass;
    DatabaseReference databasereference;
    TtileDescGetterSetter td;
    public static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTitleDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //to hide the action bar----
        getSupportActionBar().hide();

        databasereference = FirebaseDatabase.getInstance().getReference().child("Data");

        System.out.println("@@@@@@@@@@@@@@" + MainActivity.loginUserId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@" + RegisterActivity.usernamePlusPass1);
        if(!RegisterActivity.usernamePlusPass1.equals("Sakshi"))
        {
            userNameAndPass = RegisterActivity.usernamePlusPass1;
        }

        if(!MainActivity.loginUserId.equals("Adhiraj"))
        {
            userNameAndPass = MainActivity.loginUserId;
        }

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(TitleDescriptionActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                String title = binding.titleWriting.getText().toString();
                String des = binding.descriptionWriting.getText().toString();
                String mood=getIntent().getExtras().getString("CurrentMood");
                if(mood.equals("Happy"))
                {
                    td = new TtileDescGetterSetter(title,des);
                    uid=databasereference.child(userNameAndPass).child(userNameAndPass+"Happy").push().setValue(td).toString();
                }
                if(mood.equals("Sad"))
                {
                    td = new TtileDescGetterSetter(title,des);
                    uid=databasereference.child(userNameAndPass).child(userNameAndPass+"Sad").push().setValue(td).toString();
                }
                if(mood.equals("Angry"))
                {
                    td = new TtileDescGetterSetter(title,des);
                    uid=databasereference.child(userNameAndPass).child(userNameAndPass+"Angry").push().setValue(td).toString();
                }
                if(mood.equals("Other"))
                {
                    td = new TtileDescGetterSetter(title,des);
                    databasereference.child(userNameAndPass).child(userNameAndPass+"Other").push().setValue(td);
                }
            }
        });



    }
}