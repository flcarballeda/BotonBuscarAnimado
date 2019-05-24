package com.example.intentimplicito;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Animation2Activity extends AppCompatActivity {

    private static final int DURATION = 1000;
    private ImageView image;
    private float rightInitialPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        image = findViewById( R.id.imageViewLion);
        ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams)image.getLayoutParams();
        rightInitialPosition = lparams.rightMargin;
        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "translationX", -600f);
        animation.setDuration(2);
        animation.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "x", -600f, 0f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(20000);
        animation.start();
    }
}
