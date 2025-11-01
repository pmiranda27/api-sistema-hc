package br.com.fiap.beans;

public class ContaPaciente {
    // ATRIBUTOS DA CONTA DO PACIENTE
    private int id;
    private String email;
    private String senha;
    private Paciente paciente;
    private ConvenioMedico convenioMedico;

    //CONSTRUTORES
    public ContaPaciente(){

    }
    public ContaPaciente(String email, String senha, Paciente paciente, ConvenioMedico convenioMedico) {
        this.email = email;
        this.senha = senha;
        this.paciente = paciente;
        this.convenioMedico = convenioMedico;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getEmail() {
        // se o email for null ou estiver vazio, ele retornará nulo
        if (email == null || email.isEmpty()){
            return null;
        }
        // caso contrário, ele retornará o email
        return email;
    }

    public String getSenha() {
        // se a senha for null ou estiver vazia, ela retornará nulo
        if (senha == null || senha.isEmpty()){
            return null;
        }
        // caso contrário, ela retornará a senha
        return senha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public ConvenioMedico getConvenioMedico() {
        return convenioMedico;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        // Se o parâmetro email conter um '@' e tiver mais que 6 caracteres, o setter funcionará corretamente
        if (email.contains("@") && email.length() > 6){
            this.email = email;

        }else {
            // Caso contrário, um print no terminal dirá que o email é inválido
            System.out.println("Email inválido");
        }
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setConvenioMedico(ConvenioMedico convenioMedico) {
        this.convenioMedico = convenioMedico;
    }


    // Retorna as informações da conta do paciente de forma formatada
    @Override
    public String toString(){
        return "\nEmail: " + getEmail() +
                "\nSenha: " + getSenha() +
                "\nDono: " + getPaciente().getNomePaciente() +
                "\nOperadora de Convênio: " + getConvenioMedico().getOperadora();
    }
}
