package com.marcosroman.banco_utn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String nombre_persona_confirmacion;
    private String apellido_persona_confirmacion;

    private EditText text_nombre;
    private EditText text_apellido;
    private Spinner currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_nombre = findViewById(R.id.nombre);
        text_apellido = findViewById(R.id.apellido);
        currency = findViewById(R.id.spinnerMoneda);

        Bundle extras = getIntent().getExtras();

        if( extras != null ){
            nombre_persona_confirmacion = extras.getString("confirmacion_nombre_persona");
            apellido_persona_confirmacion = extras.getString("confirmacion_apellido_persona");

            text_nombre.setText(nombre_persona_confirmacion);
            text_apellido.setText(apellido_persona_confirmacion);

            AlertDialog.Builder popup_confirmacion_plazoFijo = new AlertDialog.Builder(MainActivity.this);
            popup_confirmacion_plazoFijo.setMessage("Tu plazo fijo fue confirmado!")
                    .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog titulo = popup_confirmacion_plazoFijo.create();
            titulo.setTitle("Felicitaciones " + nombre_persona_confirmacion + " " + apellido_persona_confirmacion + "!");
            titulo.show();
        }
    }

    public void simularPlazoFijo(View v){
        String nombreText = text_nombre.getText().toString();
        String apellidoText = text_apellido.getText().toString();
        String currencyText = currency.getSelectedItem().toString();

        Intent intent = new Intent( MainActivity.this, Main2Activity.class );
        intent.putExtra("currency_plazoFijo", currencyText);
        intent.putExtra("nombre_persona", nombreText);
        intent.putExtra("apellido_persona", apellidoText);

        startActivity(intent);
    }

}