package com.example.assignment.adapter;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.models.Detail;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    Context context;
    List<Detail> detailList;

    public DetailAdapter(Context context, List<Detail> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    public void setData(List<Detail> newData) {
        // Update the dataset with the new data
        this.detailList = newData;
        // Notify the adapter that the data has changed
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(com.example.assignment.R.layout.employee_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        holder.emp_id.setText(detailList.get(position).getEmp_id());
        holder.emp_no.setText(detailList.get(position).getEmp_no());
        holder.emp_name.setText(detailList.get(position).getEmp_name());
        holder.emp_dob.setText(detailList.get(position).getEmp_dob());
        holder.emp_role.setText(detailList.get(position).getEmp_role());

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView emp_id, emp_no, emp_name, emp_dob, emp_role;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_id =itemView.findViewById(com.example.assignment.R.id.emp_id);
            emp_no =itemView.findViewById(com.example.assignment.R.id.emp_no);
            emp_name =itemView.findViewById(com.example.assignment.R.id.emp_name);
            emp_dob =itemView.findViewById(com.example.assignment.R.id.emp_dob);
            emp_role =itemView.findViewById(com.example.assignment.R.id.emp_role);
        }
    }
}
