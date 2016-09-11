package com.jhonatan.quarentapontos.domain;

/**
 * Created by jhonatan on 08/07/16.
 */
public class Rodada {
    private Integer id;
    private Integer id_jogo;
    private Integer rodada;
    private Integer pontos_jogador1;
    private Integer pontos_jogador2;
    private Integer pontos_jogador3;
    private Integer pontos_jogador4;

    public Rodada() {}

    public Rodada(Integer id, Integer id_jogo, Integer rodada, Integer pontos_jogador1, Integer pontos_jogador2, Integer pontos_jogador3, Integer pontos_jogador4) {
        this.id = id;
        this.id_jogo = id_jogo;
        this.rodada = rodada;
        this.pontos_jogador1 = pontos_jogador1;
        this.pontos_jogador2 = pontos_jogador2;
        this.pontos_jogador3 = pontos_jogador3;
        this.pontos_jogador4 = pontos_jogador4;
    }

    public Rodada(Integer id_jogo, Integer rodada, Integer pontos_jogador1, Integer pontos_jogador2, Integer pontos_jogador3, Integer pontos_jogador4) {
        this.id_jogo = id_jogo;
        this.rodada = rodada;
        this.pontos_jogador1 = pontos_jogador1;
        this.pontos_jogador2 = pontos_jogador2;
        this.pontos_jogador3 = pontos_jogador3;
        this.pontos_jogador4 = pontos_jogador4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_jogo() {
        return id_jogo;
    }

    public void setId_jogo(Integer id_jogo) {
        this.id_jogo = id_jogo;
    }

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public Integer getPontos_jogador1() {
        return pontos_jogador1;
    }

    public void setPontos_jogador1(Integer pontos_jogador1) {
        this.pontos_jogador1 = pontos_jogador1;
    }

    public Integer getPontos_jogador2() {
        return pontos_jogador2;
    }

    public void setPontos_jogador2(Integer pontos_jogador2) {
        this.pontos_jogador2 = pontos_jogador2;
    }

    public Integer getPontos_jogador3() {
        return pontos_jogador3;
    }

    public void setPontos_jogador3(Integer pontos_jogador3) {
        this.pontos_jogador3 = pontos_jogador3;
    }

    public Integer getPontos_jogador4() {
        return pontos_jogador4;
    }

    public void setPontos_jogador4(Integer pontos_jogador4) {
        this.pontos_jogador4 = pontos_jogador4;
    }
}

