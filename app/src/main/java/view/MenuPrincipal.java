package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import devandroid.zocarato.ferramentadebolso.AreaActivity;
import devandroid.zocarato.ferramentadebolso.ContadorActivity;
import devandroid.zocarato.ferramentadebolso.JurosCompostoActivity;
import devandroid.zocarato.ferramentadebolso.LeiDeOhmsActivity;
import devandroid.zocarato.ferramentadebolso.MenuEletricaActivity;
import devandroid.zocarato.ferramentadebolso.PesoActivity;
import devandroid.zocarato.ferramentadebolso.PorcentagemActivity;
import devandroid.zocarato.ferramentadebolso.R;
import util.UltilidadesGeral;

public class MenuPrincipal extends AppCompatActivity {

    String bloqueio = ("28/06/2023");
    String TAG = "Layout principal";

    UltilidadesGeral dataHoraSistema;

    private Handler handler;
    private Runnable runnable;
    ImageButton btn_Eletrica_Menu;
    ImageButton btn_Comprimento_Menu;
    ImageButton btn_Pressao_Menu;
    ImageButton btn_Temperatura_Menu;
    ImageButton btn_Porcentagem_Menu;

    ImageButton btn_Area_Menu;
    ImageButton btn_Peso_Menu;
    ImageButton btn_Juros_Composto;
    ImageButton btn_Contador;
    ImageButton btn_LeiDeOhms;



    TextView txtDataSistema;
    TextView txtHoraSistema;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        dataHoraSistema = new UltilidadesGeral();
        //horaSistema = new UltilidadesGeral();

        //dataSistema.dataDoSistema();

        txtDataSistema = findViewById(R.id.txtDataSistemaXml);
        txtHoraSistema = findViewById(R.id.txtHoraSistemaXml);

        btn_Eletrica_Menu = findViewById(R.id.btnEletricaMenuXml);
        btn_Comprimento_Menu = findViewById(R.id.btnComprimentoMenuXml);
        btn_Pressao_Menu = findViewById(R.id.btnPressaoMenuXml);
        btn_Temperatura_Menu = findViewById(R.id.btnTemperaturaMenu);
        btn_Porcentagem_Menu = findViewById(R.id.btnPorcentagemMenu);
        btn_Area_Menu = findViewById(R.id.btnAreaMenu);
        btn_Peso_Menu = findViewById(R.id.btnPesuMenu);
        btn_Juros_Composto = findViewById(R.id.btn_juros_composto);
        btn_Contador = findViewById(R.id.btn_contador);
        btn_LeiDeOhms = findViewById(R.id.btnLeiDeOhms);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                txtHoraSistema.setText(dataHoraSistema.horarioDoSistema());
                handler.postDelayed(this, 1000); // Atualiza a cada 1 segundo
            }
        };

        txtDataSistema.setText(dataHoraSistema.dataDoSistema());
       // txtHoraSistema.setText(dataHoraSistema.horarioDoSistema());
        // Pegar Click dos Botoes




        btn_Eletrica_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, MenuEletricaActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });
        // ============================================================================
        btn_Comprimento_Menu.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, ComprimentoNewActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

             }


        });
        // ============================================================================
        btn_Pressao_Menu.setOnClickListener(new View.OnClickListener() {

            //========================================
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, PressaoActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }


        });
        // ============================================================================
        btn_Temperatura_Menu.setOnClickListener(new View.OnClickListener() {

               @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, TemperaturaActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }
        });
        // ============================================================================
        btn_Porcentagem_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, PorcentagemActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }
        });
        // ============================================================================
        btn_Area_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, AreaActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }
        });
        // ============================================================================

        btn_Peso_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, PesoActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });
        // ============================================================================

        btn_Juros_Composto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, JurosCompostoActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });


        btn_Contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, ContadorActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });
        btn_LeiDeOhms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, LeiDeOhmsActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });










    }

    public void abrirFacebook(View view) {
        String facebookUrl = "https://www.facebook.com/profile.php?id=100093407326893&mibextid=ZbWKwL"; // Substitua pelo URL do perfil do Facebook desejado

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + facebookUrl)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
        }
    }


    public void abrirInsta(View view) {
        String instagramUrl = "https://instagram.com/zocarato_dev?igshid=MzRlODBiNWFlZA=="; // Substitua pelo URL do perfil do Facebook desejado

        try {
            // Abre o aplicativo do Instagram, se instalado
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)));
        } catch (Exception e) {
            // Se o aplicativo do Instagram não estiver instalado, abre o navegador com o link do perfil
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)));
        }
    }

    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 0);
    }
}