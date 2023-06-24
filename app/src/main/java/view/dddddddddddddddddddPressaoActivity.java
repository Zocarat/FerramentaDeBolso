package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import devandroid.zocarato.ferramentadebolso.MenuPrincipal;
import devandroid.zocarato.ferramentadebolso.R;
import util.UtilidadesPressao;

public class dddddddddddddddddddPressaoActivity extends AppCompatActivity {

   EditText editBar;
   EditText editPsi;

    Button btnMenuPrincipal;
    Button btnCalcularPressao;
    Button btnLimparPressao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddddddddddddddddddesusoactivity_pressao);

      editBar = findViewById(R.id.edit_bar);
      editPsi = findViewById(R.id.edit_psi_xml);

       btnMenuPrincipal = findViewById(R.id.btnVoltarMenu);
       btnLimparPressao = findViewById(R.id.btnLimPartPressao);
       btnCalcularPressao = findViewById(R.id.btnCalcularPressao);

        btnLimparPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPsi.setText("");
                editBar.setText("");
            }
        });


        btnCalcularPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(editBar.getText()) && (TextUtils.isEmpty(editPsi.getText()))){
                    editBar.setError("* Valor Inválido");
                    editPsi.setError("* Valor Inválido");

                }
                if (TextUtils.isEmpty(editPsi.getText()) && (editBar.getText().length() >0)){
                    String resultado;
                    UtilidadesPressao converter;
                    converter = new UtilidadesPressao();
                   // resultado = converter.barParaPsi(Double.parseDouble(editBar.getText().toString()));
                    //String resultMenor = String.format("%.2f", resultado);
                    //editPsi.setText(resultado);

                }
                if (TextUtils.isEmpty(editBar.getText()) && (editPsi.getText().length() >0)){
                    String resultado;
                    UtilidadesPressao converter;
                    converter = new UtilidadesPressao();
                    //resultado = converter.PsiParaBar(Double.parseDouble(editPsi.getText().toString()));
                    //String resultMenor = String.format("%.2f", resultado);
                    //editBar.setText(resultado);

                }

            }
        });


        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaPrincipal = new Intent(dddddddddddddddddddPressaoActivity.this, MenuPrincipal.class);
                        startActivity(telaPrincipal);
                        finish();
                    }
                },0);
            }
        });


    }











}