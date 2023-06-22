package devandroid.zocarato.ferramentadebolso;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class LeiDeOhmsAlternadaActivity extends AppCompatActivity {

    private boolean isRaioVisible = false;
    private Handler handler;
    private Runnable raioRunnable;
    private int numPiscadas = 0;
    private int maxPiscadas = 6; // Define o número máximo de piscadas desejadas

    RelativeLayout layoutGeral;
    TextView ControleT;
    LinearLayout layoutTetoTesla;
    LinearLayout layoutBotoesOhmsContinua;
    LinearLayout  layoutResultaTesla;
    FrameLayout layoutCampoTensao;

    //ImageButton btnTesla;
    ImageButton btnEdson;


    ImageView imgCampoTensao;
    EditText editTextTensao;
    boolean TensaoOk = false;

    ImageView imgCampoPotencia;
    EditText editTextPotencia;
    boolean PotenciaOk = false;

    ImageView imageViewFatorPotencia;
    EditText editTextFatorPotencia;
    boolean fatorPotenciaOK = false;

    ImageView imageViewRendimento;
    EditText editTextRendimento;
    boolean rendimentoOK = false;

    ImageButton btnTrifasico;
    boolean trifasicoOK = false;

    ImageButton btnWattsHp;
    boolean wattsOK = true;

    ImageButton bntCloseIcone;
    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcular;


    EditText editTextCorrente;
    EditText editTextResitencia;
    EditText editTextPOtencia;

    ImageButton btnAvisoEdson;
    TextView resultadoOhmsAlternada;
    LinearLayout layotInferiorAviso;
    int Controle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_de_ohms_alternada);

        layoutResultaTesla = findViewById(R.id.layoutResultaTesla);
        layoutResultaTesla.setVisibility(View.GONE);
        bntCloseIcone = findViewById(R.id.btn_close_icone);
        bntCloseIcone.setVisibility(View.GONE);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnVoltar = findViewById(R.id.btnVoltar);

        imgCampoTensao = findViewById(R.id.imgCampoVazioTensao);
        editTextTensao = findViewById(R.id.editTextTensao);
        editTextTensao.addTextChangedListener(textWatcher);

        imgCampoPotencia = findViewById(R.id.imgCampoPotencia);
        editTextPotencia = findViewById(R.id.editTextPotencia);
        editTextPotencia.addTextChangedListener(textWatcher);

        btnTrifasico =  findViewById(R.id.btnTrifasico);
        btnWattsHp =  findViewById(R.id.btnWattsHp);

        imageViewFatorPotencia = findViewById(R.id.imgFatorPotencia);
        editTextFatorPotencia = findViewById(R.id.editTextFatorPotencia);
        editTextFatorPotencia.addTextChangedListener(textWatcher);

        imageViewRendimento = findViewById(R.id.imgRendimento);
        editTextRendimento = findViewById(R.id.editTextRendimento);
        editTextRendimento.addTextChangedListener(textWatcher);

        resultadoOhmsAlternada = findViewById(R.id.TextViewResultado);

        btnEdson = findViewById(R.id.btnEdson);

        btnAvisoEdson = findViewById(R.id.ButtonAvisoEdson);
        btnAvisoEdson.setVisibility(View.GONE);

        layotInferiorAviso = findViewById(R.id.layotInferiorAviso);

        layoutGeral  = findViewById(R.id.layoutGeral);

        layoutCampoTensao  = findViewById(R.id.layoutCampoTensao);

        layoutTetoTesla = findViewById(R.id.layoutTetoTesla);

         layoutBotoesOhmsContinua = findViewById(R.id.layoutBotoesOhmsContinua);

        ControleT = findViewById(R.id.Controle);

        bntCloseIcone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutResultaTesla.setVisibility(View.GONE);

                bntCloseIcone.setVisibility(View.GONE);
                layoutTetoTesla.setVisibility(View.VISIBLE);
            }
        });

        btnTrifasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(trifasicoOK){
                    btnTrifasico.setImageResource(R.drawable.img_trifasico_alternada_off);
                    trifasicoOK = false;
                }else{
                    btnTrifasico.setImageResource(R.drawable.img_trifasico_alternada_on);
                    trifasicoOK = true;
                }

            }
        });
        btnWattsHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(wattsOK){
                    btnWattsHp.setImageResource(R.drawable.img_hp_trifasico);
                    wattsOK = false;
                }else{
                    btnWattsHp.setImageResource(R.drawable.img_watts_trifasico);
                    wattsOK = true;
                }

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();

            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutResultaTesla.setVisibility(View.VISIBLE);
                layoutTetoTesla.setVisibility(View.GONE);
                calcular();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEletrica = new Intent(LeiDeOhmsAlternadaActivity.this, LeiDeOhmsActivity.class);
                startActivity(telaEletrica);
                finish();
            }
        });
        btnAvisoEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Controle = 2;
               // layoutGeral.setBackgroundResource(0);
               // layoutGeral.setBackground(R.color.black);
                btnAvisoEdson.setVisibility(View.GONE);
               /// layoutGeral.setBackgroundResource(R.color.corFundoPadrao);
               // layoutRaioTeslaEdson.setBackgroundResource(0);


            }

        });

        btnEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControleT.setText("" + Controle);


            }

        });

    }

    public void piscaRaio() {

        handler = new Handler();
        raioRunnable = new Runnable() {
            @Override
            public void run() {

                if (isRaioVisible) {
                    layoutGeral.setBackgroundResource(R.color.corFundoPadrao);
                    layoutGeral.setBackgroundResource(0);

                } else {
                    layoutGeral.setBackgroundResource(R.color.black);
                    layoutGeral.setBackgroundResource(R.drawable.img_background_raio);
                }
                isRaioVisible = !isRaioVisible;
                numPiscadas++;

                if (numPiscadas < maxPiscadas) {
                    handler.postDelayed(this, 60); // Define o tempo de duração do pisca-pisca em milissegundos (neste exemplo, 500ms)
                } else {
                    // Parar o pisca-pisca após o número desejado de piscadas
                    handler.removeCallbacks(this);
                    layoutGeral.setBackgroundResource(R.color.corFundoPadrao);
                }


            }
        };

        layoutGeral.setBackgroundResource(R.color.corFundoPadrao);

        isRaioVisible = false;
        numPiscadas= 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.post(raioRunnable);
            }
        }, 10); // Define um atraso de 1 segundo (1000ms) antes de iniciar o piscar

        layoutGeral.setBackgroundResource(R.color.corFundoPadrao);

        isRaioVisible = false;
    }
    public void limpar (){

        recreate();
        editTextTensao.setText("");
        editTextPotencia.setText("");
        editTextFatorPotencia.setText("1");
        editTextRendimento.setText("1");
    }
    public void calcular (){

        bntCloseIcone.setVisibility(View.VISIBLE);
        String tensaoS = editTextTensao.getText().toString();
        String potenciaS = editTextPotencia.getText().toString();
        String fatorPotenciaS = editTextFatorPotencia.getText().toString();
        String rendimentoS = editTextRendimento.getText().toString();

        double tensao = 0;
        double potencia = 0;
        double fase = 0;
        double fatorPotencia = 0;
        double rendimento = 0;

        try {
             tensao = Double.parseDouble(tensaoS);

        } catch (NumberFormatException e) {      }
        try {
            potencia = Double.parseDouble(potenciaS);

        } catch (NumberFormatException e) {        }
        try {
            fatorPotencia = Double.parseDouble(fatorPotenciaS);

        } catch (NumberFormatException e) {        }
        try {
            rendimento = Double.parseDouble(rendimentoS);

        } catch (NumberFormatException e) {        }


        if (!wattsOK){
             potencia = potencia * 745.7; // Conversão para watts
        }else {
            potencia = potencia * 1000;
        }

        // verifica se os campos possuem dados validos
        if ((tensao == 0) || (potencia ==0 )){

            piscaRaio();


            resultadoOhmsAlternada.setText("  Digite um valor válido") ;
            resultadoOhmsAlternada.setTextSize(28f);
            resultadoOhmsAlternada.setTextColor(Color.BLACK);

            return;
        }

        double resultadoCalculo;
        resultadoOhmsAlternada.setTextColor(Color.BLACK);

        if (trifasicoOK){
            double R3 = 1.73205081;
            resultadoCalculo = potencia/ (tensao * R3 * rendimento * fatorPotencia);

            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define o formato desejado, nesse caso, com duas casas decimais
            String valorFormatado = decimalFormat.format(resultadoCalculo); // Formata o valor
            resultadoOhmsAlternada.setText( valorFormatado +" (A)");

        }else {
            resultadoCalculo = potencia/ (tensao * rendimento * fatorPotencia);
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define o formato desejado, nesse caso, com duas casas decimais
            String valorFormatado = decimalFormat.format(resultadoCalculo); // Formata o valor

            resultadoOhmsAlternada.setText(valorFormatado + " (A)") ;
        }

    }
    public void verificaCampo (){
       // EditText meuEditText = findViewById(R.id.meuEditText);
        String texto = editTextTensao.getText().toString();
        String textoPotencia = editTextPotencia.getText().toString();
        String textoFatorPotencia = editTextFatorPotencia.getText().toString();
        String textoRendimento = editTextRendimento.getText().toString();


        if (!TextUtils.isEmpty(texto)) {
            // O EditText não está vazio
            TensaoOk = true;
        } else {
            // O EditText está vazio
            TensaoOk = false;
        }
        if (!TextUtils.isEmpty(textoPotencia)) {
            // O EditText não está vazio
            PotenciaOk = true;
        } else {
            // O EditText está vazio
            PotenciaOk = false;
        }
        if (textoFatorPotencia.equals("1")){
            fatorPotenciaOK = false;
        }else {
            fatorPotenciaOK = true;
        }
        if (textoRendimento.equals("1")){
            rendimentoOK = false;
        }else {
            rendimentoOK = true;
        }





    }
    public void alteraCampo (){

        if (TensaoOk) {
            imgCampoTensao.setImageResource(R.drawable.img_tensao_alternada_on);

        }if (!TensaoOk){
            imgCampoTensao.setImageResource(R.drawable.img_tensao_alternada_off);
        }
        if (PotenciaOk) {
            imgCampoPotencia.setImageResource(R.drawable.img_potencia_alternada_on);

        }if (!PotenciaOk){
            imgCampoPotencia.setImageResource(R.drawable.img_potencia_alternada_off);
        }
        if (fatorPotenciaOK) {
            imageViewFatorPotencia.setImageResource(R.drawable.img_fator_potencia_trifasico_on);
            editTextFatorPotencia.setTextColor(Color.parseColor("#2A3BBD"));

        }if (!fatorPotenciaOK){
            imageViewFatorPotencia.setImageResource(R.drawable.img_fator_potencia_trifasico_off);
            editTextFatorPotencia.setTextColor(Color.BLACK);
        }
        if (rendimentoOK) {
            imageViewRendimento.setImageResource(R.drawable.img_rendimento_on);
            editTextRendimento.setTextColor(Color.parseColor("#2A3BBD"));
        }if (!rendimentoOK){
            imageViewRendimento.setImageResource(R.drawable.img_rendimento_off);
            editTextRendimento.setTextColor(Color.BLACK);
        }

    }

    public void garantidorConformidade (){

        String rentimento = editTextRendimento.getText().toString();
        Double rendimentoDouble = 1.0;

        String fatorDePotencia = editTextFatorPotencia.getText().toString();
        Double fatorPotenciaDouble = 1.0;

        try {
            fatorPotenciaDouble = Double.parseDouble(fatorDePotencia);

        } catch (NumberFormatException e) {      }
        if (fatorPotenciaDouble > 1) {
            editTextFatorPotencia.setText("");
        }

        try {
            rendimentoDouble = Double.parseDouble(rentimento);

        } catch (NumberFormatException e) {      }
        if (rendimentoDouble > 1) {
            editTextRendimento.setText("");
        }

    }
    TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Método chamado antes de o texto ser alterado
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                verificaCampo();
                alteraCampo();
                garantidorConformidade();


            }

            @Override
            public void afterTextChanged(Editable s) {
                // Método chamado após o texto ser alterado
            }
        };

}