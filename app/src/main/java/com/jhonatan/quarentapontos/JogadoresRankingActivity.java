package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jhonatan Gomes on 11/07/16. *
 */
public class JogadoresRankingActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView lista_ranking;
    private LinearLayout.LayoutParams params;
    private List<Map<String, Object>> jogadores;
    private SimpleAdapter adapter;
    private DAO dao;
    private Integer valor_maior_derrota, valor_maior_vitoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_jogadores);

        dao = new DAO(this);

        valor_maior_derrota = dao.maiorQtdDerrota();
        valor_maior_vitoria = dao.maiorQtdVitoria();

        lista_ranking = (ListView) findViewById(R.id.lista_ranking_jogadores_ListView);
        lista_ranking.setDividerHeight(20);

        String[] de = new String[]{ "nome", "jogos", "vitorias", "derrotas", "saldo" };
        int[] para = { R.id.nome_TextView, R.id.jogos_TextView, R.id.vitorias_TextView,
                        R.id.derrotas_TextView, R.id.saldo_TextView };

        adapter = new SimpleAdapter(this, listar_ranking_jogadores(), R.layout.jogador_ranking, de, para);

        lista_ranking.setAdapter(adapter);

        adapter.setViewBinder(new rankingBinder());

        lista_ranking.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String message =
                jogadores.get(position).get("nome") + "\n" +
                getString(R.string.maior_derrota) + " " +
                dao.maior_derrota_jogador( Integer.valueOf(jogadores.get(position).get("id").toString()) ) + "\n" +
                getString(R.string.maior_vitoria) + " " +
                dao.maior_vitoria_jogador( Integer.valueOf(jogadores.get(position).get("id").toString()) );

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public List<Map<String, Object>> listar_ranking_jogadores(){
        jogadores = new ArrayList<>();
        List<Jogador> jogadores_list = dao.listarJogadoresRanking();
        for (Jogador jogador : jogadores_list){
            Map<String, Object> item = new HashMap<>();
            item.put("id", jogador.getId());
            item.put("nome", jogador.getNome());
            item.put("jogos", jogador.getQtd_jogos());
            item.put("vitorias", jogador.getQtd_vitorias());
            item.put("derrotas", jogador.getQtd_derrotas());
            item.put("saldo", jogador.getSaldo());
            jogadores.add(item);
        }
        Collections.reverse(jogadores);
        return jogadores;
    }

    private class rankingBinder implements SimpleAdapter.ViewBinder{
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            switch (view.getId()){
                case R.id.saldo_TextView:
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    if(Integer.valueOf(textRepresentation) > 0){
                        textView.setTextColor(getResources().getColor(R.color.vencedor));
                    }
                    else if(Integer.valueOf(textRepresentation) < 0){
                        textView.setTextColor(getResources().getColor(R.color.perdedor));
                    }
                    return true;
                case R.id.vitorias_TextView:
                    TextView vitoria_textView = (TextView) view;
                    vitoria_textView.setText(textRepresentation);
                    if(Integer.valueOf(textRepresentation) == valor_maior_vitoria){
                        vitoria_textView.setTextColor(getResources().getColor(R.color.vencedor));
                    }
                    else{
                        vitoria_textView.setTextColor(getResources().getColor(R.color.branco));
                    }
                    return true;
                case R.id.derrotas_TextView:
                    TextView derrota_textView = (TextView) view;
                    derrota_textView.setText(textRepresentation);
                    Log.d("derrota", textRepresentation + ", " + valor_maior_derrota + ", " + view.getId());
                    if(Integer.valueOf(textRepresentation) == valor_maior_derrota){
                        Log.d("derrota", textRepresentation + ", " + valor_maior_derrota + " igual");
                        derrota_textView.setTextColor(getResources().getColor(R.color.perdedor));
                    }
                    else{
                        derrota_textView.setTextColor(getResources().getColor(R.color.branco));
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

}
