package com.example.android.xpenseauditor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class ProfileManagementActivity extends AppCompatActivity {
    private TextView NameView,EmailView,PhnView,AddressView;
    private Button addTran;
    private ListView catView;
    private ArrayList<String> Catg=new ArrayList<>();
    private Firebase mRootRef;
    private Firebase RefUid;
    private Firebase RefName,RefEmail,RefPhnnum;
    private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
   /* private DatabaseReference mRootRef;
    private DatabaseReference RefUid;
    private DatabaseReference RefName,RefEmail,RefPhnnum;
    private DatabaseReference RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        //addTran = (Button) findViewById(R.id.addTran);
        NameView = (TextView) findViewById(R.id.getName);
        EmailView = (TextView) findViewById(R.id.getMail);
        PhnView = (TextView) findViewById(R.id.getPhnnum);

        //catView = (ListView) findViewById((R.id.Cat));
        //final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Catg);
        //catView.setAdapter(arrayAdapter);
        //mRootRef= FirebaseDatabase.getInstance().getReference();
        mRootRef=new Firebase("https://expense-2a69a.firebaseio.com/");

        mRootRef.keepSynced(true);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String Uid=auth.getUid();
        RefUid= mRootRef.child(Uid);
        RefName = RefUid.child("Name");
        RefEmail=RefUid.child("Email");
        RefPhnnum=RefUid.child("Phone Number");
        RefCat=RefUid.child("Categories");


        /*addTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileManagementActivity.this, Transac.class));
            }
        });*/

        RefName.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot DS){
                String n=DS.getValue(String.class);
                NameView.setText(n);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        RefEmail.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot DS){
                String n=DS.getValue().toString().trim();

                EmailView.setText(n);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefPhnnum.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot DS){
                String n=DS.getValue().toString().trim();

                PhnView.setText(n);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

/*

        RefCat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value= dataSnapshot.getKey().trim();
                Catg.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


*/






       /* Toast.makeText(ProfileManagement.this,"yo",Toast.LENGTH_LONG).show();
        RefName.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot DS) {
                String n=DS.getValue().toString().trim();
                Toast.makeText(ProfileManagement.this,n,Toast.LENGTH_LONG).show();
                NameView.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        RefEmail.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot DS) {
                String n=DS.getValue().toString().trim();
                EmailView.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        RefPhnnum.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot DS) {
                String n=DS.getValue().toString().trim();
                PhnView.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        RefCat.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                String value= dataSnapshot.getKey().trim();
                Catg.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/



    }




}
