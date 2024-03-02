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

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public DetailAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        com.example.assignment.R.layout.employee_detail, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Detail details = (Detail) listRecyclerItem.get(i);

                itemViewHolder.emp_no.setText(details.getEmp_no());
                itemViewHolder.emp_id.setText(details.getEmp_id());
                itemViewHolder.emp_name.setText(details.getEmp_name());
                itemViewHolder.emp_dob.setText(details.getEmp_dob());
                itemViewHolder.emp_role.setText(details.getEmp_role());
        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView emp_no;
        private TextView emp_id;
        private TextView emp_name;
        private TextView emp_dob;
        private TextView emp_role;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_no = (TextView) itemView.findViewById(com.example.assignment.R.id.emp_no);
            emp_id = (TextView) itemView.findViewById(com.example.assignment.R.id.emp_id);
            emp_name = (TextView) itemView.findViewById(com.example.assignment.R.id.emp_name);
            emp_dob = (TextView) itemView.findViewById(com.example.assignment.R.id.emp_dob);
            emp_role = (TextView) itemView.findViewById(com.example.assignment.R.id.emp_role);
        }
    }

}
