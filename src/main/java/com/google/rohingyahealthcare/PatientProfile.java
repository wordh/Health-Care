package com.google.rohingyahealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientProfile extends AppCompatActivity implements View.OnClickListener {

    private Button callDoctor, PatientHistory, AddSymptom, sendImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

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

    }
}
