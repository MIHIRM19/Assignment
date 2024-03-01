package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ForgotActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private FirebaseUser FUser;
    private EditText forgot_email, forgot_pass;
    private Button foremail, forpass;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        forgot_email = findViewById(R.id.forgot_email);
        forgot_pass = findViewById(R.id.forgot_pass);
        foremail = findViewById(R.id.foremail);
        forpass = findViewById(R.id.forpass);
        forgot_pass.setVisibility(View.GONE);
        forpass.setVisibility(View.GONE);


        foremail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = forgot_email.getText().toString();
                if (TextUtils.isEmpty(txtname)){
                    Toast.makeText(ForgotActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                }else {
                    forgot_email.setVisibility(View.GONE);
                    foremail.setVisibility(View.GONE);
                    forgot_pass.setVisibility(View.VISIBLE);
                    forpass.setVisibility(View.VISIBLE);
                }
            }
        });

        FUser = FirebaseAuth.getInstance().getCurrentUser();


        forpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        pd = new ProgressDialog(this);
    }

}