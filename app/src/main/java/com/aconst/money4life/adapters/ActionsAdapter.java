package com.aconst.money4life.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aconst.money4life.R;
import com.aconst.money4life.model.Actions;

import java.util.Collections;
import java.util.List;

public class ActionsAdapter extends BaseAdapter {
    private List<Actions> actions = Collections.emptyList();

    @Override
    public int getCount() {
        return actions.size();
    }

    @Override
    public Object getItem(int position) {
        return actions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return actions.get(position).getId();
    }

    private static class ViewHolder {
        private TextView date;
        private TextView account;
        private TextView operation;
        private TextView value;
        private TextView valueCur;
        private TextView commission;
        private TextView comment;

        public ViewHolder(View view) {
            this.date = view.findViewById(R.id.action_date);
            this.operation = view.findViewById(R.id.action_operation);
            this.value = view.findViewById(R.id.action_amount);
//            this.account = ;
//            this.valueCur = valueCur;
//            this.commission = commission;
//            this.comment = comment;
        }

        public void bind(Actions action) {
            date.setText(action.getDate().toString());
            operation.setText(action.getOperation().getName());
            value.setText(String.valueOf(action.getValue()));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_listitem,
                    parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Actions action = actions.get(position);
        viewHolder.bind(action);

        return convertView;
    }
}
