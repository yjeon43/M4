package TEAM79b.m4.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Item;
import TEAM79b.m4.model.Location;
import TEAM79b.m4.model.LocationContainer;
import TEAM79b.m4.model.User;

public class SearchScreen extends AppCompatActivity {

    private Spinner locSpinner;
    private EditText itemEntry;
    private EditText catEntry;
    private LocationContainer locContainer = LocationContainer.getInstance();
    private HashMap<Location, List<Item>> locationKeys = locContainer.getLocationMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        itemEntry = findViewById(R.id.itemEntry);
        catEntry = findViewById(R.id.catEntry);

        Set<Location> keys = locationKeys.keySet();
        final Location[] keyListTemp = keys.toArray(new Location[keys.size()]);
        final String[] keyList = new String[keys.size() + 1];
        keyList[0] = "All Locations";
        for (int i = 1; i < keyList.length; i++) {
            keyList[i] = keyListTemp[i - 1].getName();
        }

        locSpinner = findViewById(R.id.locSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(adapter);
    }

    public void searchItem(View view) {
        String location = locSpinner.getSelectedItem().toString();
        String itemName = itemEntry.getText().toString();
        Intent intent = new Intent(SearchScreen.this, SearchListScreen.class);
        intent.putExtra("locationName", location);
        intent.putExtra("itemName", itemName);
        intent.putExtra("mode", 0);
        startActivity(intent);
    }

    public void searchCat(View view) {
        String location = locSpinner.getSelectedItem().toString();
        String catName = catEntry.getText().toString();
        Intent intent = new Intent(SearchScreen.this, SearchListScreen.class);
        intent.putExtra("locationName", location);
        intent.putExtra("catName", catName);
        intent.putExtra("mode", 1);
        startActivity(intent);
    }

    public void goToApp(View view) {
        startActivity(new Intent(SearchScreen.this, AppScreen.class));
    }
}
