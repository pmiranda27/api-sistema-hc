package br.com.fiap.beans;

public class Medico {
    // ATRIBUTOS DO MÉDICO
    private int id;
    private String nomeMedico;
    private String sexo;
    private String setorMedico;
    private int cargaHoraria;
    private double valorHora;
    private Instituicao empregador;

    //CONSTRUTORES
    public Medico(){

    }
    public Medico(String nomeMedico, String sexo, String setorMedico, int cargaHoraria, double valorHora, Instituicao empregador) {
        this.nomeMedico = nomeMedico;
        this.sexo = sexo;
        this.setorMedico = setorMedico;
        this.cargaHoraria = cargaHoraria;
        this.valorHora = valorHora;
        this.empregador = empregador;
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getSexo() {
        return sexo;
    }

    public String getSetorMedico() {
        return setorMedico;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public double getValorHora() {
        return valorHora;
    }

    public Instituicao getEmpregador() {
        return empregador;
    }


    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setSetorMedico(String setorMedico) {
        this.setorMedico = setorMedico;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public void setEmpregador(Instituicao empregador) {
        this.empregador = empregador;
    }


    // Retorna as informações do médico responsável de forma formatada
    @Override
    public String toString(){
        return "Médico Responsável:" +
                "\n     Nome: " + getNomeMedico() +
                "\n     Sexo: " + getSexo() +
                "\n     Setor: " + getSetorMedico() +
                "\n     Carga Horária: " + getCargaHoraria() + " horas" +
                "\n     Valor/Hora: R$" + getValorHora() +
                "\n     Empregador: " + getEmpregador().getNomeFantasia();
    }
}
