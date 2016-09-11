package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogador;

/**
 * Created by Jhonatan Gomes on 07/07/16. *
 */
public class JogadorActivity extends Activity {
    private DAO dao;
    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criacao_jogador);

        nome = (EditText) findViewById(R.id.nome_jogador_EditText);

        dao = new DAO(this);
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    public void salvarJogador(View view){
        String nome_jogador = nome.getText().toString();
        if(nome_jogador.length() > 0){
            Jogador jogador = new Jogador(nome_jogador);
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            if(dao.inserir(jogador) > 0){
                Toast.makeText(this, getString(R.string.salvo_sucesso), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
            }

            finish();
        }
        else{
            Toast.makeText(this, getString(R.string.insira_nome), Toast.LENGTH_SHORT).show();
        }

    }


}
