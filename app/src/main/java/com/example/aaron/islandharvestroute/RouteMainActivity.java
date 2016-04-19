package com.example.aaron.islandharvestroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RouteMainActivity extends AppCompatActivity {

    private TextView mEndAddressLabel;
    private TextView mEndAddressTextView;
    private ImageView mMapView;
    private Button mRouteDetailButton;
    private Button mDataEntryButton;
    private Button mCompleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Layout Components

        mEndAddressLabel = (TextView) findViewById(R.id.endAddressLabel);
        mEndAddressTextView = (TextView) findViewById(R.id.endAddressTextView);
        mMapView = (ImageView) findViewById(R.id.mapImageView);
        mRouteDetailButton = (Button) findViewById(R.id.routeDetailButton);
        mDataEntryButton = (Button) findViewById(R.id.dataEntryButton);
        mCompleteButton = (Button) findViewById(R.id.completeButton);

        // Intialize Button Handlers
        intializeButtonEventHandlers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_route_main, menu);
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

    private void intializeButtonEventHandlers() {
        // Route Detail Button
        mRouteDetailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Takes user to RouteDetailActivity
                Intent takeUserToRouteDetailActivity = new Intent(RouteMainActivity.this, RouteDetailActivity.class);
                startActivity(takeUserToRouteDetailActivity);
            }
        });
        // Data Entry Button
        mDataEntryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Takes user to DataEntryActivity
                Intent takeUserToDataEntryActivity = new Intent(RouteMainActivity.this, DataEntryActivity.class);
                startActivity(takeUserToDataEntryActivity);
            }
        });
        // Complete Button
        mCompleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Complete the route and registers a checkpoint

                // Placeholder Code: Takes user to Login Activity
                Intent takeUserToLoginActivity = new Intent(RouteMainActivity.this, LoginActivity.class);
                startActivity(takeUserToLoginActivity);
            }
        });
    }
}
