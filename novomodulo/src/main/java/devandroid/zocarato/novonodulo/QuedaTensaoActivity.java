package devandroid.zocarato.novonodulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import devandroid.zocarato.ferramentadebolso.*;


public class QuedaTensaoActivity extends AppCompatActivity {

    ImageButton btnQuedaAdmissivel;
    boolean setBtnQuedaAdmissivel;
    ImageButton btnQuedaTesao;
    boolean setBtnQuedaTensao;

    ImageButton btnCorrenteContinua;
    boolean setBtnCorrenteContinua;

    ImageButton btnMonoBifaisico;
    boolean setBtnMonoBifaisico;

    ImageButton btnTrifasico;
    boolean setBtnTrifasico;

    ImageButton btnCobre;
    boolean setCobre;
    ImageButton btnAluminio;
    boolean setAluminio;

    ImageButton btnMaisBitola;
    ImageButton btnMenosBitola;
    int setBitola = 0;

    ImageButton btnSom;

    ImageButton btnCalcular;

    ImageButton btnVoltar;

    ImageView imageViewTemperatura;
    EditText editTextTemperatura;
    boolean temperaturaOk;

    ImageView imageViewCampoComprimento;
    EditText editTextComprimento;
    boolean comprimentoOK;

    ImageView imageViewCampoTensao;
    EditText editTextTensao;
    boolean tensaoOK;

    ImageView imageViewCampoCorrente;
    EditText editTextCorrente;
    boolean correnteOK;

    ImageView imageViewCampoBitola;
    EditText editTextBitola;
    boolean bitolaOK;

    boolean SomConfig = true;
    boolean calculoContinuaOk;

    TextView textViewResultado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queda_tensao);

        MediaPlayer ironSound = MediaPlayer.create(this, R.raw.ferro_sound);

        btnSom = findViewById(R.id.btnSom);

        btnQuedaAdmissivel = findViewById(R.id.btnQuedaAdmissivel);
        setBtnQuedaAdmissivel = false;

        btnQuedaTesao = findViewById(R.id.btnQuedaTensao);
        setBtnQuedaTensao = false;

        btnCorrenteContinua = findViewById(R.id.btnCorrenteContinua);
        setBtnCorrenteContinua = false;

        btnMonoBifaisico = findViewById(R.id.btnMonoBifaisico);
        setBtnMonoBifaisico = false;

        btnTrifasico = findViewById(R.id.btnTrifasico);
        setBtnTrifasico = false;

        imageViewTemperatura = findViewById(R.id.imageViewTemperatura);
        editTextTemperatura = findViewById(R.id.editTextTemperatura);
        editTextTemperatura.addTextChangedListener(textWatcher);

        imageViewCampoComprimento = findViewById(R.id.imageViewCampoComprimento);
        editTextComprimento = findViewById(R.id.editTextComprimento);
        editTextComprimento.addTextChangedListener(textWatcher);

        imageViewCampoTensao = findViewById(R.id.imageViewCampoTensao);
        editTextTensao = findViewById(R.id.editTextTensao);
        editTextTensao.addTextChangedListener(textWatcher);

        imageViewCampoCorrente = findViewById(R.id.imageViewCampoCorrente);
        editTextCorrente = findViewById(R.id.editTextCorrente);
        editTextCorrente.addTextChangedListener(textWatcher);

        imageViewCampoBitola = findViewById(R.id.imageViewCampoBitola);
        editTextBitola = findViewById(R.id.editTextBitola);
        editTextBitola.addTextChangedListener(textWatcher);



        btnCobre = findViewById(R.id.btnCobre);
        setCobre = false;
        btnAluminio = findViewById(R.id.btnAluminio);
        setAluminio = false;

        btnMaisBitola = findViewById(R.id.btnMaisBitola);
        btnMenosBitola = findViewById(R.id.btnMenosBitola);
        btnMenosBitola.setVisibility(View.GONE);

        textViewResultado = findViewById(R.id.textViewResultado);

        btnCalcular  = findViewById(R.id.btnCalcular);
        btnVoltar  = findViewById(R.id.btnVoltar);





        btnSom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SomConfig){
                    btnSom.setImageResource(R.drawable.btn_som_off);
                    SomConfig = false;
                }else {
                    btnSom.setImageResource(R.drawable.btn_som_on);
                    SomConfig = true;
                }

            }
        });
        btnCorrenteContinua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cotroles();
                btnCorrenteContinua.setImageResource(R.drawable.btn_continua_on);
                setBtnCorrenteContinua = true;
                btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_off);
                setBtnMonoBifaisico = false;
                btnTrifasico.setImageResource(R.drawable.btn_trifasico_off);
                setBtnTrifasico = false;
            }
        });
        btnMonoBifaisico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cotroles();
                btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_on);
                setBtnMonoBifaisico = true;
                btnCorrenteContinua.setImageResource(R.drawable.btn_continua_off);
                setBtnCorrenteContinua = false;

                btnTrifasico.setImageResource(R.drawable.btn_trifasico_off);
                setBtnTrifasico = false;
            }
        });
        btnTrifasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cotroles();
                btnTrifasico.setImageResource(R.drawable.btn_trifasico_on);
                setBtnTrifasico = true;
                btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_off);
                setBtnMonoBifaisico = false;
                btnCorrenteContinua.setImageResource(R.drawable.btn_continua_off);
                setBtnCorrenteContinua = false;


            }
        });
        btnQuedaAdmissivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cotroles();
                if (setBtnQuedaAdmissivel){
                    btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_off);
                    setBtnQuedaAdmissivel = false;
                    return;
                }
                if(!setBtnQuedaAdmissivel) {
                    btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_on);
                    setBtnQuedaAdmissivel = true;
                    btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_off);
                    setBtnQuedaTensao = false;
                }

            }
        });
        btnQuedaTesao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cotroles();
                if (setBtnQuedaTensao){
                    btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_off);
                    setBtnQuedaTensao = false;
                    return;
                }
                if(!setBtnQuedaTensao) {
                    btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_on);
                    setBtnQuedaTensao = true;
                    btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_off);
                    setBtnQuedaAdmissivel = false;
                }

            }
        });
        btnCobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SomConfig) {ironSound.start();}
                //cotroles();
                if (setCobre){
                    btnCobre.setImageResource(R.drawable.img_cobre_off);
                    setCobre = false;
                }else{
                    btnCobre.setImageResource(R.drawable.img_cobre_on);
                    setCobre = true;
                    btnAluminio.setImageResource(R.drawable.img_aluminio_off);
                    setAluminio = false;
                }
            }
        });
        btnAluminio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SomConfig) {ironSound.start();}
                //cotroles();
                if (setAluminio){
                    btnAluminio.setImageResource(R.drawable.img_aluminio_off);
                    setAluminio = false;
                }else{
                    btnAluminio.setImageResource(R.drawable.img_aluminio_on);
                    setAluminio = true;
                    btnCobre.setImageResource(R.drawable.img_cobre_off);
                    setCobre = false;
                }
            }
        });
        btnMaisBitola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cotroles();
                setBitola = setBitola + 1;
                bitolas();
            }
        });
        btnMenosBitola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cotroles();
                setBitola = setBitola - 1;
                bitolas();
                if (setBitola <=  0 ){
                    setBitola = 1 ;
                    btnMenosBitola.setVisibility(View.GONE);
                }
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculo();
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModulo = new Intent(QuedaTensaoActivity.this,  MenuPrincipal.class);
                startActivity(intentModulo);
                finish();
            }
        });

    }

    public void verificaCampo (){

        String temperatura = editTextTemperatura.getText().toString();
        String comprimento = editTextComprimento.getText().toString();
        String tensao = editTextTensao.getText().toString();
        String corrente = editTextCorrente.getText().toString();
        String bitola = editTextBitola.getText().toString();

        if (!TextUtils.isEmpty(comprimento)) {
            // O EditText não está vazio
            comprimentoOK = true;
        } else {
            // O EditText está vazio
            comprimentoOK = false;
        }
        if (!TextUtils.isEmpty(tensao)) {
            // O EditText não está vazio
            tensaoOK = true;
        } else {
            // O EditText está vazio
            tensaoOK = false;
        }
        if (!TextUtils.isEmpty(corrente)) {
            // O EditText não está vazio
            correnteOK = true;
        } else {
            // O EditText está vazio
            correnteOK = false;
        }
        if (!TextUtils.isEmpty(bitola)) {
            // O EditText não está vazio
            bitolaOK = true;
            btnMenosBitola.setVisibility(View.VISIBLE);
        } else {
            // O EditText está vazio
            bitolaOK = false;
            btnMenosBitola.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(temperatura)) {
            // O EditText não está vazio
            temperaturaOk = true;

        } else {
            // O EditText está vazio
            temperaturaOk = false;

        }





    }

    public void alteraCampo (){

        if (comprimentoOK){
            imageViewCampoComprimento.setImageResource(R.drawable.img_campo_metros_on);
        }else {
            imageViewCampoComprimento.setImageResource(R.drawable.img_campo_metros_off);
        }
        if (tensaoOK){
            imageViewCampoTensao.setImageResource(R.drawable.img_campo_tensao_on);
        }else {
            imageViewCampoTensao.setImageResource(R.drawable.img_campo_tensao_off);
        }
        if (correnteOK){
            imageViewCampoCorrente.setImageResource(R.drawable.img_campo_corrente_on);
        }else {
            imageViewCampoCorrente.setImageResource(R.drawable.img_campo_corrente_off);
        }
        if (bitolaOK){
            imageViewCampoBitola.setImageResource(R.drawable.img_campo_bitola_on);
        }else {
            imageViewCampoBitola.setImageResource(R.drawable.img_campo_bitola_off);
        }
        if (temperaturaOk){
            imageViewTemperatura.setImageResource(R.drawable.img_campo_temperatura_on);
            editTextTemperatura.setTextColor(Color.BLACK);
        }else {
            imageViewTemperatura.setImageResource(R.drawable.img_campo_temperatura_off);
        }

    }

    public void bitolas (){

        double [] listaBitola = { 0, 0.5, 0.75, 1.0, 1.5, 2.5, 4.0, 6.0, 10.0, 16.0, 25.0, 35.0, 50.0, 70, 95, 120, 150, 185,
                                    240, 300, 400, 500, 630, 800, 1000};
        if (setBitola >= 24 ) {
            setBitola = 24;
            btnMaisBitola.setVisibility(View.GONE);
        }
        if (setBitola < 24){
            btnMaisBitola.setVisibility(View.VISIBLE);
        }

        editTextBitola.setText(""+listaBitola[setBitola]);

    }

    public void calculo (){

        String temperaturaS = editTextTemperatura.getText().toString();
        String tensaoS = editTextTensao.getText().toString();
        String comprimentoS =  editTextComprimento.getText().toString();
        String correnteS = editTextCorrente.getText().toString();
        String bitolaS = editTextBitola.getText().toString();

        double temperatura = 0;
        double tensao = 0;
        double comprimento = 0;
        double corrente = 0;
        double bitola = 0;
        double resistenciaMaterial = 0;
        double resistividadeAcumulada = 0;

        double quedaVolts;
        double porcentagemVolts;

        try {
            tensao = Double.parseDouble(tensaoS);
        } catch (NumberFormatException e) {       }
        try {
            comprimento = Double.parseDouble(comprimentoS);
        } catch (NumberFormatException e) {        }
        try {
            corrente = Double.parseDouble(correnteS);
        } catch (NumberFormatException e) {        }
        try {
            bitola = Double.parseDouble(bitolaS);
        } catch (NumberFormatException e) {        }
        try {
            temperatura = Double.parseDouble(temperaturaS);
        } catch (NumberFormatException e) {        }




        // Confirma todos os campos ok pra dar condição pro calculo de corrente continua
        if ( setBtnQuedaTensao && setBtnCorrenteContinua && tensaoOK && correnteOK && bitolaOK && ( setCobre || setAluminio)  ){

            calculoContinuaOk = true;
        }

        // Calculo corrente continua
        if (calculoContinuaOk) {

             // calculo da resistencia de acordo com a temperatura
            if (setCobre){ resistenciaMaterial = 0.0172 * ( 1 + ( 0.00393 * (temperatura - 20)));}
            if (setAluminio) { resistenciaMaterial = 0.0282  * ( 1 + ( 0.00391 * (temperatura - 20))) ; }

            resistividadeAcumulada = resistenciaMaterial * ( comprimento / bitola );



           quedaVolts = 2 * resistividadeAcumulada * corrente * comprimento ;


            textViewResultado.setText("Queda Volts  : "+ quedaVolts + "Resistencia Total : " + resistividadeAcumulada +

            " ResistenciaTemperatura: " + resistenciaMaterial);





        }



    }



    public void cotroles (){

        if (SomConfig) {
            MediaPlayer click = MediaPlayer.create(this, R.raw.click);
            click.start();
        }

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Método chamado antes de o texto ser alterado
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            verificaCampo();
            alteraCampo();
          //  garantidorConformidade();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Método chamado após o texto ser alterado
        }
    };
}