/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class Cidadee {
    private Integer codigo;
    private String nome;
    private String sigla;
    private Integer nrhabitantes;
    private LocalDate dataeman;
    private Double areatotal;
    private Double distancia;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getNrhabitantes() {
        return nrhabitantes;
    }

    public void setNrhabitantes(Integer nrhabitantes) {
        this.nrhabitantes = nrhabitantes;
    }

    public LocalDate getDataeman() {
        return dataeman;
    }

    public void setDataeman(LocalDate dataeman) {
        this.dataeman = dataeman;
    }

    public Double getAreatotal() {
        return areatotal;
    }

    public void setAreatotal(Double areatotal) {
        this.areatotal = areatotal;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Cidadee{" + "nome=" + nome + '}';
    }
    
}
