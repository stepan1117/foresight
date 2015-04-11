package com.stepan.foresight.adapter;

/**
 * Created by Stepan on 1. 3. 2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stepan.foresight.R;
import com.stepan.foresight.model.Price;
import com.stepan.foresight.model.ShoppingItem;
import com.stepan.foresight.model.enumeration.UnitsEnum;

import java.text.NumberFormat;
import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {
    private final Context context;
    private final List<ShoppingItem> values;

    public ShoppingListAdapter(Context context, List<ShoppingItem> values) {
        super(context, R.layout.favourite_item, values);
        this.context = context;
        this.values = values;
    }

    public double getTotalPrice(){
        double price = 0;
        for (ShoppingItem si : values){
            price += si.getAmount() * si.getPrice().getPrice();
        }
        return price;
    }

    public int getItemsCount(){
        int count = 0;
        for (ShoppingItem si : values){
            if (si.getUnits() == UnitsEnum.PIECES){
                count += si.getAmount();
            } else {
                count++;
            }
        }
        return count;
    }

    public void updateItem(ShoppingItem item){
        int position = getPosition(item);
        remove(item);
        values.add(position, item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shopping_list_item, parent, false);
        TextView description = (TextView) rowView.findViewById(R.id.shoppingListItemDescription);
        TextView price = (TextView) rowView.findViewById(R.id.shoppingListItemPrice);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ShoppingItem si = values.get(position);

        if (si.getPrice().getProduct() == null){
            description.setText(si.getPrice().getProductDescription());
        } else {
            description.setText(si.getPrice().getProduct().getName());
        }
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

        price.setText(si.getAmount() + " * " + si.getPrice().getPrice());

        return rowView;
    }
}

