package com.phycaresolutions.mymap.map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient client;
    double lati =17.4064967;
    double longi = 78.4772433;
    private GoogleMap mGoogleMap;
    private ActivityMapsBinding binding;
    List<LatLng> sourcePoints = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* sourcePoints.add(new LatLng(-35.28099,149.12929));
        sourcePoints.add(new LatLng(-35.28144,149.12984));
        sourcePoints.add(new LatLng(-35.28194,149.13003));
        sourcePoints.add(new LatLng(-35.28282,149.12956));
        sourcePoints.add(new LatLng(-35.28302,149.12881));
        sourcePoints.add(new LatLng(-35.28473,149.12836));*/
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null) {
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLatitude());
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLongitude());
                    lati = location.getLatitude();
                    longi = location.getLongitude();
                    sourcePoints.add(new LatLng(location.getLatitude(), location.getLongitude()));
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Address address = addressList.get(0);
                        address.getAdminArea();
                        address.getLocality();
                        Log.e("LOCATION", "AdminArea<>>>>>>>: " + address.getAdminArea());
                        Log.e("LOCATION", "Locality()<>>>>>>>: " + address.getLocality());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    sourcePoints.add(new LatLng(17.3064967,78.5772433));


                }


            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        sourcePoints.add(new LatLng(lati,longi));
        LatLng carPos;
        carPos = new LatLng(lati, longi);

        addMarker(carPos);
        /*mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                PolylineOptions polyLineOptions;
                LatLng carPos;
                polyLineOptions = new PolylineOptions();
                polyLineOptions.addAll(sourcePoints);
                polyLineOptions.width(10);
                polyLineOptions.color(Color.BLUE);
                mGoogleMap.addPolyline(polyLineOptions);

                carPos = new LatLng(lati, longi);
                addMarker(carPos);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sourcePoints.get(0), 15));

                *//*for (int i = 0; i < sourcePoints.size() - 1; i++) {
                    LatLng segmentP1 = sourcePoints.get(i);
                    LatLng segmentP2 = sourcePoints.get(i+1);
                    List<LatLng> segment = new ArrayList<>(2);
                    segment.add(segmentP1);
                    segment.add(segmentP2);

                    if (PolyUtil.isLocationOnPath(carPos, segment, true, 30)) {
                        polyLineOptions = new PolylineOptions();
                        polyLineOptions.addAll(segment);
                        polyLineOptions.width(10);
                        polyLineOptions.color(Color.RED).geodesic(true);
                        mGoogleMap.addPolyline(polyLineOptions);
                        LatLng snappedToSegment = getMarkerProjectionOnSegment(carPos, segment, mGoogleMap.getProjection());
                        addMarker(snappedToSegment);
                        break;
                    }
                }*//*
            }
        });*/
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sourcePoints.get(0), 15));


    }
    private LatLng getMarkerProjectionOnSegment(LatLng carPos, List<LatLng> segment, Projection projection) {
        LatLng markerProjection = null;

        Point carPosOnScreen = projection.toScreenLocation(carPos);
        Point p1 = projection.toScreenLocation(segment.get(0));
        Point p2 = projection.toScreenLocation(segment.get(1));
        Point carPosOnSegment = new Point();

        float denominator = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        // p1 and p2 are the same
        if (Math.abs(denominator) <= 1E-10) {
            markerProjection = segment.get(0);
        } else {
            float t = (carPosOnScreen.x * (p2.x - p1.x) - (p2.x - p1.x) * p1.x
                    + carPosOnScreen.y * (p2.y - p1.y) - (p2.y - p1.y) * p1.y) / denominator;
            carPosOnSegment.x = (int) (p1.x + (p2.x - p1.x) * t);
            carPosOnSegment.y = (int) (p1.y + (p2.y - p1.y) * t);
            markerProjection = projection.fromScreenLocation(carPosOnSegment);
        }
        return markerProjection;
    }

    public void addMarker(LatLng latLng) {
        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
        );
    }
}

