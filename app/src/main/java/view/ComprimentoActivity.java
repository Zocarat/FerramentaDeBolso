package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.zocarato.ferramentadebolso.R;
import util.PolegadasFracionadas;
import util.Utilidades;

public class ComprimentoActivity extends AppCompatActivity {

    PolegadasFracionadas fracionado;

    EditText editMilimetro;
    EditText editPolegada;


    Utilidades menuPrincipal;
    Button btnMenuPrincipal;
    Button btnCalcular;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimento);

        //menuPrincipal = new Utilidades();

        editMilimetro = findViewById(R.id.edit_milimetroxml);
        editPolegada = findViewById(R.id.edit_polegada_xml);

        btnMenuPrincipal = findViewById(R.id.btnVoltarMenu);
        btnCalcular = findViewById(R.id.btnCalcularComprimento);
        btnLimpar = findViewById(R.id.btnLimparComprimento);

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(ComprimentoActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }

        });

        btnCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // confirma se os dados estao valido
                boolean isDadosOK = true;

                if(TextUtils.isEmpty(editMilimetro.getText()) && (TextUtils.isEmpty(editPolegada.getText()))){
                    editMilimetro.setError("* Valor inválido");
                    editPolegada.setError("* Valor inválido");
                    isDadosOK = false;
                }
                if(TextUtils.isEmpty(editMilimetro.getText())  && (editPolegada.getText().length() > 0)){

                    double polegada = 25.4;
                    double resultado = Double.parseDouble(editPolegada.getText().toString());
                    resultado = resultado * polegada;
                    editMilimetro.setText(Double.toString(resultado));

                }

                if (TextUtils.isEmpty(editPolegada.getText())  && (editMilimetro.getText().length() > 0)){
                    String fracao;
                    fracionado = new PolegadasFracionadas();

                    double resultado = Double.parseDouble(editMilimetro.getText().toString());
                    fracao = fracionado.polegadasFracionadas(resultado);
                    resultado = resultado / 25.4;
                    editPolegada.setText(Double.toString(resultado) + " ( " + fracao + " ) " );


                }

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPolegada.setText("");
                editMilimetro.setText("");
            }
        });



    }



}