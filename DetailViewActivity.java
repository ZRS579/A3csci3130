package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

/**
 * The type Detail view activity.
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, bus, busNum, add, prov;
    private Spinner spinner1, spinner2;
    /**
     * The Received person info.
     */
    Contact receivedPersonInfo;
    /**
     * The S ref.
     */
    DatabaseReference sRef = FirebaseDatabase.getInstance().getReference().child("contacts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        spinner1 = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner1);

        nameField = (EditText) findViewById(R.id.name);
        busNum = (EditText)findViewById(R.id.busNum);
        add = (EditText)findViewById(R.id.address);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            spinner1.setSelection(getIndex(spinner1, receivedPersonInfo.primaryBusiness.toString()));
            spinner2.setSelection(getIndex(spinner2, receivedPersonInfo.proTer.toString()));
            String holder = String.valueOf(receivedPersonInfo.businessNumber);
            busNum.setText(holder);
            add.setText(receivedPersonInfo.address);
        }
    }

    /**
     * Update contact.
     *
     * @param v the v
     */
    public void updateContact(View v){

        sRef.child(receivedPersonInfo.uid).child("address").setValue(add.getText().toString().trim());
        if (busNum.getText().toString().trim().length() != 9)
        {
            makeText(getApplicationContext(), "Error: Business Number must be 9 characters long", Toast.LENGTH_LONG).show();
            return;
        }
        int num = new Integer(busNum.getText().toString().trim()).intValue();
        sRef.child(receivedPersonInfo.uid).child("businessNumber").setValue(num);
        sRef.child(receivedPersonInfo.uid).child("name").setValue(nameField.getText().toString().trim());
        sRef.child(receivedPersonInfo.uid).child("primaryBusiness").setValue(String.valueOf(spinner1.getSelectedItem()));
        sRef.child(receivedPersonInfo.uid).child("proTer").setValue(String.valueOf(spinner2.getSelectedItem()));

        Toast toast = makeText(getApplicationContext(), R.string.update,Toast.LENGTH_SHORT);
        toast.show();

        finish();
    }

    /**
     * Erase contact.
     *
     * @param v the v
     */
    public void eraseContact(View v)
    {
        sRef.child(receivedPersonInfo.uid).setValue(null);
        Toast toast = makeText(getApplicationContext(), R.string.erase, Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
