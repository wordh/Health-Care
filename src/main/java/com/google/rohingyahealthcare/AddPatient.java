package com.google.rohingyahealthcare;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPatient extends AppCompatActivity implements View.OnClickListener {
    private Button addpatientbutton;
    private TextView name,age,gender;
    private String UniqueKey;

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

        UniqueKey=databaseReference.child("Patient").push().getKey();
        patient.setUniqueKey(UniqueKey);
        databaseReference.child("Patient").child(UniqueKey).setValue(patient);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Unique Key");
        builder.setMessage(UniqueKey);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(AddPatient.this,"Patient Added",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(AddPatient.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
