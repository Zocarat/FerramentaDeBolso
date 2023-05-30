package util;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import view.MenuPrincipal;

public class UtilidadesPressao extends AppCompatActivity {


    public void menuPrincipal(AppCompatActivity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent telaPrincipal = new Intent(activity, MenuPrincipal.class);
                activity.startActivity(telaPrincipal);
                activity.finish();
            }
        }, 0);
    }

    public double barParaPsi(double valorBar) {
        return valorBar * 14.5038;
    }

    public double psiParaBar(double valorPsi) {
        return valorPsi / 14.5038;
    }

    public double barParaPascal(double valorBar) {
        return valorBar * 100000;
    }

    public double pascalParaBar(double valorPascal) {
        return valorPascal / 100000;
    }

    public double barParaAtmosfera(double valorBar) {
        return valorBar * 0.9869;
    }

    public double atmosferaParaBar(double valorAtmosfera) {
        return valorAtmosfera / 0.9869;
    }

    public double barParaTorr(double valorBar) {
        return valorBar * 750.062;
    }

    public double torrParaBar(double valorTorr) {
        return valorTorr / 750.062;
    }

    public double psiParaPascal(double valorPsi) {
        return valorPsi * 6894.76;
    }

    public double pascalParaPsi(double valorPascal) {
        return valorPascal / 6894.76;
    }

    public double psiParaAtmosfera(double valorPsi) {
        return valorPsi * 0.068046;
    }

    public double atmosferaParaPsi(double valorAtmosfera) {
        return valorAtmosfera / 0.068046;
    }

    public double psiParaTorr(double valorPsi) {
        return valorPsi * 51.7149;
    }

    public double torrParaPsi(double valorTorr) {
        return valorTorr / 51.7149;
    }

    public double pascalParaAtmosfera(double valorPascal) {
        return valorPascal * 0.00000986923;
    }

    public double atmosferaParaPascal(double valorAtmosfera) {
        return valorAtmosfera / 0.00000986923;
    }

    public double pascalParaTorr(double valorPascal) {
        return valorPascal * 0.00750062;
    }

    public double torrParaPascal(double valorTorr) {
        return valorTorr / 0.00750062;
    }

    public double atmosferaParaTorr(double valorAtmosfera) {
        return valorAtmosfera * 760;
    }

    public double torrParaAtmosfera(double valorTorr) {
        return valorTorr / 760;
    }

    // Adicione outras funções de conversão de acordo com suas necessidades

}