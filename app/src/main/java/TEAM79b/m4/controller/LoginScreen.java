package TEAM79b.m4.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Item;
import TEAM79b.m4.model.Location;
import TEAM79b.m4.model.LocationContainer;
import TEAM79b.m4.model.Login;

public class LoginScreen extends AppCompatActivity implements Serializable {

    private EditText loginEmail;
    private EditText loginPass;
    private Button loginSubmit;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPass = (EditText) findViewById(R.id.loginPass);
        loginSubmit = (Button) findViewById(R.id.loginSubmit);

        firebaseAuth = FirebaseAuth.getInstance();

        goToWelcome();
    }

    public void loginPress(View view) {
        firebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), loginPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            String userID = user.getEmail();
                            String[] userIDArr = userID.split("\\.");
                            userID = userIDArr[0];
                            mDatabase
                                    .child("users")
                                    .child(userID)
                                    .child("locations")
                                    .getRef()
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            //@SuppressWarnings("unchecked")
                                            //Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                                String firstValue = (String) map.get("city");
//                                String secondValue = (String) map.get("name");
//                                String thirdValue = (String) map.get("state");
//                            List<Item> itemList = (List<Item>) dataSnapshot.child("item_list").getValue();
//                            GenericTypeIndicator<List<Item>> t = new GenericTypeIndicator<List<Item>>() {};
//
//                            List<Item> arr = dataSnapshot.getValue(t);
                                            //try {
                                            //Log.d("BRUH", dataSnapshot.getValue().toString());
                                            LocationContainer locContainer = LocationContainer.getInstance();
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                GenericTypeIndicator<List<Item>> t = new GenericTypeIndicator<List<Item>>() {};
                                                //List<Item> itemList = snapshot.child("item_list").getValue(t);
                                                Location loc = snapshot.getValue(Location.class);
                                                List<Item> itemList = snapshot.child("item_list").getValue(t);
                                                //List<Item> itemList = new ArrayList<Item>();
                                                //Item dog = new Item("a",loc, "ITEM_LIST","a", 3, "a");
                                                //itemList.add(dog);
                                                locContainer.addLocation(loc, itemList);
                                                Log.d("HOLAs", itemList.toString());
                                            }
                                            Log.d("HOLAs", Integer.toString(locContainer.getLocationMap().size()));
                                            Log.d("HOLAs", locContainer.getLocationMap().toString());

//                                            Map<String, Object> locMap;
//                                            locMap = (Map<String, Object>) dataSnapshot.getValue();
//                                            for (Map.Entry<String, Object> entry : locMap.entrySet()){
//                                                //Location singleLoc = (Location) entry.getValue();
//                                                Map currLoc = (Map) entry.getValue();
//                                                Integer zip = (Integer) currLoc.get("zip");
//                                                Log.d("BRUH", zip.toString());
//                                                //locContainer.addLocation((Location) singleLoc, new ArrayList<Item>());
//                                            }
                                            Log.d("BRUHs", Integer.toString(locContainer.getLocationMap().size()));
//                                            Collection<Location> locSet;
//                                            locSet = locMap.values();
//                                            for (Location l : locSet) {
//                                                locContainer.addLocation(l, new ArrayList<Item>());
//                                            }
//                                            Log.d("AMAZING", locSet.toString());
                                            // } catch (NullPointerException e) {

                                            //}
                                            //locContainer.addLocation(dataSnapshot.getValue(Location.class), dataSnapshot.child("item_list").getValue(List.class));
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {/*Do Nothing*/}
                                    });
//                            mDatabase.child("users")
//                                    .child(userID)
//                                    .child("locations")
//                                    .child(l.toString())
                            Intent intent = new Intent(LoginScreen.this, AppScreen.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

//        Login model = Login.getInstance();
//        if (model.getUserAccounts().containsKey(loginEmail.getText().toString()) && model.getUserAccounts().get(loginEmail.getText().toString()).equals(loginPass.getText().toString())) {
//            startActivity(new Intent(LoginScreen.this, AppScreen.class));
//        } else {
//            startActivity(new Intent(LoginScreen.this, BadIdScreen.class));
//        }
    }

    private void goToWelcome() {
        Button loginBack = (Button) findViewById(R.id.loginBack);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, WelcomeScreen.class));
            }
        });
    }
}
