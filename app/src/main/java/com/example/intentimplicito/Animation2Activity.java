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
        // Animo el elemento
        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "translationX", (-1f *image.getDrawable().getIntrinsicWidth()));
        animation.setDuration(1);
        animation.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "x", (-1f *image.getDrawable().getIntrinsicWidth()), 0f);
        animation.setStartDelay( 1000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(2000);
        animation.start();
    }
}
