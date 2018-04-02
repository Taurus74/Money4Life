package com.aconst.money4life;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        RatesDownloadActivity activity = (RatesDownloadActivity) getActivity();
        TextView textView;
        if (this.getTag().equals("dateFromPicker")) {
            activity.setDateFrom(MyApplication.getDate(year, month, dayOfMonth));
            textView = activity.findViewById(R.id.tvDateFrom);
        } else {
            activity.setDateTo(MyApplication.getDate(year, month, dayOfMonth));
            textView = activity.findViewById(R.id.tvDateTo);
        }
        textView.setText(MyApplication.getDateAsString(year, month, dayOfMonth));
    }
}
