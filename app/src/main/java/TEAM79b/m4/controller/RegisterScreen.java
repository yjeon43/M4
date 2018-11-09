package TEAM79b.m4.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import TEAM79b.m4.R;
import TEAM79b.m4.model.Item;
import TEAM79b.m4.model.Location;
import TEAM79b.m4.model.LocationContainer;
import TEAM79b.m4.model.Login;
import TEAM79b.m4.model.User;

public class RegisterScreen extends AppCompatActivity {

    private EditText regEmail;
    private EditText regPass;
    private Button regSubmit;
    private Spinner roleSpinner;

    private User user;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    private final Activity a = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        regEmail = (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        regSubmit = (Button) findViewById(R.id.regSubmit);
        roleSpinner = (Spinner) findViewById(R.id.roleSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, User.rolesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        firebaseAuth = FirebaseAuth.getInstance();

        goToWelcome();
    }


    private void goToWelcome() {
        Button regBack = (Button) findViewById(R.id.regBack);
        regBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this, WelcomeScreen.class));
            }
        });
    }

    public void regPress(View view) {
        firebaseAuth.createUserWithEmailAndPassword(regEmail.getText().toString(), regPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            LocationContainer locContainer = LocationContainer.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            String[] userIDarr = regEmail.getText().toString().split("\\.");
                            mDatabase.child("users").child(userIDarr[0]).setValue(user);
                            mDatabase.child("users").child(userIDarr[0]).child("role").setValue(roleSpinner.getSelectedItem().toString());

                            //FirebaseUser user = (FirebaseUser) getIntent().getSerializableExtra("user");

                            try {
                                InputStream locationStream = getResources().openRawResource(R.raw.locationdata);
                                BufferedReader locationStreamBuffer = new BufferedReader(new InputStreamReader(locationStream, StandardCharsets.UTF_8));

                                String line;
                                locationStreamBuffer.readLine(); //get rid of header line
                                while ((line = locationStreamBuffer.readLine()) != null) {
                                    Log.d("REGISTRATION_SCREEN", line);
                                    String[] tokens = line.split(",");
                                    //int id = Integer.parseInt(tokens[0]);
                                    //List<String> locationDataTemp = Arrays.asList(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10]);
                                    Location keyEntry = new Location(tokens[1], Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]), tokens[4], tokens[5], tokens[6], Integer.parseInt(tokens[7]), tokens[8], tokens[9], tokens[10]);
                                    //Item dog = new Item("a",keyEntry, "a","a", 3, "a");
                                    ArrayList<Item> valueEntry = new ArrayList<>();
                                    Item tempItem = new Item("NOW",keyEntry, "ITEM_LIST","Format of the ITEM_LIST", 0, "ITEM_LIST");
                                    //valueEntry.add(tempItem);
                                    //valueEntry.add(dog);
                                    locContainer.addLocation(keyEntry, valueEntry);
                                }
                                locationStreamBuffer.close();
                            } catch (IOException e) {
                                Log.e("REGISTRATION_SCREEN", "error reading assets", e);
                            }
                            String userID = userIDarr[0];
                            String[] userIDArr = userID.split("\\.");
                            userID = userIDArr[0];
                            Set<Location> locSet = locContainer.getLocationMap().keySet();
                            for (Location l : locSet) {
                                mDatabase.child("users").child(userID).child("locations").child(l.toString()).setValue(l);
                                List<Item> tempItemList = locContainer.getLocationMap().get(l);
                                tempItemList.add(new Item("NOW",l, "ITEM_LIST","Format of the ITEM_LIST", 0, "ITEM_LIST"));
                                mDatabase.child("users").child(userID).child("locations").child(l.toString()).child("item_list").setValue(tempItemList);
                            }

                            startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }

//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        Toast.makeText(a, "success", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(a, "failure", Toast.LENGTH_LONG).show();
//                    }
//                });
        });
    }
}
