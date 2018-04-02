package com.aconst.money4life.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aconst.money4life.MyApplication;
import com.aconst.money4life.R;
import com.aconst.money4life.model.Accounts;
import com.aconst.money4life.model.Currency;

import java.util.Collections;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class AccountsAdapter extends RealmRecyclerViewAdapter<Accounts,
        AccountsAdapter.AccountsViewHolder> {
    public AccountsAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Accounts> data) {
        super(context, data, true);
        setHasStableIds(true);
    }

    @Override
    public AccountsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_listitem, parent, false);
        return new AccountsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountsViewHolder holder, int position) {
        final Accounts account = getItem(position);
        holder.account = account;
        holder.name.setText(account.getName());
        holder.currency.setText(account.getCurrency().getName());
        holder.cache.setChecked(account.isCache());
        holder.comment.setText(account.getComment());
    }

    class AccountsViewHolder extends RecyclerView.ViewHolder {
        public Accounts account;
        private TextView name;
        private TextView currency;
        private CheckBox cache;
        private TextView comment;

        public AccountsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.account_name);
            currency = view.findViewById(R.id.account_currency);
            comment = view.findViewById(R.id.account_comment);
            cache = view.findViewById(R.id.account_cache);
        }
    }

}
