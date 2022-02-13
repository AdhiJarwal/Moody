package com.example.moody;

//import static com.example.moody.check.flag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.moody.ButtonsActivity.HappyActivity;
import com.example.moody.databinding.ActivityRegisterBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseReference databasereference, databaseReference2;
    public String usernamePlusPass;
    public static String usernamePlusPass1 = "Sakshi";
    FirebaseDatabase database;
    boolean CHECK=false;

    static String regUser="";
    static String pass1="";
    static String pass2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databasereference = FirebaseDatabase.getInstance().getReference().child("Users");



        //to hide the actionbar
        getSupportActionBar().hide();


        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                regUser = binding.usernameRegister.getText().toString();
                pass1 = binding.passwordRegister.getText().toString();
                pass2 = binding.confirmPasswordRegister.getText().toString();

                if(regUser.length()<10)
                {
                    binding.usernameRegister.setError("Username must contain atleast 10 characters!");
                }
                if(pass1.length()<6)
                {
                    binding.passwordRegister.setError("Password must contain atleast 6 characters!");
                }
                if(!pass1.equals(pass2))
                {
                    binding.confirmPasswordRegister.setError("Password does not match!");
                }
                if(regUser.equals(pass1))
                {
                    binding.passwordRegister.setError("Username and password are same,enter another password!");
                }
                if(pass1.equals("123456")||pass1.equals("654321")||pass1.equals("Pass@123"))
                {
                    binding.passwordRegister.setError("Password is too weak or common to use!");
                }
                if(check.flag_check_special_symbol)
                {
                    binding.passwordRegister.setError("Special symbols are not allowed!");
                }


                usernamePlusPass = regUser + pass1;

                if(pass1.equals(pass2) && check.is_Valid_Password())
                {
                    usernamePlusPass1=usernamePlusPass;

                    // this will insert registration info(username and password) into firebase
                    regInfo obj = new regInfo(regUser, pass1);
                    databasereference.child(usernamePlusPass).push().setValue(obj);
                    //------------------------------------------------------------------
                    Intent intent = new Intent(RegisterActivity.this, IntroductionPageActivity.class);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                }


            }
        });

    }
}