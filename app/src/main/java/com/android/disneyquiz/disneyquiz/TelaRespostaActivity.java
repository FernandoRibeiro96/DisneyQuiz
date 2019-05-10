package com.android.disneyquiz.disneyquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaRespostaActivity extends AppCompatActivity {

    private TextView resposta;
    private ImageView imgResposta;
    private ImageView btnJogarNovamente;
    private ImageView sair;
    public static int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resposta);


         imgResposta = (ImageView)findViewById(R.id.imgResposta);
         resposta = (TextView)findViewById(R.id.textoAcERId);
         btnJogarNovamente = (ImageView) findViewById(R.id.btnJogarNovamente);
         sair = (ImageView) findViewById(R.id.sairId);
         sair.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         btnJogarNovamente.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(TelaRespostaActivity.this, TelaQuizActivity.class);
                 startActivity(intent);
                 finish();
             }
         });


        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        if(intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            sair.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            if (acertou) {
                cont ++;
                imgResposta.setImageResource(R.drawable.acerto);
                resposta.setText("Parabéns você acertou! Pontos: " + pontos);

            } else {
                imgResposta.setImageResource(R.drawable.erro);
                resposta.setText("Resposta errada tente novamente!");
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        } else{
            btnJogarNovamente.setVisibility(View.VISIBLE);
            sair.setVisibility(View.VISIBLE);
            resposta.setText("Você fez: " + cont + " pontos!");
            cont = 0;

            if(pontos >= 3) {
                imgResposta.setImageResource(R.drawable.acerto);
                //resposta.setText("Parabens vc foi muito bem "+pontos);
            }else{
                imgResposta.setImageResource(R.drawable.erro);
                //resposta.setText("Hum, vc não foi tão bem, vamos tentar novamente "+pontos);
            }

        }
    }

   /* public void btnJogarNovamenteOnClick(View v){
        Intent intent = new Intent(this, TelaQuizActivity.class);
        startActivity(intent);
        finish();
    }*/
}
