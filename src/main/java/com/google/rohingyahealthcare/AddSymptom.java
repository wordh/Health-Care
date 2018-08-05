package com.google.rohingyahealthcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSymptom extends AppCompatActivity implements View.OnClickListener{
    private String others;


    private DatabaseReference databaseReference;



    String uid ;

    private CheckBox gastricCheckBox,upperAbdomenPainCheckBox,LowerAbdomenPainCheckBox,headacheCheckBox,aenmiaCheckBox,chestPainCheckBox;
    private Button Submit;
    private TextView othersTextView;

    private symptoms sym= new symptoms();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptom);

        Intent intent = getIntent();
        uid= intent.getExtras().getString("uid");

        gastricCheckBox=(CheckBox)findViewById(R.id.gastricCheckBox);
        upperAbdomenPainCheckBox=(CheckBox)findViewById(R.id.painUpperAbdomenCheckBox);
        LowerAbdomenPainCheckBox=(CheckBox)findViewById(R.id.painLowerAbdomenCheckBox);
        headacheCheckBox=(CheckBox)findViewById(R.id.headacheCheckBox);
        aenmiaCheckBox=(CheckBox)findViewById(R.id.AnemiaCheckBox);
        chestPainCheckBox=(CheckBox)findViewById(R.id.chestPainCheckBox);
        othersTextView=(TextView)findViewById(R.id.otherTextBox);
        Submit=(Button)findViewById(R.id.symptomSubmitButton);


        gastricCheckBox.setOnClickListener(this);
        upperAbdomenPainCheckBox.setOnClickListener(this);
        LowerAbdomenPainCheckBox.setOnClickListener(this);
        headacheCheckBox.setOnClickListener(this);
        aenmiaCheckBox.setOnClickListener(this);
        chestPainCheckBox.setOnClickListener(this);
        Submit.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View view) {

        if(view ==gastricCheckBox){
            if(gastricCheckBox.isChecked()){
                sym.setGastric(true);
            }
        }
        if(view ==LowerAbdomenPainCheckBox){
            if(LowerAbdomenPainCheckBox.isChecked()){
                sym.setPain_lower_abdomen(true);
            }
        }

        if(view ==upperAbdomenPainCheckBox){
            if(upperAbdomenPainCheckBox.isChecked()){
                sym.setPain_upper_abdomen(true);
            }
        }

        if(view ==headacheCheckBox){
            if(headacheCheckBox.isChecked()){
                sym.setHeadache(true);
            }
        }

        if(view ==chestPainCheckBox){
            if(chestPainCheckBox.isChecked()){
                sym.setChest_pain(true);
            }
        }

        if(view==aenmiaCheckBox){
            if(aenmiaCheckBox.isChecked()) {
                sym.setAnemia(true);
            }
        }

        if(view == Submit){
            others = othersTextView.getText().toString().trim();
            sym.setOthers(others);
            databaseReference.child("Patient").child(uid).child("Symptoms").setValue(sym);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Patient Symptom Added");
            builder.setMessage("Successfull");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(AddSymptom.this,"Patient Symptoms Added",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(AddSymptom.this, ReviewPatient.class);
                    startActivity(intent);
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
