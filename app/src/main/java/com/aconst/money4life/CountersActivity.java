package com.aconst.money4life;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aconst.money4life.adapters.CountersAdapter;
import com.aconst.money4life.model.Counter;

import io.realm.Realm;
import io.realm.Sort;

public class CountersActivity extends AppCompatActivity {
    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counters);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.counters_list);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        CountersAdapter adapter = new CountersAdapter(this, realm.where(Counter.class).findAll()
                .sort("name", Sort.DESCENDING, "type", Sort.ASCENDING));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
    }
}
