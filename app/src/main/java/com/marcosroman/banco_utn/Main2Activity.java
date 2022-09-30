package com.marcosroman.banco_utn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView txtDias;
    private Integer dias;
    private EditText editTextCapital;
    private TextView textCapital;
    private EditText editTextTasaNominalAnual;
    private EditText editTextTasaEfectivaAnual;
    private TextView interesesGanados;
    private TextView montoTotal;
    private TextView montoTotalAnual;
    private SeekBar seek;
    private Button simularPlazoFijoButton;

    private Boolean hay_capital_a_invertir = false;
    private Boolean hay_tasaNominal_anual = false;
    private Boolean hay_tasaEfectiva_anual = false;

    private TextView plazo_fijo_en_currency;
    private String currency_value;
    private String nombre_persona;
    private String apellido_persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        nombre_persona = extras.getString("nombre_persona");
        apellido_persona = extras.getString("apellido_persona");
        currency_value = extras.getString("currency_plazoFijo");

        plazo_fijo_en_currency = findViewById(R.id.editTextTipoMoneda);
        plazo_fijo_en_currency.setText("Simulador Plazo Fijo en " + currency_value);

        txtDias = findViewById(R.id.textView6);
        seek = findViewById(R.id.seekBar);

        editTextCapital = findViewById(R.id.editTextCapital_invertir);
        editTextTasaNominalAnual = findViewById(R.id.editTasaNominalAnual);
        editTextTasaEfectivaAnual = findViewById(R.id.editTasaEfectivaAnual);

        textCapital = findViewById(R.id.textViewCapital_invertir);

        interesesGanados = findViewById(R.id.interesesGanados);

        montoTotal = findViewById(R.id.montoTotal);
        montoTotalAnual = findViewById(R.id.montoTotalAnual);


        /** Le seteo 330 como maximo de dias y luego le voy sumando 30 en el onProgressChanged
         para que muestre como minimo 30 dias (1 mes) y maximo 360 dias (12 meses) **/
        seek.setMax(330);
        dias = 30;
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int valor = i + 30;
                dias = valor;
                txtDias.setText(String.valueOf(valor) + " días");
            }


            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {

                if(hay_tasaNominal_anual && hay_tasaEfectiva_anual && hay_capital_a_invertir){
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(true);
                }
            }
        });

        /** Tasa Nominal Anual **/
        editTextTasaNominalAnual.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //System.out.println(s.toString() + " " + start + " " + count + " " + after);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length() > 0){
                    hay_tasaNominal_anual = true;
                } else {
                    hay_tasaNominal_anual = false;
                }
                if(hay_tasaNominal_anual && hay_tasaEfectiva_anual && hay_capital_a_invertir){
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(true);
                } else {
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(false);
                }

            }

            public void afterTextChanged(Editable s) {
                //System.out.println(s.toString());
            }
        });

        /** Tasa Efectiva Anual **/
        editTextTasaEfectivaAnual.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //System.out.println(s.toString() + " " + start + " " + count + " " + after);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length() > 0){
                    hay_tasaEfectiva_anual = true;
                } else {
                    hay_tasaEfectiva_anual = false;
                }
                if(hay_tasaNominal_anual && hay_tasaEfectiva_anual && hay_capital_a_invertir){
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(true);
                } else {
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(false);
                }

            }

            public void afterTextChanged(Editable s) {
                //System.out.println(s.toString());
            }
        });

        /** Capital a invertir **/
        editTextCapital.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //System.out.println(s.toString() + " " + start + " " + count + " " + after);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length() > 0){
                    hay_capital_a_invertir = true;
                } else {
                    hay_capital_a_invertir = false;
                }
                if(hay_tasaNominal_anual && hay_tasaEfectiva_anual && hay_capital_a_invertir){
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(true);
                } else {
                    simularPlazoFijoButton = findViewById(R.id.simularPlazoFijo);
                    simularPlazoFijoButton.setEnabled(false);
                }

            }

            public void afterTextChanged(Editable s) {
                //System.out.println(s.toString());
            }
        });

    }

    public void calcularPlazoFijo(View view){
        float capital, intereses, montoT, montoTotalA;
        capital = Float.parseFloat(editTextCapital.getText().toString());
        intereses = Float.parseFloat(editTextCapital.getText().toString()) * (((Float.parseFloat(editTextTasaNominalAnual.getText().toString()) / 100) * dias ) / 365 );
        montoT = capital + intereses;
        montoTotalA = (intereses*12)+capital;
        textCapital.setText("Capital: $ " + capital);
        interesesGanados.setText("Intereses ganados: $ " + intereses );
        montoTotal.setText("Monto Total: $ " + montoT );
        montoTotalAnual.setText("Monto Total Anual: $ " + montoTotalA );
    }

    public void confirmarSimulacion(View view){
        Intent intent = new Intent( Main2Activity.this, MainActivity.class );
        intent.putExtra("confirmacion_nombre_persona", nombre_persona);
        intent.putExtra("confirmacion_apellido_persona", apellido_persona);
        intent.putExtra("confirmacion_currency", currency_value);
        startActivity(intent);
    }

    public void cancelarSimulacion(View view){
        Intent intent = new Intent( Main2Activity.this, MainActivity.class );
        startActivity(intent);
    }
}