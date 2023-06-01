package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class QuedaDeTensaoActivity extends AppCompatActivity {
    Button btnVoltar;
    Button btnCalcular;

    EditText editTextCorrente;
    EditText editTextBitola;
    EditText editTextDistancia;
    EditText editTextTensao;



    TextView txtResultadoQueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queda_de_tensao);

        btnVoltar = findViewById(R.id.btnVoltarQuedaDeTensao);
        btnCalcular = findViewById(R.id.btnCalcularQuedaDeTensao);

        editTextCorrente = findViewById(R.id.editCorrenteQueda);
        editTextBitola = findViewById(R.id.editBitolaQueda);
        editTextDistancia = findViewById(R.id.editDistanciaQueda);
        editTextTensao = findViewById(R.id.editTensaoQueda);

        txtResultadoQueda = findViewById(R.id.txtResultadoQueda);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularQuedaTensao();
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
                }, 0);
            }
        });
    }

    private void calcularQuedaTensao() {
        double resistividadeCobre = 0.0172;

        String strTensao = editTextTensao.getText().toString();
        String strCorrente = editTextCorrente.getText().toString();
        String strBitola = editTextBitola.getText().toString();
        String strDistancia = editTextDistancia.getText().toString();

        if (strTensao.isEmpty() || strCorrente.isEmpty() || strBitola.isEmpty() || strDistancia.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double tensao = Double.parseDouble(strTensao);
        double corrente = Double.parseDouble(strCorrente);
        double bitola = Double.parseDouble(strBitola);
        double distancia = Double.parseDouble(strDistancia);

        // Conversão da unidade da distância (exemplo: de metros para quilômetros)
        double distanciaConvertida = distancia ;

        // Cálculo da área da seção transversal do condutor em mm²
        double areaCondutor = bitola;

        // Cálculo da resistência do condutor em ohms
        double resistencia =(( resistividadeCobre * distanciaConvertida )/ areaCondutor);



        // Cálculo da queda de tensão em volts
        double quedaTensao = (2 * resistencia * corrente * distanciaConvertida) / 1000;

        // Cálculo da porcentagem de perda
        double porcentagemPerda = (quedaTensao / tensao) * 100;

        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        String resultadoFormatado = decimalFormat.format(quedaTensao);
        String porcentagemFormatada = decimalFormat.format(porcentagemPerda);

        String textoResultado = "A queda de tensão será de " + resultadoFormatado + " V (" + porcentagemFormatada + "%).";

        txtResultadoQueda.setText(textoResultado);
        txtResultadoQueda.setVisibility(View.VISIBLE);

        if (porcentagemPerda >= 4) {
            Toast.makeText(this, "Atenção: A queda de tensão está acima do limite permitido pela norma NBR 5410.", Toast.LENGTH_LONG).show();
        }
    }



}