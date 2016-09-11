package com.jhonatan.quarentapontos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;

import com.jhonatan.quarentapontos.DataBaseHelper;
import com.jhonatan.quarentapontos.domain.Jogador;
import com.jhonatan.quarentapontos.domain.Jogo;
import com.jhonatan.quarentapontos.domain.Rodada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jhonatan Gomes on 08/07/16 *.
 */
public class DAO {
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public DAO(Context context){
        helper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDb(){
        if(db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
    }

    public long inserir_jogo_inicio(Jogo jogo){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Jogo.DATA, jogo.getData().getTime());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR1, jogo.getId_jogador1());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR2, jogo.getId_jogador2());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR3, jogo.getId_jogador3());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR4, jogo.getId_jogador4());
        //dados para atualizar no fim do jogo
        values.putNull(DataBaseHelper.Jogo.ID_VENCEDOR);
        values.putNull(DataBaseHelper.Jogo.ID_PERDEDOR);
        values.putNull(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR1);
        values.putNull(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR2);
        values.putNull(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR3);
        values.putNull(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR4);
        values.putNull(DataBaseHelper.Jogo.QTD_RODADAS);

        return getDb().insert(DataBaseHelper.Jogo.TABELA, null, values);
    }

    public long inserir_jogo_finalizado(Jogo jogo){
        String whereClause = DataBaseHelper.Jogo._ID + " = ? ";
        String[] whereArgs = new String[]{ jogo.getId().toString() };

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Jogo.DATA, jogo.getData().getTime());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR1, jogo.getId_jogador1());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR2, jogo.getId_jogador2());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR3, jogo.getId_jogador3());
        values.put(DataBaseHelper.Jogo.ID_JOGADOR4, jogo.getId_jogador4());
        values.put(DataBaseHelper.Jogo.ID_VENCEDOR, jogo.getId_vencedor());
        values.put(DataBaseHelper.Jogo.ID_PERDEDOR, jogo.getId_perdedor());
        values.put(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR1, jogo.getpontos_final_jogador1());
        values.put(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR2, jogo.getpontos_final_jogador2());
        values.put(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR3, jogo.getpontos_final_jogador3());
        values.put(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR4, jogo.getpontos_final_jogador4());
        values.put(DataBaseHelper.Jogo.QTD_RODADAS, jogo.getQtd_rodadas());

        return getDb().update(DataBaseHelper.Jogo.TABELA, values, whereClause, whereArgs);
    }

    public long inserir(Rodada rodada){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Rodada.ID_JOGO, rodada.getId_jogo());
        values.put(DataBaseHelper.Rodada.PONTOS_JOGADOR1, rodada.getPontos_jogador1());
        values.put(DataBaseHelper.Rodada.PONTOS_JOGADOR2, rodada.getPontos_jogador2());
        values.put(DataBaseHelper.Rodada.PONTOS_JOGADOR3, rodada.getPontos_jogador3());
        values.put(DataBaseHelper.Rodada.PONTOS_JOGADOR4, rodada.getPontos_jogador4());
        values.put(DataBaseHelper.Rodada.RODADA, rodada.getRodada());

        return getDb().insert(DataBaseHelper.Rodada.TABELA, null, values);
    }

    public long inserir(Jogador jogador){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Jogador.NOME, jogador.getNome());
        values.put(DataBaseHelper.Jogador.QTD_JOGOS, jogador.getQtd_jogos());
        values.put(DataBaseHelper.Jogador.QTD_VITORIAS, jogador.getQtd_vitorias());
        values.put(DataBaseHelper.Jogador.QTD_DERROTAS, jogador.getQtd_derrotas());
        values.put(DataBaseHelper.Jogador.RECORDE_PONTOS_VITORIA, jogador.getRecordePontosVitorias());
        values.put(DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA, jogador.getRecordePontosDerrota());
        values.put(DataBaseHelper.Jogador.SALDO, jogador.getSaldo());
        values.put(DataBaseHelper.Jogador.DATA_MAIOR_DERROTA, jogador.getData_maior_derrota().getTime());

        return getDb().insert(DataBaseHelper.Jogador.TABELA, null, values);
    }

    public long atualizar_jogador(Jogador jogador){
        String whereClause = DataBaseHelper.Jogador._ID + " = ? ";
        String[] whereArgs = new String[]{ jogador.getId().toString() };

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Jogador.NOME, jogador.getNome());
        values.put(DataBaseHelper.Jogador.QTD_JOGOS, jogador.getQtd_jogos());
        values.put(DataBaseHelper.Jogador.QTD_VITORIAS, jogador.getQtd_vitorias());
        values.put(DataBaseHelper.Jogador.QTD_DERROTAS, jogador.getQtd_derrotas());
        values.put(DataBaseHelper.Jogador.RECORDE_PONTOS_VITORIA, jogador.getRecordePontosVitorias());
        values.put(DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA, jogador.getRecordePontosDerrota());
        values.put(DataBaseHelper.Jogador.SALDO, jogador.getSaldo());
        values.put(DataBaseHelper.Jogador.DATA_MAIOR_DERROTA, jogador.getData_maior_derrota().getTime());

        return getDb().update(DataBaseHelper.Jogador.TABELA, values, whereClause, whereArgs);
    }

    public Jogador buscar(Integer id){
        String selection = DataBaseHelper.Jogador._ID + " = ? ";
        String[] selectionArgs = new String[]{ id.toString() };
        Cursor cursor = getDb().query(DataBaseHelper.Jogador.TABELA, DataBaseHelper.Jogador.COLUNAS,
                                selection, selectionArgs, null, null, null);


        if(cursor.moveToNext()){
            Jogador jogador = criarJogador(cursor);
            cursor.close();
            return jogador;
        }
        cursor.close();
        return null;
    }

    public Jogo buscar_jogo(Integer id){
        String selection = DataBaseHelper.Jogo._ID + " = ? ";
        String[] selectionArgs = new String[]{ id.toString() };

        Cursor cursor = getDb().query(DataBaseHelper.Jogo.TABELA, DataBaseHelper.Jogo.COLUNAS,
                selection, selectionArgs,
                null, null, null);

        if(cursor.moveToNext()){
            Jogo jogo = criarJogo(cursor);
            cursor.close();
            return jogo;
        }

        cursor.close();
        return null;
    }

    public List<Jogador> listarJogadores(){
        Cursor cursor = getDb().query(DataBaseHelper.Jogador.TABELA, DataBaseHelper.Jogador.COLUNAS,
                                      null, null, null, null, DataBaseHelper.Jogador.NOME);
        List<Jogador> jogadores = new ArrayList<>();
        while (cursor.moveToNext()){
            Jogador jogador = criarJogador(cursor);
            jogadores.add(jogador);
        }
        cursor.close();
        return jogadores;
    }

    public List<Jogador> listarJogadoresRanking(){
        Cursor cursor = getDb().query(DataBaseHelper.Jogador.TABELA, DataBaseHelper.Jogador.COLUNAS,
                                      null, null, null, null, DataBaseHelper.Jogador.SALDO);
        List<Jogador> jogadores = new ArrayList<>();
        while (cursor.moveToNext()){
            Log.d("ranking", "rank");
            Jogador jogador = criarJogador(cursor);
            Log.d("ranking", "rank criou");
            jogadores.add(jogador);
            Log.d("ranking", "rank add");
        }
        Log.d("ranking", "rank acabou");
        cursor.close();
        return jogadores;
    }

    public List<Jogo> listarJogos(){
        Cursor cursor = getDb().query(DataBaseHelper.Jogo.TABELA, DataBaseHelper.Jogo.COLUNAS,
                                null, null, null, null, null);

        List<Jogo> lista_jogo = new ArrayList<>();

        while (cursor.moveToNext()){
            lista_jogo.add(criarJogo(cursor));
        }
        cursor.close();
        return lista_jogo;
    }

    public List<Rodada> listarRodadas(Integer id_jogo){
        String selection = DataBaseHelper.Rodada.ID_JOGO + " = ? ";
        String[] selectionArgs = new String[]{ id_jogo.toString() };

        Cursor cursor = getDb().query(DataBaseHelper.Rodada.TABELA,
                DataBaseHelper.Rodada.COLUNAS, selection, selectionArgs, null, null,
                DataBaseHelper.Rodada.RODADA);

        List<Rodada> rodadas = new ArrayList<>();
        while (cursor.moveToNext()){
            rodadas.add(criarRodada(cursor));
        }
        cursor.close();
        return rodadas;
    }

    public List<Jogador> buscarJogadorComMaiorDerrota(){
        // buscando qtd_pontos da maior derrota
        Cursor cursor = getDb().rawQuery(
                "SELECT MAX(" + DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA + ") FROM " +
                DataBaseHelper.Jogador.TABELA,
                null);

        cursor.moveToFirst();
        // gravando a qtd_pontos da maior derrota
        Integer maior = cursor.getInt(0);

        // buscando jogaores que tenham a maior_derrota = qtd_pontos da maior derrota buscada acima
        String selection = DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA + " = ? ";
        String[] selectionArgs = new String[]{ maior.toString() };

        cursor = getDb().query(
                DataBaseHelper.Jogador.TABELA, DataBaseHelper.Jogador.COLUNAS,
                selection, selectionArgs, null, null, null);

        List<Jogador> maiores_derrotados = new ArrayList<>();
        while(cursor.moveToNext()){
            maiores_derrotados.add(criarJogador(cursor));
        }
        cursor.close();
        return maiores_derrotados;
    }

    public Integer pontosMaiorDerrota(){
        Cursor cursor = getDb().rawQuery(
                "SELECT MAX(" + DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA + ") FROM " +
                        DataBaseHelper.Jogador.TABELA, null);
        if(cursor.moveToFirst()){
            Integer maior = cursor.getInt(0);
            cursor.close();
            return maior;
        }
        cursor.close();
        return null;
    }

    public Integer maiorQtdVitoria(){
        Cursor cursor = getDb().rawQuery(
                "SELECT MAX(" + DataBaseHelper.Jogador.QTD_VITORIAS + ") FROM " +
                DataBaseHelper.Jogador.TABELA, null);
        if(cursor.moveToFirst()){
            Integer maior = cursor.getInt(0);
            cursor.close();
            return maior;
        }
        cursor.close();
        return null;
    }

    public Integer maiorQtdDerrota(){
        Cursor cursor = getDb().rawQuery(
                "SELECT MAX(" + DataBaseHelper.Jogador.QTD_DERROTAS + ") FROM " +
                        DataBaseHelper.Jogador.TABELA, null);
        if(cursor.moveToFirst()){
            Integer maior = cursor.getInt(0);
            cursor.close();
            return maior;
        }
        cursor.close();
        return null;
    }

    public Integer maior_derrota_jogador(Integer id){
        String selection = DataBaseHelper.Jogador._ID + " = ? ";
        String[] selectionArgs = new String[]{ id.toString() };

        Cursor cursor = getDb().query(
                DataBaseHelper.Jogador.TABELA,
                new String[]{ DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA },
                selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            Integer maior = cursor.getInt(0);
            cursor.close();
            return maior;
        }
        cursor.close();
        return null;
    }

    public Integer maior_vitoria_jogador(Integer id){
        String selection = DataBaseHelper.Jogador._ID + " = ? ";
        String[] selectionArgs = new String[]{ id.toString() };

        Cursor cursor = getDb().query(
                DataBaseHelper.Jogador.TABELA,
                new String[]{ DataBaseHelper.Jogador.RECORDE_PONTOS_VITORIA },
                selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            Integer maior = cursor.getInt(0);
            cursor.close();
            return maior;
        }
        cursor.close();
        return null;
    }

    private Jogador criarJogador(Cursor cursor){
        return new Jogador(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Jogador.NOME)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.QTD_JOGOS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.QTD_VITORIAS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.QTD_DERROTAS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.SALDO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.RECORDE_PONTOS_VITORIA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogador.RECORDE_PONTOS_DERROTA)),
                new Date(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.Jogador.DATA_MAIOR_DERROTA)))
        );
    }

    private Jogo criarJogo(Cursor cursor){
        Jogo jogo = new Jogo(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo._ID)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.QTD_RODADAS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_JOGADOR1)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_JOGADOR2)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_JOGADOR3)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_JOGADOR4)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_VENCEDOR)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR1)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR2)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR3)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.PONTOS_FINAL_JOGADOR4)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Jogo.ID_PERDEDOR)),
                new Date(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.Jogo.DATA)))
        );
        return jogo;
    }

    private Rodada criarRodada(Cursor cursor){
        return new Rodada(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada._ID)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.ID_JOGO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.RODADA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.PONTOS_JOGADOR1)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.PONTOS_JOGADOR2)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.PONTOS_JOGADOR3)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Rodada.PONTOS_JOGADOR4)));
    }

}

