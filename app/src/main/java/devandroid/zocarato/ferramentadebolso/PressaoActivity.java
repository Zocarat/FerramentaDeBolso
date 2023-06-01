package devandroid.zocarato.ferramentadebolso;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;


        import java.util.ArrayList;
        import java.util.List;

        import view.MenuPrincipal;

public class PressaoActivity extends AppCompatActivity {

    Spinner spinnerPressao;
    EditText editPressao;
    ListView listResultados;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao);

        btnVoltar = findViewById(R.id.btnVoltarMenu);
        spinnerPressao = findViewById(R.id.spinnerPressao);
        editPressao = findViewById(R.id.editPressao);
        listResultados = findViewById(R.id.listResultados);

        // Criar um adapter com as opções da lista
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unidades_pressao,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPressao.setAdapter(adapter);

        Button btnCalcularPressao = findViewById(R.id.btnCalcularPressao);
        btnCalcularPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorDigitado = editPressao.getText().toString();

                if (valorDigitado.isEmpty()) {
                    // O campo de texto está vazio, exibir mensagem de erro ao usuário
                    Toast.makeText(PressaoActivity.this, "Digite um valor de pressão", Toast.LENGTH_SHORT).show();
                } else {
                    // O campo de texto contém um valor, realizar a conversão
                    calcularConversao();
                }
            }
        });

        Button btnLimpar = findViewById(R.id.btnLimPartPressao);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparDados();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(PressaoActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });
    }

    private void calcularConversao() {
        String unidadeSelecionada = spinnerPressao.getSelectedItem().toString();
        String valorDigitado = editPressao.getText().toString();

        List<String> resultados = new ArrayList<>();

        // Realize as conversões e adicione os resultados à lista
        if (!valorDigitado.isEmpty()) {
            resultados.add("Pascal (Pa): " + converterParaPascal(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Bar (bar): " + converterParaBar(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Kilograma-força por centímetro quadrado (kgf/cm²): " + converterParaKilogramaForcaCm2(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Atmosfera (atm): " + converterParaAtmosfera(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Milímetro de mercúrio (mmHg): " + converterParaMilimetroHg(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Libra por polegada quadrada (psi): " + converterParaPsi(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Torr (Torr): " + converterParaTorr(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Kilopascal (kPa): " + converterParaQuilopascal(Double.parseDouble(valorDigitado), unidadeSelecionada));
            resultados.add("Newton por metro quadrado (N/m²): " + converterParaNewtonMetroQuadrado(Double.parseDouble(valorDigitado), unidadeSelecionada));
        }

        // Crie um adapter para exibir a lista de resultados no ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultados
        );
        listResultados.setAdapter(adapter);
    }

    private double converterParaPascal(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Bar (bar)")) {
            resultado = valor * 100000; // 1 bar = 100000 Pa
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 101325; // 1 atm = 101325 Pa
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor * 133.322; // 1 Torr = 133.322 Pa
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 6894.757; // 1 psi = 6894.757 Pa
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor * 133.322; // 1 mmHg = 133.322 Pa
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 1000; // 1 kPa = 1000 Pa
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor; // Já está em Pascal
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 98066.5; // 1 kgf/cm² = 98066.5 Pa
        }

        return resultado;
    }

    private double converterParaBar(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor / 100000; // 1 Pa = 0.00001 bar
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 0.98692; // 1 atm = 0.98692 bar
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor * 0.00133322; // 1 Torr = 0.00133322 bar
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 0.0689476; // 1 psi = 0.0689476 bar
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor * 0.00133322; // 1 mmHg = 0.00133322 bar


        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor / 100; // 1 kPa = 0.01 bar
            int i = 0;


        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor / 100000; // 1 N/m² = 0.00001 bar
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 14.2233; // 1 kgf/cm² = 14.2233 bar
        }

        return resultado;
    }

    private double converterParaKilogramaForcaCm2(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor * 0.0000101972; // 1 Pa = 0.0000101972 kgf/cm²
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 1.01972; // 1 bar = 1.01972 kgf/cm²
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 1.03323; // 1 atm = 1.03323 kgf/cm²
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor * 0.00135951; // 1 Torr = 0.00135951 kgf/cm²
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 0.070307; // 1 psi = 0.070307 kgf/cm²
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor * 0.00133322; // 1 mmHg = 0.00133322 kgf/cm²
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 0.0101972; // 1 kPa = 0.0101972 kgf/cm²
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor * 0.0000101972; // 1 N/m² = 0.0000101972 kgf/cm²
        }

        return resultado;
    }

    private double converterParaAtmosfera(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor / 101325; // 1 Pa = 0.00000987 atm
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor / 1.01325; // 1 bar = 0.98692 atm
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor / 760; // 1 Torr = 0.00131579 atm
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor / 14.6959; // 1 psi = 0.068046 atm
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor / 760; // 1 mmHg = 0.00131579 atm
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor / 101.325; // 1 kPa = 0.009869 atm
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor / 101325; // 1 N/m² = 0.00000987 atm
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 0.967841; // 1 kgf/cm² = 0.967841 atm
        }

        return resultado;
    }

    private double converterParaMilimetroHg(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor / 133.322; // 1 Pa = 0.00750062 mmHg
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor / 0.00133322; // 1 bar = 750.062 mmHg
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor / 0.00131579; // 1 atm = 760 mmHg
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor; // Já está em mmHg
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor / 51.7149; // 1 psi = 51.7149 mmHg
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 7.501; // 1 kPa = 7.50062 mmHg
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor / 133.322; // 1 N/m² = 0.00750062 mmHg
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 0.0193368; // 1 kgf/cm² = 0.0193368 mmHg
        }

        return resultado;
    }

    private double converterParaPsi(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            double resultadoTemp = valor / 6894.757; // 1 Pa = 0.0001450377 psi
            resultado = Double.parseDouble(String.format("%.9f", resultadoTemp)); // Arredondamento para 9 casas decimais

            int i = 0;
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 14.5038; // 1 bar = 14.5038 psi
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor / 0.068046; // 1 atm = 14.6959 psi
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor / 51.7149; // 1 Torr = 0.0193368 psi
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor / 51.7149; // 1 mmHg = 0.0193368 psi
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor / 6.89476; // 1 kPa = 0.145038 psi
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor / 6894.757; // 1 N/m² = 0.0001450377 psi
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 14.2233; // 1 kgf/cm² = 14.2233 psi
        }

        return resultado;
    }

    private double converterParaTorr(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor * 7.50062; // 1 Pa = 7.50062 Torr
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 750.062; // 1 bar = 750.062 Torr
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 760; // 1 atm = 760 Torr
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 51.7149; // 1 psi = 51.7149 Torr
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor * 1.33322; // 1 mmHg = 1.33322 Torr
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 7.50062; // 1 kPa = 7.50062 Torr
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor * 7.50062; // 1 N/m² = 7.50062 Torr
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 14.6959; // 1 kgf/cm² = 14.6959 Torr
        }

        return resultado;
    }

    private double converterParaQuilopascal(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor / 1000; // 1 Pa = 0.001 kPa
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 100; // 1 bar = 100 kPa
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 98.0665; // 1 kgf/cm² = 98.0665 kPa
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 101.325; // 1 atm = 101.325 kPa
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor / 7.50062; // 1 mmHg = 0.133322 kPa
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 6.89476; // 1 psi = 6.89476 kPa
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor / 1000; // 1 N/m² = 0.001 kPa
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 0.0101972; // 1 kgf/cm² = 0.0101972 kPa
        }

        return resultado;
    }

    private double converterParaNewtonMetroQuadrado(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor; // Já está em N/m²
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 100; // 1 bar = 100 N/m²
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 98066.5; // 1 kgf/cm² = 98066.5 N/m²
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 101325; // 1 atm = 101325 N/m²
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor / 133.322; // 1 mmHg = 133.322 N/m²
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 6894.757; // 1 psi = 6894.757 N/m²
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 1000; // 1 kPa = 1000 N/m²
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor * 9.80665; // 1 kgf/cm² = 9.80665 N/m²
        }

        return resultado;
    }

    private double converterParaKilogramaForcaPorCmQuadrado(double valor, String unidade) {
        double resultado = 0.0;

        if (unidade.equals("Pascal (Pa)")) {
            resultado = valor * 0.0000101972; // 1 Pa = 0.0000101972 kgf/cm²
        } else if (unidade.equals("Bar (bar)")) {
            resultado = valor * 1.01972; // 1 bar = 1.01972 kgf/cm²
        } else if (unidade.equals("Atmosfera (atm)")) {
            resultado = valor * 1.03323; // 1 atm = 1.03323 kgf/cm²
        } else if (unidade.equals("Torr (Torr)")) {
            resultado = valor * 0.00135951; // 1 Torr = 0.00135951 kgf/cm²
        } else if (unidade.equals("Libra por polegada quadrada (psi)")) {
            resultado = valor * 0.070307; // 1 psi = 0.070307 kgf/cm²
        } else if (unidade.equals("Milímetro de mercúrio (mmHg)")) {
            resultado = valor * 0.00133322; // 1 mmHg = 0.00133322 kgf/cm²
        } else if (unidade.equals("Quilopascal (kPa)")) {
            resultado = valor * 0.0101972; // 1 kPa = 0.0101972 kgf/cm²
        } else if (unidade.equals("Newton por metro quadrado (N/m²)")) {
            resultado = valor * 0.0000101972; // 1 N/m² = 0.0000101972 kgf/cm²
        } else if (unidade.equals("Kilograma-força por centímetro quadrado (kgf/cm²)")) {
            resultado = valor; // Já está em kgf/cm²
        }

        return resultado;
    }

    private void limparDados() {
        editPressao.setText(""); // Limpa o campo de texto
        listResultados.setAdapter(null); // Limpa a lista de resultados
    }



}