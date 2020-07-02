package com.example.route;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer son;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.buttonOK);
        son = MediaPlayer.create(MainActivity.this, R.raw.ferrari_menu);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Route.class);
                ContextCompat.startActivity(view.getContext(), intent, null);

            }
        });
    }

    @Override
    protected void onResume() {
        son.start();
        son.setLooping(true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        son.pause();
        super.onPause();
    }
}
