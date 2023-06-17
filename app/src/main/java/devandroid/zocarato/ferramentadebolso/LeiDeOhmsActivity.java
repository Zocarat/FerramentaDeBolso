package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import view.MenuPrincipal;

public class LeiDeOhmsActivity extends AppCompatActivity {




    private boolean isRaioVisible = false;
    private Handler handler;
    private Runnable raioRunnable;
    private int numPiscadas = 0;
    private int maxPiscadas = 6; // Define o número máximo de piscadas desejadas


    RelativeLayout layoutGeral;

    LinearLayout layoutRaioTeslaEdson;
    LinearLayout layoutBotoesOhmsContinua;

    LinearLayout   layoutEditTextContinua;

    ImageButton btnTesla;
    ImageButton btnEdson;




    ImageButton btnTensaoSelect;
    boolean btnTensaoClick = false;
    ImageButton btnCorrenteSelect;
    boolean btnCorrenteClick = false;

    ImageButton btnResistenciaSelect;
    boolean btnResistenciaClick = false;

    ImageButton btnPotenciaSelect;
    boolean btnPotenciaClick = false;

    ImageView imageView;
    ImageView imgLeiDeOhms;
    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcular;

    String[] opcoesGrandezas = {"Tensão", "Corrente", "Potência", "Resistência"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_de_ohms);

        imgLeiDeOhms= findViewById(R.id.imgLeiDeOhms);

        btnLimpar = findViewById(R.id.btnVoltar);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnTensaoSelect = findViewById(R.id.btnTensaoSelect);
        btnCorrenteSelect = findViewById(R.id.btnCorrenteSelect);
        btnResistenciaSelect = findViewById(R.id.btnResistenciaSelect);
        btnPotenciaSelect = findViewById(R.id.btnpotenciaSelect);

        btnTesla = findViewById(R.id.btnTesla);
        btnEdson = findViewById(R.id.btnEdson);



        layoutGeral  = findViewById(R.id.layoutGeral);

        layoutRaioTeslaEdson = findViewById(R.id.layoutRaioTeslaEdson);
        layoutRaioTeslaEdson.setBackgroundResource(0);


        layoutEditTextContinua = findViewById(R.id.layoutEditTextContinua);
        layoutEditTextContinua.setVisibility(View.GONE);

        layoutBotoesOhmsContinua = findViewById(R.id.layoutBotoesOhmsContinua);
        layoutBotoesOhmsContinua.setVisibility(View.GONE);
        animarImageButtonDaDireita( btnTesla);
        animarImageButtonDaEsquerda( btnEdson);
        piscaRaio();



        btnTensaoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnTensaoClick) {
                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_off);
                    btnTensaoClick = false;
                } else {
                    btnTensaoSelect.setImageResource(R.drawable.btn_tensao_on);
                    btnTensaoClick = true;
                }

            }
        });
        btnCorrenteSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCorrenteClick) {
                    btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_off);
                    btnCorrenteClick = false;
                } else {
                    btnCorrenteSelect.setImageResource(R.drawable.btn_corrente_on);
                    btnCorrenteClick = true;
                }

            }
        });
        btnResistenciaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnResistenciaClick) {
                    btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_off);
                    btnResistenciaClick = false;
                } else {
                    btnResistenciaSelect.setImageResource(R.drawable.btn_resistencia_on);
                    btnResistenciaClick = true;
                }

            }
        });
        btnPotenciaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnPotenciaClick) {
                    btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_off);
                    btnPotenciaClick = false;
                } else {
                    btnPotenciaSelect.setImageResource(R.drawable.btn_potencia_on);
                    btnPotenciaClick = true;
                }

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEletrica = new Intent(LeiDeOhmsActivity.this, MenuPrincipal.class);
                startActivity(telaEletrica);
                finish();
            }
        });

        btnEdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEletrica = new Intent(LeiDeOhmsActivity.this, LeiDeOhmsContinuaActivity.class);
                startActivity(telaEletrica);
                finish();
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
                    layoutRaioTeslaEdson.setBackgroundResource(0);



                } else {
                    layoutGeral.setBackgroundResource(R.color.black);
                    layoutRaioTeslaEdson.setBackgroundResource(R.drawable.img_background_raio);

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
        }, 1000); // Define um atraso de 1 segundo (1000ms) antes de iniciar o piscar
    }

    private void animarImageButtonDaEsquerda(ImageButton imageButton) {
        // Define as coordenadas iniciais e finais para a animação
        float startX = -imageButton.getWidth(); // Define a posição inicial fora da tela à esquerda
        float endX = 0; // Define a posição final como a posição atual

        // Criação da animação de deslocamento
        TranslateAnimation animation = new TranslateAnimation(startX, endX, 0, 0);
        animation.setDuration(1000); // Define a duração da animação em milissegundos (neste exemplo, 1 segundo)
        animation.setFillAfter(true); // Mantém a posição final da imagem após a animação

        // Aplica a animação ao ImageButton
        imageButton.startAnimation(animation);
    }
    private void animarImageButtonDaDireita(ImageButton imageButton) {
        // Define as coordenadas iniciais e finais para a animação
        float startX = imageButton.getWidth(); // Define a posição inicial fora da tela à direita
        float endX = 0; // Define a posição final como a posição atual

        // Criação da animação de deslocamento
        TranslateAnimation animation = new TranslateAnimation(startX, endX, 0, 0);
        animation.setDuration(5000); // Define a duração da animação em milissegundos (neste exemplo, 1 segundo)
        animation.setFillAfter(true); // Mantém a posição final da imagem após a animação

        // Aplica a animação ao ImageButton
        imageButton.startAnimation(animation);
    }



}