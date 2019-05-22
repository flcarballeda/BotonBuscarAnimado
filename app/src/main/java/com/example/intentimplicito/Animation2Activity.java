package com.example.intentimplicito;

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
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) image.getLayoutParams();
        layoutParams.l = (-1 * layoutParams.width);
        image.setLayoutParams( layoutParams);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
