package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import view.MenuPrincipal;
import view.TemperaturaActivity;

public class PorcentagemActivity extends AppCompatActivity {

    ImageButton btnVoltar;
    ImageButton btnLimpar;


    ////////////////////////////////////////////

    EditText editTextPorcentagemDeTotal01;
    EditText editTextTotal01;
    TextView textViewResultado01;

    ////////////////////////////////

    EditText editTextParte02;
    EditText editTextTotal02;
    TextView textViewResultado02;

    ////////////////////////////////////

    EditText editTextParte03;
    EditText editTextTotal03;
    TextView textViewResultado03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porcentagem);

        btnLimpar = findViewById(R.id.btnLimparPorcentagem);
        btnVoltar = findViewById(R.id.btnVoltarMenuPorcentagem);


       //================ PARTE 1 ============================
        editTextPorcentagemDeTotal01 = findViewById(R.id.editTextPorcentagemDeTotal);
        editTextTotal01 = findViewById(R.id.editTextValorTotal1);
        textViewResultado01 = findViewById(R.id.primeiroResultado);

        editTextPorcentagemDeTotal01.addTextChangedListener(textWatcher);
        editTextTotal01.addTextChangedListener(textWatcher);


        //================ PARTE 2 ============================
        editTextParte02 = findViewById(R.id.editTextParte2);
        editTextTotal02 = findViewById(R.id.editTextTotal02);
        textViewResultado02 = findViewById(R.id.segundoResultado);

        editTextParte02.addTextChangedListener(textWatcher);
        editTextTotal02.addTextChangedListener(textWatcher);

        // ============== PARTE 03 =========================
        editTextParte03 = findViewById(R.id.editValorPrimario03);
        editTextTotal03 = findViewById(R.id.editValorSecundario03);
        textViewResultado03 = findViewById(R.id.terceiroResultado);

        editTextParte03.addTextChangedListener(textWatcher);
        editTextTotal03.addTextChangedListener(textWatcher);



        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextPorcentagemDeTotal01.setText("");
                editTextTotal01.setText("");
                textViewResultado01.setText("");

                editTextParte02.setText("");
                editTextTotal02.setText("");
                textViewResultado02.setText("");

                editTextParte03.setText("");
                editTextTotal03.setText("");
                textViewResultado03.setText("");



            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPrincipal = new Intent(PorcentagemActivity.this, MenuPrincipal.class);
                startActivity(telaPrincipal);
                finish();
            }
        });













    }

    private void calcularPorcentagem() {
        String porcentagemStr01 = editTextPorcentagemDeTotal01.getText().toString();
        String valorTotalStr01 = editTextTotal01.getText().toString();

        if (!porcentagemStr01.isEmpty() && !valorTotalStr01.isEmpty()) {
            try {
                double porcentagem = Double.parseDouble(porcentagemStr01);
                double valorTotal = Double.parseDouble(valorTotalStr01);

                double resultado = (porcentagem / 100) * valorTotal;
                textViewResultado01.setText(String.valueOf(resultado ));
            } catch (NumberFormatException e) {
                // Trate o erro de conversão de string para double, se necessário
            }
        } else {
            // Lide com o caso em que um dos valores é vazio ou nulo
            textViewResultado01.setText("");
        }

        //=============================  Parte 02  ========================================
        String parteStr02 = editTextParte02.getText().toString();
        String valorTotalStr02 = editTextTotal02.getText().toString();

        if (!parteStr02.isEmpty() && !valorTotalStr02.isEmpty()) {
            try {
                double parte02 = Double.parseDouble(parteStr02);
                double valorTotal02 = Double.parseDouble(valorTotalStr02);

                double resultado2 = (parte02 / valorTotal02) * 100;

                textViewResultado02.setText(String.valueOf(resultado2 + "%"));
            } catch (NumberFormatException e) {
                // Trate o erro de conversão de string para double, se necessário
            }
        } else {
            // Lide com o caso em que um dos valores é vazio ou nulo
            textViewResultado02.setText("");
        }


        // ============================= Parte 03 =========================================

        String valorPrimarioStr03 = editTextParte03.getText().toString();
        String valorSecundarioStr03 = editTextTotal03.getText().toString();

        if (!valorPrimarioStr03.isEmpty() && !valorSecundarioStr03.isEmpty()) {
            try {
                double primario03 = Double.parseDouble(valorPrimarioStr03);
                double secundario03 = Double.parseDouble(valorSecundarioStr03);
               // Porcentagem Faltante = ((Valor Desejado - Valor Atual) / Valor Desejado) * 100
                //resultado3 = ((valorSecundarioStr03 - valorPrimarioStr03) / valorSecundarioStr03) * 100
                double resultado3 = ((secundario03 - primario03)/  primario03) * 100;

                textViewResultado03.setText(String.valueOf(" Diferença "+resultado3 + "%"));
            } catch (NumberFormatException e) {
                // Trate o erro de conversão de string para double, se necessário
            }
        } else {
            // Lide com o caso em que um dos valores é vazio ou nulo
            textViewResultado03.setText("");
        }




    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Não é necessário implementar esse método
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            calcularPorcentagem();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Não é necessário implementar esse método
        }
    };


}