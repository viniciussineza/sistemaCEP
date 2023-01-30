package br.com.ada.api.model.pais;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "paises")
public class Pais implements Comparable<Pais> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nomeDoPais;
    private String siglaPais;

    public Pais() {}

    public Pais(UUID id, String pais, String siglaPais) {
        this.id = id;
        this.nomeDoPais = pais;
        this.siglaPais = siglaPais;
    }

    public UUID getId() { return id; }

    public String getNomeDoPais() { return nomeDoPais; }

    public void setNomeDoPais(String nomeDoPais) { this.nomeDoPais = nomeDoPais; }

    public String getSiglaPais() { return siglaPais; }

    public void setSiglaPais(String siglaPais) { this.siglaPais = siglaPais; }

    public String getPaisESigla() { return nomeDoPais + " - " + siglaPais; }

    @Override
    public boolean equals(Object obj) {
        Pais outroPais = (Pais) obj;
        return this.nomeDoPais.equals(outroPais.getNomeDoPais());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDoPais);
    }

    @Override
    public String toString() {
        return "id - " + this.id
                + "\nPais nome: " + this.getPaisESigla();
    }

    @Override
    public int compareTo(Pais outroPais) {
        if(getNomeDoPais() == null || outroPais.getNomeDoPais() == null) return 0;
        return getNomeDoPais().compareTo(outroPais.getNomeDoPais());
    }
}

