package util;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import view.MenuPrincipal;

public class UtilidadesPressao extends AppCompatActivity {

     //TODO: deixar classe funcional pra nao ficar toda hora digitando

    public void menuPrincipal (){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent telaPrincipal = new Intent(UtilidadesPressao.this, MenuPrincipal.class);
                startActivity(telaPrincipal);
                finish();
            }
        },0);
    }
    public  String barParaPsi (double valorBar){

        double valorPsi = valorBar * 14.5038;
        String resultado =  valorPsi + " psi";
        return resultado;

    }
    public String PsiParaBar (double valorPsi){
        double valorBar = 1 / 14.5038 ;
        String resultado = valorBar +"";

        return resultado;
    }

}
