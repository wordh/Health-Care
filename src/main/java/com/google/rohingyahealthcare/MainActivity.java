package com.google.rohingyahealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogout;
    private Button addPatientbtn;
    private Button reviewPatientbtn;
    private TextView textViewUserEmail;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonLogout = (Button) findViewById(R.id.logout);
        addPatientbtn=(Button) findViewById(R.id.addPatient);
        reviewPatientbtn=(Button) findViewById(R.id.reviewPatient);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, SignIn.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.nameofvolunteer);

        textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        addPatientbtn.setOnClickListener(this);
        reviewPatientbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, SignIn.class));
        }

        if (view ==addPatientbtn){
            startActivity(new Intent(this, AddPatient.class));
        }

        if(view==reviewPatientbtn){
            startActivity(new Intent(this, ReviewPatient.class));
        }

    }
}
