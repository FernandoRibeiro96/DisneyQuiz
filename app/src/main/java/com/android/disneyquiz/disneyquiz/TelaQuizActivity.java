package com.android.disneyquiz.disneyquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaQuizActivity extends AppCompatActivity {

    private TextView perguntas;
    private RadioGroup rgRespostas;
    private RadioButton rbResposta1;
    private RadioButton rbResposta2;
    private RadioButton rbResposta3;
    private ImageView escolher;
    private int respostaCerta;
    //private TextView pontuacao;

    private int pontos= 0;

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("As irmãs malvadas de cinderela se chamavam Anastásia e...", R.id.rbResposta3Id,
                    "Grizella", "Amélia", "Drizella"));
            add(new Questao("Na branca de Neves existem 7 anôes: Zangado, Dengoso, Atchim, Feliz, Soneca, e...", R.id.rbResposta1Id,
                    "Dunga", "Preguiçoso", "Fedorento"));
            add(new Questao("Como se chama o homem que quer casar com Bella no filme A Bella e a Fera? ", R.id.rbResposta2Id,
                    "Phillipe", "Gaston", "Arnald"));
            add(new Questao("Onde Aladdim encontra a lâmpada do gênio? ",R.id.rbResposta2Id,
                    "Na Caverna dos Sonhos", "Na Caverna das Maravilhas", "Na Caverna dos Milagres"));
            add(new Questao("O que a Branca de Neve come que a envenena?", R.id.rbResposta3Id,
                    "Banana","Frango", "Maçã"));
            add(new Questao("Na pequena sereia, Ariel tem um amigo que é um caranguejo. Como ele se chama?",R.id.rbResposta3Id,
                    "Christian", "william", "Sebastian"));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);

        perguntas = (TextView) findViewById(R.id.perguntaId);
        //pontuacao = (TextView) findViewById(R.id.pontosId);

        rbResposta1 = (RadioButton) findViewById(R.id.rbResposta1Id);
        rbResposta2 = (RadioButton) findViewById(R.id.rbResposta2Id);
        rbResposta3 = (RadioButton) findViewById(R.id.rbResposta3Id);
        rgRespostas = (RadioGroup) findViewById(R.id.rbrespostaId);

        escolher = (ImageView) findViewById(R.id.btnResponderId);
        escolher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rg = (RadioButton) findViewById(rgRespostas.getCheckedRadioButtonId());
                Intent intent = new Intent(TelaQuizActivity.this, TelaRespostaActivity.class);
                if (rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
                    intent.putExtra("acertou", true);
                    pontos++;
                    //pontuacao.setText("Pontos: "+pontos);

                }
                else intent.putExtra("acertou", false);
                intent.putExtra("pontos", pontos);
                startActivity(intent);
                rg.setChecked(false);
            }
        });
        carregarQuestao();
    }
    /*public void btnResponderOnClick(View v) {
        RadioButton rg = (RadioButton) findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(TelaQuizActivity.this, TelaRespostaActivity.class);
        if (rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;

        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rg.setChecked(false);

    }*/

    private void carregarQuestao(){
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            perguntas.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        }
        else{ //acabaram as questões
            Intent intent = new Intent(this, TelaRespostaActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();

    }

}
