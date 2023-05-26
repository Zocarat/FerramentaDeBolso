package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuedaDeTensaoActivity extends AppCompatActivity {

    Button btnVoltar;
    Button btnCalcular;

    EditText editTextcorrente;
    EditText editTextTensao;
    EditText editTextBitola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queda_de_tensao);

        btnVoltar = findViewById(R.id.btnVoltarQuedaDeTensao);
        btnCalcular = findViewById(R.id.btnCalcularQuedaDeTensao);

        editTextBitola = findViewById(R.id.editBitolaQueda);
        //editTextTensao = findViewById(R.id.editTensaoQueda);
        editTextcorrente = findViewById(R.id.editCorrenteQueda);



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(QuedaDeTensaoActivity.this, MenuEletricaActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });

    }
}