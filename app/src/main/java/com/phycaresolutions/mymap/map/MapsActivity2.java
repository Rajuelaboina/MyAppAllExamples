package com.phycaresolutions.mymap.map;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity2 extends FragmentActivity  {

    private GoogleMap mMap;
    public static boolean mMapIsTouched = false;
    MySupportMapFragment customMapFragment;
    Projection projection;
    public double latitude;
    public double longitude;
    Boolean Is_MAP_Moveable = false;
    List val = new ArrayList<LatLng>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        FrameLayout fram_map = (FrameLayout) findViewById(R.id.fram_map);
        Button btn_draw_State = (Button) findViewById(R.id.btn_draw_State);

        btn_draw_State.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Is_MAP_Moveable = !Is_MAP_Moveable;
            }
        });
        fram_map.setOnTouchListener(new View.OnTouchListener() {     @Override
        public boolean onTouch(View v, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            int x_co = Math.round(x);
            int y_co = Math.round(y);
            Point x_y_points = new Point(x_co, y_co);
            LatLng latLng = null;
            if (mMap != null) {
                projection = mMap.getProjection();
               // LatLng tapLocation = mMap.getProjection().fromScreenLocation(new Point((int) x, (int) y));
                 latLng = mMap.getProjection().fromScreenLocation(x_y_points);
                // LatLng latLng = mMap.getProjection().fromScreenLocation(x_y_points);
                latitude = latLng.latitude;

                longitude = latLng.longitude;

                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        // finger touches the screen
                        val.add(new LatLng(latitude, longitude));

                    case MotionEvent.ACTION_MOVE:
                        // finger moves on the screen
                        val.add(new LatLng(latitude, longitude));

                    case MotionEvent.ACTION_UP:
                        // finger leaves the screen
                        Draw_Map();
                        break;
                }
            }





            return Is_MAP_Moveable;

        }
        });
    }
    public void Draw_Map() {
       PolygonOptions rectOptions = new PolygonOptions();
        rectOptions.addAll(val);
        rectOptions.strokeColor(Color.BLUE);
        rectOptions.strokeWidth(7);
        rectOptions.fillColor(Color.CYAN);
        Polygon polygon = mMap.addPolygon(rectOptions);
    }
}