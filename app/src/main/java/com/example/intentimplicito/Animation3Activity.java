package com.example.intentimplicito;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class Animation3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation3);

        View v = findViewById(R.id.imageViewScaleFadeOut);
        scaleView(v, 1.5f, 10f);
    }
    private void scaleView(View v, float startScale, float endScale) {
        final Animator scaleAnimatorX = ObjectAnimator
                .ofFloat( v, "scaleX", 1,5f)
                .setDuration(1000);
        final Animator scaleAnimatorY = ObjectAnimator
                .ofFloat( v, "scaleY", 1,5f)
                .setDuration(1000);

        final AnimatorSet animatorInitialSet = new AnimatorSet();
        animatorInitialSet.playTogether(
                scaleAnimatorX,
                scaleAnimatorY
        );

        final Animator alphaAnimator = ObjectAnimator
                .ofFloat(v, View.ALPHA, 1f, 0f)
                .setDuration(5000);
        final Animator scaleAnimatorX2 = ObjectAnimator
                .ofFloat( v, "scaleX", 10f)
                .setDuration(5000);
        final Animator scaleAnimatorY2 = ObjectAnimator
                .ofFloat( v, "scaleY", 10f)
                .setDuration(5000);

        final AnimatorSet animatorFinalSet = new AnimatorSet();
        animatorInitialSet.playTogether(
                scaleAnimatorX2,
                scaleAnimatorY2,
                alphaAnimator
        );

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(
                animatorInitialSet,
                animatorFinalSet
        );
        animatorSet.start();
    }
}
