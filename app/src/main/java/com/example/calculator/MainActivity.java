package com.example.calculator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SoundPool sp;
    private int soundKapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundKapla = sp.load(this, R.raw.kaplya, 1);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.начать) {
            sp.play(soundKapla, 1, 1, 0, 0, 1);
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            finish();
        }
    }
}