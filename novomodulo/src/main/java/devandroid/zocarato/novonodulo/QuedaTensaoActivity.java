package devandroid.zocarato.novonodulo;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


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

    ImageButton btnLimpar;

    ImageButton btnFecharLayoutResult;

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

    ImageView imageViewFatorPotencia;
    EditText editTextFatorPotencia;
    boolean fatorPotenciaOk;

    TextView textViewResultado;

    ScrollView scrollViewResultado;

    LinearLayout layoutAdmissivelQueda;
    LinearLayout LayoutResultadoQuedaTensao;
    LinearLayout layoutDasCorrentes;

    float temperaturaLocal;


    boolean SomConfig = true;
    boolean calculoContinuaOk;

    boolean isCalculoMonoBifasicoOk;

    boolean isCalculoTrifasico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queda_tensao);

        MediaPlayer ironSound = MediaPlayer.create(this, R.raw.ferro_sound);

        LayoutResultadoQuedaTensao = findViewById(R.id.layoutExibeResultado );
        layoutDasCorrentes = findViewById(R.id.layoutDasCorrentes );
        layoutAdmissivelQueda = findViewById(R.id.layoutAdmissivelQueda );

        //temperaturaLocal
       // cotroles();


        btnSom = findViewById(R.id.btnSom);

        btnFecharLayoutResult = findViewById(R.id.imageButtonFecharResultado);

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

        scrollViewResultado =  findViewById(R.id.ScrollResultado);
        scrollViewResultado.setVisibility(View.GONE);



        btnCobre = findViewById(R.id.btnCobre);
        setCobre = false;
        btnAluminio = findViewById(R.id.btnAluminio);
        setAluminio = false;

        btnMaisBitola = findViewById(R.id.btnMaisBitola);
        btnMenosBitola = findViewById(R.id.btnMenosBitola);
        btnMenosBitola.setVisibility(View.GONE);

        imageViewFatorPotencia = findViewById(R.id.ImageViewFatorPotencia);
        editTextFatorPotencia = findViewById(R.id.editTextFatorPotencia);
       // btnMenosBitola.setVisibility(View.GONE);

       // textViewResultado = findViewById(R.id.textViewResultado);

        btnCalcular  = findViewById(R.id.btnCalcular);
        btnVoltar  = findViewById(R.id.btnVoltar);
        btnLimpar  = findViewById(R.id.btnLimpar);


        inicializar();

        // =====================================[ CONFIG BOTOES ] ==========================================
        {
            btnSom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SomConfig) {
                        btnSom.setImageResource(R.drawable.btn_som_off);
                        SomConfig = false;
                    } else {
                        btnSom.setImageResource(R.drawable.btn_som_on);
                        SomConfig = true;
                    }

                }
            });
            btnCorrenteContinua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btnCorrenteContinua.setImageResource(R.drawable.btn_continua_on);
                    setBtnCorrenteContinua = true;
                    btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_off);
                    setBtnMonoBifaisico = false;
                    btnTrifasico.setImageResource(R.drawable.btn_trifasico_off);
                    setBtnTrifasico = false;
                    cotroles();
                }
            });
            btnMonoBifaisico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_on);
                    setBtnMonoBifaisico = true;
                    btnCorrenteContinua.setImageResource(R.drawable.btn_continua_off);
                    setBtnCorrenteContinua = false;

                    btnTrifasico.setImageResource(R.drawable.btn_trifasico_off);
                    setBtnTrifasico = false;
                    cotroles();
                }
            });
            btnTrifasico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    btnTrifasico.setImageResource(R.drawable.btn_trifasico_on);
                    setBtnTrifasico = true;
                    btnMonoBifaisico.setImageResource(R.drawable.btn_monofasico_off);
                    setBtnMonoBifaisico = false;
                    btnCorrenteContinua.setImageResource(R.drawable.btn_continua_off);
                    setBtnCorrenteContinua = false;
                    cotroles();

                }
            });
            btnQuedaAdmissivel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (setBtnQuedaAdmissivel) {
                        btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_off);
                        setBtnQuedaAdmissivel = false;
                        return;
                    }
                    if (!setBtnQuedaAdmissivel) {
                        btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_on);
                        setBtnQuedaAdmissivel = true;
                        btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_off);
                        setBtnQuedaTensao = false;
                    }
                    cotroles();
                }
            });
            btnQuedaTesao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (setBtnQuedaTensao) {
                        btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_off);
                        setBtnQuedaTensao = false;
                        return;
                    }
                    if (!setBtnQuedaTensao) {
                        btnQuedaTesao.setImageResource(R.drawable.button_img_queda_tensao_on);
                        setBtnQuedaTensao = true;
                        btnQuedaAdmissivel.setImageResource(R.drawable.button_img_queda_admissivel_off);
                        setBtnQuedaAdmissivel = false;
                    }
                    cotroles();
                }
            });
            btnCobre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SomConfig) {
                        ironSound.start();
                    }
                    //cotroles();
                    if (setCobre) {
                        btnCobre.setImageResource(R.drawable.img_cobre_off);
                        setCobre = false;
                    } else {
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

                    if (SomConfig) {
                        ironSound.start();
                    }
                    //cotroles();
                    if (setAluminio) {
                        btnAluminio.setImageResource(R.drawable.img_aluminio_off);
                        setAluminio = false;
                    } else {
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

                   // cotroles();
                    setBitola = setBitola + 1;
                    bitolas();
                }
            });
            btnMenosBitola.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cotroles();
                    setBitola = setBitola - 1;
                    bitolas();
                    if (setBitola <= 0) {
                        setBitola = 1;
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
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            btnLimpar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    limpar();
                }
            });
            btnFecharLayoutResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LayoutResultadoQuedaTensao.setVisibility(View.GONE);
                    scrollViewResultado.setVisibility(View.GONE);
                    layoutAdmissivelQueda.setVisibility(View.VISIBLE);
                    layoutDasCorrentes.setVisibility(View.VISIBLE);
                }
            });

        }

    }

    public void verificaCampo (){

        String temperatura = editTextTemperatura.getText().toString();
        String comprimento = editTextComprimento.getText().toString();
        String tensao = editTextTensao.getText().toString();
        String corrente = editTextCorrente.getText().toString();
        String bitola = editTextBitola.getText().toString();
        String fatorPotecia = editTextFatorPotencia.getText().toString();

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
        if (!TextUtils.isEmpty(fatorPotecia)) {
            // O EditText não está vazio
            fatorPotenciaOk = true;

        } else {
            // O EditText está vazio
            fatorPotenciaOk = false;

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
        if (fatorPotenciaOk){
            imageViewFatorPotencia.setImageResource(R.drawable.img_campo_fatorpotencia_on);
            editTextFatorPotencia.setTextColor(Color.BLACK);
        }else {
            imageViewFatorPotencia.setImageResource(R.drawable.img_campo_fatorpotencia_on);
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
        String fatorPotenciaS = editTextFatorPotencia.getText().toString();
        String tipoCorrente = "";

        double temperatura = 0;
        double tensao = 0;
        double quedaVoltsTotal = 0;
        double comprimento = 0;
        double corrente = 0;
        double bitola = 0;
        double fatorPotencia = 0;
        double resistenciaMaterial = 0;
        double resistividadeAcumulada = 0;

        double quedaVoltsPorcentagem = 0;


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
        try {
            fatorPotencia = Double.parseDouble(fatorPotenciaS);
        } catch (NumberFormatException e) {        }




        // calculo da resistencia de acordo com a temperatura
        if (setCobre){ resistenciaMaterial = 0.0172 * ( 1 + ( 0.00393 * (temperatura - 20)));}
        if (setAluminio) { resistenciaMaterial = 0.0282  * ( 1 + ( 0.00391 * (temperatura - 20))) ; }
        // Confirma todos os campos ok pra dar condição pro calculo de corrente continua

        if ( setBtnQuedaTensao && setBtnCorrenteContinua && tensaoOK && correnteOK && bitolaOK && ( setCobre || setAluminio)  ){
            calculoContinuaOk = true;
        }

        if ( setBtnQuedaTensao && setBtnMonoBifaisico && tensaoOK && correnteOK && bitolaOK && fatorPotenciaOk && ( setCobre || setAluminio)  ){
            isCalculoMonoBifasicoOk = true;
        }
        if ( setBtnQuedaTensao && setBtnTrifasico && tensaoOK && correnteOK && bitolaOK && fatorPotenciaOk && ( setCobre || setAluminio)  ){
            isCalculoTrifasico = true;
        }

        // Calculo corrente continua
        if (calculoContinuaOk) {
            tipoCorrente = "Corrente continua";
            resistividadeAcumulada = resistenciaMaterial * ( comprimento / bitola );
           quedaVoltsPorcentagem = 2 * resistividadeAcumulada * corrente * comprimento ;
            calculoContinuaOk = false;
        }

        if ( isCalculoMonoBifasicoOk) {
            tipoCorrente = "Monofasico / Bifasico";
            resistividadeAcumulada = (resistenciaMaterial * comprimento) / bitola;
            quedaVoltsPorcentagem = 2 * resistividadeAcumulada * corrente * fatorPotencia;
            quedaVoltsTotal = tensao * (quedaVoltsPorcentagem / 100);
            isCalculoMonoBifasicoOk = false ;
        }
        if ( isCalculoTrifasico){
            tipoCorrente = "Trifasico";
            resistividadeAcumulada = (resistenciaMaterial * (comprimento * 1.73205081) / bitola);
            quedaVoltsTotal =  (resistividadeAcumulada * corrente) * fatorPotencia;
            quedaVoltsPorcentagem = (100  / tensao ) * quedaVoltsTotal;
            isCalculoTrifasico = false;

        }


        setResultados(tipoCorrente, quedaVoltsPorcentagem, quedaVoltsTotal, resistividadeAcumulada , tensao);
        scrollViewResultado.setVisibility(View.VISIBLE);


    }

    public void cotroles (){

        LinearLayout layoutSetConfigSecundaria = findViewById(R.id.layoutSetConfigSecundaria);
        FrameLayout layoutFatorPotencia = findViewById(R.id.layoutFatorPotencia);

        if (SomConfig) {
            MediaPlayer click = MediaPlayer.create(this, R.raw.click);

            click.start();
            //click.release();

        }

        if ( ( setBtnQuedaAdmissivel ) || ( setBtnQuedaTensao) ){
            layoutDasCorrentes.setVisibility(View.VISIBLE);
        }

        if ((setBtnCorrenteContinua)||(setBtnMonoBifaisico)||(setBtnTrifasico)){

            layoutSetConfigSecundaria.setVisibility(View.VISIBLE);
        }else {
            layoutSetConfigSecundaria.setVisibility(View.GONE);
        }
        if (setBtnCorrenteContinua){
             // no caso da corrente continua nao se usa fator de potencia
            layoutFatorPotencia.setVisibility(View.GONE);
        }else{
            layoutFatorPotencia.setVisibility(View.VISIBLE);
        }

    }

    public void limpar (){

        recreate();
        onRestart();

    }

    public void setResultados (String tipoCorrente, double tensaoPorcentagemD, double tensaoTotal ,
                               double resistividadeTotal , double tensaoEditDouble){

        // Definir o formato desejado
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat formato = new DecimalFormat("#,##0.##", symbols);

        // Definir o número de casas decimais após o zero
        formato.setMinimumFractionDigits(1);
        formato.setMaximumFractionDigits(3);

        String numeroFormatado;

        TextView editTextTrasferidor = findViewById(R.id.textViewTipoCorrente);
        //numeroFormatado = formato.format(tensaoPorcentagemD);
        editTextTrasferidor.setText(tipoCorrente);

        editTextTrasferidor = findViewById(R.id.ResultTextTensaoPorcentagem);
        numeroFormatado = formato.format(tensaoPorcentagemD);
        editTextTrasferidor.setText(numeroFormatado);

        editTextTrasferidor = findViewById(R.id.ResultTextTensaoVolts);
        numeroFormatado = formato.format(tensaoTotal);
        editTextTrasferidor.setText( numeroFormatado);

        editTextTrasferidor = findViewById(R.id.resultTextResistenciaTotal);
        numeroFormatado = formato.format(resistividadeTotal);
        editTextTrasferidor.setText( numeroFormatado);

        editTextTrasferidor = findViewById(R.id.resultTextTensaoFinal);
        numeroFormatado = formato.format(tensaoEditDouble - tensaoTotal);
        editTextTrasferidor.setText( numeroFormatado);






        layoutAdmissivelQueda.setVisibility(View.GONE);
        layoutDasCorrentes.setVisibility(View.GONE);

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
            cotroles();
          //  garantidorConformidade();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Método chamado após o texto ser alterado
        }
    };


    public void inicializar (){
        layoutDasCorrentes.setVisibility(View.GONE);

        //SomConfig = false;
        setBtnTrifasico = false;
        setBtnCorrenteContinua = false;
        setBtnTrifasico = false;
        cotroles();

        verificaCampo();
        alteraCampo();
        corEditText();
    }


    public void corEditText (){

        editTextBitola.setTextColor(Color.BLACK);
        editTextCorrente.setTextColor(Color.BLACK);
        editTextComprimento.setTextColor(Color.BLACK);
        editTextTensao.setTextColor(Color.BLACK);

    }
}