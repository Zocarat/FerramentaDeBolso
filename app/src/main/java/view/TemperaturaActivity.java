package view;

import androidx.appcompat.app.AppCompatActivity;





import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import devandroid.zocarato.ferramentadebolso.MenuPrincipal;
import devandroid.zocarato.ferramentadebolso.R;
import util.UltilidadeTemperatura;

public class TemperaturaActivity extends AppCompatActivity {
    EditText editCelsus;
    EditText editFahrenheit;
    EditText editKevin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        editCelsus = findViewById(R.id.edit_celsus_xml);
        editFahrenheit = findViewById(R.id.editTextFirenait);
        editKevin = findViewById(R.id.EditTextKevin);

        ImageButton btnCalcularTemperatura = findViewById(R.id.btnCalcularTemperatura);
        btnCalcularTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTemperatura();
            }
        });

        ImageButton btnLimparTemperatura = findViewById(R.id.btnLimparTemperatura);
        btnLimparTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparTemperatura();
            }
        });

        ImageButton btnVoltarMenu = findViewById(R.id.btnVoltarMenu);
        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarMenuPrincipal();
            }
        });
    }

    private void calcularTemperatura() {
        String celsiusString = editCelsus.getText().toString().trim();
        String fahrenheitString = editFahrenheit.getText().toString().trim();
        String kelvinString = editKevin.getText().toString().trim();

        if (!TextUtils.isEmpty(celsiusString)) {
            double celsius = Double.parseDouble(celsiusString);
            double fahrenheit = UltilidadeTemperatura.celsusParaFahrenheit(celsius);
            double kelvin = UltilidadeTemperatura.celsusParaKelvin(celsius);
            editFahrenheit.setText(String.valueOf(fahrenheit));
            editKevin.setText(String.valueOf(kelvin));
        } else if (!TextUtils.isEmpty(fahrenheitString)) {
            double fahrenheit = Double.parseDouble(fahrenheitString);
            double celsius = UltilidadeTemperatura.fahrenheitParaCelsus(fahrenheit);
            double kelvin = UltilidadeTemperatura.celsusParaKelvin(celsius);
            editCelsus.setText(String.valueOf(celsius));
            editKevin.setText(String.valueOf(kelvin));
        } else if (!TextUtils.isEmpty(kelvinString)) {
            double kelvin = Double.parseDouble(kelvinString);
            double celsius = UltilidadeTemperatura.kelvinParaCelsius(kelvin);
            double fahrenheit = UltilidadeTemperatura.celsusParaFahrenheit(celsius);
            editCelsus.setText(String.valueOf(celsius));
            editFahrenheit.setText(String.valueOf(fahrenheit));
        }
    }

    private void limparTemperatura() {
        editCelsus.setText("");
        editFahrenheit.setText("");
        editKevin.setText("");
    }

    private void voltarMenuPrincipal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(TemperaturaActivity.this, MenuPrincipal.class);
                startActivity(telaPrincipal);
                finish();
            }
        }, 0);
    }
}