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
import com.aconst.money4life.model.Tariff;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TariffAdapter extends RealmRecyclerViewAdapter<Tariff,
        TariffAdapter.TariffViewHolder> {
    public TariffAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Tariff> data) {
        super(context, data, true);
        setHasStableIds(true);
    }

    @Override
    public TariffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tariff_listitem, parent, false);
        return new TariffViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TariffViewHolder holder, int position) {
        final Tariff tariff = getItem(position);
        holder.mTariff = tariff;
        holder.date.setText(MyApplication.getDateAsString(tariff.getDate()));
        holder.counter.setText(tariff.getCounter().getShortName());
        holder.tariff.setText(tariff.getTariffStr());
        holder.price.setText(String.valueOf(tariff.getPrice()));
    }

    class TariffViewHolder extends RecyclerView.ViewHolder {
        public Tariff mTariff;
        TextView date;
        TextView counter;
        TextView tariff;
        TextView price;

        public TariffViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.tariff_date);
            counter = view.findViewById(R.id.tariff_counter);
            tariff = view.findViewById(R.id.tariff_name);
            price = view.findViewById(R.id.tariff_price);
        }
    }
}
