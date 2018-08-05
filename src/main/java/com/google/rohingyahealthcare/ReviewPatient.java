package com.google.rohingyahealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;

public class ReviewPatient extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private TextView mTempView;
    private Button searchButton;
    private EditText findPatient;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_patient);
        mTempView=(TextView) findViewById(R.id.RivewPatientText);
        searchButton=(Button)findViewById(R.id.SearchButton);
        findPatient=(EditText)findViewById(R.id.FindText);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        searchButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == searchButton){
            uid= findPatient.getText().toString().trim();

            myRef.child("Patient").child(uid).child("uniqueKey").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value= dataSnapshot.getValue(String.class);
                    //Toast.makeText(ReviewPatient.this,uid + value,Toast.LENGTH_LONG).show();
                    if (value.equals(uid)){
                        Toast.makeText(ReviewPatient.this,"Value matched",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ReviewPatient.this,PatientProfile.class);
                        i.putExtra("UID",value.toString());
                        ReviewPatient.this.startActivity(i);
                    }
                    else Toast.makeText(ReviewPatient.this,"Patient not Found",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}