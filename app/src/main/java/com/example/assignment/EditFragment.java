package com.example.assignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.models.Detail;
import com.example.assignment.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditFragment extends Fragment {

    private FirebaseUser FUser;
    private EditText edit_name, edit_dob, edit_role, edit_pass;
    private Button edit_update;
    private TextView logout;
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        edit_name = view.findViewById(R.id.edit_name);
        edit_dob = view.findViewById(R.id.edit_dob);
        edit_role = view.findViewById(R.id.edit_role);
        edit_pass = view.findViewById(R.id.edit_pass);
        edit_update = view.findViewById(R.id.edit_update);
        logout = view.findViewById(R.id.logout);
        pd = new ProgressDialog(getContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Logging out!");
                pd.show();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Log Out Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        FUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference().child("Users").child(FUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                edit_name.setText(user.getName());
                edit_dob.setText(user.getDob());
                edit_role.setText(user.getRole());
                edit_pass.setText(user.getPass());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        return view;
    }

    private void updateProfile() {
        pd.setMessage("Updating!");
        pd.show();
        HashMap<String, Object> map =new HashMap<>();
        map.put("name",edit_name.getText().toString());
        map.put("dob",edit_dob.getText().toString());
        map.put("pass",edit_pass.getText().toString());
        map.put("role",edit_role.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Users").child(FUser.getUid()).updateChildren(map);
        Toast.makeText(getContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}