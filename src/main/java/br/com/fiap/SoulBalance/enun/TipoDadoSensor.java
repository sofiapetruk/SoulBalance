package br.com.fiap.SoulBalance.enun;

public enum TipoDadoSensor {

    SONO_HORAS("Horas de Sono"),
    BATIMENTOS_MEDIOS("Média de Batimentos Cardíacos"),
    ATIVIDADE_PASSOS("Passos Diários"),
    ATIVIDADE_CALORIAS("Calorias Queimadas");

    private final String descricao;

    TipoDadoSensor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
