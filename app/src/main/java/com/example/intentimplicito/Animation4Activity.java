package com.example.intentimplicito;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class Animation4Activity extends AppCompatActivity {

    private boolean expand = false;
    private RelativeLayout menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation4);

        menu = findViewById(R.id.menu);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( expand) {
                    collapseMenu( 1000);
                } else {
                    expandMenu();
                }
                expand = !expand;
            }
        });
        collapseMenu(1);
    }

    private void expandMenu() {
        final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) menu.getLayoutParams();

        // Creo una animación de un valor Entero (Integer o Int)
        ValueAnimator scaleAnimator = ValueAnimator.ofInt(40, 400);
        // Pongo el CallBack a la nueva clase que se crea para esta animación.
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // En este método tomo el nuevo valor que va tomando el Entero
                // animado
                int val = (Integer) valueAnimator.getAnimatedValue();
                // Lo asigno a la Constraint que quiero animar
                lparams.width = val;
                lparams.height = val;
                // Actualizo el valor en el objeto EditText animado.
                menu.setLayoutParams(lparams);
            }
        });
        scaleAnimator.setDuration( 1000);

        ObjectAnimator translateX = ObjectAnimator.ofFloat(menu, "translationX", -6f, 170f).setDuration( 1000);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(menu, "translationY", -6f, 170f).setDuration( 1000);

        final Animator alphaAnimator = ObjectAnimator
                .ofFloat( menu, View.ALPHA, 0.5f, 1f)
                .setDuration(1000);

        final AnimatorSet animatorFinalSet = new AnimatorSet();
        animatorFinalSet.playTogether(
                scaleAnimator,
                translateX,
                translateY,
                alphaAnimator
        );
        animatorFinalSet.start();
    }

    private void collapseMenu(int duration) {
        final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) menu.getLayoutParams();

        // Creo una animación de un valor Entero (Integer o Int)
        ValueAnimator scaleAnimator = ValueAnimator.ofInt(400, 40);
        // Pongo el CallBack a la nueva clase que se crea para esta animación.
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // En este método tomo el nuevo valor que va tomando el Entero
                // animado
                int val = (Integer) valueAnimator.getAnimatedValue();
                // Lo asigno a la Constraint que quiero animar
                lparams.width = val;
                lparams.height = val;
                // Actualizo el valor en el objeto EditText animado.
                menu.setLayoutParams(lparams);
            }
        });
        scaleAnimator.setDuration( duration);

        ObjectAnimator translateX = ObjectAnimator.ofFloat(menu, "translationX", 170f, -6f).setDuration( duration);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(menu, "translationY", 170f, -6f).setDuration( duration);

        final Animator alphaAnimator = ObjectAnimator
                .ofFloat( menu, View.ALPHA, 1f, 0.5f)
                .setDuration(duration);

        final AnimatorSet animatorFinalSet = new AnimatorSet();
        animatorFinalSet.playTogether(
                scaleAnimator,
                translateX,
                translateY,
                alphaAnimator
        );
        animatorFinalSet.start();
    }

    public void onClick(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
