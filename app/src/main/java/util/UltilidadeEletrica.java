package util;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.zocarato.ferramentadebolso.R;

public class UltilidadeEletrica extends AppCompatActivity {

    EditText editTextTensao;
    EditText editTextCorrente;
    EditText editTextPotencia;
    EditText editTextResistencia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grandezas_eletricas);

        editTextTensao = findViewById(R.id.editTextTensao);
        editTextCorrente = findViewById(R.id.editTextCorrente);
        editTextResistencia = findViewById(R.id.editTextResistencia);
        editTextPotencia = findViewById(R.id.editTextPotencia);



    }

    public int checaCampo (EditText tensao, EditText corrente, EditText potencia, EditText resistencia) {
        boolean isTensaoOK = false;
        boolean isCorrenteOK =false;
        boolean isPotenciaOK = false;
        boolean isResistenciaOK = false;
        int resposta;
        int cont = 0;

        if (TextUtils.isEmpty(tensao.getText()) &&
            TextUtils.isEmpty(corrente.getText()) &&
            TextUtils.isEmpty(potencia.getText()) &&
            TextUtils.isEmpty(resistencia.getText())) {
            return 1;
        }
        if (!TextUtils.isEmpty(tensao.getText())){
            isTensaoOK = true;
            cont = cont + 1;
        }
        if (!TextUtils.isEmpty(corrente.getText())){
            isCorrenteOK = true;
            cont = cont + 1;}
        if (!TextUtils.isEmpty(potencia.getText())){
            isPotenciaOK = true;
            cont = cont + 1;}
        if (!TextUtils.isEmpty(resistencia.getText())){
            isResistenciaOK = true;
            cont = cont + 1;}
        if(cont < 2){ return 2; }
        if (cont > 2){ return 3; }

        if (isTensaoOK == true && isCorrenteOK == true){ return  100; } // tensao corrente
        if (isTensaoOK == true && isPotenciaOK == true){ return  200; } // tensao potencia
        if (isTensaoOK == true && isResistenciaOK == true){return 300; } // tensao resistencia

        if (isCorrenteOK == true &&  isPotenciaOK == true){return 400; } //corrente potencia
        if (isCorrenteOK == true && isResistenciaOK == true){return 500; }  // corrente reistencia

        //tensaoPotenciaResistencia
        if (isPotenciaOK == true && isResistenciaOK == true ) {return 600;}  // potencia e resistencia



        if (cont == 2){ return 1000; }


        return 500;


    }

    public String potenciaTensaoCorrente (EditText tensao, EditText corrente){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double resultado = tensaoD * correnteD;
        return resultado +"";
    }
    public String resistenciaTensaoCorrente(EditText tensao, EditText corrente){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double resistencia = tensaoD / correnteD;
        return resistencia +"";
    }
    public String correnteTensaoPotencia(EditText tensao, EditText potencia){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double corrente = potenciaD / tensaoD;
        return corrente +"";
    }
    public String resistenciaTensaoPotencia(EditText tensao, EditText potencia){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double resistencia = (tensaoD * tensaoD) / potenciaD;
        return resistencia +"";
    }
    public String correnteTensaoResistencia (EditText tensao, EditText resistencia){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double resistenciaD = Double.parseDouble(resistencia.getText().toString());
        double correnteD = (tensaoD / resistenciaD);
        return correnteD +"";
    }
    public String potenciaTensaoResistencia (EditText tensao, EditText resistencia){
        double tensaoD = Double.parseDouble(tensao.getText().toString());
        double resistenciaD = Double.parseDouble(resistencia.getText().toString());
        double potenciaD = ((tensaoD * tensaoD) / resistenciaD);
        return potenciaD +"";
    }
    public String tensaoPotenciaResistencia (EditText potencia, EditText resistencia) {
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double resistenciaD = Double.parseDouble(resistencia.getText().toString());
        double tensaoD = Math.sqrt(potenciaD * resistenciaD);
        return tensaoD + "";
    }
    public String correntePotenciaResistencia  (EditText potencia, EditText resistencia) {
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double resistenciaD = Double.parseDouble(resistencia.getText().toString());
        double correnteD = Math.sqrt(potenciaD / resistenciaD);
        return  correnteD + "";
    }

    // tensaoCorrentePotencia
    public String tensaoCorrentePotencia  (EditText corrente, EditText potencia) {
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double tensaoD = (potenciaD / correnteD);
        return  tensaoD + "";
    }
    //resistenciaCorretePotencia
    public String resistenciaCorretePotencia  (EditText corrente, EditText potencia) {
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double potenciaD = Double.parseDouble(potencia.getText().toString());
        double reistenciaD = (potenciaD / (correnteD * correnteD));
        return  reistenciaD + "";
    }
    // tensaoCorrenteResistencia
    public String tensaoCorrenteResistencia  (EditText corrente, EditText reistencia) {
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double resistenciaD = Double.parseDouble(reistencia.getText().toString());
        double tensaoD = (resistenciaD * correnteD);
        return  tensaoD + "";
    }
    // potenciaCorrenteResistencia
    public String potenciaCorrenteResistencia  (EditText corrente, EditText reistencia) {
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double resistenciaD = Double.parseDouble(reistencia.getText().toString());
        double potenciaD = (resistenciaD * ( correnteD * correnteD));
        return  potenciaD + "";
    }
}


















