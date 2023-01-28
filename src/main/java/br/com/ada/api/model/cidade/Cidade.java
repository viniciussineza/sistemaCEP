package br.com.ada.api.model.cidade;

import br.com.ada.api.model.estado.Estado;

import java.util.UUID;

public class Cidade implements Comparable<Cidade>{

    private UUID id;
    private String nomeDaCidade;
    private Estado estado;

    public Cidade(){}

    public Cidade(String nomeDaCidade, Estado estado) {
        this.id = UUID.randomUUID();
        this.nomeDaCidade = nomeDaCidade;
        this.estado = estado;
    }

    public UUID getId() { return this.id; }

    public String getNomeDaCidade() { return this.nomeDaCidade; }

    public void setNomeDaCidade(String nomeDaCidade) { this.nomeDaCidade = nomeDaCidade; }

    public Estado getEstado() { return estado; }

    @Override
    public String toString() {
        return "id - " + this.id
                + "\nCidade nome: " + this.nomeDaCidade;
    }

    @Override
    public int compareTo(Cidade outraCidade) {
        if(getNomeDaCidade() == null || outraCidade.getNomeDaCidade() == null) return 0;
        return getNomeDaCidade().compareTo(outraCidade.getNomeDaCidade());
    }
}
