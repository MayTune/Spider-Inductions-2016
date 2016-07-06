package com.example.aishwarya.spidertask3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button sbutton;
    private EditText movieText;
    private EditText Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieText = (EditText) findViewById(R.id.movieText);
        Year = (EditText) findViewById(R.id.Year);
        sbutton = (Button) findViewById(R.id.sbutton);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MovieList.class);
                intent.putExtra("NAME",movieText.getText().toString());
                intent.putExtra("YEAR",Year.getText().toString());
                startActivity(intent);

            }
        });

    }
}



