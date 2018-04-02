package com.aconst.money4life;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aconst.money4life.model.Operations;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyApplication";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_currencies :
                intent = new Intent(this, CurrencyActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_rates :
                intent = new Intent(this, RatesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_accounts :
                intent = new Intent(this, AccountsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_operations :
                intent = new Intent(this, OperationsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_counters :
                intent = new Intent(this, CountersActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_tariffs :
                intent = new Intent(this, TariffActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnMoney :
                // Денежные операции
                intent = new Intent(this, ActionsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCounters :
                // Ввод показаний счетчиков
                Toast.makeText(getApplicationContext(), "Пункт в разработке",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnReferences :
                // Убрать
                break;
        }
    }
}
