package com.example.aishwarya.spidertask1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {




    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button)findViewById(R.id.myButton);
        TextView myText = (TextView) findViewById(R.id.myText);
        myText.setText("No of clicks=" + String.valueOf(counter));
        myButton.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {

                        counter = counter + 1;
                        TextView myText = (TextView) findViewById(R.id.myText);
                        if (counter == 1) {
                            myText.setText("No of clicks=" + String.valueOf(counter));
                        } else {
                            myText.setText("No of clicks=" + String.valueOf(counter));
                        }

                    }
                }

        );
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
