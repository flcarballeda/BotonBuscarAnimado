package com.example.intentimplicito;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

public class DiferentesIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diferentes_intent);
    }

    public void buscar(View v) {
        EditText editText = findViewById(R.id.editText);
        String texto = editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        // Añado el texto a buscar al Intent
        intent.putExtra(SearchManager.QUERY, texto);
        // Compruebo que tengo alguna aplicación que puede realizar
        // Esta acción.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Lanzo el Intent para que el Teléfono realice la función solicitada.
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "No se encuentra aplicación para realizar la búsqueda.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void mapas(View v) {
        EditText editText = findViewById(R.id.editText);
        String texto = editText.getText().toString();
        try {
            String query = URLEncoder.encode(texto, "utf-8");
            String url = "geo:0,0?z=16&q=" + query;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Añado el texto a buscar al Intent
            intent.setData(android.net.Uri.parse( url));
            // Compruebo que tengo alguna aplicación que puede realizar
            // Esta acción.
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Lanzo el Intent para que el Teléfono realice la función solicitada.
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "No se encuentra aplicación para realizar la búsqueda.", Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (UnsupportedEncodingException ex) {
            Log.d("MYAPP", ex.getMessage());
        }
    }

    public void mensaje(View v) {
        EditText editText = findViewById(R.id.editText);
        String texto = editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        // Añado el texto a buscar al Intent
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        // Compruebo que tengo alguna aplicación que puede realizar
        // Esta acción.
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Lanzo el Intent para que el Teléfono realice la función solicitada.
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "No se encuentra aplicación para enviar el mensaje.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void webView(View v) {
        EditText editText = findViewById(R.id.editText);
        String texto = editText.getText().toString();
        try {
            new java.net.URL( texto);
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra( WebActivity.INTENT_PARAMETER, texto);
            startActivity( intent);
        } catch (MalformedURLException ex) {
            Toast toast = Toast.makeText(this, "El Texto no es una URL Válida.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
