package com.example.lahiru.webapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.provider.Settings;
import android.widget.Toast;


public class Activity3 extends ActionBarActivity implements LocationListener{
    EditText shopName, owner, password, openingtime, closingtime;
    String shopName1, owner1, password1, openingtime1, closingtime1, latitude1, longitude1;
    double latitude, longitude;

    private LocationManager locationManager;
    private  LocationListener locationListener;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,2000, 10, this);
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity3, menu);
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
        System.out.println("/////////////inside location");
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

    public void Regis(View view){
        shopName = (EditText) findViewById(R.id.editText3);
        owner = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText6);
        openingtime = (EditText) findViewById(R.id.editText4);
        closingtime = (EditText) findViewById(R.id.editText5);

        shopName1 = shopName.getText().toString();
        owner1 = owner.getText().toString();
        password1 = password.getText().toString();
        openingtime1 = openingtime.getText().toString();
        closingtime1 = closingtime.getText().toString();

        String method = "register";
        Registration registration = new Registration(this);
        registration.execute(method, shopName1, owner1, password1, openingtime1, closingtime1, latitude1, longitude1);
        finish();

    }



}
