package com.google.rohingyahealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPatient extends AppCompatActivity implements View.OnClickListener {
    private Button addpatientbutton;
    private TextView name,age,gender;

    //firebase database reference
    private DatabaseReference databaseReference;

    Patient patient = new Patient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        addpatientbutton=(Button) findViewById(R.id.addthepatient);
        name=(TextView)findViewById(R.id.patientName);
        age=(TextView)findViewById(R.id.patientAge);
        gender=(TextView)findViewById(R.id.patientGender);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        addpatientbutton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == addpatientbutton){
            addPatient();
        }
    }

    private void addPatient(){
        String NAME=name.getText().toString().trim();
        String AGE=age.getText().toString().trim();
        String GENDER=gender.getText().toString().trim();

        patient.setAge(AGE);
        patient.setGender(GENDER);
        patient.setName(NAME);

        databaseReference.child("Patient").setValue(patient);
    }
}
