package devandroid.zocarato.ferramentadebolso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// Importe as classes necessárias
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import view.MenuPrincipal;


public class ComprimentoNewActivity extends AppCompatActivity {
    Spinner spinnerComprimento;
    EditText editComprimento;
    ListView listResultados;
    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnCalcularComprimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimento_new);

        btnVoltar = findViewById(R.id.btnVoltarMenu);
        spinnerComprimento = findViewById(R.id.spinnerUnidadeEntrada);
        editComprimento = findViewById(R.id.editComprimento);
        listResultados = findViewById(R.id.listResultados);
        btnLimpar = findViewById(R.id.btnLimparComprimento);
        btnCalcularComprimento = findViewById(R.id.btnCalcularComprimento);

        // Criar um adapter com as opções da lista
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unidades_comprimento,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComprimento.setAdapter(adapter);


        btnCalcularComprimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorDigitado = editComprimento.getText().toString();

                if (valorDigitado.isEmpty()) {
                    // O campo de texto está vazio, exibir mensagem de erro ao usuário
                    Toast.makeText(ComprimentoNewActivity.this, "Digite um valor de comprimento", Toast.LENGTH_SHORT).show();
                } else {
                    // O campo de texto contém um valor, realizar a conversão
                    calcularConversao();
                }
            }
        });


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
                        Intent telaPrincipal = new Intent(ComprimentoNewActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                }, 0);
            }
        });
    }

    private void calcularConversao() {
        String unidadeSelecionada = spinnerComprimento.getSelectedItem().toString();
        String valorDigitado = editComprimento.getText().toString();

        List<String> resultados = new ArrayList<>();

        // Realize as conversões e adicione os resultados à lista
        if (!valorDigitado.isEmpty()) {
            double valor = Double.parseDouble(valorDigitado);

            resultados.add("Milímetro (mm): " + converterParaMilimetro(valor, unidadeSelecionada));
            resultados.add("Centímetro (cm): " + converterParaCentimetro(valor, unidadeSelecionada));
            resultados.add("Metro (m): " + converterParaMetro(valor, unidadeSelecionada));
            resultados.add("Kilômetro (km): " + converterParaKilometro(valor, unidadeSelecionada));
            resultados.add("Polegada (in): " + converterParaPolegada(valor, unidadeSelecionada));
            resultados.add("Pé (ft): " + converterParaPe(valor, unidadeSelecionada));
            resultados.add("Jarda (yd): " + converterParaJarda(valor, unidadeSelecionada));
            resultados.add("Milha (mi): " + converterParaMilha(valor, unidadeSelecionada));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultados
        );
        listResultados.setAdapter(adapter);
    }

    private double converterParaCentimetro(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 10.0;
            case "Metro (m)":
                return valor * 100.0;
            case "Kilômetro (km)":
                return valor * 100000.0;
            case "Polegada (in)":
                return valor * 2.54;
            case "Pé (ft)":
                return valor * 30.48;
            case "Jarda (yd)":
                return valor * 91.44;
            case "Milha (mi)":
                return valor * 160934.0;
            default:
                return valor;
        }
    }

    private double converterParaMetro(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 1000.0;
            case "Centímetro (cm)":
                return valor / 100.0;
            case "Kilômetro (km)":
                return valor * 1000.0;
            case "Polegada (in)":
                return valor * 0.0254;
            case "Pé (ft)":
                return valor * 0.3048;
            case "Jarda (yd)":
                return valor * 0.9144;
            case "Milha (mi)":
                return valor * 1609.34;
            default:
                return valor;
        }
    }

    private double converterParaKilometro(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 1000000.0;
            case "Centímetro (cm)":
                return valor / 100000.0;
            case "Metro (m)":
                return valor / 1000.0;
            case "Polegada (in)":
                return valor * 0.0000254;
            case "Pé (ft)":
                return valor * 0.0003048;
            case "Jarda (yd)":
                return valor * 0.0009144;
            case "Milha (mi)":
                return valor * 1.60934;
            default:
                return valor;
        }
    }

    private String  converterParaPolegada(double valor, String unidade) {
        double resultado = 0.0;
        switch (unidade) {
            case "Milímetro (mm)":
                resultado = valor / 25.4;
                break;
            case "Centímetro (cm)":
                resultado = valor / 2.54;
                break;
            case "Metro (m)":
                resultado = valor * 39.37;
                break;
            case "Kilômetro (km)":
                resultado = valor * 39370.1;
                break;
            case "Pé (ft)":
                resultado = valor * 12.0;
                break;
            case "Jarda (yd)":
                resultado = valor * 36.0;
                break;
            case "Milha (mi)":
                resultado = valor * 63360.0;
                break;
            default:
                resultado = valor;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#0.####");
        String valorFormatado = decimalFormat.format(resultado);


        if (resultado >= 1){

            int parteInteira = (int) Math.floor(resultado);
            String fracao = obterFracao(resultado);


            return valorFormatado + " ==> (" + parteInteira + " " + fracao + ")";
        }else{
            String fracao = obterFracao(resultado);
            return valorFormatado + " ==> (" + fracao + ")";
        }



    }

    private double converterParaPe(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 304.8;
            case "Centímetro (cm)":
                return valor / 30.48;
            case "Metro (m)":
                return valor * 3.28084;
            case "Kilômetro (km)":
                return valor * 3280.84;
            case "Polegada (in)":
                return valor * 0.0833333;
            case "Jarda (yd)":
                return valor * 3.0;
            case "Milha (mi)":
                return valor * 5280.0;
            default:
                return valor;
        }
    }

    private double converterParaJarda(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 914.4;
            case "Centímetro (cm)":
                return valor / 91.44;
            case "Metro (m)":
                return valor * 1.09361;
            case "Kilômetro (km)":
                return valor * 1093.61;
            case "Polegada (in)":
                return valor * 0.0277778;
            case "Pé (ft)":
                return valor * 0.333333;
            case "Milha (mi)":
                return valor * 1760.0;
            default:
                return valor;
        }
    }

    private double converterParaMilha(double valor, String unidade) {
        switch (unidade) {
            case "Milímetro (mm)":
                return valor / 1609340.0;
            case "Centímetro (cm)":
                return valor / 160934.0;
            case "Metro (m)":
                return valor / 1609.34;
            case "Kilômetro (km)":
                return valor / 1.60934;
            case "Polegada (in)":
                return valor / 63360.0;
            case "Pé (ft)":
                return valor / 5280.0;
            case "Jarda (yd)":
                return valor / 1760.0;
            default:
                return valor;
        }
    }

    private double converterParaMilimetro(double valor, String unidade) {
        switch (unidade) {
            case "Centímetro (cm)":
                return valor * 10.0;
            case "Metro (m)":
                return valor * 1000.0;
            case "Kilômetro (km)":
                return valor * 1000000.0;
            case "Polegada (in)":
                return valor * 25.4;
            case "Pé (ft)":
                return valor * 304.8;
            case "Jarda (yd)":
                return valor * 914.4;
            case "Milha (mi)":
                return valor * 1609340.0;
            default:
                return valor;
        }
    }
    private String obterFracao(double valor) {
        int numerador = (int) Math.round((valor - Math.floor(valor)) * 128);
        int denominador = 128;

        int divisor = obterMaiorDivisorComum(numerador, denominador);
        numerador /= divisor;
        denominador /= divisor;

        if (denominador == 1) {
            return String.valueOf(numerador);
        } else {
            return numerador + "/" + denominador;
        }
    }

    private int obterMaiorDivisorComum(int a, int b) {
        if (b == 0) {
            return a;
        }
        return obterMaiorDivisorComum(b, a % b);
    }
    private void limparDados() {
        editComprimento.setText("");
        listResultados.setAdapter(null);
    }
}