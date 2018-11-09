package TEAM79b.m4.model;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private String timeStamp;
    private Location location;
    private String shortDesc;
    private String longDesc;
    private float value;
    private String category;

    public Item() {

    }

    public Item(String timeStamp, Location location, String shortDesc, String longDesc, float value, String category) {
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.value = value;
        this.category = category;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Location getLocation() {
        return location;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public float getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(item.value, value) == 0 &&
                Objects.equals(timeStamp, item.timeStamp) &&
                Objects.equals(location, item.location) &&
                Objects.equals(shortDesc, item.shortDesc) &&
                Objects.equals(longDesc, item.longDesc) &&
                Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timeStamp, location, shortDesc, longDesc, value, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "shortDesc='" + shortDesc + '\'' +
                '}';
    }
}
