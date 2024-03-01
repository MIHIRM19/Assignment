package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private TextView login;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;
    private DatabaseReference databaseReference;
    private EditText name, dob, role, email, pass;
    private Button signup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        role = findViewById(R.id.role);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();



        login = findViewById(R.id.login_acc);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = name.getText().toString();
                String txtdob = dob.getText().toString();
                String txtrole = role.getText().toString();
                String txtemail = email.getText().toString();
                String txtpass = pass.getText().toString();

                if (TextUtils.isEmpty(txtname) || TextUtils.isEmpty(txtdob)
                        || TextUtils.isEmpty(txtrole) || TextUtils.isEmpty(txtemail) || TextUtils.isEmpty(txtpass)){
                    Toast.makeText(SignUp.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtpass.length() < 6){
                    Toast.makeText(SignUp.this, "Password to short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtname, txtdob, txtrole, txtemail, txtpass);
                }
            }
        });
    }

    private void registerUser(String name, String dob, String role, String email, String pass) {
        pd.setMessage("Please Wait!");
        pd.show();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("dob", dob);
                map.put("role", role);
                map.put("email", email);
                map.put("pass", pass);
                map.put("id", mAuth.getCurrentUser().getUid());
                databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Created Employee Id Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(SignUp.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SignUp.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finish();
        }
    }
}