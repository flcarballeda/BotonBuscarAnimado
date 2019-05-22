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
    private static final int DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editText);
        originalWidth = editText.getMinimumWidth();
        Log.d("MYAPP", Integer.toString(originalWidth));
    }

    public void buscar(View v) {
        // Tomo el objeto que necesito animar
        final EditText editText = findViewById(R.id.editText);
        // Compruebo si está expandido
        if (editText.getWidth() == originalWidth) { // Debo animar el expand
            TextView textView = findViewById(R.id.textViewLabel);
            // Tomo el campo TextView como referencia para el tamaño
            final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
            // Creo una animación de in Entero entre los valores
            ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), textView.getWidth());
            // Pongo el CallBack a la nueva clase que se crea para esta animación.
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    // En este método tomo el nuevo valor que va tomando el Entero
                    // animado
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    // Lo asigno a la Constraint que quiero animar
                    lparams.width = val;
                    // Actualizo el valor en el objeto EditText animado.
                    editText.setLayoutParams(lparams);
                }
            });
            // Establezco la duración de la animación
            anim.setDuration(DURATION);
            // Lanzo la animación.
            anim.start();
        } else {
            // Ya está expandido
            String texto = editText.getText().toString();
            if (texto.isEmpty()) { // No tengo texto, debo colapsar
                // Tomo el valor de las constraint que tiene el objeto EditText en este momento.
                final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
                // Creo una animación de un valor Entero (Integer o Int)
                ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), originalWidth);
                // Pongo el CallBack a la nueva clase que se crea para esta animación.
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        // En este método tomo el nuevo valor que va tomando el Entero
                        // animado
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        // Lo asigno a la Constraint que quiero animar
                        lparams.width = val;
                        // Actualizo el valor en el objeto EditText animado.
                        editText.setLayoutParams(lparams);
                    }
                });
                // Establezco la duración de la animación
                anim.setDuration(DURATION);
                // Lanzo la animación.
                anim.start();
            } else {
                // Tengo un texto a buscar, creo el Intent Implicito
                // para que el Teléfono utilice la aplicación que pueda
                // realizar esta funcionalidad
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                // Añado el texto a buscar al Intent
                intent.putExtra(SearchManager.QUERY, texto);
                // Compruebo que tengo alguna aplicación que puede realizar
                // Esta acción.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Lanzo el Intent para que el Teléfono realice la función solicitada.
                    startActivity(intent);
                }
            }
        }
    }
}
