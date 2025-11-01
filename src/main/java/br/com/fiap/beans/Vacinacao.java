package br.com.fiap.beans;

public class Vacinacao {
    // ATRIBUTOS DA VACINAÇÃO
    private String nomeVacina;
    private String dataAplicacao;
    private String dataValidade;
    private Medico medicoAplicador;
    private Paciente paciente;

    //CONSTRUTORES
    public Vacinacao(){

    }
    public Vacinacao(String nomeVacina, String dataAplicacao, String dataValidade, Medico medicoAplicador, Paciente paciente) {
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = dataAplicacao;
        this.dataValidade = dataValidade;
        this.medicoAplicador = medicoAplicador;
        this.paciente = paciente;
    }

    //GETTERS
    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public Medico getMedicoAplicador() {
        return medicoAplicador;
    }

    public Paciente getPaciente() {
        return paciente;
    }


    //SETTERS
    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setMedicoAplicador(Medico medicoAplicador) {
        this.medicoAplicador = medicoAplicador;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    // Retorna as informações da Vacinacao de forma formatada
    @Override
    public String toString(){
        /* Todos as String são pré-declaradas e são vazias */
        String nomeVacina = "";
        String dataAplicacao = "";
        String dataValidade = "";
        String nomeMedico = "";
        String nomePaciente = "";
        /* ******* */

        // Se o Nome da Vacina nao for vazia
        if(getNomeVacina() != null){
            // nomeVacina refere-se ao getNomeVacina()
            nomeVacina = getNomeVacina();
        }
        // Se a Data de Aplicação não for vazia
        if(getDataAplicacao() != null){
            // dataAplicacao refere-se à getDataAplicacao()
            dataAplicacao = getDataAplicacao();
        }
        // Se a Data de Validade não for vazia
        if(getDataValidade() != null){
            // dataValidade refere-se à getDataValidade()
            dataValidade = getDataValidade();
        }
        // Se o MedicoAplicador não for vazio
        if(getMedicoAplicador() != null){
            // nomeMedico refere-se ao getMedicoAplicador().getNomeMedico
            nomeMedico = getMedicoAplicador().getNomeMedico();
        }
        // Se o Paciente não for vazio
        if(getPaciente() != null){
            // nomePaciente refere-se ao getPaciente().getNomePaciente()
            nomePaciente = getPaciente().getNomePaciente();
        }
        return "Vacina: " + nomeVacina +
                "\nData de Aplicação: " + dataAplicacao+
                "\nData de Validade: " + dataValidade +
                "\nMédico Aplicador: " + nomeMedico +
                "\nPaciente: " + nomePaciente;
    }
}
