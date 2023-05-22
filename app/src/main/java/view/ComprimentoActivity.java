package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import devandroid.zocarato.ferramentadebolso.R;
import util.Utilidades;

public class ComprimentoActivity extends AppCompatActivity {

    Utilidades menuPrincipal;
    Button btnMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimento);

        //menuPrincipal = new Utilidades();

        btnMenuPrincipal = findViewById(R.id.btnVoltarMenu);

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(ComprimentoActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);

            }

        });
    }
}