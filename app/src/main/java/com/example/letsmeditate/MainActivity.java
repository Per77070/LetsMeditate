package com.example.letsmeditate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void button1(View view) {
        Intent intent1 = new Intent(MainActivity.this,MountainsActivity.class);

        startActivity(intent1);

    }

    public void button2(View view) {
        Intent intent2 = new Intent(MainActivity.this,TreeActivity.class);

        startActivity(intent2);


    }

    public void button3(View view) {
        Intent intent3 = new Intent(MainActivity.this,SeaActivity.class);

        startActivity(intent3);


    }


}
