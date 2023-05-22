package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import devandroid.zocarato.ferramentadebolso.R;
import devandroid.zocarato.ferramentadebolso.TemperaturaActivity;

public class MenuPrincipal extends AppCompatActivity {

    String TAG = "Layout principal";

    Button btn_Comprimento_Menu;
    Button btn_Pressao_Menu;
    Button btn_Temperatura_Menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_Comprimento_Menu = findViewById(R.id.btnComprimentoMenu);
        btn_Pressao_Menu = findViewById(R.id.btnPressaoMenu);
        btn_Temperatura_Menu = findViewById(R.id.btnTemperaturaMenu);
        // Pegar Click dos Botoes

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