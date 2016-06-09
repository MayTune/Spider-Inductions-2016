package com.example.aishwarya.spidertask1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Timestamp;


public class Next extends ActionBarActivity {
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);
        Intent i=getIntent();
        //getting Name from previous activity
        String str=i.getExtras().getString("Name", "");
        EditText et=(EditText)findViewById(R.id.et);
        et.setText("Thank you "+ str+ " for your response");

        addListenerOnButton();
        //for displaying current timestamp
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        EditText timestamp=(EditText)findViewById(R.id.timestamp);
        timestamp.setText(ts);
    }
    //for going back to previous activity on click of a button
    public void addListenerOnButton() {
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backs =new Intent(Next.this,MainActivity.class);
                startActivity(backs);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_next, menu);
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
