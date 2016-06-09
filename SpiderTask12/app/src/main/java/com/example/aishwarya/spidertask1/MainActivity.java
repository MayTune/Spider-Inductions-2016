package com.example.aishwarya.spidertask1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence>adapter;
    private CheckBox n1;
    private CheckBox n2;
    private CheckBox n3;
    private CheckBox n4;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.dept);
        adapter=(ArrayAdapter.createFromResource(this,R.array.Department,android.R.layout.simple_spinner_item));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addListenerOnButton();

    }

   public void addListenerOnButton(){
        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName, sRoll;
                int n = 0;
                EditText name = (EditText) findViewById(R.id.Name);
                EditText roll = (EditText) findViewById(R.id.Roll);
                sName = name.getText().toString();
                sRoll = roll.getText().toString();
                n1 = (CheckBox) findViewById(R.id.n1);
                n2 = (CheckBox) findViewById(R.id.n2);
                n3 = (CheckBox) findViewById(R.id.n3);
                n4 = (CheckBox) findViewById(R.id.n4);
                //checking if Name field is empty
                if (sName.matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a Name", Toast.LENGTH_SHORT).show();
                    n = 1;
                    return;
                }
                //Checking if Roll no field is empty
                if (sRoll.matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a Roll number", Toast.LENGTH_SHORT).show();
                    n = 1;
                    return;
                }
                //Checking if no profile is selected
                if ((n1.isChecked() == false) && (n2.isChecked() == false) && (n3.isChecked() == false) && (n4.isChecked() == false)) {
                    Toast.makeText(MainActivity.this, "Profile not selected", Toast.LENGTH_LONG).show();
                    n = 1;
                    return;
                }
                if (n != 1) {
                    //to open new activity 'Next'
                    Intent pass =new Intent(MainActivity.this,Next.class);

                    //to pass Name
                    EditText Name=(EditText)findViewById(R.id.Name);
                    String str=Name.getText().toString();
                    pass.putExtra("Name",str);

                    startActivity(pass);

                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

