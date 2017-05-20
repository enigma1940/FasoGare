package it.isig.fasogare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class mymap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button btnIntineraire;
    private SlidingUpPanelLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnIntineraire = (Button)findViewById(R.id.btnIntineraire);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);


        checGPS();
        // init sliding panel



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

        // Add a marker in Sydney and move the camera
        LatLng tcv = new LatLng(12.37942406, -1.52489426);
        LatLng stm = new LatLng(12.39667366, -1.52202555);
        //BitmapDescriptor icon=BitmapDescriptorFactory.fromResource(R.drawable.icon_map_marker);

        MarkerOptions mq=new MarkerOptions().position(tcv).title("3STCV dapoya ").snippet("Dapoya \n 3STV");

        mMap.addMarker(mq);
        mMap.addMarker(new MarkerOptions().position(stm).title("STM dapoya"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        moveToCurrentLocation(tcv);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Log.d("Marker","clicked");

                btnIntineraire.setVisibility(View.VISIBLE);
                //sliding pane

                mLayout.setAnchorPoint(0.3f);

                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

            }
        });


        //
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
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
        mMap.setMyLocationEnabled(true);



    }
    private void moveToCurrentLocation(LatLng currentLocation)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
    }

    public void findIntineraire(View view) {


        LatLng myposition= new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude());
        LatLng dest=new LatLng(12.37942406, -1.52489426);

        String urldirections = getDirectionsUrl(myposition,dest);

        Log.d("MAPS",urldirections);
        // get url
        mMap.addPolyline(new PolylineOptions().add(
                new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude()),
                new LatLng(12.37942406, -1.52489426)
        ).width(10).color(Color.RED));
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";



//      Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor+"&";

//      Output format
        String output = "json";

//      Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }



    private void checGPS(){


        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

// check if enabled and if not send user to the GSP settings
// Better solution would be to display a dialog and suggesting to
// go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }
}
