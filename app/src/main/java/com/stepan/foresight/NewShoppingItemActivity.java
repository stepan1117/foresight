package com.stepan.foresight;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.stepan.foresight.activity.SimpleScannerActivity;
import com.stepan.foresight.model.Price;
import com.stepan.foresight.model.ShoppingItem;
import com.stepan.foresight.model.enumeration.UnitsEnum;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;


public class NewShoppingItemActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shopping_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ShoppingItemFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_shopping_item, menu);
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
    public static class ShoppingItemFragment extends Fragment {

        public ShoppingItemFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_new_shopping_item, container, false);

            Button cancel = (Button)rootView.findViewById(R.id.cancelNewItemButton);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
            ShoppingItem si = (ShoppingItem)getActivity().getIntent().getSerializableExtra("value");

            boolean update = false;

            if (si != null){
                fillFromShoppingItem(si,rootView);
                update = true;
            }
            Button save = (Button)rootView.findViewById(R.id.saveNewItemButton);
            if (update){
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent();
                        ShoppingItem si = (ShoppingItem)getActivity().getIntent().getSerializableExtra("value");
                        populateShoppingItem(si, rootView);
                        intent.putExtra("SHOPPING_ITEM",si);
                        getActivity().setResult(3,intent);
                        getActivity().finish();
                    }
                });
            } else {
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        ShoppingItem si = new ShoppingItem();
                        populateShoppingItem(si,rootView);
                        intent.putExtra("SHOPPING_ITEM",si);
                        getActivity().setResult(2,intent);
                        getActivity().finish();
                    }
                });
            }

            ImageButton scanCodeButton = (ImageButton)rootView.findViewById(R.id.scanCodeButton);
            scanCodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SimpleScannerActivity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }

        private void fillFromShoppingItem(ShoppingItem si, View rootView){
            EditText barcodeText = (EditText)rootView.findViewById(R.id.barcodeText);
            EditText productNameText = (EditText)rootView.findViewById(R.id.productNameText);
            EditText priceText = (EditText)rootView.findViewById(R.id.priceText);
            EditText piecesText = (EditText)rootView.findViewById(R.id.piecesText);
            Spinner unitsSpinner = (Spinner)rootView.findViewById(R.id.unitsSpinner);

            // barcodeText.setText(si.getPrice().getProduct().getBarcode());
            productNameText.setText(si.getPrice().getProductDescription());

            NumberFormat nf = DecimalFormat.getInstance();
            nf.setRoundingMode(RoundingMode.FLOOR);
            nf.setMinimumFractionDigits(0);
            nf.setMaximumFractionDigits(4);

            switch (si.getUnits()){
                default:
                case PIECES:
                    unitsSpinner.setSelection(0);
                    break;
                case KGS:
                    unitsSpinner.setSelection(1);
                    break;
                case DKG:
                    unitsSpinner.setSelection(2);
                    break;
                case G:
                    unitsSpinner.setSelection(3);
                    break;
            }

            priceText.setText(nf.format(si.getPrice().getPrice()));
            piecesText.setText(nf.format(si.getAmount()));
        }

        private void populateShoppingItem(ShoppingItem s, View rootView){

            EditText barcodeText = (EditText)rootView.findViewById(R.id.barcodeText);
            final EditText productNameText = (EditText)rootView.findViewById(R.id.productNameText);
            final EditText priceText = (EditText)rootView.findViewById(R.id.priceText);
            final EditText piecesText = (EditText)rootView.findViewById(R.id.piecesText);
            final Spinner unitsSpinner = (Spinner)rootView.findViewById(R.id.unitsSpinner);

            s.setAmount(Float.parseFloat(piecesText.getText().toString()));
            Price price = new Price();
            price.setCurrency(Currency.getInstance("CZK"));
            price.setPrice(Double.parseDouble(priceText.getText().toString()));
            price.setProductDescription(productNameText.getText().toString());

            switch (unitsSpinner.getSelectedItemPosition()){
                default:
                case 0:
                    s.setUnits(UnitsEnum.PIECES);
                    break;
                case 1:
                    s.setUnits(UnitsEnum.KGS);
                    break;
                case 2:
                    s.setUnits(UnitsEnum.DKG);
                    break;
                case 3:
                    s.setUnits(UnitsEnum.G);
                    break;
            }

            s.setPrice(price);

        }
    }

}
