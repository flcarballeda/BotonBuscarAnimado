package com.example.intentimplicito;

import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int originalWidth;
    private static int DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editText);
        originalWidth = editText.getMinimumWidth();
        Log.d("MYAPP", Integer.toString(originalWidth));
    }

    public void buscar(View v) {
        final EditText editText = findViewById(R.id.editText);
        if (editText.getWidth() == originalWidth) { // Debo animar el expand
            final TextView textView = findViewById(R.id.textViewLabel);
            final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
            ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), textView.getWidth());
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    lparams.width = val;
                    editText.setLayoutParams(lparams);
                }
            });
            anim.setDuration(DURATION);
            anim.start();
        } else {
            String texto = editText.getText().toString();
            if (texto.isEmpty()) {
                final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
                ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), originalWidth);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        lparams.width = val;
                        editText.setLayoutParams( lparams);
                    }
                });
                anim.setDuration(DURATION);
                anim.start();
            } else {
                Log.d("MYAPP", Integer.toString(editText.getWidth()) + " Expandido");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, texto);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        }
    }
}
