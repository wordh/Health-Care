package com.google.rohingyahealthcare;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class PatientHistory extends AppCompatActivity {

    private DatabaseReference databaseReference;
    String uid ;

    private ListView listView;

    private ArrayList<String> arrayList= new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        Intent intent = getIntent();
        uid= intent.getExtras().getString("uid");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        listView=(ListView)findViewById(R.id.historyView);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        databaseReference.child("Patient").child(uid).child("History").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String date = dataSnapshot.getKey();
                String key = map.toString();
                arrayList.add(date);
                arrayList.add(key);
                adapter.notifyDataSetChanged();
                //for(DataSnapshot ds:dataSnapshot.getChildren()){
                    //String key = ds.getKey();
                    //String medicine = ds.child("Medicine").getValue(String.class);
                    //String practice = ds.child("Practice").getValue(String.class);

                    //arrayList.add(key);
                    //arrayList.add(medicine);
                    //arrayList.add(practice);
                    //adapter.notifyDataSetChanged();
                //}

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
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
