package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.widget.Toast.makeText;

/**
 * The type Create contact acitivity.
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText busNum, name, add;
    private Spinner spinner1, spinner2;
    private String prov = "empty", bus = "empty";
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton2);
        busNum = (EditText) findViewById(R.id.bus);
        name = (EditText) findViewById(R.id.name);
        add = (EditText)findViewById(R.id.address);

    }

    public void submitInfoButton(View view) {
        String personID = appState.firebaseReference.push().getKey();
        spinner1 = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner1);

        bus = String.valueOf(spinner1.getSelectedItem());
        prov = String.valueOf(spinner2.getSelectedItem());
        if (busNum.getText().toString().trim().length() != 9)
        {
            makeText(getApplicationContext(), "Error: Business Number must be 9 characters long", Toast.LENGTH_LONG).show();
            return;
        }
        int num = new Integer(busNum.getText().toString().trim()).intValue();


        Contact person = new Contact(personID, num, name.getText().toString().trim(), bus, prov, add.getText().toString().trim());

        appState.firebaseReference.child(personID).setValue(person).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast toast = makeText(getApplicationContext(), R.string.Business,Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
                else
                {
                    Toast toast = makeText(getApplicationContext(), R.string.creat,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
