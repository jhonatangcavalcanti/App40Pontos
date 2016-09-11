package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jhonatan Gomes on 04/07/16. *
 */
public class DashboardActivity extends Activity {
    private LinearLayout maior_derrota;
    private ListView maiores_derrotados;
    private List<Map<String, Object>> maiores_derrotados_list;
    private DateFormat dateFormat;
    private DAO dao;
    private SimpleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        dao = new DAO(this);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        maior_derrota = (LinearLayout) findViewById(R.id.maior_derrota_LinearLayout);

        String[] de = new String[]{"nome", "pontos", "data"};
        int[] para = {R.id.nome_maior_derrota_TextView, R.id.pontos_maior_derrota_TextView, R.id.data_maior_derrota_TextView};

        adapter = new SimpleAdapter(this, lista_maiores_derrotados(), R.layout.jogador_maior_derrota_list, de ,para);

        maiores_derrotados = (ListView) findViewById(R.id.maiores_derrotados_ListView);

        maiores_derrotados.setAdapter(adapter);
    }

    public List<Map<String, Object>> lista_maiores_derrotados(){
        maiores_derrotados_list = new ArrayList<>();
        List<Jogador> lista_jogadores_maiores_derrotas = dao.buscarJogadorComMaiorDerrota();
        if(lista_jogadores_maiores_derrotas != null) {
            for (Jogador jogador : lista_jogadores_maiores_derrotas) {
                Map<String, Object> item = new HashMap<>();
                item.put("nome", jogador.getNome());
                item.put("pontos", jogador.getRecordePontosDerrota());
                item.put("data", dateFormat.format(jogador.getData_maior_derrota()));
                //((TextView) maior_derrota.getChildAt(0)).setText(maiores_derrotado.getNome());
                //((TextView) maior_derrota.getChildAt(1)).setText(
                //        maior_derrotado.getRecordePontosDerrota().toString() + " Pontos!");
                //((TextView) maior_derrota.getChildAt(2)).setText("11/07/2016");
                maiores_derrotados_list.add(item);
            }
        }
        return maiores_derrotados_list;
    }

    public void selecionarOpcao(View view){
        switch (view.getId()){
            case R.id.novo_jogo:
                startActivity(new Intent(this, NovoJogoActivity.class));
                break;
            case R.id.historico:
                startActivity(new Intent(this, JogoListActivity.class));
                break;
            case R.id.jogadores:
                startActivity(new Intent(this, JogadoresRankingActivity.class));
                break;
            case R.id.regras:
                startActivity(new Intent(this, RegrasActivity.class));
                break;
        }
    }

}
