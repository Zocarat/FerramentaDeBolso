package devandroid.zocarato.ferramentadebolso;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import view.MenuPrincipal;

public class Verificacao {
    private String dataExpiracao;
    Context context;


    //public Verificacao(String dataExpiracao) {
    //    this.dataExpiracao = dataExpiracao;

    public int compararData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date();

        try {
            Date dataFornecida = sdf.parse(data);

            if (dataFornecida.before(dataAtual)) {


                return 1; // A data fornecida já passou


            } else {
                return 0; // A data fornecida ainda não passou
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Erro ao fazer o parsing da data
        }
    }







   // }







}
