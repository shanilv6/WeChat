package com.example.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wechat.databinding.ActivityForgetpasswordBinding;
import com.example.wechat.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpasswordActivity extends AppCompatActivity {
     ActivityForgetpasswordBinding binding;
    FirebaseAuth mAuth;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetpasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mAuth=FirebaseAuth.getInstance();

        binding.backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetpasswordActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        binding.btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });



    }

    private void validateData() {

        email =binding.etEmail.getText().toString().trim();
        if(email.isEmpty()){
            binding.etEmail.setError("Required");

        }else{
            forgetPass();
        }


    }

    private void forgetPass() {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful())  {
                         Toast.makeText(ForgetpasswordActivity.this, "Email Send", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(ForgetpasswordActivity.this,SignInActivity.class));
                         finish();
                     }else{
                         Toast.makeText(ForgetpasswordActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                    }
                });
    }
}