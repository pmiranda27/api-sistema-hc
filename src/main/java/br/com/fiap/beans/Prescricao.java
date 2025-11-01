package br.com.fiap.beans;

public class Prescricao {
    // ATRIBUTOS DA PRESCRIÇÃO
    private String nomeMedicamento;
    private String dataInicio;
    private String dataValidade;
    private Medico medicoPrescritor;
    private Paciente paciente;

    //CONSTRUTORES
    public Prescricao(){

    }
    public Prescricao(String nomeMedicamento, String dataInicio, String dataValidade, Medico medicoPrescritor, Paciente paciente) {
        this.nomeMedicamento = nomeMedicamento;
        this.dataInicio = dataInicio;
        this.dataValidade = dataValidade;
        this.medicoPrescritor = medicoPrescritor;
        this.paciente = paciente;
    }


    //GETTERS
    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public Medico getMedicoPrescritor() {
        return medicoPrescritor;
    }

    public Paciente getPaciente() {
        return paciente;
    }


    //SETTERS
    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setMedicoPrescritor(Medico medicoPrescritor) {
        this.medicoPrescritor = medicoPrescritor;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    // Retorna as informações da Prescrição de forma formatada
    @Override
    public String toString(){
        return "Medicamento: " + getNomeMedicamento() +
                "\nData de Início: " + getDataInicio() +
                "\nData de Validade: " + getDataValidade() +
                "\nMédico Prescritor: " + getMedicoPrescritor().getNomeMedico() +
                "\nPaciente: " + getPaciente().getNomePaciente();
    }
}
