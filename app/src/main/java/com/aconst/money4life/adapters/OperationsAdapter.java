package com.aconst.money4life.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aconst.money4life.R;
import com.aconst.money4life.model.Operations;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class OperationsAdapter extends RealmRecyclerViewAdapter<Operations,
        OperationsAdapter.OperationsViewHolder> {
    public OperationsAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Operations> data) {
        super(context, data, true);
        setHasStableIds(true);
    }

    @Override
    public OperationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.operation_listitem, parent, false);
        return new OperationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OperationsViewHolder holder, int position) {
        final Operations operation = getItem(position);
        holder.operation = operation;
        holder.name.setText(operation.getName());
        holder.group.setText(operation.getGroupId());
        holder.income.setChecked(operation.isIncome());
        holder.comment.setText(operation.getComment());
    }

    class OperationsViewHolder extends RecyclerView.ViewHolder {
        public Operations operation;
        private TextView name;
        private TextView group;
        private CheckBox income;
        private TextView comment;

        public OperationsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.operation_name);
            group = view.findViewById(R.id.operation_group);
            income = view.findViewById(R.id.operation_income);
            comment = view.findViewById(R.id.operation_comment);
        }

    }

}
