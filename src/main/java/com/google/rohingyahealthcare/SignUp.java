package com.google.rohingyahealthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editEmail,editPassword;
    private Button btnSignUp,btnSignIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();

        editEmail=findViewById(R.id.emailSignUp);
        editPassword=findViewById(R.id.passwordSignUp);
        btnSignIn=findViewById(R.id.signinButton);
        btnSignUp=findViewById(R.id.signupbutton);

        progressDialog = new ProgressDialog(this);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerUser();
            }
        });

    }

    private void registerUser(){
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter email",Toast.LENGTH_LONG).show();
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter password",Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Registering new Volunteer...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Volunteer Registration Success",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUp.this,SignIn.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SignUp.this,"Error Registration",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}
