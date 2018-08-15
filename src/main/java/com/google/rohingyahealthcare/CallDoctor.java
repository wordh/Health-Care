package com.google.rohingyahealthcare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CallDoctor extends AppCompatActivity implements View.OnClickListener {

    private Button callKimia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doctor);

        callKimia = (Button)findViewById(R.id.CallKimiaButton);
        callKimia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==callKimia){
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "01796588922", null)));
        }
    }
}
