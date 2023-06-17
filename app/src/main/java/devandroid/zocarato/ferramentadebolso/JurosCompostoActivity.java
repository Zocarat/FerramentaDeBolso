package devandroid.zocarato.ferramentadebolso;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import view.MenuPrincipal;
public class JurosCompostoActivity extends AppCompatActivity {


    private ImageButton btnClose;
    private ImageView imgJurosComposto;
    private ImageButton btnVoltar;
    private ImageButton btnLimpar;
    private ImageButton btnCalcular;
    private LinearLayout layoutGrafico;

    private EditText editTextInicial;
    private EditText editTextMensal;
    private EditText editTextTaxa;
    private EditText editTextPeriodo;

    private DecimalFormat decimalFormat;

    // Grafico de linha do Jurus composto com valor total
    private LineChart lineChart;
    // grafico redondo total investido com rendimento total
    private PieChart pieChart;

    private BarChart barChart;

    private TextView textViewValorAplicado;
    private TextView textViewValorAcumulado;
    private TextView textViewRendimentoTotal;
    private TextView textViewRentabilidadeMensal;
    private TextView textViewRentabilidadeMensalFinal;




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
                   double rentabilidadeFinal = valorTotal * taxa;


                    graficosDisco (valorTotal, rendimentoTotal, rentabilidadeMensal, rentabilidadeFinal, taxa);


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

        imgJurosComposto = findViewById(R.id.imgLeiDeOhms);

        btnClose = findViewById(R.id.btn_close);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular = findViewById(R.id.btnCalcular);

        editTextInicial = findViewById(R.id.editTextInicial);
        editTextMensal = findViewById(R.id.editTextMensal);
        editTextTaxa = findViewById(R.id.editTextTaxa);
        editTextPeriodo = findViewById(R.id.editTextPeriodo);


        textViewValorAplicado = findViewById(R.id.textViewValorAplicado);
        textViewValorAcumulado = findViewById(R.id.textViewValorAcumulado);
        textViewRendimentoTotal = findViewById(R.id.textViewRendimentoTotal);
        textViewRentabilidadeMensal = findViewById(R.id.textViewRentabilidadeMensal);
        textViewRentabilidadeMensalFinal = findViewById(R.id.textViewRentabilidadeMensalFinal);


        // Adicionar o TextWatcher aos campos de entrada
       // editTextInicial.addTextChangedListener(textWatcher);
        //editTextMensal.addTextChangedListener(textWatcher);
        //editTextTaxa.addTextChangedListener(textWatcher);
       // editTextPeriodo.addTextChangedListener(textWatcher);

        layoutGrafico = findViewById(R.id.layoutGrafico);
        layoutGrafico.setVisibility(View.GONE);


        lineChart = findViewById(R.id.lineChart);
        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.bar_chart);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgJurosComposto.getVisibility() == View.VISIBLE) {
                    imgJurosComposto.setVisibility(View.GONE);
                } else {
                    imgJurosComposto.setVisibility(View.VISIBLE);
                }
            }
        });
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
                layoutGrafico.setVisibility(View.GONE);
                imgJurosComposto.setVisibility(View.VISIBLE);

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // fecha o icone da tela pra ver melhor os graficos
                if (imgJurosComposto.getVisibility() == View.VISIBLE) {
                    imgJurosComposto.setVisibility(View.GONE);
                }


                // Verificar se o EditText está vazio
                if (TextUtils.isEmpty(editTextInicial.getText().toString())) {
                    layoutGrafico.setVisibility(View.GONE);
                    editTextInicial.setError("Digite um valor valido");
                    // Faça o que for necessário nesse caso
                } else {
                    layoutGrafico.setVisibility(View.VISIBLE);
                }

                double valorInicial = parseEditTextValue(editTextInicial);
                double valorMensal = parseEditTextValue(editTextMensal);
                double taxa = parseEditTextValue(editTextTaxa);
                int periodo = parseEditTextIntValue(editTextPeriodo);




                // Realize o cálculo dos valores do juros composto
               double valorTotal = calcularJurosComposto(valorInicial, valorMensal, taxa, periodo);
               double rendimentoTotal = valorTotal - (valorInicial + (valorMensal * periodo));
                double rentabilidadeMensal = rendimentoTotal / periodo;
                double rentabilidadeFinal = valorTotal * taxa;


                graficosDisco (valorTotal, rendimentoTotal, rentabilidadeMensal, rentabilidadeFinal, taxa);
                updateJurosCompostosChart();

                animateTextViewValues(textViewValorAplicado, 0, (valorTotal - rendimentoTotal));
                animateTextViewValues(textViewValorAcumulado, 0, valorTotal);
                animateTextViewValues(textViewRendimentoTotal, 0, rendimentoTotal);
                animateTextViewValues(textViewRentabilidadeMensal, 0, rentabilidadeMensal);
                animateTextViewValues(textViewRentabilidadeMensalFinal, 0, (valorTotal * (taxa / 100)));

                // Atualizar os valores exibidos acima do gráfico
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
             //  textViewValorAcumulado.setText("Valor Acumulado: R$ " + decimalFormat.format(valorTotal));
              //  textViewRendimentoTotal.setText("Rendimento Total: R$ " + decimalFormat.format(rendimentoTotal));
              //  textViewRentabilidadeMensal.setText("Rentabilidade Mensal Media: R$ " + decimalFormat.format(rentabilidadeMensal));



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


        // Configuração básica do gráfico
        lineChart.setDrawGridBackground(false);
        Description description = new Description();
        description.setText(""); // Desativa a descrição
        lineChart.setDescription(description);
        lineChart.animateX(3000);
        lineChart.animateY(3000);
        lineChart.getAxisLeft().setEnabled(false); // tira eixo esquerdo


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
        dataSet.setColor(Color.BLUE);
        dataSet.setLineWidth(3f); // Define a largura das barras


        // Crie o objeto LineData contendo o conjunto de dados
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        LineData lineData = new LineData(dataSets);



        // Configure os dados no gráfico
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void graficosDisco ( double valorTotal, double rendimentoTotal , double rentabilidadeMensal ,  double rentabilidadeFinal , double taxa   ){

        float valorTotalFloat = (float) valorTotal;
        float valorAplicadoFloat = (float) (valorTotal - rendimentoTotal);
        float valorRendimentoTotalFloat = (float) rendimentoTotal;



        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(valorAplicadoFloat, "APLICADO"));

        entries.add(new PieEntry(valorRendimentoTotalFloat, "RENDIMENTO"));
        //entries.add(new PieEntry(50, "Categoria 3"));



        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(Color.RED, Color.GREEN); // Definir cores personalizadas
        dataSet.setValueTextColor(Color.BLACK);










        PieData data = new PieData(dataSet);
       data.setValueTextColor(Color.BLUE); // Definir cor do texto do título
        data.setValueTextSize(10f);

        data.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
      //  data.setValueTextColors(Color.BLUE);


        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(android.R.color.black);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(3000);
        pieChart.setHoleRadius(25f);
        pieChart.invalidate();
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(15f);
        pieChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
      //  pieChart.setDrawCenterText(Color.RED);

       // double valorTotal = calcularJurosComposto(valorInicial, valorMensal, taxa, periodo);
       // double rendimentoTotal = valorTotal - (valorInicial + (valorMensal * periodo));
       // double rentabilidadeMensal = rendimentoTotal / periodo;
      //  double rentabilidadeFinal = valorTotal * taxa;

        // Configuração do gráfico de barras
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);
        barChart.animateY(3000);
        barChart.animateX(3000);
        barChart.setDrawBorders(false);
        barChart.setDrawGridBackground(false);
        barChart.getLegend().setEnabled(true); //legenda de cores
        barChart.getAxisLeft().setEnabled(false); // tira eixo esquerdo
        barChart.getAxisRight().setEnabled(false); // tira eixo direito
        barChart.getAxisLeft().setAxisMinimum(0);



        // Adicionar valores aos conjuntos de dados
        List<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0, valorTotalFloat));

        List<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, valorAplicadoFloat));

        List<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(2, valorRendimentoTotalFloat));


        // Configuração do eixo Y (valores)
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextSize(10f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false); // Desativa a exibição das linhas de grade de escala

        // Configuração do eixo X (categorias)
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false); // Desativa a exibição das linhas de grade de escala



        // Conjuntos de dados
        BarDataSet dataSet1 = new BarDataSet(entries1, "Acumulado");
        dataSet1.setColor(Color.BLUE);

        BarDataSet dataSet2 = new BarDataSet(entries2, "Aplicado");
        dataSet2.setColor(Color.RED);

        BarDataSet dataSet3 = new BarDataSet(entries3, "Rendimento");
        dataSet3.setColor(Color.GREEN);




        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        dataSets.add(dataSet3);


        BarData barData = new BarData(dataSets);
        barData.setValueTextSize(15f); // Definir o tamanho da fonte para os valores
        barData.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        barData.setBarWidth(0.7f); // Define a largura das barras
        barData.setDrawValues(true); // Desativa a exibição dos valores nas barras


        barChart.setData(barData);
        barChart.invalidate();
        animateChartValues();

    }

    // ... outros métodos ...

    private void animateChartValues() {
        BarData barData = barChart.getBarData();
        for (IBarDataSet dataSet : barData.getDataSets()) {
            for (int i = 0; i < dataSet.getEntryCount(); i++) {
                BarEntry entry = dataSet.getEntryForIndex(i);
                float startValue = 0f; // Valor inicial para a animação
                float targetValue = entry.getY(); // Valor final para a animação

                ValueAnimator animator = ValueAnimator.ofFloat(startValue, targetValue);
                animator.setDuration(3000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        entry.setY(animatedValue);
                        barChart.notifyDataSetChanged();
                        barChart.invalidate();
                    }
                });
                animator.start();
            }
        }
    }
    private void animateTextViewValues(final TextView textView, final double startValue, final double targetValue) {
        ValueAnimator animator = ValueAnimator.ofFloat((float) startValue, (float) targetValue);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                textView.setText(decimalFormat.format(animatedValue));
            }
        });
        animator.start();
    }



}



