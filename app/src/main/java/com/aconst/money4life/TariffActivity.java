package com.aconst.money4life;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.aconst.money4life.adapters.TariffAdapter;
import com.aconst.money4life.model.Tariff;

import io.realm.Realm;
import io.realm.Sort;

public class TariffActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tariff);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.tariff_list);
        setUpRecyclerView();

        FloatingActionButton fabTariff = findViewById(R.id.fabTariffs);
        fabTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TariffActivity.this,
                        TariffAddEditActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUpRecyclerView() {
        TariffAdapter adapter = new TariffAdapter(this, realm.where(Tariff.class).findAll()
                .sort("date", Sort.DESCENDING, "counter.name", Sort.ASCENDING));
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
        // ToDo: edit tariff
    }
}
