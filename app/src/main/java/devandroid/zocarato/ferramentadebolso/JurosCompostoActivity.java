package devandroid.zocarato.ferramentadebolso;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import view.MenuPrincipal;
public class JurosCompostoActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private ImageButton btnLimpar;
    private ImageButton btnCalcular;

    private EditText editTextInicial;
    private EditText editTextMensal;
    private EditText editTextTaxa;
    private EditText editTextPeriodo;

    private DecimalFormat decimalFormat;
    private LineChart lineChart;


    private TextView textViewValorAcumulado;
    private TextView textViewRendimentoTotal;
    private TextView textViewRentabilidadeMensal;
   //  double valorTotal = 1;
  // double rendimentoTotal = 1;
    //double rentabilidadeMensal = 1;
 //  double valorInicial = 1;
  // double valorMensal = 1;

   //double taxa = 1;
   // int periodo = 1;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Não é necessário implementar neste caso
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Não é necessário implementar neste caso
        }

        public void afterTextChanged(Editable s) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Atualizar os valores exibidos acima do gráfico
                    double valorInicial = parseEditTextValue(editTextInicial);
                   double valorMensal = parseEditTextValue(editTextMensal);
                    double taxa = parseEditTextValue(editTextTaxa);
                    int periodo = parseEditTextIntValue(editTextPeriodo);



                   double valorTotal = calcularJurosComposto(valorInicial, valorMensal, taxa, periodo);
                   double rendimentoTotal = valorTotal - (valorInicial + (valorMensal * periodo));
                   double rentabilidadeMensal = rendimentoTotal / periodo;

                    try {DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                       textViewValorAcumulado.setText("Valor Acumulado: R$ " + decimalFormat.format(valorTotal));
                        textViewRendimentoTotal.setText("Rendimento Total: R$ " + decimalFormat.format(rendimentoTotal));
                        textViewRentabilidadeMensal.setText("Rentabilidade Mensal: R$ " + decimalFormat.format(rentabilidadeMensal));
                    } catch (Exception e) {
                        e.printStackTrace();
                        int i = 0;
                    }

                    // Atualizar o gráfico de juros compostos
                      updateJurosCompostosChart();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juros_composto);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular = findViewById(R.id.btnCalcular);

        editTextInicial = findViewById(R.id.editTextInicial);
        editTextMensal = findViewById(R.id.editTextMensal);
        editTextTaxa = findViewById(R.id.editTextTaxa);
        editTextPeriodo = findViewById(R.id.editTextPeriodo);

        textViewValorAcumulado = findViewById(R.id.textViewValorAcumulado);
        textViewRendimentoTotal = findViewById(R.id.textViewRendimentoTotal);
        textViewRentabilidadeMensal = findViewById(R.id.textViewRentabilidadeMensal);


        // Adicionar o TextWatcher aos campos de entrada
        editTextInicial.addTextChangedListener(textWatcher);
        editTextMensal.addTextChangedListener(textWatcher);
        editTextTaxa.addTextChangedListener(textWatcher);
        editTextPeriodo.addTextChangedListener(textWatcher);


        lineChart = findViewById(R.id.lineChart);

        // Configuração básica do gráfico
        lineChart.setDrawGridBackground(false);
        Description description = new Description();
        description.setText(""); // Desativa a descrição
        lineChart.setDescription(description);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent telaEletrica = new Intent(JurosCompostoActivity.this, MenuPrincipal.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                }, 0);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInicial.setText("");
                editTextMensal.setText("");
                editTextPeriodo.setText("");
                editTextTaxa.setText("");

                textViewRendimentoTotal.setText("");
                textViewValorAcumulado.setText("");
                textViewRentabilidadeMensal.setText("");

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorInicial = parseEditTextValue(editTextInicial);
                double valorMensal = parseEditTextValue(editTextMensal);
                double taxa = parseEditTextValue(editTextTaxa);
                int periodo = parseEditTextIntValue(editTextPeriodo);

                // Realize o cálculo dos valores do juros composto
               double valorTotal = calcularJurosComposto(valorInicial, valorMensal, taxa, periodo);
               double rendimentoTotal = valorTotal - (valorInicial + (valorMensal * periodo));
                double rentabilidadeMensal = rendimentoTotal / periodo;

                // Atualizar os valores exibidos acima do gráfico
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                textViewValorAcumulado.setText("Valor Acumulado: R$ " + decimalFormat.format(valorTotal));
                textViewRendimentoTotal.setText("Rendimento Total: R$ " + decimalFormat.format(rendimentoTotal));
                textViewRentabilidadeMensal.setText("Rentabilidade Mensal Media: R$ " + decimalFormat.format(rentabilidadeMensal));


                // Gere os dados do gráfico
                List<Entry> entries = new ArrayList<>();
                for (int i = 0; i <= periodo; i++) {
                    double valor = calcularJurosComposto(valorInicial, valorMensal, taxa, i);
                    entries.add(new Entry(i, (float) valor));
                }

                // Crie o conjunto de dados do gráfico
                LineDataSet dataSet = new LineDataSet(entries, "Juros Composto");
                dataSet.setDrawCircles(false);
                dataSet.setDrawValues(false);
                dataSet.setColor(Color.RED);

                // Crie o objeto LineData contendo o conjunto de dados
                List<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(dataSet);
                LineData lineData = new LineData(dataSets);

                // Configure os dados no gráfico
                lineChart.setData(lineData);
                lineChart.invalidate();
            }
        });
    }

    private double parseEditTextValue(EditText editText) {
        String text = editText.getText().toString().replaceAll(",", ".");
        if (text.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(text);
    }

    private int parseEditTextIntValue(EditText editText) {
        String text = editText.getText().toString();
        if (text.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    private double calcularJurosComposto(double valorInicial, double valorMensal, double taxa, int periodo) {



        double valor = valorInicial;


        for (int i = 1; i <= periodo; i++) {
            valor *= (1 + (taxa/100));
            valor += valorMensal;
            int iz = 1;

        }


        return valor;

    }
    private void updateJurosCompostosChart() {
        double valorInicial = parseEditTextValue(editTextInicial);
        double valorMensal = parseEditTextValue(editTextMensal);
        double taxa = parseEditTextValue(editTextTaxa);
        int periodo = parseEditTextIntValue(editTextPeriodo);

        // Realizar o cálculo dos valores do juros composto
        double valorTotal = calcularJurosComposto(valorInicial, valorMensal, taxa, periodo);

        // Gere os dados do gráfico
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i <= periodo; i++) {
            double valor = calcularJurosComposto(valorInicial, valorMensal, taxa, i);
            entries.add(new Entry(i, (float) valor));
        }

        // Crie o conjunto de dados do gráfico
        LineDataSet dataSet = new LineDataSet(entries, "Juros Composto");
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setColor(Color.RED);

        // Crie o objeto LineData contendo o conjunto de dados
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        LineData lineData = new LineData(dataSets);

        // Configure os dados no gráfico
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    // ... outros métodos ...

}



