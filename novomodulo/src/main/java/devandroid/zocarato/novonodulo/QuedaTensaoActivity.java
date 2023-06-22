package devandroid.zocarato.novonodulo;

import androidx.appcompat.app.AppCompatActivity;

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
    int setBitola;





    ImageView imageViewCampoComprimento;
    EditText editTextComprimento;
    boolean comprimentoOK;

    ImageView imageViewCampoTensao;
    EditText editTextTensao;
    boolean tensaoOK;

    ImageView imageViewCampoCorrente;
    EditText editTextCorrente;
    boolean correnteOK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queda_tensao);

        MediaPlayer click = MediaPlayer.create(this, R.raw.click);

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

        imageViewCampoComprimento = findViewById(R.id.imageViewCampoComprimento);
        editTextComprimento = findViewById(R.id.editTextComprimento);
        editTextComprimento.addTextChangedListener(textWatcher);

        imageViewCampoTensao = findViewById(R.id.imageViewCampoTensao);
        editTextTensao = findViewById(R.id.editTextTensao);
        editTextTensao.addTextChangedListener(textWatcher);

        imageViewCampoCorrente = findViewById(R.id.imageViewCampoCorrente);
        editTextCorrente = findViewById(R.id.editTextCorrente);
        editTextCorrente.addTextChangedListener(textWatcher);


        btnCobre = findViewById(R.id.btnCobre);
        setCobre = false;
        btnAluminio = findViewById(R.id.btnAluminio);
        setAluminio = false;

        btnMaisBitola = findViewById(R.id.btnMaisBitola);
        btnMenosBitola = findViewById(R.id.btnMenosBitola);



        btnCorrenteContinua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
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

    }

    public void verificaCampo (){

        String comprimento = editTextComprimento.getText().toString();
        String tensao = editTextTensao.getText().toString();
        String corrente = editTextCorrente.getText().toString();


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