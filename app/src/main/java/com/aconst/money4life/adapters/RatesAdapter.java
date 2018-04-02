package com.aconst.money4life.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aconst.money4life.MyApplication;
import com.aconst.money4life.R;
import com.aconst.money4life.model.Rates;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RatesAdapter extends RealmRecyclerViewAdapter<Rates,
        RatesAdapter.RatesViewHolder> {
    public RatesAdapter(@NonNull Context context,
                        @Nullable OrderedRealmCollection<Rates> data) {
        super(context, data, true);
        setHasStableIds(true);
    }

    @Override
    public RatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rate_listitem, parent, false);
        return new RatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RatesAdapter.RatesViewHolder holder, int position) {
        final Rates rate = getItem(position);
        holder.rate = rate;
        holder.date.setText(MyApplication.getDateAsString(rate.getDate()));
        holder.currency.setText(rate.getCurrency().getName());
        holder.value.setText(String.valueOf(rate.getValue()));
    }

    class RatesViewHolder extends RecyclerView.ViewHolder {
        public Rates rate;
        TextView date;
        TextView currency;
        TextView value;

        public RatesViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.rate_date);
            currency = view.findViewById(R.id.rate_currency);
            value = view.findViewById(R.id.rate_value);
        }
    }
}
