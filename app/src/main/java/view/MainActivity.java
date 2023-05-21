package view;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import devandroid.zocarato.ferramentadebolso.R;

public class MainActivity extends AppCompatActivity {

    String TAG = "Layout principal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG , "onCreate:  Tela Splash carregada");
    }
}