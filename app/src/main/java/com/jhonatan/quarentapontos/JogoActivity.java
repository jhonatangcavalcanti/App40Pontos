package com.jhonatan.quarentapontos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.jhonatan.quarentapontos.dao.DAO;
import com.jhonatan.quarentapontos.domain.Jogador;
import com.jhonatan.quarentapontos.domain.Jogo;
import com.jhonatan.quarentapontos.domain.Rodada;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jhonatan Gomes on 04/07/16. *
 */
public class JogoActivity extends Activity {
    private Jogo jogo;
    private Rodada rodada;
    private Date data;
    private Integer rodadas, indiceInsercao, qtdJogadores;
    private ScrollView scrollView;
    private TableLayout placar;
    private LinearLayout jogadores, pontosRodadaAtual, total, botoes, nomeJogadoresPontos;
    private TextView rodada_final, rodadaAtualInfo;
    private TableRow.LayoutParams rowParams;
    private List<Jogador> jogadores_list;
    private DAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jogo);


        //BD
        dao = new DAO(this);

        placar = (TableLayout) findViewById(R.id.placar_tablelayout);
        scrollView = (ScrollView) findViewById(R.id.pontosRodadaEdit_ScroolView);
        rodada_final = (TextView) findViewById(R.id.totalFinal_TextView);
        rodadaAtualInfo = (TextView) findViewById(R.id.rodada_atual_TextView);
        total = (LinearLayout) findViewById(R.id.total_LinearLayout);
        pontosRodadaAtual = (LinearLayout) findViewById(R.id.pontosRodadaEdit_LinearLayout);
        botoes = (LinearLayout) findViewById(R.id.botoes_LinearLayout);
        rodadas = 1;
        indiceInsercao = 0;

        if(getIntent().hasExtra(JogoListActivity.RECUPERAR_JOGO)) { // novo jogo
            recuperarJogo();
        }
        else{
            Calendar calendar = Calendar.getInstance();
            data = criarData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            //para adicionar aos itens criados dinamicamente no tableLayout
            rowParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
            //rowParams.gravity = Gravity.CENTER;

            jogadores = (LinearLayout) findViewById(R.id.jogadores_LinearLayout);
            nomeJogadoresPontos = (LinearLayout) findViewById(R.id.nomes_jogadores_pontos_LinearLayout);
            qtdJogadores = 4; // TODO verificar quantidade de jogadores
            jogadores_list = new ArrayList<>();
            //for (int i = 0; i < qtdJogadores; i++) {
            jogadores_list.add(dao.buscar(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR1, -1)));
            jogadores_list.add(dao.buscar(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR2, -1)));
            jogadores_list.add(dao.buscar(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR3, -1)));
            jogadores_list.add(dao.buscar(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR4, -1)));
            //}

            // pegando nome dos jogadores selecionados para o jogo
            /*jogadores_list.get(0).setNome(getIntent().getStringExtra(NovoJogoActivity.NOME_JOGADOR1));
            jogadores_list.get(1).setNome(getIntent().getStringExtra(NovoJogoActivity.NOME_JOGADOR2));
            jogadores_list.get(2).setNome(getIntent().getStringExtra(NovoJogoActivity.NOME_JOGADOR3));
            jogadores_list.get(3).setNome(getIntent().getStringExtra(NovoJogoActivity.NOME_JOGADOR4));*/
            // setando o nome dos jogadores selecionados no titulo e acima das pontuacoes da rodada
            for (int i = 0; i < qtdJogadores; i++) {
                ((TextView) jogadores.getChildAt(i)).setText(jogadores_list.get(i).getNome());
                ((TextView) nomeJogadoresPontos.getChildAt(i)).setText(jogadores_list.get(i).getNome());
            }
            // setando os ids dos jogadores selecionados para o jogo
            // TODO verificar validade -> -1 como valor invalido
            /*jogadores_list.get(0).setId(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR1, -1));
            jogadores_list.get(1).setId(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR2, -1));
            jogadores_list.get(2).setId(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR3, -1));
            jogadores_list.get(3).setId(getIntent().getIntExtra(NovoJogoActivity.ID_JOGADOR4, -1));*/

            //((TextView)pontosRodadaAtual.getChildAt(0)).setText(getResources().getString(R.string.rodada) + " " + (rodadas));

            // inserindo jogo no BD
            salvarJogo();
        }
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    public void recuperarJogo(){
        Integer id_jogo = getIntent().getIntExtra(JogoListActivity.RECUPERAR_JOGO, -1);
        jogo = dao.buscar_jogo(id_jogo);
        List<Rodada> rodadas_list = dao.listarRodadas(id_jogo);

        //para adicionar aos itens criados dinamicamente no tableLayout
        rowParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);

        jogadores = (LinearLayout) findViewById(R.id.jogadores_LinearLayout);
        nomeJogadoresPontos = (LinearLayout) findViewById(R.id.nomes_jogadores_pontos_LinearLayout);
        qtdJogadores = 4; // TODO verificar quantidade de jogadores
        jogadores_list = new ArrayList<>();

        jogadores_list.add(dao.buscar(jogo.getId_jogador1()));
        jogadores_list.add(dao.buscar(jogo.getId_jogador2()));
        jogadores_list.add(dao.buscar(jogo.getId_jogador3()));
        jogadores_list.add(dao.buscar(jogo.getId_jogador4()));

        // setando o nome dos jogadores selecionados no titulo e acima das pontuacoes da rodada
        for (int i = 0; i < qtdJogadores; i++) {
            ((TextView) jogadores.getChildAt(i)).setText(jogadores_list.get(i).getNome());
            ((TextView) nomeJogadoresPontos.getChildAt(i)).setText(jogadores_list.get(i).getNome());
        }

        for(int i_jogo = 0 ; i_jogo < rodadas_list.size() ; i_jogo++) {
            /* --- Texto que informa a rodada acima dos pontos  --- */
            TextView texto_rodada = new TextView(this);
            texto_rodada.setTextColor(getResources().getColor(R.color.branco));
            texto_rodada.setText(rodadas.toString());
            texto_rodada.setBackgroundColor(getResources().getColor(R.color.cinza));
            texto_rodada.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            texto_rodada.setGravity(Gravity.CENTER);

            /* --- separador de linha --- */
            View line = new View(this);
            line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
            line.setBackgroundColor(getResources().getColor(R.color.divisor));

            /* --- nova linha para inserir pontos --- */
            TableRow pontos_rodada = new TableRow(this);
            pontos_rodada.setGravity(Gravity.CENTER);

            Integer[] pontos = new Integer[4];
            // adicionando novas colunas com as pontuacoes atualizadas
            for (int i = 0; i < qtdJogadores; i++) {
                pontos_rodada.addView(atualiza_pontuacao_recuperacao_jogo(i, rodadas_list.get(i_jogo)));
                pontos[i] = Integer.valueOf(((TextView)pontos_rodada.getChildAt(i)).getText().toString());
            }

            // pegando indice do perdedor atual e vencedor atual
            int indice_perdedor = 0, indice_ganhador = 0;
            for (int i = 0; i < qtdJogadores; i++) {
                if (Integer.valueOf(((TextView) pontos_rodada.getChildAt(i)).getText().toString()) >=
                        Integer.valueOf(((TextView) pontos_rodada.getChildAt(indice_perdedor)).getText().toString())) {
                    indice_perdedor = i;
                }
                if (Integer.valueOf(((TextView) pontos_rodada.getChildAt(i)).getText().toString()) <=
                        Integer.valueOf(((TextView) pontos_rodada.getChildAt(indice_ganhador)).getText().toString())) {
                    indice_ganhador = i;
                }
            }

            // colorindo perdedor atual e vencedor atual
            ((TextView) pontos_rodada.getChildAt(indice_perdedor)).setTextColor(getResources().getColor(R.color.perdedor));
            ((TextView) pontos_rodada.getChildAt(indice_ganhador)).setTextColor(getResources().getColor(R.color.vencedor));

            // adicionando o texto
            placar.addView(texto_rodada, indiceInsercao++);

            //adicionando novas pontuacoes da rodada a tabela
            placar.addView(pontos_rodada, indiceInsercao++);

            //adicionando a linha separadora
            placar.addView(line, indiceInsercao++);

            //limpando as pontuacoes digitadas
            for (int i = 0; i < qtdJogadores; i++) {
                ((EditText) pontosRodadaAtual.getChildAt(i)).setText("");
            }

            //movendo scrollView para a ultima rodada
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            });

            // gravando rodada no BD -> ja gravado

            // verificando se o perdedor atual perdeu o jogo
            if (Integer.valueOf(((TextView) pontos_rodada.getChildAt(indice_perdedor)).getText().toString()) >= 40) {
                // remover e atualizar
                ((Button) botoes.getChildAt(0)).setVisibility(View.GONE);
                ((Button) botoes.getChildAt(1)).setVisibility(View.GONE);
                // iniciar novo jogo
                ((Button) botoes.getChildAt(2)).setVisibility(View.VISIBLE);

                pontosRodadaAtual.setVisibility(View.GONE);
                rodadaAtualInfo.setVisibility(View.GONE);
                nomeJogadoresPontos.setVisibility(View.GONE);

                // salvando jogo no BD
            } else { //proxima rodada se ninguem perdeu
                //movendo foco para primeiro edittext
                pontosRodadaAtual.getChildAt(0).requestFocus();
                //proxima rodada
                rodadas++;
                // texto informando a rodada
                String rodada_atual_info = getString(R.string.pontos_da_rodada) + " " + (rodadas);
                rodadaAtualInfo.setText(rodada_atual_info);
            }

        }
    }

    public void salvarJogo(){
        jogo = new Jogo(
                jogadores_list.get(0).getId(),
                jogadores_list.get(1).getId(),
                jogadores_list.get(2).getId(),
                jogadores_list.get(3).getId(),
                new Date(data.getTime()));

        long id = dao.inserir_jogo_inicio(jogo);
        //TODO verificar se foi inserido com sucesso
        jogo.setId(Integer.valueOf(((int)id)));
    }

    private Date criarData(int ano, int mes, int dia){
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        return calendar.getTime();
    }

    private TextView atualiza_pontuacao_recuperacao_jogo(int id, Rodada rodada){
        TextView textView = new TextView(this);
        textView.setLayoutParams(rowParams);

        Integer pontos_total=0;
        switch (id){
            case 0:
                pontos_total = rodada.getPontos_jogador1();
                break;
            case 1:
                pontos_total = rodada.getPontos_jogador2();
                break;
            case 2:
                pontos_total = rodada.getPontos_jogador3();
                break;
            case 3:
                pontos_total = rodada.getPontos_jogador4();
                break;
        }
        Integer novo_total = pontos_total;

        // novo textview para guardar a pontuacao da rodada
        textView.setText(novo_total.toString());
        textView.setGravity(Gravity.CENTER);
        textView.setTextAppearance(this, android.R.style.TextAppearance_Large);

        /// atualizando a pontuacao total = total + rodada
        ((TextView) total.getChildAt(id)).setText(novo_total.toString());

        //textview com a nova pontuacao do jogador com id do parametro
        return textView;
    }

    private TextView atualiza_pontuacao(int id){
        TextView textView = new TextView(this);
        textView.setLayoutParams(rowParams);

        Integer pontos_total = Integer.valueOf(((TextView) total.getChildAt(id)).getText().toString());
        Integer pontos_rodada = Integer.valueOf(((TextView)pontosRodadaAtual.getChildAt(id)).getText().toString());
        Integer novo_total = pontos_total + pontos_rodada;

        // novo textview para guardar a pontuacao da rodada
        textView.setText(novo_total.toString());
        textView.setGravity(Gravity.CENTER);
        textView.setTextAppearance(this, android.R.style.TextAppearance_Large);

        /// atualizando a pontuacao total = total + rodada
        ((TextView) total.getChildAt(id)).setText(novo_total.toString());

        //textview com a nova pontuacao do jogador com id do parametro
        return textView;
    }

    public void atualizarJogo(View view){
        //verificando se existe alguma pontuação invalida (sem valor)
        for(int i = 0 ; i < qtdJogadores ; i++){
            if(((EditText)pontosRodadaAtual.getChildAt(i)).getText().length() == 0){
                ((EditText)pontosRodadaAtual.getChildAt(i)).requestFocus();//(getResources().getColor(R.color.perdedor));
                return;
            }
        }

        /* --- Texto que informa a rodada acima dos pontos  --- */
        TextView texto_rodada = new TextView(this);
        texto_rodada.setTextColor(getResources().getColor(R.color.branco));
        texto_rodada.setText(rodadas.toString());
        texto_rodada.setBackgroundColor(getResources().getColor(R.color.cinza));
        texto_rodada.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        texto_rodada.setGravity(Gravity.CENTER);

        /* --- separador de linha --- */
        View line = new View(this);
        line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
        line.setBackgroundColor(getResources().getColor(R.color.divisor));

        /* --- nova linha para inserir pontos --- */
        TableRow pontos_rodada = new TableRow(this);
        pontos_rodada.setGravity(Gravity.CENTER);

        Integer[] pontos = new Integer[4];
        // adicionando novas colunas com as pontuacoes atualizadas
        for(int i = 0 ; i < qtdJogadores ; i++){
            pontos_rodada.addView(atualiza_pontuacao(i));
            pontos[i] = Integer.valueOf(((TextView)pontos_rodada.getChildAt(i)).getText().toString());
        }

        // pegando indice do perdedor atual e vencedor atual
        int indice_perdedor=0, indice_ganhador=0;
        for(int i = 0 ; i < qtdJogadores ; i++){
            if(Integer.valueOf(((TextView)pontos_rodada.getChildAt(i)).getText().toString()) >=
               Integer.valueOf(((TextView)pontos_rodada.getChildAt(indice_perdedor)).getText().toString())){
                indice_perdedor = i;
            }
            if(Integer.valueOf(((TextView)pontos_rodada.getChildAt(i)).getText().toString()) <=
               Integer.valueOf(((TextView)pontos_rodada.getChildAt(indice_ganhador)).getText().toString())){
                indice_ganhador = i;
            }
        }

        // colorindo perdedor atual e vencedor atual
        ((TextView)pontos_rodada.getChildAt(indice_perdedor)).setTextColor(getResources().getColor(R.color.perdedor));
        ((TextView)pontos_rodada.getChildAt(indice_ganhador)).setTextColor(getResources().getColor(R.color.vencedor));

        // adicionando o texto
        placar.addView(texto_rodada, indiceInsercao++);

        //adicionando novas pontuacoes da rodada a tabela
        placar.addView(pontos_rodada, indiceInsercao++);

        //adicionando a linha separadora
        placar.addView(line, indiceInsercao++);

        //limpando as pontuacoes digitadas
        for(int i = 0 ; i < qtdJogadores ; i++){
            ((EditText)pontosRodadaAtual.getChildAt(i)).setText("");
        }

        //movendo scrollView para a ultima rodada
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        /* gravando rodada no BD */
        rodada = new Rodada();
        rodada.setId_jogo(jogo.getId());
        rodada.setRodada(rodadas);
        rodada.setPontos_jogador1(pontos[0]);
        rodada.setPontos_jogador2(pontos[1]);
        rodada.setPontos_jogador3(pontos[2]);
        rodada.setPontos_jogador4(pontos[3]);
        long id = dao.inserir(rodada);

        // verificando se o perdedor atual perdeu o jogo
        if(Integer.valueOf(((TextView)pontos_rodada.getChildAt(indice_perdedor)).getText().toString()) >= 40){
            // remover e atualizar
            ((Button)botoes.getChildAt(0)).setVisibility(View.GONE);
            ((Button)botoes.getChildAt(1)).setVisibility(View.GONE);
            // iniciar novo jogo
            ((Button)botoes.getChildAt(2)).setVisibility(View.VISIBLE);

            pontosRodadaAtual.setVisibility(View.GONE);
            rodadaAtualInfo.setVisibility(View.GONE);
            nomeJogadoresPontos.setVisibility(View.GONE);

            jogo.setId_vencedor(indice_ganhador);
            jogo.setId_perdedor(indice_perdedor);
            jogo.setpontos_final_jogador1(pontos[0]);
            jogo.setpontos_final_jogador2(pontos[1]);
            jogo.setpontos_final_jogador3(pontos[2]);
            jogo.setpontos_final_jogador4(pontos[3]);
            jogo.setQtd_rodadas(rodadas);
            //salvando jogo no BD
            dao.inserir_jogo_finalizado(jogo);
            //atualizando dados dos jogadores
            List<Jogador> jogadores_atualizados = new ArrayList<>();
            for(int i = 0 ; i < qtdJogadores ; i++) {
                Jogador jogador = jogadores_list.get(i);
                if(i == indice_ganhador){
                    jogador.setQtd_vitorias(jogador.getQtd_vitorias()+1);
                    jogador.setSaldo(jogador.getSaldo()+1);
                    if(jogador.getRecordePontosVitorias() > pontos[i]){
                        jogador.setRecordePontosVitorias(pontos[i]);
                    }
                }
                if(i == indice_perdedor){
                    jogador.setQtd_derrotas(jogador.getQtd_derrotas()+1);
                    jogador.setSaldo(jogador.getSaldo()-1);
                    if(jogador.getRecordePontosDerrota() < pontos[i]){
                        jogador.setRecordePontosDerrota(pontos[i]);
                        jogador.setData_maior_derrota(data);
                    }
                }
                Log.d("qtdjogos", "jogos: " + jogador.getQtd_jogos());
                jogador.setQtd_jogos(jogador.getQtd_jogos()+1);
                jogadores_atualizados.add(jogador);
            }
            // salvando dados dos jogadores atualizados no bd
            for(int i = 0 ; i < qtdJogadores ; i++) {
                Log.d("atualizando_jogador", "atualizando " + i + ", " + jogadores_atualizados.get(i).getNome());
                dao.atualizar_jogador(jogadores_atualizados.get(i));
            }
        }
        else{ //proxima rodada se ninguem perdeu
            //movendo foco para primeiro edittext
            pontosRodadaAtual.getChildAt(0).requestFocus();
            //proxima rodada
            rodadas++;
            // texto informando a rodada
            String rodada_atual_info = getString(R.string.pontos_da_rodada) + " " + (rodadas);
            rodadaAtualInfo.setText(rodada_atual_info);
        }
    }

    public void editarUltimaRodada(View view){
        /// atualizando a pontuacao total = total + rodada
        //((TextView) total.getChildAt(id)).setText(novo_total.toString());

    }
}
