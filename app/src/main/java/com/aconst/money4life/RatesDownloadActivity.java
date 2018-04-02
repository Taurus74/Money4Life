package com.aconst.money4life;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aconst.money4life.controllers.CurrencyController;
import com.aconst.money4life.currency.CbrRateController;
import com.aconst.money4life.model.Currency;

import java.util.Date;
import java.util.List;

public class RatesDownloadActivity extends AppCompatActivity {
    private Date dateFrom = new Date();
    private Date dateTo = new Date();

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates_download);

        TextView textView = findViewById(R.id.tvDateFrom);
        textView.setText(MyApplication.getDateAsString(dateFrom));

        textView = findViewById(R.id.tvDateTo);
        textView.setText(MyApplication.getDateAsString(dateTo));
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        String tag = "";
        switch (v.getId()) {
            case R.id.tvDateFrom :
                tag = "dateFromPicker";
                break;
            case R.id.tvDateTo :
                tag = "dateToPicker";
                break;
        }
        newFragment.show(getSupportFragmentManager(), tag);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.buttonDownload
                && dateFrom != null
                && dateTo != null) {
            CurrencyController currencyController = new CurrencyController();
            List<Currency> currencies = currencyController.getCurrencies().blockingGet();
            for (Currency currency : currencies) {
                String cbrCode = currency.getCbrCode();
                if (!cbrCode.isEmpty()) {
                    CbrRateController cbrRateController = new CbrRateController();
                    cbrRateController.start(MyApplication.getDateAsString(dateFrom),
                            MyApplication.getDateAsString(dateTo), cbrCode);
                }
            }
        }
    }
}
