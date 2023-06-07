package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import util.UltilidadeEletrica;

public class DimensionamentoActivity extends AppCompatActivity {

    UltilidadeEletrica dimensionamentoCabo;
    UltilidadeEletrica dimensionamentoDsjuntor;

    Button btnCalcular;
    Button btnVoltar;

    TextView txtResultadoCabo;
    TextView txtResultadoDisjuntor;

    EditText corrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensionamento);

        dimensionamentoCabo = new UltilidadeEletrica();
        dimensionamentoDsjuntor = new UltilidadeEletrica();

        btnCalcular = findViewById(R.id.btnCalcularDimensionamento);
        btnVoltar = findViewById(R.id.btnVoltarDimensionamento);

        corrente = findViewById(R.id.editCorrenteDimensionamento);

        txtResultadoCabo = findViewById(R.id.txtCaboresultado);
        txtResultadoDisjuntor = findViewById(R.id.txtDsjuntorResultado);



        btnCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean dadosOk = true;


                if(TextUtils.isEmpty(corrente.getText())){
                    corrente.setError("* Obrigatório");
                    corrente.requestFocus();  // faz com que o cursor volte para o campo
                    dadosOk = false;
                }
                if (dadosOk == true){
                    txtResultadoCabo.setText(dimensionamentoCabo.dimensionamentoCabo(corrente));
                    txtResultadoDisjuntor.setText(dimensionamentoDsjuntor.dimensionamentoDisjuntor(txtResultadoCabo));


                }



            }
        });



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(DimensionamentoActivity.this, MenuEletricaActivity.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });

    }
}