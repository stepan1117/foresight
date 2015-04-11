package com.stepan.foresight.task;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.stepan.foresight.R;
import com.stepan.foresight.model.Category;
import com.stepan.foresight.model.Coordinates;
import com.stepan.foresight.model.Price;
import com.stepan.foresight.model.Product;
import com.stepan.foresight.model.Shop;


import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Created by Stepan on 1. 3. 2015.
 */

public class LoadFavouriteProductsTask extends AsyncTask<Object, Void, List<Price>>{

    ListView listView;

    public LoadFavouriteProductsTask(ListView listView){
        this.listView = listView;
    }

    @Override
    protected List<Price> doInBackground(Object... params) {
        Shop shop = new Shop();
        shop.setId(423);
        shop.setName("Globus Cerny Most");
        shop.setCoordinates(new Coordinates(){{
            setLatitude(235353);
            setLongitude(75654);
        }});

        Price p1 = new Price();
        p1.setCurrency(Currency.getInstance("CZK"));
        p1.setMinimumPieces(1);
        p1.setPrice(50);
        p1.setProduct(new Product(){{
            setBarcode("85938327793");
            setCategory(new Category(){{
                setId(367);
                setName("Cukrovinky");
            }});
            setName("Tatranky 50g");
            setManufacturer("Nestle");
        }});
        p1.setShop(shop);
        p1.setValidFrom(new Date());

        Price p2 = new Price();
        p2.setCurrency(Currency.getInstance("CZK"));
        p2.setMinimumPieces(1);
        p2.setPrice(180);
        p2.setProduct(new Product(){{
            setBarcode("85938343293");
            setCategory(new Category(){{
                setId(367);
                setName("Mlecne vyrobky");
            }});
            setName("Tatra mleko nizkotucne");
            setManufacturer("Tatra");
        }});
        p2.setShop(shop);
        p2.setValidFrom(new Date());

        List<Price> prices = new ArrayList<>();
        prices.add(p1);
        prices.add(p2);
        return prices;
    }

    @Override
    protected void onPostExecute(List<Price> result) {
        super.onPostExecute(result);
        ArrayAdapter<Price> adpt = (ArrayAdapter) listView.getAdapter();

        adpt.clear();
        adpt.addAll(result);
        adpt.notifyDataSetChanged();
    }
}
