package br.ufrpe.artemis.avaliacao.dominio;

public class Classificacao {
    private double mediaPreco;
    private double mediaAtendimento;
    private double mediaQualidade;

    public double getMediaPreco() {
        return mediaPreco;
    }

    public void setMediaPreco(double mediaPreco) {
        this.mediaPreco = mediaPreco;
    }

    public double getMediaAtendimento() {
        return mediaAtendimento;
    }

    public void setMediaAtendimento(double mediaAtendimento) {
        this.mediaAtendimento = mediaAtendimento;
    }

    public double getMediaQualidade() {
        return mediaQualidade;
    }

    public void setMediaQualidade(double mediaQualidade) {
        this.mediaQualidade = mediaQualidade;
    }
}
