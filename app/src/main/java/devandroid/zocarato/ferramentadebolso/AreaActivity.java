package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AreaActivity extends AppCompatActivity {
    ImageButton btnVoltar;
    ImageButton btnLimpar;

    LinearLayout linearLayout2D;
    EditText editTextBase2D;
    EditText editTextAltura2D;
    TextView textViewResultado2D;

    EditText editTextX3D;
    EditText editTextY3D;
    EditText editTextZ3D;
    TextView textViewResultado3D;

    EditText editTextDiametro;
    TextView textViewDiametroResultado;

    LinearLayout linearLayout3D;
    LinearLayout linearLayoutCirculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        btnLimpar = findViewById(R.id.btnLimparArea);
        btnVoltar = findViewById(R.id.btnVoltarMenuArea);

        linearLayout2D = findViewById(R.id.layoutCalculo2D);
        linearLayout3D = findViewById(R.id.layoutCalculo3D);
        linearLayoutCirculo = findViewById(R.id.layoutCalculoCirculo);

        editTextBase2D = findViewById(R.id.editTextLargura2D);
        editTextAltura2D = findViewById(R.id.editTextAltura2D);
        textViewResultado2D = findViewById(R.id.textViewResultado2D);


        editTextX3D = findViewById(R.id.editTextX3D);
        editTextY3D = findViewById(R.id.editTextY3D);
        editTextZ3D = findViewById(R.id.editTextZ3D);
        textViewResultado3D= findViewById(R.id.textViewResultado3D);

        editTextDiametro = findViewById(R.id.editTextDiametro);
        textViewDiametroResultado = findViewById(R.id.textViewResultadoDiametro);

        // Associe o TextWatcher aos campos de texto
        editTextBase2D.addTextChangedListener(new CustomTextWatcher(editTextBase2D));
        editTextAltura2D.addTextChangedListener(new CustomTextWatcher(editTextAltura2D));

        editTextX3D.addTextChangedListener(new CustomTextWatcher(editTextX3D));
        editTextY3D.addTextChangedListener(new CustomTextWatcher(editTextY3D));
        editTextZ3D.addTextChangedListener(new CustomTextWatcher(editTextZ3D));
        editTextDiametro.addTextChangedListener(new CustomTextWatcher(editTextDiametro));


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
    }

    private void limparCampos() {
        // Implemente a lógica para limpar os campos aqui
        editTextBase2D.setText("");
        editTextAltura2D.setText("");
        editTextX3D.setText("");
        editTextY3D.setText("");
        editTextZ3D.setText("");
        editTextDiametro.setText("");
    }

    private void voltarAoMenuPrincipal() {
        Intent telaPrincipal = new Intent(AreaActivity.this, MenuPrincipal.class);
        startActivity(telaPrincipal);
        finish();
    }

    private class CustomTextWatcher implements TextWatcher {
        private EditText editText;

        public CustomTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Nada a fazer aqui
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Atualize o valor do campo de texto conforme o usuário digita
            String input = s.toString();
            calcularArea2D();
            calcularArea3D();
            calcularAreaCirculo ();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Nada a fazer aqui
        }
    }
    private void calcularArea2D() {
        // Obtenha os valores dos campos de texto
        String larguraStr = editTextBase2D.getText().toString();
        String alturaStr = editTextAltura2D.getText().toString();

        // Verifique se os valores são válidos
        if (!larguraStr.isEmpty() && !alturaStr.isEmpty()) {
            double largura = Double.parseDouble(larguraStr);
            double altura = Double.parseDouble(alturaStr);

            // Faça o cálculo da área
            double area = largura * altura;

            // Atualize o resultado
            textViewResultado2D.setText(String.valueOf(area));
        } else {
            // Se algum valor estiver vazio, defina o resultado como vazio
            textViewResultado2D.setText("");
        }
    }

    private void calcularArea3D() {
        // Obtenha os valores dos campos de texto
        String XStr = editTextX3D.getText().toString();
        String YStr = editTextY3D.getText().toString();
        String ZStr = editTextZ3D.getText().toString();

        // Verifique se os valores são válidos
        if (!XStr.isEmpty() && !YStr.isEmpty()  && !ZStr.isEmpty()) {

            double X = Double.parseDouble(XStr);
            double Y = Double.parseDouble(YStr);
            double Z = Double.parseDouble(YStr);

            // Faça o cálculo da área
            double area = X * Y * Z;

            // Atualize o resultado
            textViewResultado3D.setText(String.valueOf(area));
        } else {
            // Se algum valor estiver vazio, defina o resultado como vazio
            textViewResultado3D.setText("");
        }



    }

    private void calcularAreaCirculo () {
        // Obtenha os valores dos campos de texto
        String diametroStr = editTextDiametro.getText().toString();


        // Verifique se os valores são válidos
        if (!diametroStr.isEmpty() ) {
            double raio = ( Double.parseDouble(diametroStr) / 2 );


            // Faça o cálculo da área
            double areaDiametro =  Math.PI * Math.pow(raio, 2);

            // Atualize o resultado
            textViewDiametroResultado.setText(String.valueOf(areaDiametro));
        } else {
            // Se algum valor estiver vazio, defina o resultado como vazio
            textViewDiametroResultado.setText("");
        }
    }

}