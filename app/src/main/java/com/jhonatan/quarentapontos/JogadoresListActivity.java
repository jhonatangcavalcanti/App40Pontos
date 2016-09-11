package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jhonatan Gomes on 07/07/16. *
 */
public class JogadoresListActivity extends Activity implements OnItemClickListener{
    static final int CRIAR_JOGADOR = 1;
    private ListView jogadores_ListView;
    private ArrayAdapter<String> adapter;
    private List<String> jogadores;
    private List<Jogador> jogadores_list;
    private TextView selecione_jogador;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadores_list);

        dao = new DAO(this);

        jogadores_ListView = (ListView) findViewById(R.id.jogadores_ListView);

        jogadores = listarJogadores();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jogadores);

        jogadores_ListView.setAdapter(adapter);
        jogadores_ListView.setOnItemClickListener(this);

        selecione_jogador = (TextView) findViewById(R.id.selecione_jogador_TextView);
        if(getIntent().hasExtra("id")) { // selecionando jogador para iniciar um novo jogo
            selecione_jogador.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getIntent().hasExtra("id")){ // selecionando jogador para iniciar um novo jogo
            Intent returnIntent = new Intent();
            returnIntent.putExtra(NovoJogoActivity.NOME_JOGADOR, ((TextView)view).getText().toString());
            returnIntent.putExtra(NovoJogoActivity.ID_JOGADOR, jogadores_list.get(position).getId());
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    public List< String > listarJogadores(){
        jogadores_list = new ArrayList<>();
        jogadores_list = dao.listarJogadores();
        jogadores = new ArrayList<>();

        for (Jogador jogador : jogadores_list){
            jogadores.add(jogador.getNome());
        }

        return jogadores;
    }

    public void criarJogador(View view){
        Intent intent = new Intent(this, JogadorActivity.class);
        startActivityForResult(intent, CRIAR_JOGADOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CRIAR_JOGADOR) {
            if(resultCode == RESULT_OK) {
                adapter.clear(); // TODO verificar metodo para atualizar listview
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listarJogadores());
                jogadores_ListView.setAdapter(adapter);
                //jogadores = listarJogadores();
                /*jogadores_ListView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        jogadores_ListView.invalidateViews();
                    }
                });*/
            }
        }
    }
}
