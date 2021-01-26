package com.myjre.sqlitelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends Activity implements View.OnClickListener {

    Button addtodobtn;
    EditText subjectEditText;
    EditText descEditText;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("Add Record");
        setContentView(R.layout.activity_add_country);

        subjectEditText=findViewById(R.id.subject_edittext);
        descEditText=findViewById(R.id.description_edittext);
        addtodobtn=findViewById(R.id.add_record);

        dbManager=new DBManager(this);
        dbManager.open();
        addtodobtn.setOnClickListener(this);





    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_record:
                final String name=subjectEditText.getText().toString();
                final String desc=descEditText.getText().toString();

                dbManager.insert(name,desc);

                Intent main=new Intent(AddCountryActivity.this,CountryListActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);break;
        }

    }
}