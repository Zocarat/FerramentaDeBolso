package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import view.GrandezasEletricaActivity;
import view.MenuPrincipal;
import view.PressaoActivity;

public class MenuEletricaActivity extends AppCompatActivity {

    Button btnGrandezasEletricas;
    Button btnDimensionamento;
    Button btnQuedaDeTensao;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_eletrica);

        btnGrandezasEletricas = findViewById(R.id.btnGrandezasEletricas);
        btnDimensionamento = findViewById(R.id.btnDimensionamento);
        btnQuedaDeTensao = findViewById(R.id.btnQuedaDeTensao);
        btnVoltar = findViewById(R.id.btnVoltarMenuEletrica2);




        btnGrandezasEletricas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuEletricaActivity.this, GrandezasEletricaActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });


        btnDimensionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuEletricaActivity.this, DimensionamentoActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });

        btnQuedaDeTensao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuEletricaActivity.this, QuedaDeTensaoActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });





        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(MenuEletricaActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });


    }
}