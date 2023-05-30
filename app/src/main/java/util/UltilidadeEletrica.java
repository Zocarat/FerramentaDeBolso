package util;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

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

    public String dimensionamentoCabo (EditText corrente){
        double correnteD = Double.parseDouble(corrente.getText().toString());
        String resultado ="";

        if(correnteD <= 15){ resultado = "1,5 mm";}
        if((correnteD > 15) && (correnteD <= 21 )){ resultado = "2,5 mm";}
        if((correnteD > 21) && (correnteD <= 28 )){ resultado = "4,0 mm";}
        if((correnteD > 28) && (correnteD <= 36 )){ resultado = "6,0 mm";}
        if((correnteD > 36) && (correnteD <= 50 )){ resultado = "10,0 mm";}
        if((correnteD > 50) && (correnteD <= 68 )){ resultado = "16,0 mm";}
        if((correnteD > 68) && (correnteD <= 89 )){ resultado = "25,0 mm";}
        if((correnteD > 89) && (correnteD <= 111 )){ resultado = "35,0 mm";}
        if((correnteD > 111) && (correnteD <= 134 )){ resultado = "50,0 mm";}
        if((correnteD > 134) && (correnteD <= 171 )){ resultado = "70,0 mm";}
        if((correnteD > 171) && (correnteD <= 207 )){ resultado = "95,0 mm";}
        if((correnteD > 207) && (correnteD <= 240 )){ resultado = "120,0 mm";}
        if((correnteD > 240) && (correnteD <= 275 )){ resultado = "150,0 mm";}
        if((correnteD > 275) && (correnteD <= 314 )){ resultado = "185,0 mm";}
        if((correnteD > 314) && (correnteD <= 370 )){ resultado = "240,0 mm";}
        if((correnteD > 370) && (correnteD <= 425 )){ resultado = "300,0 mm";}
        if((correnteD > 425) && (correnteD <= 510 )){ resultado = "400,0 mm";}
        if((correnteD > 510) && (correnteD <= 585 )){ resultado = "500,0 mm";}


        return resultado;
    }


    public String dimensionamentoDisjuntor (TextView cabo) {
        String caboS = cabo.getText().toString();
        String resultado ="";


        if (caboS.equals("1,5 mm")) {
            resultado = "10A";
        } else if (caboS.equals("2,5 mm")) {
            resultado = "16A";
        } else if (caboS.equals("4,0 mm")) {
            resultado = "20A";
        } else if (caboS.equals("6,0 mm")) {
            resultado = "32A";
        } else if (caboS.equals("10,0 mm")) {
            resultado = "40A";
        } else if (caboS.equals("16,0 mm")) {
            resultado = "50A";
        } else if (caboS.equals("25,0 mm")) {
            resultado = "63A";
        } else if (caboS.equals("35,0 mm")) {
            resultado = "80A";
        } else if (caboS.equals("50,0 mm")) {
            resultado = "100A";
        } else if (caboS.equals("70,0 mm")) {
            resultado = "125A";
        } else if (caboS.equals("95,0 mm")) {
            resultado = "160A";
        } else if (caboS.equals("120,0 mm")) {
            resultado = "200A";
        } else if (caboS.equals("150,0 mm")) {
            resultado = "225A";
        } else if (caboS.equals("185,0 mm")) {
            resultado = "250A";
        } else if (caboS.equals("240,0 mm")) {
            resultado = "315A";
        } else {
            resultado = "Bitola de cabo inválida";
        }
        return resultado;

    }

    public double calcularQuedaDeTensao(EditText bitola, EditText corrente, EditText distancia) {
        double bitolaD = Double.parseDouble(bitola.getText().toString());
        double correnteD = Double.parseDouble(corrente.getText().toString());
        double distanciaD = Double.parseDouble(distancia.getText().toString());
        double resistenciaD = (1.72e-8 * distanciaD) / bitolaD;
        double quedaTensao = resistenciaD * correnteD;

        return quedaTensao;
    }






}


















