package view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import devandroid.zocarato.ferramentadebolso.R;

public class SplashActivity extends AppCompatActivity {

    String TAG = "APP_MINHA_IDEIA";

    int tempoEspera = 1000 * 3;


    //public static final int TIME_OUT_SPLASH = 10000;   // conta em mili segundos antes de abrir a proxima tela

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_splash);       // seta o arquivo xml com as informacoea do layout

        Log.d(TAG, "onCreate:  Tela Splash carregada");
        trocarTelaSplashMain();
    }

    private void trocarTelaSplashMain() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent telaPrincipal = new Intent(SplashActivity.this, MenuPrincipal.class);
                startActivity(telaPrincipal);
                finish();
            }
        },tempoEspera);

    }
}
