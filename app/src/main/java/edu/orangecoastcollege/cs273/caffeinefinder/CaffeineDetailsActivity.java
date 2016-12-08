package edu.orangecoastcollege.cs273.caffeinefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView positionTextView;
    private Location location;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        positionTextView = (TextView) findViewById(R.id.positionTextView);


        location = getIntent().getExtras().getParcelable("Location");

        nameTextView.setText(location.getName());
        addressTextView.setText(location.getFullAddress());
        phoneTextView.setText(location.getPhone());
        positionTextView.setText(location.getLatitude() +" N  "+ location.getLongitude()+" W");

        SupportMapFragment caffeineMapDetailFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapDetailFragment);
        caffeineMapDetailFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coordinate =  new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coordinate).title(location.getName()));
        LatLng currentPosition = new LatLng(location.getLatitude(),location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(currentPosition).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

}
