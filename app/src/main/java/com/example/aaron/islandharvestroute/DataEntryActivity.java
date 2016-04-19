package com.example.aaron.islandharvestroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DataEntryActivity extends AppCompatActivity {

    private TextView mFoodTypeLabel;
    private TextView mQuantityLabel;
    private EditText mAmountEditText;
    private Spinner mFoodTypeSpinner;
    private Spinner mQuantityTypeSpinner;
    private Button mCheckInButton;
    private Button mExtraButton;
    private ArrayAdapter<CharSequence> adapter;

    private IHDatabaseHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing Layout Components
        mFoodTypeLabel = (TextView) findViewById(R.id.foodTypeLabel);
        mQuantityLabel = (TextView) findViewById(R.id.quantityLabel);
        mAmountEditText = (EditText) findViewById(R.id.amountEditText);
        mFoodTypeSpinner = (Spinner) findViewById(R.id.foodTypeSpinner);
        mQuantityTypeSpinner = (Spinner) findViewById(R.id.quantityTypeSpinner);
        mCheckInButton = (Button) findViewById(R.id.checkInButton);
        mExtraButton = (Button) findViewById(R.id.extraButton);

        // Setting up spinner item menu
        adapter = ArrayAdapter.createFromResource(this, R.array.food_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFoodTypeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.amount_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuantityTypeSpinner.setAdapter(adapter);

        initEventHandlers();
        dbh = new IHDatabaseHelper(this);
    }

    private void initEventHandlers() {
        // Initializing Check In Button
        mCheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = mFoodTypeSpinner.getSelectedItem().toString();
                double quantity = Double.parseDouble(mAmountEditText.getText().toString());
                String units = mQuantityTypeSpinner.getSelectedItem().toString();

                String dataText = dbh.insertData(type, units, quantity);
                Toast.makeText(DataEntryActivity.this, dataText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
