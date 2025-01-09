package com.phycaresolutions.mymap.map;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.databinding.ActivityMapsTwoLocatinsBinding;

public class MapsActivityTwoLocatins extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String GOOGLE_API_KEY = "AIzaSyDkYg5dUtTnctXtzjX84eyQgmvNtxsAjn0" /*"AIzaSyCeguW2kOE2_6vd0Izuk6jly_GrwhfJ9SA"*/; // Put your key here
    private GeoApiContext mGeoApiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_two_locatins);

        mGeoApiContext = new GeoApiContext.Builder()
                .apiKey(GOOGLE_API_KEY)
                .build();
        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Sample start and end points (change with your dynamic data)
        LatLng origin = new LatLng(17.3850,  78.4867);  // San Francisco
        LatLng destination = new LatLng(17.9689, 79.5941);  // Los Angeles

        mMap.addMarker(new MarkerOptions().position(origin).title("Start"));
        mMap.addMarker(new MarkerOptions().position(destination).title("End"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 7));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(24f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // Get directions and draw polyline
        getDirections(origin, destination);
    }

    private void getDirections(LatLng origin, LatLng destination) {
        DirectionsApiRequest request = DirectionsApi.getDirections(mGeoApiContext,
                origin.latitude + "," + origin.longitude,
                destination.latitude + "," + destination.longitude);

        try {
            DirectionsResult result = request.await();
            for (DirectionsRoute route : result.routes) {
                for (DirectionsLeg leg : route.legs) {
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.add(new LatLng(leg.startLocation.lat, leg.startLocation.lng));
                    for (DirectionsStep step : leg.steps) {
                        /*LatLng start = new LatLng(step.startLocation.lat, step.startLocation.lng);
                        LatLng end = new LatLng(step.endLocation.lat, step.endLocation.lng);
                        PolylineOptions options = new PolylineOptions().add(start, end);
                                   options.color(Color.RED).width(15).geodesic(true);
                                  mMap.addPolyline(options);
                        Log.e("Polyline", "Step start: " + step.startLocation.lat + ", " + step.startLocation.lng);
                        Log.e("Polyline", "Step end: " + step.endLocation.lat + ", " + step.endLocation.lng);
*/
                        polylineOptions.add(new LatLng(step.endLocation.lat, step.endLocation.lng));


                    }
                    polylineOptions.color(Color.RED).width(15).geodesic(true);
                    mMap.addPolyline(polylineOptions);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
https://maps.googleapis.com/maps/api/directions/json?origin=${17.3850},${78.4867}" +
        "&destination=${17.9689},${79.5941}" +
        "&sensor=false" +
        "&mode=driving" +
        "&key=AIzaSyCeguW2kOE2_6vd0Izuk6jly_GrwhfJ9SA"*/
