package com.aconst.money4life.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aconst.money4life.R;
import com.aconst.money4life.model.Currency;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class CurrencyAdapter extends RealmRecyclerViewAdapter<Currency,
        CurrencyAdapter.CurrencyViewHolder> {
    public CurrencyAdapter(@NonNull Context context,
                           @Nullable OrderedRealmCollection<Currency> data) {
        super(context, data, true);
        setHasStableIds(true);
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_listitem, parent, false);
        return new CurrencyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        final Currency currency = getItem(position);
        holder.currency = currency;
        holder.name.setText(currency.getName());
        holder.shortName.setText(currency.getShortName());
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView shortName;
        public Currency currency;

        public CurrencyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.currName_textView);
            shortName = view.findViewById(R.id.currShortName_textView);
        }
    }

}
