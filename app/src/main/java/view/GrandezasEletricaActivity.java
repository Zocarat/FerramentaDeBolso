package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import devandroid.zocarato.ferramentadebolso.MenuEletricaActivity;
import devandroid.zocarato.ferramentadebolso.R;
import util.UltilidadeEletrica;

public class GrandezasEletricaActivity extends AppCompatActivity {

    UltilidadeEletrica funcao;
    ImageButton btnVoltarMenu;
    ImageButton btnLimparEletrica;
    ImageButton btnCalcularEletrica;

    EditText editTextTensao;
    EditText editTextCorrente;
    EditText editTextPotencia;
    EditText editTextResistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grandezas_eletricas);

        funcao = new UltilidadeEletrica();

        btnVoltarMenu = findViewById(R.id.btnVoltarMenuEletrica);
        btnLimparEletrica = findViewById(R.id.btnLimparMenuEletrica);
        btnCalcularEletrica = findViewById(R.id.btnCalcularEletrica);

        editTextTensao = findViewById(R.id.editTextTensao);
        editTextCorrente = findViewById(R.id.editTextCorrente);
        editTextResistencia = findViewById(R.id.editTextResistencia);
        editTextPotencia = findViewById(R.id.editTextPotencia);

        btnCalcularEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dadosOK;
                dadosOK = funcao.checaCampo(editTextTensao, editTextCorrente, editTextPotencia, editTextResistencia);
                if (dadosOK == 1) {
                    Toast.makeText(getApplicationContext(), "Preencha pelo menos dois campos!", Toast.LENGTH_SHORT).show();
                    editTextTensao.setError("No mínimo dois campos");
                    editTextCorrente.setError("No mínimo dois campos");
                    editTextPotencia.setError("No mínimo dois campos");
                    editTextResistencia.setError("No mínimo dois campos");
                }
                if (dadosOK == 2) {
                    Toast.makeText(getApplicationContext(),
                            "Preencha mais um campo", Toast.LENGTH_SHORT).show();
                }
                if (dadosOK == 3) {
                    Toast.makeText(getApplicationContext(),
                            "Apague um campo!", Toast.LENGTH_SHORT).show();
                }
                // calcula potencia  e resistencia
                if (dadosOK == 100) {
                    editTextPotencia.setText(funcao.potenciaTensaoCorrente(editTextTensao, editTextCorrente));
                    editTextResistencia.setText(funcao.resistenciaTensaoCorrente(editTextTensao, editTextCorrente));
                }
                //calcula corrente e resitencia
                if (dadosOK == 200) {
                    editTextCorrente.setText(funcao.correnteTensaoPotencia(editTextTensao, editTextPotencia));
                    editTextResistencia.setText(funcao.resistenciaTensaoPotencia(editTextTensao, editTextPotencia));
                }
                //  corrernte e potencia
                if (dadosOK == 300) {
                    editTextCorrente.setText(funcao.correnteTensaoResistencia(editTextTensao, editTextResistencia));
                    editTextPotencia.setText(funcao.potenciaTensaoResistencia(editTextTensao, editTextResistencia));
                }
                // tensaoCorrentePotencia
                //resistenciaCorretePotencia
                if (dadosOK == 400){
                    editTextTensao.setText(funcao.tensaoCorrentePotencia(editTextCorrente, editTextPotencia));
                    editTextResistencia.setText(funcao.resistenciaCorretePotencia(editTextCorrente, editTextPotencia));
                }
                // tensaoCorrenteResistencia
                // potenciaCorrenteResistencia
                if (dadosOK == 500){
                    editTextTensao.setText(funcao.tensaoCorrenteResistencia(editTextCorrente, editTextResistencia));
                    editTextPotencia.setText(funcao.potenciaCorrenteResistencia(editTextCorrente, editTextResistencia));
                }


                //tensaoPotenciaResistencia
                //correntePotenciaResistencia
                if (dadosOK == 600){
                    editTextTensao.setText(funcao.tensaoPotenciaResistencia(editTextPotencia, editTextResistencia));
                    editTextCorrente.setText(funcao.correntePotenciaResistencia(editTextPotencia, editTextResistencia));

                }


            }
        });


        btnLimparEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPotencia.setText("");
                editTextTensao.setText("");
                editTextResistencia.setText("");
                editTextCorrente.setText("");
            }
        });


        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaMenu = new Intent(GrandezasEletricaActivity.this, MenuEletricaActivity.class);
                        startActivity(telaMenu);
                        finish();
                    }
                }, 0);

            }
        });


    }


}