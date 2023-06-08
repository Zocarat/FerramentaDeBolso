package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import devandroid.zocarato.ferramentadebolso.R;
import util.UltilidadeTemperatura;
import util.UtilidadesPressao;

public class TemperaturaActivity extends AppCompatActivity {

    EditText editCelsus;
    EditText editFahrenheit;

    ImageButton btnLimparTemperatura;
    ImageButton btnCacularTemperatura;
    ImageButton btnMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);


        editCelsus = findViewById(R.id.edit_celsus_xml);
        editFahrenheit = findViewById(R.id.edit_Firen_xml);

        btnLimparTemperatura = findViewById(R.id.btnLimparTemperatura);
        btnCacularTemperatura = findViewById(R.id.btnCalcularTemperatura);
        btnMenuPrincipal = findViewById(R.id.btnVoltarMenu);

        btnLimparTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO colocar na classe de operacao

                //UltilidadeTemperatura limpar;
                //limpar =  new UltilidadeTemperatura();
               // limpar.lmparTemperatura();
                editCelsus.setText("");
                editFahrenheit.setText("");

            }
        });

        btnCacularTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editFahrenheit.getText()) && (TextUtils.isEmpty(editCelsus.getText()))){
                    editFahrenheit.setError("* Valor Inválido");
                    editCelsus.setError("* Valor Inválido");

                }
                if (TextUtils.isEmpty(editCelsus.getText()) && (editFahrenheit.getText().length() >0)){
                    String resultado;
                    UltilidadeTemperatura converter;
                    converter = new UltilidadeTemperatura();
                    resultado = converter.fahrenheitParaCelsus(Double.parseDouble(editFahrenheit.getText().toString()));
                    //String resultMenor = String.format("%.2f", resultado);
                    editCelsus.setText(resultado);

                }
                if (TextUtils.isEmpty(editFahrenheit.getText()) && (editCelsus.getText().length() >0)){
                    String resultado;
                    UltilidadeTemperatura converter;
                    converter = new UltilidadeTemperatura();
                    resultado = converter.celsusParafahrenheit(Double.parseDouble(editCelsus.getText().toString()));
                    //String resultMenor = String.format("%.2f", resultado);
                    editFahrenheit.setText(resultado);

                }
            }
        });

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(TemperaturaActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                }, 0);

            }


        });

    }
}