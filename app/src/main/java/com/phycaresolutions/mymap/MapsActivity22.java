package com.phycaresolutions.mymap;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.phycaresolutions.mymap.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity22 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    List list = new ArrayList<LatLng>();
    private FusedLocationProviderClient client;
    double lati =17.4064967;
    double longi = 78.4772433;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       /* requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
             Log.e("LOCATION","Location<>>>>>>>: "+ location.getLatitude());
                Log.e("LOCATION","Location<>>>>>>>: "+ location.getLongitude());
                lati= location.getLatitude();
                longi=location.getLongitude();
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                  List<Address> addressList = geocoder.getFromLocation( location.getLatitude(),location.getLongitude(),1);
                    Address address = addressList.get(0);
                    address.getAdminArea();
                    address.getLocality();
                    Log.e("LOCATION","AdminArea<>>>>>>>: "+ address.getAdminArea());
                    Log.e("LOCATION","Locality()<>>>>>>>: "+ address.getLocality());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });*/
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
        mMap = googleMap;
        LatLng latLng;
        // Add a marker in Sydney and move the camera
         latLng = new LatLng(17.458, 78.405);
         latLng = new LatLng(lati, longi);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Hyderabad"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(24f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        /*list.add(new LatLng(17.458,78.405));
        list.add(new LatLng(17.558,78.605));
        list.add(new LatLng(17.358,78.805));
        list.add(new LatLng(17.658,78.205));
        Polygon polygon = mMap.addPolygon(new PolygonOptions().addAll(list));*/
       // DrawingOption.DrawingType currentDrawingType = DrawingOption.DrawingType.POLYGON;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
               // Log.e("LATLNG",">>>><<>>: "+latLng);
                list.add(latLng);

               /* Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(list)
                        .strokeColor(Color.RED)
                        .fillColor(Color.BLUE));
                polygon.setClickable(true);
                mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
                    @Override
                    public void onPolygonClick(@NonNull Polygon polygon) {
                       polygon.getPoints();
                       polygon.getHoles();
                        Log.e("LATLNG",">polygon.getPoints()>>><<>>: "+polygon.getPoints());
                        Log.e("LATLNG",">polygon.getPoints()>>><<>>: "+polygon.getTag());
                    }
                });*/


                mMap.addPolyline((new PolylineOptions()).addAll(list).
                        // below line is use to specify the width of poly line.
                                width(5)
                        // below line is use to add color to our poly line.
                        .color(Color.RED)
                                        .clickable(true)
                        // below line is to make our poly line geodesic.
                        .geodesic(true));
                // on below line we will be starting the drawing of polyline.
               // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 13));

            }


        });
        /*mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (list!=null) {
                    Polygon polygon = mMap.addPolygon(new PolygonOptions().addAll(list));
                }
            }
        });*/

    }
}
/*5 + years of software development experience with swift and swift UI
Design build and maintain high performance, reusable mobile native Android application using Kotlin.
A clear understanding of Android SDK.
Experience in writing unit tests, UI automation and knowledge on CI/CD and automation tools.
Competence in working with architecture components and Jetpack libraries.
        Ensure performance, code quality, testing and stability of application.
Familiarity with Restful API integration.
Experience with architectural patterns such as Clean code architecture, MVP, MVVM, MVI etc.
Understanding Android design principles and interface guidelines
Google Play store guidelines for app submission, review and update processes.
Contribute to industry best practices to the development and maintenance of our applications
Experience with performance  and memory tuning tools
Participate in reviewing the code adhering to industry standards.
Android UI/UX guidelines and standards*/


/*
Skills:
Kotlin, Android SDK, Restful API, Jetpack, Jetpack compose

Good to have
JSON, Material design principles, Knowledge on GCP

Lloyds Technology center
Job Location- Hyderabad

Work Mode : Hybrid

Office: Knowledge city,  Hyderabad, Telangana 500032, India*/
