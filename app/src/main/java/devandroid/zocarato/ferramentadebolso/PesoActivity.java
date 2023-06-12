package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import view.MenuPrincipal;

public class PesoActivity extends AppCompatActivity {

    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcular;

    EditText editTextKilo;
    EditText editTextLibra;
    EditText editTextOnca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);

        editTextKilo = findViewById(R.id.textFielKilograma);
        editTextLibra = findViewById(R.id.textFielLibra);
        editTextOnca = findViewById(R.id.textFieldOnca);

        btnVoltar = findViewById(R.id.btnVoltarMenu);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular = findViewById(R.id.btnCalcular);








        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(PesoActivity.this, MenuPrincipal.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });


        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implemente o código para limpar os campos de entrada
                editTextKilo.setText("");
                editTextLibra.setText("");
                editTextOnca.setText("");
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!camposVazios()) {
                    // Verifica qual campo foi o último a ser preenchido
                    if (!editTextKilo.getText().toString().isEmpty()) {
                        // Chama a função de conversão de quilogramas para outras unidades
                        converterQuilograma();
                    } else if (!editTextLibra.getText().toString().isEmpty()) {
                        // Chama a função de conversão de libras para outras unidades
                        converterLibra();
                    } else if (!editTextOnca.getText().toString().isEmpty()) {
                        // Chama a função de conversão de onças para outras unidades
                        converterOnca();
                    }
                }
            }
        });
    }

    private boolean camposVazios() {
        // Verifica se algum campo de entrada está vazio
        return editTextKilo.getText().toString().isEmpty() && editTextLibra.getText().toString().isEmpty()
                && editTextOnca.getText().toString().isEmpty();
    }

    private void converterQuilograma() {
        // Implemente a lógica para converter quilogramas para outras unidades
        double quilograma = Double.parseDouble(editTextKilo.getText().toString());
        double libra = quilograma * 2.20462;
        double onca = quilograma * 35.27396;

        // Exiba os resultados ou faça o que for necessário com os valores convertidos
        editTextLibra.setText(String.valueOf(libra));
        editTextOnca.setText(String.valueOf(onca));
    }

    private void converterLibra() {
        // Implemente a lógica para converter libras para outras unidades
        double libra = Double.parseDouble(editTextLibra.getText().toString());
        double quilograma = libra * 0.45359;
        double onca = libra * 16;

        // Exiba os resultados ou faça o que for necessário com os valores convertidos
        editTextKilo.setText(String.valueOf(quilograma));
        editTextOnca.setText(String.valueOf(onca));
    }

    private void converterOnca() {
        // Implemente a lógica para converter onças para outras unidades
        double onca = Double.parseDouble(editTextOnca.getText().toString());
        double quilograma = onca * 0.02835;
        double libra = onca * 0.0625;

        // Exiba os resultados ou faça o que for necessário com os valores convertidos
        editTextKilo.setText(String.valueOf(quilograma));
        editTextLibra.setText(String.valueOf(libra));
    }
}


