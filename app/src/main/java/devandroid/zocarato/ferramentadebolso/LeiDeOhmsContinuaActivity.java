package devandroid.zocarato.ferramentadebolso;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeiDeOhmsContinuaActivity extends AppCompatActivity {

    private boolean isRaioVisible = false;
    private Handler handler;
    private Runnable raioRunnable;
    private int numPiscadas = 0;
    private int maxPiscadas = 6; // Define o número máximo de piscadas desejadas

    RelativeLayout layoutGeral;
    TextView ControleT;

    LinearLayout layoutTetoEdson;
    LinearLayout layoutBotoesOhmsContinua;
    LinearLayout   layoutResultaEdson;
    FrameLayout layoutCampoTensao;

    ImageButton btnEdson;

    // ===================================================== [ BOTOES E BOLEANOS DAS GRANDEZAS ================================]
    ImageButton btnTensaoSelect;
    boolean btnTensaoClick = false;
    ImageButton btnCorrenteSelect;
    boolean btnCorrenteClick = false;

    ImageButton btnResistenciaSelect;
    boolean btnResistenciaClick = false;

    ImageButton btnPotenciaSelect;
    boolean btnPotenciaClick = false;

    // ============================================ [ IMAGE VIEW ] ============================================
    ImageView CampoVazioOhms;
    ImageView imgCampoVazioTensao;
    ImageView imgCampoVazioCorrente;
    ImageView imgCampoVazioResistencia;
    ImageView imgCampoVazioPotencia;

    // =========================================== [ IMAGE BUTTON  UTILITARIOS] =====================================

    ImageButton btnCloseIcone;
    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcular;
   // ===============================================[ EDIT TEXT ] ==================================================
    EditText editTextTensao;
    EditText editTextCorrente;
    EditText editTextResitencia;
    EditText editTextPOtencia;
    // ========================================================
    ImageButton btnAvisoEdson;
    TextView textViewAviso;
    LinearLayout layotInferiorAviso;

    int Controle = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_de_ohms_continua);

        textViewAviso = findViewById(R.id.TextViewResultado);
        layoutResultaEdson = findViewById(R.id.layoutResultaEdson);
        layoutResultaEdson.setVisibility(View.GONE);


        btnCloseIcone = findViewById(R.id.btn_close_icone);
        btnCloseIcone.setVisibility(View.GONE);

        // Itens Layout Inferior ( Limpar, Calcular, Fechar )
        {
            btnLimpar = findViewById(R.id.btnLimpar);
            btnCalcular = findViewById(R.id.btnCalcular);
            btnVoltar = findViewById(R.id.btnVoltar);
        }

         // Tensão itens inicial
        {
            btnTensaoSelect = findViewById(R.id.btnTensaoSelect);
            imgCampoVazioTensao = findViewById(R.id.imgCampoVazioTensao);
            imgCampoVazioTensao.setVisibility(View.GONE);
            editTextTensao = findViewById(R.id.editTextTensao);
            editTextTensao.setVisibility(View.GONE);
        }

        // Corrente Itens inicial
        {
            btnCorrenteSelect = findViewById(R.id.btnCorrenteSelect);
            imgCampoVazioCorrente = findViewById(R.id.imgCampoVazioCorrente);
            imgCampoVazioCorrente.setVisibility(View.GONE);
            editTextCorrente = findViewById(R.id.editTextCorrente);
            editTextCorrente.setVisibility(View.GONE);
        }

        // Resistencia Itens Iniciais
        {
            btnResistenciaSelect = findViewById(R.id.btnResistenciaSelect);
            imgCampoVazioResistencia = findViewById(R.id.imgCampoVazioResistencia);
            imgCampoVazioResistencia.setVisibility(View.GONE);
            editTextResitencia = findViewById(R.id.editTextResistencia);
            editTextResitencia.setVisibility(View.GONE);
        }

        // Potencia Itens Iniciais
        {
            btnPotenciaSelect = findViewById(R.id.btnPotenciaSelect);
            imgCampoVazioPotencia = findViewById(R.id.imgCampoVazioPotencia);
            imgCampoVazioPotencia.setVisibility(View.GONE);
            editTextPOtencia = findViewById(R.id.editTextPOtencia);
            editTextPOtencia.setVisibility(View.GONE);
        }


        btnEdson = findViewById(R.id.btnEdson);
        btnEdson.setVisibility(View.VISIBLE);

        btnAvisoEdson = findViewById(R.id.ButtonAvisoEdson);
        btnAvisoEdson.setVisibility(View.GONE);

         layotInferiorAviso = findViewById(R.id.layotInferiorAviso);

        layoutGeral  = findViewById(R.id.layoutGeral);

        layoutCampoTensao  = findViewById(R.id.layoutCampoTensao);
        // layoutCampoTensao.setVisibility(View.GONE);

        layoutTetoEdson = findViewById(R.id.layoutTetoTesla);
      // layoutRaioTeslaEdson.setBackgroundResource(0);

         layoutBotoesOhmsContinua = findViewById(R.id.layoutBotoesOhmsContinua);
        // layoutBotoesOhmsContinua.setVisibility(View.GONE);
        ControleT = findViewById(R.id.Controle);


        btnCloseIcone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCloseIcone.setVisibility(View.GONE);
                layoutResultaEdson.setVisibility(View.GONE);
                layoutTetoEdson.setVisibility(View.VISIBLE);
            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
                Controle = 0;
                //layoutCampoTensao.setBackgroundResource(R.color.black);
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculo();
                //layoutCampoTensao.setBackgroundResource(R.color.black);
            }
        });


        btnTensaoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (btnTensaoClick) {

                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_off);
                    imgCampoVazioTensao.setVisibility(View.GONE);
                    editTextTensao.setVisibility(View.GONE);
                    if (Controle > 0)  {Controle --;}
                    btnTensaoClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }
                    Controle ++;
                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);
                    imgCampoVazioTensao.setVisibility(View.VISIBLE);
                    editTextTensao.setVisibility(View.VISIBLE);
                    editTextTensao.requestFocus();

                    btnTensaoClick = true;
                }

            }
        });
        btnCorrenteSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnCorrenteClick) {

                    btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_off);
                    imgCampoVazioCorrente.setVisibility(View.GONE);
                    editTextCorrente.setVisibility(View.GONE);
                    if (Controle > 0)  {Controle --;}
                    btnCorrenteClick = false;
                } else {
                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }
                    Controle ++;
                    btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);
                    imgCampoVazioCorrente.setVisibility(View.VISIBLE);
                    editTextCorrente.setVisibility(View.VISIBLE);
                    editTextCorrente.requestFocus();

                    btnCorrenteClick = true;
                }

            }
        });
        btnResistenciaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnResistenciaClick) {
                    btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_off);
                    imgCampoVazioResistencia.setVisibility(View.GONE);
                    editTextResitencia.setVisibility(View.GONE);
                    if (Controle > 0)  {Controle --;}
                    btnResistenciaClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }
                    Controle ++;
                    btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);
                    imgCampoVazioResistencia.setVisibility(View.VISIBLE);
                    editTextResitencia.setVisibility(View.VISIBLE);
                    editTextResitencia.requestFocus();

                    btnResistenciaClick = true;
                }

            }
        });
        btnPotenciaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (btnPotenciaClick) {

                    btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_off);
                    imgCampoVazioPotencia.setVisibility(View.GONE);
                    editTextPOtencia.setVisibility(View.GONE);
                    if (Controle > 0)  {Controle --;}
                    btnPotenciaClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }

                    Controle ++;
                    btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);
                    imgCampoVazioPotencia.setVisibility(View.VISIBLE);
                    editTextPOtencia.setVisibility(View.VISIBLE);
                    editTextPOtencia.requestFocus();

                    btnPotenciaClick = true;
                }

            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEletrica = new Intent(LeiDeOhmsContinuaActivity.this, LeiDeOhmsActivity.class);
                startActivity(telaEletrica);
                finish();
            }
        });
        btnAvisoEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnAvisoEdson.setVisibility(View.GONE);


            }

        });
        btnEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControleT.setText("" + Controle);


            }

        });

    }


    public void calculo (){
        double tensao = 0;
        double corrente = 0;
        double resistencia = 0;
        double potencia = 0;
        // String editTextTensaoS = "0";
        //String editTextCorrenteS = "0";
        //String editTextPotenciaS ="0";
        // String editTextResistenciaS ="0";

        String editTextTensaoS = editTextTensao.getText().toString();
        String editTextCorrenteS  = editTextCorrente.getText().toString();
        String editTextPotenciaS  = editTextPOtencia.getText().toString();
        String editTextResistenciaS = editTextResitencia.getText().toString();



        if ((btnResistenciaClick) && (btnPotenciaClick)){
            try {
                potencia = Double.parseDouble(editTextPotenciaS);
                resistencia = Double.parseDouble(editTextResistenciaS);
            } catch (NumberFormatException e) {

            }

            tensao = Math.sqrt( potencia * resistencia) ;
            corrente = Math.sqrt( potencia / resistencia) ;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double correnteArredondada = Math.round(corrente * 100) / 100.0;
            double tensaoArredondada = Math.round(tensao * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:

            editTextCorrente.setFocusable(false);
            editTextCorrente.setFocusableInTouchMode(false);
            editTextCorrente.setText(String.valueOf(correnteArredondada));
            editTextCorrente.setVisibility(View.VISIBLE);
            imgCampoVazioCorrente.setVisibility(View.VISIBLE);
            btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);

            editTextTensao.setFocusable(false);
            editTextTensao.setFocusableInTouchMode(false);
            editTextTensao.setText(String.valueOf(tensaoArredondada));
            editTextTensao.setVisibility(View.VISIBLE);
            imgCampoVazioTensao.setVisibility(View.VISIBLE);
            btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);

        }

        if ((btnCorrenteClick) && (btnResistenciaClick)){
            try {
                corrente = Double.parseDouble(editTextCorrenteS);
                resistencia = Double.parseDouble(editTextResistenciaS);
            } catch (NumberFormatException e) {

            }

            tensao = resistencia  * corrente ;
            potencia = resistencia * ( corrente  *  corrente ) ;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double potenciaArredondada = Math.round(potencia * 100) / 100.0;
            double tensaoArredondada = Math.round(tensao * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:

            editTextPOtencia.setFocusable(false);
            editTextPOtencia.setFocusableInTouchMode(false);
            editTextPOtencia.setText(String.valueOf(potenciaArredondada));
            editTextPOtencia.setVisibility(View.VISIBLE);
            imgCampoVazioPotencia.setVisibility(View.VISIBLE);
            btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);

            editTextTensao.setFocusable(false);
            editTextTensao.setFocusableInTouchMode(false);
            editTextTensao.setText(String.valueOf(tensaoArredondada));
            editTextTensao.setVisibility(View.VISIBLE);
            imgCampoVazioTensao.setVisibility(View.VISIBLE);
            btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);

        }
        if ((btnCorrenteClick) && (btnPotenciaClick)){
            try {
                corrente = Double.parseDouble(editTextCorrenteS);
                potencia = Double.parseDouble(editTextPotenciaS);
            } catch (NumberFormatException e) {

            }

            tensao = potencia / corrente ;
            resistencia = potencia / ( corrente  *  corrente ) ;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double resistenciaArredondada = Math.round(resistencia * 100) / 100.0;
            double tensaoArredondada = Math.round(tensao * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:
            editTextResitencia.setFocusable(false);
            editTextResitencia.setFocusableInTouchMode(false);
            editTextResitencia.setText(String.valueOf(resistenciaArredondada));
            editTextResitencia.setVisibility(View.VISIBLE);
            imgCampoVazioResistencia.setVisibility(View.VISIBLE);
            btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);

            editTextTensao.setFocusable(false);
            editTextTensao.setFocusableInTouchMode(false);
            editTextTensao.setText(String.valueOf(tensaoArredondada));
            editTextTensao.setVisibility(View.VISIBLE);
            imgCampoVazioTensao.setVisibility(View.VISIBLE);
            btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);

        }

        if ((btnTensaoClick) && (btnPotenciaClick)){
            try {
                tensao = Double.parseDouble(editTextTensaoS);
                potencia = Double.parseDouble(editTextPotenciaS);
            } catch (NumberFormatException e) {

            }

            resistencia = ( tensao * tensao ) / potencia;
            corrente = potencia / tensao  ;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double resistenciaArredondada = Math.round(resistencia * 100) / 100.0;
            double correnteArredondada = Math.round(corrente * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:
            editTextResitencia.setFocusable(false);
            editTextResitencia.setFocusableInTouchMode(false);
            editTextResitencia.setText(String.valueOf(resistenciaArredondada));
            editTextResitencia.setVisibility(View.VISIBLE);
            imgCampoVazioResistencia.setVisibility(View.VISIBLE);
            btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);

            editTextCorrente.setFocusable(false);
            editTextCorrente.setFocusableInTouchMode(false);
            editTextCorrente.setText(String.valueOf(correnteArredondada));
            editTextCorrente.setVisibility(View.VISIBLE);
            imgCampoVazioCorrente.setVisibility(View.VISIBLE);
            btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);

        }

        if ((btnTensaoClick) && (btnCorrenteClick)){
            try {
                 tensao = Double.parseDouble(editTextTensaoS);
                 corrente = Double.parseDouble(editTextCorrenteS);
            } catch (NumberFormatException e) {

            }

             resistencia = tensao / corrente;
             potencia = tensao * corrente;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double resistenciaArredondada = Math.round(resistencia * 100) / 100.0;
            double potenciaArredondada = Math.round(potencia * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:

            editTextResitencia.setFocusable(false);
            editTextResitencia.setFocusableInTouchMode(false);
            editTextResitencia.setText(String.valueOf(resistenciaArredondada));
            editTextResitencia.setVisibility(View.VISIBLE);
            imgCampoVazioResistencia.setVisibility(View.VISIBLE);
            btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);


            editTextPOtencia.setFocusable(false);
            editTextPOtencia.setFocusableInTouchMode(false);
            editTextPOtencia.setText(String.valueOf(potenciaArredondada));
            editTextPOtencia.setVisibility(View.VISIBLE);
            imgCampoVazioPotencia.setVisibility(View.VISIBLE);
            btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);

        }


        if ((btnTensaoClick) && (btnResistenciaClick)){
            try {
                tensao = Double.parseDouble(editTextTensaoS);
                resistencia = Double.parseDouble(editTextResistenciaS);
            } catch (NumberFormatException e) {

            }

            corrente = tensao / resistencia;
            potencia = (tensao * tensao) / resistencia;

            // Exiba os resultados em outros dois EditTexts ou faça o que desejar com eles
            double correnteArredondada = Math.round(corrente * 100) / 100.0;
            double potenciaArredondada = Math.round(potencia * 100) / 100.0;

            // Supondo que você tenha dois EditTexts para exibir os resultados chamados editTextCorrente e editTextPotencia:
            editTextCorrente.setFocusable(false);
            editTextCorrente.setFocusableInTouchMode(false);

            editTextCorrente.setText(String.valueOf(correnteArredondada));
            editTextCorrente.setVisibility(View.VISIBLE);
            imgCampoVazioCorrente.setVisibility(View.VISIBLE);
            btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);


            editTextPOtencia.setFocusable(false);
            editTextPOtencia.setFocusableInTouchMode(false);

            editTextPOtencia.setKeyListener(null);
            editTextPOtencia.setText(String.valueOf(potenciaArredondada));
            editTextPOtencia.setVisibility(View.VISIBLE);
            imgCampoVazioPotencia.setVisibility(View.VISIBLE);
            btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);

        }


    }

    public void piscaRaio() {

        handler = new Handler();
        raioRunnable = new Runnable() {
            @Override
            public void run() {

                if (isRaioVisible) {
                    layoutGeral.setBackgroundResource(R.color.corFundoPadrao);
                    layoutTetoEdson.setBackgroundResource(0);

                } else {
                    layoutGeral.setBackgroundResource(R.color.black);
                    layoutTetoEdson.setBackgroundResource(R.drawable.img_background_raio);
                }
                isRaioVisible = !isRaioVisible;
                numPiscadas++;

                if (numPiscadas < maxPiscadas) {
                    handler.postDelayed(this, 60); // Define o tempo de duração do pisca-pisca em milissegundos (neste exemplo, 500ms)
                } else {
                    // Parar o pisca-pisca após o número desejado de piscadas
                    handler.removeCallbacks(this);
                }
            }
        };

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.post(raioRunnable);
            }
        }, 10); // Define um atraso de 1 segundo (1000ms) antes de iniciar o piscar
    }

    public void erroSelecao (){


        layoutTetoEdson.setVisibility(View.GONE);
       // btnAvisoEdson.setVisibility(View.VISIBLE);
        layoutResultaEdson.setVisibility(View.VISIBLE);
        textViewAviso.setTextSize(20f);
        textViewAviso.setTextColor(Color.BLACK);
        textViewAviso.setText("Selecione apenas dois campos!");
        btnCloseIcone.setVisibility(View.VISIBLE);


    }

    public void limpar (){

        resetLayout();
        Controle = 0;

        editTextTensao.setText(String.valueOf(""));
        editTextTensao.setVisibility(View.GONE);
        imgCampoVazioTensao.setVisibility(View.GONE);
        btnTensaoSelect.setImageResource(R.drawable.btn_tensao_off);
        btnTensaoClick = !btnTensaoClick;

        editTextCorrente.setText(String.valueOf(""));
        editTextCorrente.setVisibility(View.GONE);
        imgCampoVazioCorrente.setVisibility(View.GONE);
        btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_off);
        btnCorrenteClick = !btnCorrenteClick;

        editTextResitencia.setText(String.valueOf(""));
        editTextResitencia.setVisibility(View.GONE);
        imgCampoVazioResistencia.setVisibility(View.GONE);
        btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_off);
        btnResistenciaClick = !btnResistenciaClick;


        editTextPOtencia.setText(String.valueOf(""));
        editTextPOtencia.setVisibility(View.GONE);
        imgCampoVazioPotencia.setVisibility(View.GONE);
        btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_off);
        btnPotenciaClick = !btnPotenciaClick;



    }

    private void resetLayout() {

        recreate();
       // Intent intent = getIntent();
       // finish();
        //startActivity(intent);
    }

}