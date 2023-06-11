package util;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.zocarato.ferramentadebolso.R;

public class UltilidadeTemperatura extends AppCompatActivity {

    public static void lmparTemperatura(EditText editCelsus, EditText editFahrenheit) {
        editCelsus.setText("");
        editFahrenheit.setText("");
    }

    public static double fahrenheitParaCelsus(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double celsusParaFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsusParaKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double kelvinParaCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}