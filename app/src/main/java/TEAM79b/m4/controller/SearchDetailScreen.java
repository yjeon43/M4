package TEAM79b.m4.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Item;
import TEAM79b.m4.model.Location;

public class SearchDetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail_screen);

        Item item = (Item) getIntent().getSerializableExtra("itemObj");
        Location location = (Location) getIntent().getSerializableExtra("locationObj");

        TextView itemTimeStamp = (TextView) findViewById(R.id.itemTimeStamp);
        itemTimeStamp.setText(item.getTimeStamp());
        TextView itemSDesc = (TextView) findViewById(R.id.itemSDesc);
        itemSDesc.setText(item.getShortDesc());
        TextView itemLDesc = (TextView) findViewById(R.id.itemLDesc);
        itemLDesc.setText(item.getLongDesc());
        TextView itemVal = (TextView) findViewById(R.id.itemVal);
        itemVal.setText("$ " + item.getValue());
        TextView itemCat = (TextView) findViewById(R.id.itemCat);
        itemCat.setText(item.getCategory());
        TextView itemLoc = (TextView) findViewById(R.id.itemLoc);
        itemLoc.setText("Location: " + location.getName());
    }

    public void backToList(View view) {
        String locationName = getIntent().getStringExtra("locationName");
        String itemName = getIntent().getStringExtra("itemName");
        String catName = getIntent().getStringExtra("catName");
        int mode = getIntent().getIntExtra("mode", 0);
        Intent intent = new Intent(SearchDetailScreen.this, SearchListScreen.class);
        intent.putExtra("locationName", locationName);
        intent.putExtra("itemName", itemName);
        intent.putExtra("catName", catName);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}
