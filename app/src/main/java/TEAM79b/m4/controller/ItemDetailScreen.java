package TEAM79b.m4.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Item;
import TEAM79b.m4.model.Location;

public class ItemDetailScreen extends AppCompatActivity implements Serializable {

    private TextView itemTimeStamp;
    private TextView itemSDesc;
    private TextView itemLDesc;
    private TextView itemVal;
    private TextView itemCat;
    private TextView itemLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_screen);

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

        goToItemList();
    }

    private void goToItemList() {
        Button backToItemList = (Button) findViewById(R.id.backToItemList);
        backToItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location location = (Location)  getIntent().getSerializableExtra("locationObj");
                Intent intent = new Intent(ItemDetailScreen.this, ItemListScreen.class);
                intent.putExtra("locationObj", location);
                startActivity(intent);
            }
        });
    }
}
