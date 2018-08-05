package com.google.rohingyahealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientProfile extends AppCompatActivity implements View.OnClickListener {

    private Button callDoctor, PatientHistory, AddSymptom, sendImage;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Intent intent = getIntent();
        uid = intent.getStringExtra("UID");

        callDoctor =(Button)findViewById(R.id.calldoctorButton);
        PatientHistory=(Button)findViewById(R.id.patientHistoryButton);
        AddSymptom=(Button)findViewById(R.id.symptomButtom);
        sendImage=(Button)findViewById(R.id.SendImageButton);

        callDoctor.setOnClickListener(this);
        PatientHistory.setOnClickListener(this);
        AddSymptom.setOnClickListener(this);
        sendImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==callDoctor){
            Intent intent=new Intent(PatientProfile.this, CallDoctor.class);
            startActivity(intent);
        }

        if(view==AddSymptom){
            Intent intent=new Intent(PatientProfile.this, AddSymptom.class);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }

        if(view==sendImage){
            Toast.makeText(PatientProfile.this,"This Feature will be added Soon",Toast.LENGTH_LONG).show();
        }

        if(view==PatientHistory){
            Intent intent=new Intent(PatientProfile.this, PatientHistory.class);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }

    }
}
