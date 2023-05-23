package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import devandroid.zocarato.ferramentadebolso.R;

public class EletricaActivity extends AppCompatActivity {

    Button btnVoltarMenu;
    Button btnLimparEletrica;
    Button btnCalcularEletrica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eletrica);

        btnVoltarMenu = findViewById(R.id.btnVoltarMenuEletrica);
        btnLimparEletrica = findViewById(R.id.btnLimparMenuEletrica);
        btnCalcularEletrica = findViewById(R.id.btnCalcularEletrica);


        btnLimparEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaMenu = new Intent(EletricaActivity.this, MenuPrincipal.class);
                        startActivity(telaMenu);
                        finish();
                    }
                },0);

            }
        });


    }



}