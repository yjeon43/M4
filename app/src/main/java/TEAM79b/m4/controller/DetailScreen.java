package TEAM79b.m4.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Location;
import TEAM79b.m4.model.LocationContainer;

public class DetailScreen extends AppCompatActivity implements Serializable {

//    private LocationContainer locContainer = LocationContainer.getInstance();
//    private HashMap<Location, List<Item>> model = locContainer.getLocationMap();
    private List<String> values;

    //private Location locationMain = (Location) getIntent().getSerializableExtra("locationObj");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);


        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        //values = (List<String>) model.get(key);

        LocationContainer locContainer = LocationContainer.getInstance();

        Location location = (Location) getIntent().getSerializableExtra("locationObj");

        //HashMap<Location, List<Item>> model = locContainer.getLocationMap();

        TextView detailName = (TextView) findViewById(R.id.detailName);
        detailName.setText(key);
        TextView detailLat = (TextView) findViewById(R.id.detailLat);
        detailLat.setText("Latitude: " + location.getLatitude());
        TextView detailLon = (TextView) findViewById(R.id.detailLon);
        detailLon.setText("Longitude: " + location.getLongitude());
        TextView detailAdd1 = (TextView) findViewById(R.id.detailAdd1);
        detailAdd1.setText(location.getAddress1());
        TextView detailAdd2 = (TextView) findViewById(R.id.detailAdd2);
        detailAdd2.setText(location.getCity() + ", " + location.getState() + " " + location.getZip());
        TextView detailType = (TextView) findViewById(R.id.detailType);
        detailType.setText(location.getType());
        TextView detailPhone = (TextView) findViewById(R.id.detailPhone);
        detailPhone.setText(location.getPhoneNum());
        TextView detailWeb = (TextView) findViewById(R.id.detailWeb);
        detailWeb.setText(location.getUrl());

        goToList();
        goToItemList();
    }

    private void goToList() {
        Button backToList = (Button) findViewById(R.id.backToList);
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailScreen.this, LocationListScreen.class));
            }
        });
    }

    private void goToItemList() {
        final Location location = (Location) getIntent().getSerializableExtra("locationObj");
        Button goItemList = (Button) findViewById(R.id.goItemList);
        goItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailScreen.this, ItemListScreen.class);
                intent.putExtra("locationObj", location);
                startActivity(intent);
            }
        });
    }
}
