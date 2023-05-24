package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import devandroid.zocarato.ferramentadebolso.R;
import util.UltilidadesGeral;

public class MenuPrincipal extends AppCompatActivity {

    String TAG = "Layout principal";

    UltilidadesGeral dataHoraSistema;


    Button btn_Eletrica_Menu;
    Button btn_Comprimento_Menu;
    Button btn_Pressao_Menu;
    Button btn_Temperatura_Menu;

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

        txtDataSistema.setText(dataHoraSistema.dataDoSistema());
        txtHoraSistema.setText(dataHoraSistema.horarioDoSistema());
        // Pegar Click dos Botoes




        btn_Eletrica_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(MenuPrincipal.this, GrandezasEletricaActivity.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });
        btn_Comprimento_Menu.setOnClickListener(new View.OnClickListener() {

//========================================
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuPrincipal.this, ComprimentoActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

             }
// ========================================

        });
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
// ========================================

        });

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




    }
}