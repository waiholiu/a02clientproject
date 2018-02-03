package bid.a02.a02clientproject.HouseDetailActivity;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import bid.a02.a02clientproject.R;


public class CustomSpinnerAdapter extends ArrayAdapter<CustomSpinnerAdapterInput> {

    public CustomSpinnerAdapter(Context context, CustomSpinnerAdapterInput[] objects) {
        super(context, 1, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();

        View row = inflater.inflate(R.layout.spinner_custom_dropdownrow, parent, false);

        TextView label = (TextView) row.findViewById(R.id.var1);
        label.setText(this.getItem(position).var1);

        TextView label2 = (TextView) row.findViewById(R.id.sub);
        label2.setText(this.getItem(position).var2);

        return row;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();

        View row = inflater.inflate(R.layout.spinner_custom_view, parent, false);
        TextView label = (TextView) row.findViewById(R.id.var1);
        label.setText(this.getItem(position).var1);

        return row;

    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();

        View row = inflater.inflate(R.layout.spinner_custom_dropdownrow, parent, false);
        TextView label = (TextView) row.findViewById(R.id.var1);
        label.setText(this.getItem(position).var1);

        TextView label2 = (TextView) row.findViewById(R.id.sub);
        label2.setText(this.getItem(position).var2);


        return row;
    }


}