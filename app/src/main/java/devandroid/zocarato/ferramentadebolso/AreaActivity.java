package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import view.MenuPrincipal;
import view.PressaoActivity;

public class AreaActivity extends AppCompatActivity {


    ImageButton btnVoltar;

    ImageButton btnLimpar;

    EditText editTextBase;
    EditText editTextAltura;
    TextView resultadoArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        btnLimpar = findViewById(R.id.btnLimparArea);
        btnVoltar = findViewById(R.id.btnVoltarMenuArea);

        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
        resultadoArea = findViewById(R.id.resultadoArea);



        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarAoMenuPrincipal();
            }
        });

        editTextBase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Não é necessário implementar essa parte do ouvinte
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularArea();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Não é necessário implementar essa parte do ouvinte
            }
        });

        editTextAltura.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Não é necessário implementar essa parte do ouvinte
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularArea();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Não é necessário implementar essa parte do ouvinte
            }
        });
    }

    private void limparCampos() {
        editTextBase.setText("");
        editTextAltura.setText("");
        resultadoArea.setText("");
    }

    private void voltarAoMenuPrincipal() {
        Intent telaPrincipal = new Intent(AreaActivity.this, MenuPrincipal.class);
        startActivity(telaPrincipal);
        finish();
    }

    private void calcularArea() {
        String baseText = editTextBase.getText().toString();
        String alturaText = editTextAltura.getText().toString();

        if (!baseText.isEmpty() && !alturaText.isEmpty()) {
            try {
                double base = Double.parseDouble(baseText);
                double altura = Double.parseDouble(alturaText);

                double area = base * altura;

                resultadoArea.setText(String.valueOf(area));
            } catch (NumberFormatException e) {
                resultadoArea.setText("");
                Toast.makeText(AreaActivity.this, "Valores inválidos", Toast.LENGTH_SHORT).show();
            }
        } else {
            resultadoArea.setText("");
        }
    }
}