package com.stepan.foresight;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.stepan.foresight.adapter.FavouriteProductAdapter;
import com.stepan.foresight.adapter.ShoppingListAdapter;
import com.stepan.foresight.model.Price;
import com.stepan.foresight.model.ShoppingItem;
import com.stepan.foresight.task.LoadFavouriteProductsTask;

import java.io.Serializable;
import java.util.ArrayList;


public class PurchaseListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PurchaseListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_purchase_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PurchaseListFragment extends Fragment {

        public PurchaseListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_purchase_list, container, false);

            final ListView listView = (ListView)rootView.findViewById(R.id.shoppingListView);
            listView.setAdapter(new ShoppingListAdapter(getActivity().getApplicationContext(), new ArrayList<ShoppingItem>()));
            //((ArrayAdapter<ShoppingItem>) listView.getAdapter()).add(new ShoppingItem());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), NewShoppingItemActivity.class);
                    intent.putExtra("value",(Serializable)listView.getAdapter().getItem(position));
                    startActivityForResult(intent, 3);
                }
            });

            ImageButton addNewShoppingItemButton = (ImageButton) rootView.findViewById(R.id.addNewShoppingItemButton);
            addNewShoppingItemButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NewShoppingItemActivity.class);
                    startActivityForResult(intent, 2);
                }
            });

            return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (data == null) return;

            if(requestCode==2){
                ShoppingItem si=(ShoppingItem)data.getSerializableExtra("SHOPPING_ITEM");
                ListView listView = (ListView)getActivity().findViewById(R.id.shoppingListView);
                ShoppingListAdapter sla = (ShoppingListAdapter)listView.getAdapter();
                sla.add(si);

                TextView summaryText = (TextView)getActivity().findViewById(R.id.shoppingListSummaryText);
                summaryText.setText(sla.getItemsCount() + " items for " + sla.getTotalPrice() + "CZK");

            } else if (requestCode == 3){
                ShoppingItem si=(ShoppingItem)data.getSerializableExtra("SHOPPING_ITEM");
                ListView listView = (ListView)getActivity().findViewById(R.id.shoppingListView);
                ShoppingListAdapter sla = (ShoppingListAdapter)listView.getAdapter();
                sla.updateItem(si);
                sla.notifyDataSetChanged();

                TextView summaryText = (TextView)getActivity().findViewById(R.id.shoppingListSummaryText);
                summaryText.setText(sla.getItemsCount() + " items for " + sla.getTotalPrice() + "CZK");
            }
        }
    }
}
