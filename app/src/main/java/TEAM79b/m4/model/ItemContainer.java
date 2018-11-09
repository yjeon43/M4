package TEAM79b.m4.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ItemContainer extends Application {
    private List<Item> items = new ArrayList<>();
    private Location loc = null;
    public List<Item> getList() {
        return this.items;
    }
    public Location getLoc() {return this.loc; }
}
