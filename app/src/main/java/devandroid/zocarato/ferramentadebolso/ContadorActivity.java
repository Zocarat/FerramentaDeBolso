package devandroid.zocarato.ferramentadebolso;

import static devandroid.zocarato.ferramentadebolso.R.id.btnMenosContador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Handler;

import android.widget.ImageButton;
import android.widget.TextView;

import view.MenuPrincipal;

public class ContadorActivity extends AppCompatActivity {

    ImageButton btnVoltar;
    ImageButton btnLimpar;
    ImageButton btnMaisContador;
    ImageButton btnMenosContador;
    TextView textViewContador;
    private MediaPlayer mediaPlayer;

    int contador = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        btnVoltar = findViewById(R.id.btnVoltarMenuContador);
        btnLimpar = findViewById(R.id.btnLimpar);

        btnMaisContador = findViewById(R.id.btnMaisContador);
        btnMenosContador = findViewById(R.id.btnMenosContador);

        textViewContador = findViewById(R.id.textViewContador);


        // Obtenha a instância da SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("NomeDaSharedPreferences", Context.MODE_PRIVATE);

        // Obtenha o Editor da SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Adicione o valor inteiro na SharedPreferences


        contador = sharedPreferences.getInt("chaveInteiro", 0);
        setInicial(contador);










        btnMaisContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Recupere o valor inteiro
                       int  contadorShared = sharedPreferences.getInt("chaveInteiro", 0);
                        contador = contadorShared + 1;

                        playSound();

                        if (contador <= 9){
                            textViewContador.setText("000" + contador);
                        }
                        if  ((contador >= 10) && (contador <= 99 )){
                            textViewContador.setText("00" + contador);
                        }
                        if  ((contador >= 100) && (contador <= 999 )){
                            textViewContador.setText("0" + contador);
                        }
                        if  ((contador >= 1000) && (contador <= 9999 )){
                            textViewContador.setText("" + contador);
                        }
                        if (contador > 9999 ){
                            contador = 0;
                            textViewContador.setText("0000");
                        }


                        //contador = 9990;
                        editor.putInt("chaveInteiro", contador);
                        editor.apply();

                    }
                },0);
            }
        });


        btnMenosContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        playSound();
                        // Recupere o valor inteiro
                        int  contadorShared = sharedPreferences.getInt("chaveInteiro", 0);
                        contador = contadorShared - 1;


                        if (contador < 0){contador = 0;}

                        if (contador <= 9){
                            textViewContador.setText("000" + contador);
                        }
                        if  ((contador >= 10) && (contador <= 99 )){
                            textViewContador.setText("00" + contador);
                        }
                        if  ((contador >= 100) && (contador <= 999 )){
                            textViewContador.setText("0" + contador);
                        }
                        if  ((contador >= 1000) && (contador <= 9999 )){
                            textViewContador.setText("" + contador);
                        }



                        editor.putInt("chaveInteiro", contador);
                        editor.apply();

                    }
                },0);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent telaEletrica = new Intent(ContadorActivity.this, MenuPrincipal.class);
                        startActivity(telaEletrica);
                        finish();
                    }
                },0);
            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playSound();
                        contador = 0;
                        textViewContador.setText("0000");
                        editor.putInt("chaveInteiro", 0);
                        editor.apply();
                    }
                },0);
            }
        });
    }

    private void playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_click);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();

        }
    }


    private void setInicial (int contadorFuncion){

        if (contadorFuncion <= 9){
            textViewContador.setText("000" + contadorFuncion);
        }
        if  ((contadorFuncion >= 10) && (contadorFuncion <= 99 )){
            textViewContador.setText("00" + contadorFuncion);
        }
        if  ((contadorFuncion >= 100) && (contadorFuncion <= 999 )){
            textViewContador.setText("0" + contadorFuncion);
        }
        if  ((contadorFuncion >= 1000) && (contadorFuncion <= 9999 )){
            textViewContador.setText("" + contadorFuncion);
        }
        if (contadorFuncion >= 9999){
            textViewContador.setText("0000");
            contador = 0;
        }


    }
}