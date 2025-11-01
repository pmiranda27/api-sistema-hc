package br.com.fiap.beans;

import java.util.Date;

public class RelatorioMedico {
    // ATRIBUTOS DO RELATÓRIO MÉDICO
    private int id;
    private Medico medicoRelator;
    private Date dataRelatorio;
    private String descricaoRelatorio;
    private Paciente paciente;

    // CONSTRUTORES
    public RelatorioMedico(){

    }
    public RelatorioMedico(Medico medicoRelator, Date dataRelatorio, String descricaoRelatorio, Paciente paciente) {
        this.medicoRelator = medicoRelator;
        this.dataRelatorio = dataRelatorio;
        this.descricaoRelatorio = descricaoRelatorio;
        this.paciente = paciente;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public Medico getMedicoRelator() {
        return medicoRelator;
    }

    public Date getDataRelatorio() {
        return dataRelatorio;
    }

    public String getDescricaoRelatorio() {
        return descricaoRelatorio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setMedicoRelator(Medico medicoRelator) {
        this.medicoRelator = medicoRelator;
    }

    public void setDataRelatorio(Date dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public void setDescricaoRelatorio(String descricaoRelatorio) {
        this.descricaoRelatorio = descricaoRelatorio;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    // Retorna as informações do Relatório Médico de forma formatada
    @Override
    public String toString(){
        /* Todos as String são pré-declaradas e são vazias */
        String medicoRelator = "";
        String paciente = "";
        Date dataEmissao = null;
        String descricao = "";
        /* ******* */

        // Se o MedicoRelator nao for vazio
        if(getMedicoRelator() != null){
            // medicoRelator refere-se ao getMedicoRelator().getNomeMedico()
            medicoRelator = getMedicoRelator().getNomeMedico();
        }
        // Se o Paciente nao for vazio
        if(getPaciente() != null){
            // paciente refere-se ao getPaciente().getNomePaciente()
            paciente = getPaciente().getNomePaciente();
        }
        // Se a Data do Relatorio nao for vazia
        if(getDataRelatorio() != null){
            // dataEmissao refere-se à getDataRelatorio()
            dataEmissao = getDataRelatorio();
        }
        // Se a Descrição do Relatorio nao for vazia
        if(getDescricaoRelatorio() != null){
            // descricao refere-se à getDescricaoRelatorio()
            descricao = getDescricaoRelatorio();
        }
        return "Médico Relator: " + medicoRelator +
                "\nData de Emissão: " + dataEmissao +
                "\nDescrição do Relatório: " + descricao +
                "\nPaciente: " + paciente;
    }
}
