package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import devandroid.zocarato.ferramentadebolso.MenuPrincipal;
import devandroid.zocarato.ferramentadebolso.R;
import util.PolegadasFracionadas;
import util.UtilidadesPressao;

public class ddddddddddComprimentoActivity extends AppCompatActivity {

    PolegadasFracionadas fracionado;

    EditText editMilimetro;
    EditText editPolegada;


    UtilidadesPressao menuPrincipal;

    Button btnTabelaPolegadaMilimetro;
    Button btnMenuPrincipal;
    Button btnCalcular;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dddddactivity_comprimento);

        //menuPrincipal = new Utilidades();

        editMilimetro = findViewById(R.id.edit_milimetroxml);
        editPolegada = findViewById(R.id.edit_polegada_xml);

        btnTabelaPolegadaMilimetro = findViewById(R.id.btnTabelaPolegadaMilimetro);
        btnMenuPrincipal = findViewById(R.id.btnVoltarMenu);
        btnCalcular = findViewById(R.id.btnCalcularComprimento);
        btnLimpar = findViewById(R.id.btnLimparComprimento);


        btnTabelaPolegadaMilimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaTabelaPolegadaMIlimetro = new Intent(ddddddddddComprimentoActivity.this, TabelaPolegadaMmActivity.class);
                        startActivity(telaTabelaPolegadaMIlimetro);
                        finish();
                    }
                },0);

            }

        });

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(ddddddddddComprimentoActivity.this, MenuPrincipal.class);
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

                    resultado = resultado / 25.4;

                    String resultMenor = String.format("%.2f", resultado);

                    fracao = fracionado.polegadasFracionadas(resultado);
                    editPolegada.setText(resultMenor + " ( " + fracao + " ) " );

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