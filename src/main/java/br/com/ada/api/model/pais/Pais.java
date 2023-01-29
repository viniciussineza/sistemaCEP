package br.com.ada.api.model.pais;

import java.util.UUID;

public class Pais {

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

    public String getSiglaPais() { return siglaPais; }

    public String getPaisESigla() { return nomeDoPais + " - " + siglaPais; }

}

