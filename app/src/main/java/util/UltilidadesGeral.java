package util;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devandroid.zocarato.ferramentadebolso.R;

public class UltilidadesGeral extends AppCompatActivity {

     TextView txtHora;
     TextView txtData;

    //txtHora = findViewById(R.id.txtHora);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        txtData = findViewById(R.id.txtDataSistemaXml);


    }

    public  String dataDoSistema() {
        // Obtém a data atual
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Formata a data no formato desejado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);

        //txtData.setText(formattedDate);

        return formattedDate;
    }

    public  String horarioDoSistema() {
        // Obtém o horário atual
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        // Formata o horário no formato desejado
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String retornoHora = timeFormat.format(currentTime);

        return retornoHora;
    }


}
