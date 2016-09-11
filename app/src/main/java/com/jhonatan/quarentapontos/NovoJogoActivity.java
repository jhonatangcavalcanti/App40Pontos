package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jhonatan Gomes on 07/07/16. *
 */
public class NovoJogoActivity extends Activity{
    static final int ESCOLHER_JOGADOR = 0;
    static final String QTD_JOGADORES = "QTD_JOGADORES";
    static final String NOME_JOGADOR = "NOME_JOGADOR";
    static final String ID_JOGADOR = "ID_JOGADOR";
    static final String ID_JOGADOR1 = "ID_JOGADOR1";
    static final String ID_JOGADOR2 = "ID_JOGADOR2";
    static final String ID_JOGADOR3 = "ID_JOGADOR3";
    static final String ID_JOGADOR4 = "ID_JOGADOR4";
    static final String NOME_JOGADOR1 = "NOME_JOGADOR1";
    static final String NOME_JOGADOR2 = "NOME_JOGADOR2";
    static final String NOME_JOGADOR3 = "NOME_JOGADOR3";
    static final String NOME_JOGADOR4 = "NOME_JOGADOR4";
    private int qtdJogadores;
    private String nome_jogador, button_text;
    private Button button;
    private Button jogador1, jogador2, jogador3, jogador4;
    private Intent jogo_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criacao_jogo);

        jogo_intent = new Intent(this, JogoActivity.class);

        jogador1 = (Button) findViewById(R.id.jogador1_criacao_jogo_Button);
        jogador2 = (Button) findViewById(R.id.jogador2_criacao_jogo_Button);
        jogador3 = (Button) findViewById(R.id.jogador3_criacao_jogo_Button);
        jogador4 = (Button) findViewById(R.id.jogador4_criacao_jogo_Button);

        qtdJogadores = 0;
        nome_jogador = "";
    }

    public void selecionarJogador(View view){
        button = (Button) view;
        switch (button.getId()){
            case R.id.jogador1_criacao_jogo_Button:
                break;
        }
        button_text = button.getText().toString(); // para verificar se o texto do botao foi alterado
                                                    //e aumentar a qtd_jogadores
        Intent intent = new Intent(this, JogadoresListActivity.class);
        intent.putExtra("id", button.getId());
        startActivityForResult(intent, ESCOLHER_JOGADOR);
/*
        button.post(new Runnable() {
            @Override
            public void run() {
                //if(nome_jogador.length() > 0) { // selecionou jogador
                    button.setText(nome_jogador);
                    nome_jogador = "";
                //}
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ESCOLHER_JOGADOR){
            if(resultCode == RESULT_OK){
                nome_jogador = data.getStringExtra(NOME_JOGADOR);
                Integer id_jogador = data.getIntExtra(ID_JOGADOR, Integer.valueOf(-1)); //TODO verificar validade -> != -1
                //aumenta a qtd de jogadores se esta selecionando um novo -> != selecionar
                // verificando se o jogador ja foi selecionado
                Bundle extras = jogo_intent.getExtras();
                boolean selecionado = false;
                if(extras != null){
                    for (String key : extras.keySet()){
                        if(extras.get(key) instanceof Integer){
                            if(Integer.valueOf(extras.get(key).toString()) == id_jogador) {
                                selecionado = true;
                                break;
                            }
                        }
                    }
                }
                if(!selecionado) {
                    // verificando jogador selecionado ( getid() referente ao botao da activity )
                    int id = button.getId();
                    if (id == jogador1.getId()) {
                        //primeira vez selecionando
                        if (button.getText().toString().equals(getString(R.string.selecionar_jogador1))) {
                            qtdJogadores++;
                        } else { // apenas alterando jogador selecionado
                            jogo_intent.removeExtra(ID_JOGADOR1);
                            jogo_intent.removeExtra(NOME_JOGADOR1);
                        }
                        jogo_intent.putExtra(ID_JOGADOR1, id_jogador);
                        jogo_intent.putExtra(NOME_JOGADOR1, nome_jogador);
                    } else if (id == jogador2.getId()) {
                        //primeira vez selecionando
                        if (button.getText().toString().equals(getString(R.string.selecionar_jogador2))) {
                            qtdJogadores++;
                        } else { // apenas alterando jogador selecionado
                            jogo_intent.removeExtra(ID_JOGADOR2);
                            jogo_intent.removeExtra(NOME_JOGADOR2);
                        }
                        jogo_intent.putExtra(ID_JOGADOR2, id_jogador);
                        jogo_intent.putExtra(NOME_JOGADOR2, nome_jogador);
                    } else if (id == jogador3.getId()) {
                        //primeira vez selecionando
                        if (button.getText().toString().equals(getString(R.string.selecionar_jogador3))) {
                            qtdJogadores++;
                        } else { // apenas alterando jogador selecionado
                            jogo_intent.removeExtra(ID_JOGADOR3);
                            jogo_intent.removeExtra(NOME_JOGADOR3);
                        }
                        jogo_intent.putExtra(ID_JOGADOR3, id_jogador);
                        jogo_intent.putExtra(NOME_JOGADOR3, nome_jogador);
                    } else if (id == jogador4.getId()) {
                        //primeira vez selecionando
                        if (button.getText().toString().equals(getString(R.string.selecionar_jogador4))) {
                            qtdJogadores++;
                        } else { // apenas alterando jogador selecionado
                            jogo_intent.removeExtra(ID_JOGADOR1);
                            jogo_intent.removeExtra(NOME_JOGADOR4);
                        }
                        jogo_intent.putExtra(ID_JOGADOR4, id_jogador);
                        jogo_intent.putExtra(NOME_JOGADOR4, nome_jogador);
                    }
                    button.setText(nome_jogador);
                    nome_jogador = "";
                }
                else{
                    Toast.makeText(this, getString(R.string.jogador_selecionado_anteriormente), Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Nenhum Jogador Selecionado!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void criarJogo(View view){
        if(qtdJogadores == 4){ //TODO 2 .. 4 jogadores
            jogo_intent.putExtra(QTD_JOGADORES, qtdJogadores);
            startActivity(jogo_intent);
            finish();
        }
        else {
            Toast.makeText(this, getString(R.string.selecionar_jogadores), Toast.LENGTH_SHORT).show();
        }
    }
}
