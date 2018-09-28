/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.status;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author gilmario
 */
public class Status {

    private final String versao;
    private final String nome;
    private final LocalDate dataRelease;

    public Status() {
        this.versao = "0.1.0-SNAPSHOT";
        this.nome = "Generic-API";
        this.dataRelease = LocalDate.of(2018, Month.SEPTEMBER, 16);
    }

    public String getVersao() {
        return versao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataRelease() {
        return dataRelease;
    }

}
