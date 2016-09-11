package com.jhonatan.quarentapontos.domain;

import java.util.Date;

/**
 * Created by Jhonatan Gomes on 08/07/16. *
 */
public class Jogo {
    private Integer id;
    private Integer qtd_rodadas;
    private Integer id_jogador1, id_jogador2, id_jogador3, id_jogador4;
    private Integer id_vencedor, id_perdedor;
    private Integer pontos_final_jogador1, pontos_final_jogador2, pontos_final_jogador3, pontos_final_jogador4;
    private Date data;

    public Jogo(Integer id_jogador1, Integer id_jogador2, Integer id_jogador3, Integer id_jogador4, Date data) {
        this.id_jogador1 = id_jogador1;
        this.id_jogador2 = id_jogador2;
        this.id_jogador3 = id_jogador3;
        this.id_jogador4 = id_jogador4;
        this.data = data;
    }

    public Jogo(Integer id, Integer qtd_rodadas, Integer id_jogador1, Integer id_jogador2, Integer id_jogador3, Integer id_jogador4, Integer id_vencedor, Integer pontos_final_jogador1, Integer pontos_final_jogador2, Integer pontos_final_jogador3, Integer pontos_final_jogador4, Integer id_perdedor, Date data) {
        this.id = id;
        this.qtd_rodadas = qtd_rodadas;
        this.id_jogador1 = id_jogador1;
        this.id_jogador2 = id_jogador2;
        this.id_jogador3 = id_jogador3;
        this.id_jogador4 = id_jogador4;
        this.id_vencedor = id_vencedor;
        this.pontos_final_jogador1 = pontos_final_jogador1;
        this.pontos_final_jogador2 = pontos_final_jogador2;
        this.pontos_final_jogador3 = pontos_final_jogador3;
        this.pontos_final_jogador4 = pontos_final_jogador4;
        this.id_perdedor = id_perdedor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtd_rodadas() {
        return qtd_rodadas;
    }

    public void setQtd_rodadas(Integer qtd_rodadas) {
        this.qtd_rodadas = qtd_rodadas;
    }

    public Integer getId_jogador1() {
        return id_jogador1;
    }

    public void setId_jogador1(Integer id_jogador1) {
        this.id_jogador1 = id_jogador1;
    }

    public Integer getId_jogador2() {
        return id_jogador2;
    }

    public void setId_jogador2(Integer id_jogador2) {
        this.id_jogador2 = id_jogador2;
    }

    public Integer getId_jogador3() {
        return id_jogador3;
    }

    public void setId_jogador3(Integer id_jogador3) {
        this.id_jogador3 = id_jogador3;
    }

    public Integer getId_jogador4() {
        return id_jogador4;
    }

    public void setId_jogador4(Integer id_jogador4) {
        this.id_jogador4 = id_jogador4;
    }

    public Integer getId_vencedor() {
        return id_vencedor;
    }

    public void setId_vencedor(Integer id_vencedor) {
        this.id_vencedor = id_vencedor;
    }

    public Integer getpontos_final_jogador1() {
        return pontos_final_jogador1;
    }

    public void setpontos_final_jogador1(Integer pontos_final_jogador1) {
        this.pontos_final_jogador1 = pontos_final_jogador1;
    }

    public Integer getpontos_final_jogador2() {
        return pontos_final_jogador2;
    }

    public void setpontos_final_jogador2(Integer pontos_final_jogador2) {
        this.pontos_final_jogador2 = pontos_final_jogador2;
    }

    public Integer getpontos_final_jogador3() {
        return pontos_final_jogador3;
    }

    public void setpontos_final_jogador3(Integer pontos_final_jogador3) {
        this.pontos_final_jogador3 = pontos_final_jogador3;
    }

    public Integer getpontos_final_jogador4() {
        return pontos_final_jogador4;
    }

    public void setpontos_final_jogador4(Integer pontos_final_jogador4) {
        this.pontos_final_jogador4 = pontos_final_jogador4;
    }

    public Integer getId_perdedor() {
        return id_perdedor;
    }

    public void setId_perdedor(Integer id_perdedor) {
        this.id_perdedor = id_perdedor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
