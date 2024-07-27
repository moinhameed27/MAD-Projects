package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class Signup extends AppCompatActivity {

    TextView signin;
    EditText email,password;
    Button signup;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signin = findViewById(R.id.signin0);
        email = findViewById(R.id.supEmail);
        password = findViewById(R.id.supPass);
        signup = findViewById(R.id.supbtn);
        progressBar = findViewById(R.id.ProgressBar);
        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String emails,passwords;
                emails = String.valueOf(email.getText().toString());
                passwords = String.valueOf(password.getText().toString());

                if (TextUtils.isEmpty(emails)){
                    Toast.makeText(Signup.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwords)){
                    Toast.makeText(Signup.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emails, passwords)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}