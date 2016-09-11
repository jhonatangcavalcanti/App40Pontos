package com.jhonatan.quarentapontos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogo;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jhonatan Gomes on 09/07/16. *
 */
public class JogoListActivity extends ListActivity implements OnItemClickListener{
    static final String RECUPERAR_JOGO = "RECUPERAR_JOGO";
    private List<Map<String, Object>> jogos;
    private SimpleAdapter adapter;
    private SimpleDateFormat dateFormat;
    private Integer pontos_vencedor, pontos_perdedor;
    private Integer conta_jogo;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dao = new DAO(this);

        conta_jogo = 1;

        String[] de = {"conta_jogo", "rodadas", "data", "nome_jogador1", "nome_jogador2", "nome_jogador3", "nome_jogador4",
                        "pontos_jogador1", "pontos_jogador2", "pontos_jogador3", "pontos_jogador4"};

        int[] para = {R.id.jogo_cont_TextView, R.id.rodadas_TextView, R.id.data_TextView, R.id.jogador1_TextView,
                    R.id.jogador2_TextView, R.id.jogador3_TextView, R.id.jogador4_TextView,
                    R.id.jogador1_pontos_TextView, R.id.jogador2_pontos_TextView,
                    R.id.jogador3_pontos_TextView, R.id.jogador4_pontos_TextView};

        jogos = listarJogos();

        adapter = new SimpleAdapter(this, jogos, R.layout.jogos_list, de, para);

        adapter.setViewBinder(new JogoListViewBinder());

        setListAdapter(adapter);

        getListView().setDividerHeight(20);

        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, JogoActivity.class);
        Integer id_jogo_selecionado = (Integer) jogos.get(position).get("id");
        intent.putExtra(RECUPERAR_JOGO, id_jogo_selecionado);
        startActivity(intent);
        if(jogos.get(position).get("conta_jogo").toString().contains("!")){ // NÃO FINALIZADO!
            finish();
        }
    }

    private class JogoListViewBinder implements ViewBinder{
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            TextView textView;
            switch (view.getId()){
                case R.id.jogador1_pontos_TextView:
                case R.id.jogador2_pontos_TextView:
                case R.id.jogador3_pontos_TextView:
                case R.id.jogador4_pontos_TextView:
                    textView = (TextView) view;
                    textView.setText(textRepresentation);
                    if(textRepresentation.contains("V")){
                        textView.setTextColor(getResources().getColor(R.color.vencedor));
                    }
                    else if(textRepresentation.contains("P")){
                        textView.setTextColor(getResources().getColor(R.color.perdedor));
                    }
                    else{
                        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    return true;
            }
            return false;
        }
    }



    private List<Map<String, Object>> listarJogos(){
        jogos = new ArrayList<>();

        List<Jogo> lista_jogos = dao.listarJogos();
        for (Jogo jogo : lista_jogos){
            Map<String, Object> item = new HashMap<>();
            item.put("id", jogo.getId());
            item.put("rodadas", getString(R.string.rodadas) + ": " + jogo.getQtd_rodadas());
            item.put("data", dateFormat.format(jogo.getData()));
            item.put("nome_jogador1", (dao.buscar(jogo.getId_jogador1())).getNome());
            item.put("nome_jogador2", (dao.buscar(jogo.getId_jogador2())).getNome());
            item.put("nome_jogador3", (dao.buscar(jogo.getId_jogador3())).getNome());
            item.put("nome_jogador4", (dao.buscar(jogo.getId_jogador4())).getNome());

            pontos_perdedor =
                    Math.max(
                            Math.max(jogo.getpontos_final_jogador3(), jogo.getpontos_final_jogador4()),
                            Math.max(jogo.getpontos_final_jogador1(), jogo.getpontos_final_jogador2())
                    );
            pontos_vencedor =
                    Math.min(
                            Math.min(jogo.getpontos_final_jogador3(), jogo.getpontos_final_jogador4()),
                            Math.min(jogo.getpontos_final_jogador1(), jogo.getpontos_final_jogador2())
                    );
            if (pontos_perdedor < 40){
                item.put("conta_jogo", getString(R.string.jogo) + " " + conta_jogo++ + " (NÃO FINALIZADO!)");
            }
            else {
                item.put("conta_jogo", getString(R.string.jogo) + " " + conta_jogo++);
            }
            //guardando informação para verificar quem foi o vencedor e o perdedor para colorir no bind
            if(jogo.getpontos_final_jogador1() == pontos_vencedor ||
               jogo.getpontos_final_jogador1() == pontos_perdedor){
                if(jogo.getpontos_final_jogador1() == pontos_vencedor){
                    item.put("pontos_jogador1", jogo.getpontos_final_jogador1() + " (V)");
                }
                else{
                    item.put("pontos_jogador1", jogo.getpontos_final_jogador1() + " (P)");
                }
            }
            else{
                item.put("pontos_jogador1", jogo.getpontos_final_jogador1());
            }

            if(jogo.getpontos_final_jogador2() == pontos_vencedor ||
                    jogo.getpontos_final_jogador2() == pontos_perdedor){
                if(jogo.getpontos_final_jogador2() == pontos_vencedor){
                    item.put("pontos_jogador2", jogo.getpontos_final_jogador2() + " (V)");
                }
                else{
                    item.put("pontos_jogador2", jogo.getpontos_final_jogador2() + " (P)");
                }
            }
            else{
                item.put("pontos_jogador2", jogo.getpontos_final_jogador2());
            }

            if(jogo.getpontos_final_jogador3() == pontos_vencedor ||
                    jogo.getpontos_final_jogador3() == pontos_perdedor){
                if(jogo.getpontos_final_jogador3() == pontos_vencedor){
                    item.put("pontos_jogador3", jogo.getpontos_final_jogador3() + " (V)");
                }
                else{
                    item.put("pontos_jogador3", jogo.getpontos_final_jogador3() + " (P)");
                }
            }
            else{
                item.put("pontos_jogador3", jogo.getpontos_final_jogador3());
            }

            if(jogo.getpontos_final_jogador4() == pontos_vencedor ||
                    jogo.getpontos_final_jogador4() == pontos_perdedor){
                if(jogo.getpontos_final_jogador4() == pontos_vencedor){
                    item.put("pontos_jogador4", jogo.getpontos_final_jogador4() + " (V)");
                }
                else{
                    item.put("pontos_jogador4", jogo.getpontos_final_jogador4() + " (P)");
                }
            }
            else{
                item.put("pontos_jogador4", jogo.getpontos_final_jogador4());
            }
            // TODO selecionar ponto de vencedor e perdedor para n jogadores
            item.put("id_vencedor", jogo.getId_vencedor());
            item.put("id_perdedor", jogo.getId_perdedor());
            jogos.add(item);
        }
        Collections.reverse(jogos); // para lista ficar ao contrario

        return jogos;
    }

}
