package br.com.ada.api.model.estado;

import br.com.ada.api.model.pais.Pais;

import java.util.UUID;

public class Estado {

    private UUID id;
    private String nomeDoEstado;
    private String siglaEstado;
    private Pais pais;

    public Estado() {}

    public Estado(UUID id, String nomeDoEstado, String siglaEstado, Pais pais) {
        this.id = id;
        this.nomeDoEstado = nomeDoEstado;
        this.siglaEstado = siglaEstado;
        this.pais = pais;
    }

    public UUID getId() { return id; }

    public String getNomeDoEstado() { return nomeDoEstado; }

    public String getSiglaEstado() { return siglaEstado; }

    public Pais getPais() { return pais; }

    public String getEstadoESigla() { return nomeDoEstado + " - " + siglaEstado; }






}
