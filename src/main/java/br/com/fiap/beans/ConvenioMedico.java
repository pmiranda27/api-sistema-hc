package br.com.fiap.beans;

import java.sql.Date;

public class ConvenioMedico {
    // ATRIBUTOS DO CONVÊNIO MÉDICO
    private int id;
    private String operadora;
    private String numeroCarteirinha;
    private Date dataInicio;
    private Date dataValidade;
    private int idPaciente;

    //CONSTRUTORES
    public ConvenioMedico(){

    }
    public ConvenioMedico(String operadora, String numeroCarteirinha, int id_paciente) {
        this.operadora = operadora;
        this.numeroCarteirinha = numeroCarteirinha;

        java.util.Date dataAgora = new java.util.Date();
        java.sql.Date dataFormatadaSQL = new java.sql.Date(dataAgora.getTime());

        //DATA EM TRES ANOS
        long dataEmTresAnos = dataAgora.getTime() + (3L * 365 * 24 * 60 * 60 * 1000);
        java.sql.Date dataEmTresAnosFormatadaSQL = new java.sql.Date(dataEmTresAnos);

        this.dataInicio = dataFormatadaSQL;
        this.dataValidade = dataEmTresAnosFormatadaSQL;
        this.idPaciente = idPaciente;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getOperadora() {
        return operadora;
    }

    public String getNumeroCarteirinha() {
        return numeroCarteirinha;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public void setNumeroCarteirinha(String numeroCarteirinha) {
        this.numeroCarteirinha = numeroCarteirinha;
    }

    public void setDataInicio(Date dataInicio) {
        java.util.Date dataAgora = new java.util.Date();

        this.dataInicio = new Date(dataAgora.getTime());
    }

    public void setDataValidade(Date dataValidade) {
        java.util.Date dataAgora = new java.util.Date();

        //DATA EM TRES ANOS
        long dataEmTresAnos = dataAgora.getTime() + (3L * 365 * 24 * 60 * 60 * 1000);

        this.dataValidade = new Date(dataEmTresAnos);
    }

    public void setIdPaciente(int id_paciente) {
        this.idPaciente = id_paciente;
    }

    // Retorna as informações do Convênio Médico de forma formatada
    @Override
    public String toString(){
        return "Operadora: " + getOperadora() +
                "\nCódigo da Carteirinha: " + getNumeroCarteirinha() +
                "\nData de Emissão: " + getDataInicio() +
                "\nData de Validade: " + getDataValidade();
    }
}
