package com.aconst.money4life.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aconst.money4life.R;
import com.aconst.money4life.model.Counter;

import java.util.Collections;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class CountersAdapter extends RealmRecyclerViewAdapter<Counter,
        CountersAdapter.CountersViewHolder> {
    public CountersAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Counter> data) {
        super(context, data, true);
    }

    @Override
    public CountersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.counter_listitem, parent, false);
        return new CountersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountersViewHolder holder, int position) {

        final Counter counter = getItem(position);
        holder.counter = counter;
        holder.name.setText(counter.getName());
        holder.type.setText(counter.getType());
        holder.number.setText(counter.getNumber());
        holder.shortName.setText(counter.getShortName());
    }

    class CountersViewHolder extends RecyclerView.ViewHolder {
        public Counter counter;
        private TextView name;
        private TextView type;
        private TextView number;
        private TextView shortName;

        public CountersViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.counter_name);
            type = view.findViewById(R.id.counter_type);
            number = view.findViewById(R.id.counter_number);
            shortName = view.findViewById(R.id.counter_shortName);
        }
    }

}
