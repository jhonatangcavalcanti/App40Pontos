package com.jhonatan.quarentapontos.domain;

import com.jhonatan.quarentapontos.R;

import java.util.Date;

/**
 * Created by Jhonatan Gomes on 05/07/16. *
 */
public class Jogador {
    private Integer id;
    private String nome;
    private Integer qtd_jogos;
    private Integer qtd_vitorias;
    private Integer qtd_derrotas;
    private Integer saldo;
    private Integer recordePontosVitorias;
    private Integer recordePontosDerrota;
    private Date data_maior_derrota;

    public Jogador(){}

    public Jogador(String nome) {
        this.id = 0;
        this.nome = nome;
        this.qtd_jogos = 0;
        this.qtd_vitorias = 0;
        this.qtd_derrotas = 0;
        this.saldo = 0;
        this.recordePontosVitorias = 0;
        this.recordePontosDerrota = 0;
        this.data_maior_derrota = new Date();
    }

    public Jogador(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.qtd_jogos = 0;
        this.qtd_vitorias = 0;
        this.qtd_derrotas = 0;
        this.saldo = 0;
        this.recordePontosVitorias = 0;
        this.recordePontosDerrota = 0;
        this.data_maior_derrota = new Date();
    }

    public Jogador(Integer id, String nome, Integer qtd_jogos, Integer qtd_vitorias, Integer qtd_derrotas, Integer saldo, Integer recordePontosVitorias, Integer recordePontosDerrota, Date data_maior_derrota) {
        this.id = id;
        this.nome = nome;
        this.qtd_jogos = qtd_jogos;
        this.qtd_vitorias = qtd_vitorias;
        this.qtd_derrotas = qtd_derrotas;
        this.saldo = saldo;
        this.recordePontosVitorias = recordePontosVitorias;
        this.recordePontosDerrota = recordePontosDerrota;
        this.data_maior_derrota = data_maior_derrota;
    }

    public Jogador(String nome, Integer qtd_jogos, Integer qtd_vitorias, Integer qtd_derrotas, Integer saldo, Integer recordePontosVitorias, Integer recordePontosDerrota, Date data_maior_derrota) {
        this.nome = nome;
        this.qtd_jogos = qtd_jogos;
        this.qtd_vitorias = qtd_vitorias;
        this.qtd_derrotas = qtd_derrotas;
        this.saldo = saldo;
        this.recordePontosVitorias = recordePontosVitorias;
        this.recordePontosDerrota = recordePontosDerrota;
        this.data_maior_derrota = data_maior_derrota;
    }

    public Date getData_maior_derrota() {
        return data_maior_derrota;
    }

    public void setData_maior_derrota(Date data_maior_derrota) {
        this.data_maior_derrota = data_maior_derrota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtd_jogos() {
        return qtd_jogos;
    }

    public void setQtd_jogos(Integer qtd_jogos) {
        this.qtd_jogos = qtd_jogos;
    }

    public Integer getQtd_vitorias() {
        return qtd_vitorias;
    }

    public void setQtd_vitorias(Integer qtd_vitorias) {
        this.qtd_vitorias = qtd_vitorias;
    }

    public Integer getQtd_derrotas() {
        return qtd_derrotas;
    }

    public void setQtd_derrotas(Integer qtd_derrotas) {
        this.qtd_derrotas = qtd_derrotas;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getRecordePontosVitorias() {
        return recordePontosVitorias;
    }

    public void setRecordePontosVitorias(Integer recordePontosVitorias) {
        this.recordePontosVitorias = recordePontosVitorias;
    }

    public Integer getRecordePontosDerrota() {
        return recordePontosDerrota;
    }

    public void setRecordePontosDerrota(Integer recordePontosDerrota) {
        this.recordePontosDerrota = recordePontosDerrota;
    }
}
