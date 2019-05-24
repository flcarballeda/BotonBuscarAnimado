package com.example.intentimplicito;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Animation2Activity extends AppCompatActivity {

    private static final int DURATION = 1000;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        image = findViewById( R.id.imageViewLion);
        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "translationX", -1000f);
        animation.setDuration(2);
        animation.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
