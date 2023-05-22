package util;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import view.MenuPrincipal;

public class Utilidades extends AppCompatActivity {

     //TODO: deixar classe funcional pra nao ficar toda hora digitando

    public void menuPrincipal (){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent telaPrincipal = new Intent(Utilidades.this, MenuPrincipal.class);
                startActivity(telaPrincipal);
                finish();
            }
        },0);
    }





}
