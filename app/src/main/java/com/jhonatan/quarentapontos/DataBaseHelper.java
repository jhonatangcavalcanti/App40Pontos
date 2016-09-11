package com.jhonatan.quarentapontos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jhonatan Gomes on 08/07/16. *
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String BANCO_DADOS = "QuarentaPontos";
    public static final int VERSAO = 1;

    public DataBaseHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Jogador.TABELA + " (" + Jogador._ID + " INTEGER PRIMARY KEY, " +
                    Jogador.NOME + " TEXT, " + Jogador.QTD_JOGOS + " INTEGER, " +
                    Jogador.QTD_VITORIAS + " INTEGER, " + Jogador.QTD_DERROTAS + " INTEGER, " + Jogador.SALDO + " INTEGER, " +
                    Jogador.RECORDE_PONTOS_VITORIA + " INTEGER, " + Jogador.RECORDE_PONTOS_DERROTA + " INTEGER, " +
                    Jogador.DATA_MAIOR_DERROTA + " DATE);");

        db.execSQL("CREATE TABLE " + Jogo.TABELA + " (" + Jogo._ID + " INTEGER PRIMARY KEY, " +
                Jogo.QTD_RODADAS + " INTEGER, " + Jogo.PONTOS_FINAL_JOGADOR1 + " INTEGER, " +
                Jogo.PONTOS_FINAL_JOGADOR2 + " INTEGER, " + Jogo.PONTOS_FINAL_JOGADOR3 + " INTEGER, " +
                Jogo.PONTOS_FINAL_JOGADOR4 + " INTEGER, " + Jogo.DATA + " DATE, " +
                Jogo.ID_JOGADOR1 + " INTEGER, " + Jogo.ID_JOGADOR2 + " INTEGER, " +
                Jogo.ID_JOGADOR3 + " INTEGER, " + Jogo.ID_JOGADOR4 + " INTEGER, " +
                Jogo.ID_VENCEDOR + " INTEGER, " + Jogo.ID_PERDEDOR + " INTEGER, " +
                " FOREIGN KEY(" + Jogo.ID_JOGADOR1 + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "), " +
                " FOREIGN KEY(" + Jogo.ID_JOGADOR2 + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "), " +
                " FOREIGN KEY(" + Jogo.ID_JOGADOR3 + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "), " +
                " FOREIGN KEY(" + Jogo.ID_JOGADOR4 + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "), " +
                " FOREIGN KEY(" + Jogo.ID_VENCEDOR + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "), " +
                " FOREIGN KEY(" + Jogo.ID_PERDEDOR + ") REFERENCES " + Jogador.TABELA + "(" + Jogador._ID + "));");

        db.execSQL("CREATE TABLE " + Rodada.TABELA + "( " + Rodada._ID + " INTEGER PRIMARY KEY, " +
                    Rodada.RODADA + " INTEGER, " + Rodada.PONTOS_JOGADOR1 + " INTEGER, " +
                    Rodada.PONTOS_JOGADOR2 + " INTEGER, " + Rodada.PONTOS_JOGADOR3 + " INTEGER, " +
                    Rodada.PONTOS_JOGADOR4 + " INTEGER, " + Rodada.ID_JOGO + " INTEGER, " +
                    " FOREIGN KEY(" + Rodada.ID_JOGO + ") REFERENCES " + Jogo.TABELA + "(" + Jogo._ID + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public static class Jogador{
        public static final String TABELA = "jogador";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String QTD_JOGOS = "qtd_jogos";
        public static final String QTD_VITORIAS = "qtd_vitorias";
        public static final String QTD_DERROTAS = "qtd_derrotas";
        public static final String SALDO = "saldo";
        public static final String RECORDE_PONTOS_VITORIA = "recordePontosVitorias";
        public static final String RECORDE_PONTOS_DERROTA = "recordePontosDerrota";
        public static final String DATA_MAIOR_DERROTA = "data_maior_derrota";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, QTD_JOGOS,
                QTD_VITORIAS, QTD_DERROTAS, SALDO, RECORDE_PONTOS_VITORIA, RECORDE_PONTOS_DERROTA,
                DATA_MAIOR_DERROTA
        };
    }

    public static class Jogo{
        public static final String TABELA = "jogo";
        public static final String _ID = "_id";
        public static final String QTD_RODADAS = "qtd_rodadas";
        public static final String PONTOS_FINAL_JOGADOR1 = "pontos_final_jogador1";
        public static final String PONTOS_FINAL_JOGADOR2 = "pontos_final_jogador2";
        public static final String PONTOS_FINAL_JOGADOR3 = "pontos_final_jogador3";
        public static final String PONTOS_FINAL_JOGADOR4 = "pontos_final_jogador4";
        public static final String DATA = "data";
        public static final String ID_JOGADOR1 = "id_jogador1";
        public static final String ID_JOGADOR2 = "id_jogador2";
        public static final String ID_JOGADOR3 = "id_jogador3";
        public static final String ID_JOGADOR4 = "id_jogador4";
        public static final String ID_VENCEDOR = "id_vencedor";
        public static final String ID_PERDEDOR = "id_perdedor";

        public static final String[] COLUNAS = new String[]{
                _ID, QTD_RODADAS, PONTOS_FINAL_JOGADOR1, PONTOS_FINAL_JOGADOR2,
                PONTOS_FINAL_JOGADOR3, PONTOS_FINAL_JOGADOR4, DATA,
                ID_JOGADOR1, ID_JOGADOR2, ID_JOGADOR3, ID_JOGADOR4,
                ID_VENCEDOR, ID_PERDEDOR
        };
    }

    public static class Rodada{
        public static final String TABELA = "rodada";
        public static final String _ID = "_id";
        public static final String PONTOS_JOGADOR1 = "pontos_jogador1";
        public static final String PONTOS_JOGADOR2 = "pontos_jogador2";
        public static final String PONTOS_JOGADOR3 = "pontos_jogador3";
        public static final String PONTOS_JOGADOR4 = "pontos_jogador4";
        public static final String RODADA = "rodada";
        public static final String ID_JOGO = "id_jogo";

        public static final String[] COLUNAS = new String[]{
                _ID, ID_JOGO, RODADA, PONTOS_JOGADOR1, PONTOS_JOGADOR2, PONTOS_JOGADOR3, PONTOS_JOGADOR4
        };
    }
}
