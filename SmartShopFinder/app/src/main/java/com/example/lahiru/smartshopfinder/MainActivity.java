package com.example.lahiru.smartshopfinder;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity implements LocationListener {
    EditText name, category, price;
    String name1, category1, price1, formattedTime, latitude1, longitude1;
    public static double latitude, longitude;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,2000, 10, this);
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    public void onLocationChanged(Location location) {
        String msg = "New Latitude: " + location.getLatitude()+ "New Longitude: " + location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latitude1 = Double.toString(latitude);
        longitude1 = Double.toString(longitude);

        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",Toast.LENGTH_SHORT).show();
    }

    public void search (View view){
        name = (EditText) findViewById(R.id.editText1);
        price = (EditText) findViewById(R.id.editText2);
        category = (EditText) findViewById(R.id.editText3);

        name1 = name.getText().toString();
        price1 = price.getText().toString();
        category1 = category.getText().toString();

        Calendar c = Calendar.getInstance();
        int Hr=c.get(Calendar.HOUR_OF_DAY);
        int Min=c.get(Calendar.MINUTE);
        int time = (Hr*100)+Min;
        formattedTime = Integer.toString(time);

        String method = "search";
        SearchTask searchTask = new SearchTask(this);
        searchTask.execute(method, name1, price1, category1, formattedTime, latitude1, longitude1);
        //Intent intent = new Intent(this, MapsActivity.class);
        //startActivity(intent);
    }

}
