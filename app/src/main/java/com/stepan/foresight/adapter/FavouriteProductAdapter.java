package com.stepan.foresight.adapter;

/**
 * Created by Stepan on 1. 3. 2015.
 */
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stepan.foresight.R;
import com.stepan.foresight.model.Price;

import java.text.NumberFormat;
import java.util.List;

public class FavouriteProductAdapter extends ArrayAdapter<Price> {
    private final Context context;
    private final List<Price> values;

    public FavouriteProductAdapter(Context context, List<Price> values) {
        super(context, R.layout.favourite_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.favourite_item, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Price p = values.get(position);

        firstLine.setText(p.getProduct().getName());
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        currencyFormatter.setCurrency(p.getCurrency());
        secondLine.setText(currencyFormatter.format(p.getPrice()) + " (" + p.getShop().getName() + ")");
        //imageView.setImageResource(R.drawable.ok);

        return rowView;
    }
}

