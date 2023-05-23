package util;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.zocarato.ferramentadebolso.R;

public class UltilidadeTemperatura extends AppCompatActivity {

    EditText editCelsus;
    EditText editFahrenheint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editCelsus = findViewById(R.id.edit_celsus_xml);
        editFahrenheint = findViewById(R.id.edit_Firen_xml);
    }

    public void lmparTemperatura (){
        editCelsus.setText("");
        editFahrenheint.setText("");

    }

    public String fahrenheitParaCelsus (double fahrenheit ){
        double resultado = (fahrenheit - 32) * 5 / 9;
        return resultado + "";
    }
    public String celsusParafahrenheit (double celsus){
        double resultado = (celsus * 9 / 5) + 32;

        return resultado +"";
    }

}
