package com.example.aser.blinds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import java.util.List;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

// classes needed to initialize map
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

// classes needed to add the location component
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;

// classes needed to add a marker
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

// classes to calculate a route
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

// classes needed to launch navigation UI
import android.view.View;
import android.widget.Button;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;

public class setLocationMap extends AppCompatActivity implements OnMapReadyCallback, MapboxMap.OnMapClickListener, PermissionsListener {
    // variables for adding location layer
    private MapView mapView;
    private MapboxMap mapboxMap;
    // variables for adding location layer
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;
    // variables for calculating and drawing a route
    private DirectionsRoute currentRoute;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;
    // variables needed to initialize navigation
    private Button button;
    SharedPreferences readData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_set_location_map);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        readData= getSharedPreferences("Dataguardian", MODE_PRIVATE);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(getString(R.string.navigation_guidance_day), new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);

                addDestinationIconSymbolLayer(style);

                mapboxMap.addOnMapClickListener(setLocationMap.this);

            }
        });
    }

    private void addDestinationIconSymbolLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage("destination-icon-id",
                BitmapFactory.decodeResource(this.getResources(), R.drawable.mapbox_marker_icon_default));
        GeoJsonSource geoJsonSource = new GeoJsonSource("destination-source-id");
        loadedMapStyle.addSource(geoJsonSource);
        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id", "destination-source-id");
        destinationSymbolLayer.withProperties(
                iconImage("destination-icon-id"),
                iconAllowOverlap(true),
                iconIgnorePlacement(true)
        );
        loadedMapStyle.addLayer(destinationSymbolLayer);
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public boolean onMapClick(@NonNull LatLng point) {
//for checking that either which place is selected
        Intent intent = getIntent();
        String place_name = intent.getStringExtra("Place_NAME");

   // condition for selected place

        SharedPreferences.Editor editor= getSharedPreferences("Dataguardian", MODE_PRIVATE).edit();
        Point destinationPoint = Point.fromLngLat(point.getLongitude(),point.getLatitude());
        String destinationlongitude = Double.toString(point.getLongitude());
        String destinationlatitude = Double.toString(point.getLatitude());
        String originlongitude = Double.toString(locationComponent.getLastKnownLocation().getLongitude());
        String originlatitude = Double.toString(locationComponent.getLastKnownLocation().getLatitude());

        if(place_name.equals("Masjid")){
            editor.putString("MasjidDestinationlatitude",destinationlatitude);
            editor.putString("MasjidDestinationlongitude",destinationlongitude);
            editor.putString("Originlatitude",originlatitude);
            editor.putString("Originlongitude",originlongitude);
            editor.apply();
            Toast.makeText(setLocationMap.this,"Saved",
                    Toast.LENGTH_LONG).show();

        }
        else if(place_name.equals("School"))
        {
            editor.putString("SchoolDestinationlatitude",destinationlatitude);
            editor.putString("SchoolDestinationlongitude",destinationlongitude);
            editor.putString("Originlatitude",originlatitude);
            editor.putString("Originlongitude",originlongitude);
            editor.apply();
            Toast.makeText(setLocationMap.this,"Saved",
                    Toast.LENGTH_LONG).show();
        }
        else if(place_name.equals("Park"))
        {
            editor.putString("ParkDestinationlatitude",destinationlatitude);
            editor.putString("ParkDestinationlongitude",destinationlongitude);
            editor.putString("Originlatitude",originlatitude);
            editor.putString("Originlongitude",originlongitude);
            editor.apply();
            Toast.makeText(setLocationMap.this,"Saved",
                    Toast.LENGTH_LONG).show();
        }
        else if(place_name.equals("Home"))
        {
            editor.putString("HomeDestinationlatitude",destinationlatitude);
            editor.putString("HomeDestinationlongitude",destinationlongitude);
            editor.putString("Originlatitude",originlatitude);
            editor.putString("Originlongitude",originlongitude);
            editor.apply();
            Toast.makeText(setLocationMap.this,"Saved",
                    Toast.LENGTH_LONG).show();
        }
        else if(place_name.equals("Office"))
        {
            editor.putString("OfficeDestinationlatitude",destinationlatitude);
            editor.putString("OfficeDestinationlongitude",destinationlongitude);
            editor.putString("Originlatitude",originlatitude);
            editor.putString("Originlongitude",originlongitude);
            editor.apply();
            Toast.makeText(setLocationMap.this,"Saved",
                    Toast.LENGTH_LONG).show();
        }



        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("destination-source-id");
        if (source != null) {
            source.setGeoJson(Feature.fromGeometry(destinationPoint));
        }
        return true;

    }



    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Activate the MapboxMap LocationComponent to show user location
            // Adding in LocationComponentOptions is also an optional parameter
            locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);
            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}