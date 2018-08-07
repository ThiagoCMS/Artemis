package br.ufrpe.artemis.avaliacao.dominio;

import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class Avaliacao {
    private int id;
    private double notaPreco;
    private double notaQualidade;
    private double notaAtendimento;
    private String comentario;
    private Pessoa prestadora;
    private Pessoa cliente;

    public double getNotaAtendimento() {
        return notaAtendimento;
    }

    public void setNotaAtendimento(double notaAtendimento) {
        this.notaAtendimento = notaAtendimento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Pessoa getPrestadora() {
        return prestadora;
    }

    public void setPrestadora(Pessoa prestadora) {
        this.prestadora = prestadora;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getNotaPreco() {
        return notaPreco;
    }
    public void setNotaPreco(double notaPreco) {
        this.notaPreco = notaPreco;
    }

    public double getNotaQualidade() {
        return notaQualidade;
    }
    public void setNotaQualidade(double notaQualidade) {
        this.notaQualidade = notaQualidade;
    }

}
