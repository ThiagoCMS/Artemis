package br.ufrpe.artemis.pessoa.dominio;

public class Classificacao {
    private int id;
    private double notaPreco;
    private double notaQualidade;
    private double notaAtendimento;
    private String comentario;
    private Pessoa PessoaAvaliada;
    private Pessoa PessoaAvaliadora;

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

    public Pessoa getPessoaAvaliada() {
        return PessoaAvaliada;
    }

    public void setPessoaAvaliada(Pessoa pessoaAvaliada) {
        PessoaAvaliada = pessoaAvaliada;
    }

    public Pessoa getPessoaAvaliadora() {
        return PessoaAvaliadora;
    }

    public void setPessoaAvaliadora(Pessoa pessoaAvaliadora) {
        PessoaAvaliadora = pessoaAvaliadora;
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
