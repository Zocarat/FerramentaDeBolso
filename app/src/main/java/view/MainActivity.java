package view;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import devandroid.zocarato.ferramentadebolso.R;

public class MainActivity extends AppCompatActivity {

    String TAG = "Layout principal";

    Button btn_Comprimento_Menu;
    Button btn_Pressao_Menu;
    Button btn_Temperatura_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG , "onCreate:  Tela Splash carregada");

        btn_Comprimento_Menu = findViewById(R.id.btnComprimentoMenu);
        btn_Pressao_Menu = findViewById(R.id.btnPressaoMenu);
        btn_Temperatura_Menu = findViewById(R.id.btnTemperaturaMenu);


        // Pegar Click dos Botoes

        btn_Comprimento_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_Pressao_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_Temperatura_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}