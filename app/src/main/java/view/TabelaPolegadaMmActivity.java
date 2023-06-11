package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import devandroid.zocarato.ferramentadebolso.R;

public class TabelaPolegadaMmActivity extends AppCompatActivity {

    Button btnVoltarTelaCompriento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polegada_milimetro);

        btnVoltarTelaCompriento = findViewById(R.id.btnVoltarTabelaPolegadaMilimetro);


        btnVoltarTelaCompriento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent telaComprimento = new Intent(TabelaPolegadaMmActivity.this, ComprimentoNewActivity.class);
                        startActivity(telaComprimento);
                        finish();
                    }
                },0);
            }
        });


    }
}