package com.aconst.money4life;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.aconst.money4life.adapters.CurrencyAdapter;
import com.aconst.money4life.model.Currency;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class CurrencyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Realm realm;
    private CurrencyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.currency_list);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adapter = new CurrencyAdapter(this, realm.where(Currency.class).findAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        realm.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
