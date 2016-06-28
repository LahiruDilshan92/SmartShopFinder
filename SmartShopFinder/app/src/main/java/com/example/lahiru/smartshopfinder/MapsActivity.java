package com.example.lahiru.smartshopfinder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private LocationManager locationManager;
    private GoogleMap mMap;
    public static double lat, lon;
    public static Marker myLocation = null;
    public String code;

    public MapsActivity(){}

    //public static String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager.requestLocationUpdates(locationManager.getBestProvider(crit, false),2000 , 1, this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //LatLng current = new LatLng(lat, lon);
        //mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(current));


        System.out.println("App started");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       /* try {
            Criteria crit = new Criteria();
            crit.setAccuracy(Criteria.ACCURACY_FINE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(crit, false));

            lat = location.getLatitude();
            lon = location.getLongitude();

            LatLng current = new LatLng(lat, lon);
            myLocation = mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 7.0f));
        }
        catch(Exception e)
        {
            System.out.println("Some initialization error occured!");
        }*/

        LatLng current = new LatLng(7.25475203,80.59206063);

        //LatLng current = new LatLng(MainActivity.latitude,MainActivity.longitude);
        myLocation = mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        String co1="",co2="";
        String co_ord= "";
        int size;
        int i;
        while(SearchTask.finalresult != null)
        {
            size = SearchTask.finalresult.length()-1;
            i = size;
            while(SearchTask.finalresult.charAt(i)!='>') {
                co_ord = co_ord + SearchTask.finalresult.charAt(i);
                i--;
            }
            System.out.println("***************"+co_ord);
            try{
            String[] co = co_ord.split(" ");
            //System.out.println(SearchTask.finalresult);
            current = new LatLng(Float.parseFloat(co[0]), Float.parseFloat(co[1]));
            //       LatLng current = new LatLng(7.2580, 80.602);

                myLocation = mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(current, 7.0f) );
            }
            catch(Exception e) {
                System.out.println("Some error occured when updating current location!");
            }
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();

        if(myLocation != null)
            myLocation.remove();

        LatLng current = new LatLng(lat, lon);
        //       LatLng current = new LatLng(7.2580, 80.602);

        try {
            myLocation = mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
            //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(current, 7.0f) );
        }
        catch(Exception e){
            System.out.println("Some error occured when updating current location!");
        }

        System.out.println("Location changed");


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }


}
