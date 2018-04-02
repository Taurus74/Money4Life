package com.aconst.money4life;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aconst.money4life.adapters.RatesAdapter;
import com.aconst.money4life.model.Rates;

import io.realm.Realm;
import io.realm.Sort;

public class RatesActivity extends AppCompatActivity {
    private static final String TAG = "RatesActivity";
    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.rates_list);
        setUpRecyclerView();

        FloatingActionButton fabRates = findViewById(R.id.fabRates);
        fabRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RatesActivity.this,
                        RatesDownloadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView() {
        RatesAdapter adapter = new RatesAdapter(this, realm.where(Rates.class).findAll()
                .sort("date", Sort.DESCENDING, "currency.CbrCode", Sort.ASCENDING));
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

}
