package devandroid.zocarato.ferramentadebolso;

import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import view.MenuPrincipal;

public class LeiDeOhmsContinuaActivity extends AppCompatActivity {




    private boolean isRaioVisible = false;
    private Handler handler;
    private Runnable raioRunnable;
    private int numPiscadas = 0;
    private int maxPiscadas = 6; // Define o número máximo de piscadas desejadas


    RelativeLayout layoutGeral;

    LinearLayout layoutRaioTeslaEdson;
    LinearLayout layoutBotoesOhmsContinua;

    LinearLayout   layoutEditTextContinua;

    FrameLayout layoutCampoTensao;

    //ImageButton btnTesla;
    ImageButton btnEdson;




    ImageButton btnTensaoSelect;
    boolean btnTensaoClick = false;
    ImageButton btnCorrenteSelect;
    boolean btnCorrenteClick = false;

    ImageButton btnResistenciaSelect;
    boolean btnResistenciaClick = false;

    ImageButton btnPotenciaSelect;
    boolean btnPotenciaClick = false;

    ImageView CampoVazioOhms;
    ImageView imgCampoVazioTensao;
    ImageView imgCampoVazioCorrente;
    ImageView imgCampoVazioResistencia;
    ImageView imgCampoVazioPotencia;



    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcular;

    EditText editTextTensao;
    EditText editTextCorrente;
    EditText editTextResitencia;
    EditText editTextPOtencia;

    ImageButton btnAvisoEdson;

    LinearLayout layotInferiorAviso;

    int Controle = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_de_ohms_continua);



        btnLimpar = findViewById(R.id.btnVoltar);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnVoltar = findViewById(R.id.btnVoltar);

        btnTensaoSelect = findViewById(R.id.btnTensaoSelect);
        imgCampoVazioTensao = findViewById(R.id.imgCampoVazioTensao);
        imgCampoVazioTensao.setVisibility(View.GONE);
        editTextTensao = findViewById(R.id.editTextTensao);
        editTextTensao.setVisibility(View.GONE);


        btnCorrenteSelect = findViewById(R.id.btnCorrenteSelect);
        imgCampoVazioCorrente = findViewById(R.id.imgCampoVazioCorrente);
        imgCampoVazioCorrente.setVisibility(View.GONE);
        editTextCorrente= findViewById(R.id.editTextCorrente);
        editTextCorrente.setVisibility(View.GONE);


        btnResistenciaSelect = findViewById(R.id.btnResistenciaSelect);
        imgCampoVazioResistencia = findViewById(R.id.imgCampoVazioResistencia);
        imgCampoVazioResistencia.setVisibility(View.GONE);
        editTextResitencia= findViewById(R.id.editTextResistencia);
        editTextResitencia.setVisibility(View.GONE);


        btnPotenciaSelect = findViewById(R.id.btnPotenciaSelect);
        imgCampoVazioPotencia = findViewById(R.id.imgCampoVazioPotencia);
        imgCampoVazioPotencia.setVisibility(View.GONE);
        editTextPOtencia= findViewById(R.id.editTextPOtencia);
        editTextPOtencia.setVisibility(View.GONE);

        //btnTesla = findViewById(R.id.btnTesla);
        btnEdson = findViewById(R.id.btnEdson);

         btnAvisoEdson = findViewById(R.id.ButtonAvisoEdson);
         btnAvisoEdson.setVisibility(View.GONE);

         layotInferiorAviso = findViewById(R.id.layotInferiorAviso);

        layoutGeral  = findViewById(R.id.layoutGeral);

        layoutCampoTensao  = findViewById(R.id.layoutCampoTensao);
        // layoutCampoTensao.setVisibility(View.GONE);



        layoutRaioTeslaEdson = findViewById(R.id.layoutRaioTeslaEdson);
      // layoutRaioTeslaEdson.setBackgroundResource(0);


      // layoutEditTextContinua = findViewById(R.id.layoutEditTextContinua);
       // layoutEditTextContinua.setVisibility(View.GONE);



         layoutBotoesOhmsContinua = findViewById(R.id.layoutBotoesOhmsContinua);
        // layoutBotoesOhmsContinua.setVisibility(View.GONE);






        btnTensaoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (btnTensaoClick) {

                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_off);
                    imgCampoVazioTensao.setVisibility(View.GONE);
                    editTextTensao.setVisibility(View.GONE);
                    Controle --;
                    btnTensaoClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }

                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);
                    imgCampoVazioTensao.setVisibility(View.VISIBLE);
                    editTextTensao.setVisibility(View.VISIBLE);
                    editTextTensao.requestFocus();
                    Controle ++;
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
                    Controle --;
                    btnCorrenteClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }

                    btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);
                    imgCampoVazioCorrente.setVisibility(View.VISIBLE);
                    editTextCorrente.setVisibility(View.VISIBLE);
                    editTextCorrente.requestFocus();
                    Controle ++;
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
                    Controle --;
                    btnResistenciaClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }

                    btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);
                    imgCampoVazioResistencia.setVisibility(View.VISIBLE);
                    editTextResitencia.setVisibility(View.VISIBLE);
                    editTextResitencia.requestFocus();
                    Controle ++;
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
                    Controle --;
                    btnPotenciaClick = false;
                } else {

                    if(Controle >= 2) {
                        erroSelecao();
                        return; // Interromper a execução da função
                    }


                    btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);
                    imgCampoVazioPotencia.setVisibility(View.VISIBLE);
                    editTextPOtencia.setVisibility(View.VISIBLE);
                    editTextPOtencia.requestFocus();
                    Controle ++;
                    btnPotenciaClick = true;
                }

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEletrica = new Intent(LeiDeOhmsContinuaActivity.this, MenuPrincipal.class);
                startActivity(telaEletrica);
                finish();
            }
        });

        btnAvisoEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layotInferiorAviso.setVisibility(View.GONE);
                btnAvisoEdson.setVisibility(View.GONE);


            }

        });

    }


// ...


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

        //piscaRaio();


        layotInferiorAviso.setBackgroundResource(R.drawable.img_aviso_edson);
        //layoutGeral.setBackgroundResource(0);
        //Snackbar snackbar = Snackbar.make(layoutGeral, "Erro encontrado", Snackbar.LENGTH_LONG);
       // snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.black));
       // snackbar.show();



    }



}