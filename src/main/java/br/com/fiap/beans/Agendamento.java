package br.com.fiap.beans;

import java.sql.Date;

public class Agendamento {
    // ATRIBUTOS DO AGENDAMENTO
    private int id;
    private String horario;
    private Date data;
    private Medico medicoResponsavel;
    private Instituicao local;
    private Paciente paciente;

    //CONSTRUTORES
    public Agendamento(){

    }
    public Agendamento(String horario, Date data, Medico medicoResponsavel, Instituicao local, Paciente paciente) {
        this.horario = horario;
        this.data = data;
        this.medicoResponsavel = medicoResponsavel;
        this.local = local;
        this.paciente = paciente;
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public Date getData() {
        return data;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public Instituicao getLocal() {
        return local;
    }

    public Paciente getPaciente() {
        return paciente;
    }



    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    public void setLocal(Instituicao local) {
        this.local = local;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    // Retorna as informações do agendamento de forma formatada
    @Override
    public String toString(){
         return "Horário: " + getHorario() +
                 "\nData: " + getData() +
                 "\nMédico Responsável: " + getMedicoResponsavel().getNomeMedico() +
                 "\nPaciente: " + getPaciente().getNomePaciente() +
                 "\nLocal: " + getLocal().getSede();
    }
}
