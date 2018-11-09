/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mapdemo;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class BasicMapDemoActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_demo);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(33.75416, 	-84.37742)).title("AFD Station 4").snippet("(404) 555 - 3456"));
        map.addMarker(new MarkerOptions().position(new LatLng(33.73182, -84.43971)).title("BOYS & GILRS CLUB W.W. WOOLFOLK").snippet("(404) 555 - 1234"));
        map.addMarker(new MarkerOptions().position(new LatLng(33.70866, -84.41853)).title("PATHWAY UPPER ROOM CHRISTIAN MINISTRIES").snippet("(404) 555 - 5432"));
        map.addMarker(new MarkerOptions().position(new LatLng(33.80129, -84.25537)).title("PAVILION OF HOPE INC").snippet("(404) 555 - 8765"));
        map.addMarker(new MarkerOptions().position(new LatLng(33.71747, -84.2521)).title("D&D CONVENIENCE STORE").snippet("(404) 555 - 9876"));
        map.addMarker(new MarkerOptions().position(new LatLng(33.96921, -84.3688)).title("KEEP NORTH FULTON BEAUTIFUL").snippet("(770) 555 - 7321"));
    }


}
