package com.example.assignment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.adapter.DetailAdapter;
import com.example.assignment.models.Detail;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserFragment extends Fragment {

    List<Detail> detailList;
    DetailAdapter detailAdapter;
    FirebaseFirestore db;
    ProgressBar progressBar;
    RecyclerView Employee;
    EditText searchBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        Employee = view.findViewById(R.id.Employee);
        searchBar = view.findViewById(R.id.searchBar);
        progressBar = view.findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        progressBar.setVisibility(View.VISIBLE);

        detailList = new ArrayList<>();
        detailAdapter = new DetailAdapter(getActivity(), detailList);
        Employee.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        Employee.setAdapter(detailAdapter);

        // Add a TextWatcher to the search bar EditText
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Perform search when text changes
                searchUser(s.toString());
            }
        });

        loadEmployeeList();

        return view;
    }

    private void loadEmployeeList() {
        db.collection("EmployeeList")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        detailList.clear(); // Clear the list before adding new data
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Detail detail = document.toObject(Detail.class);
                            detailList.add(detail);
                        }
                        detailAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getActivity(), "Failed to fetch employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void searchUser(String query) {
        detailList.clear();

        if (TextUtils.isEmpty(query)) {
            loadEmployeeList();
        } else {
            db.collection("EmployeeList")
                    .whereEqualTo("emp_id", query.toLowerCase())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Detail detail = document.toObject(Detail.class);
                                detailList.add(detail);
                            }
                            detailAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Failed to search employees", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


}
