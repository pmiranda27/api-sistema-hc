package br.com.fiap.beans;

public class Instituicao {
    // ATRIBUTOS DA INSTITUIÇÃO
    private int id;
    private String nomeFantasia;
    private String razaoSocial;
    private Endereco sede;
    private String setor;
    private String cnpj;

    //CONSTRUTORES
    public Instituicao(){

    }
    public Instituicao(String nomeFantasia, String razaoSocial, Endereco sede, String setor, String cnpj) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.sede = sede;
        this.setor = setor;
        this.cnpj = cnpj.replaceAll("[^0-9]", "");;
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public Endereco getSede() {
        return sede;
    }

    public String getSetor() {
        return setor;
    }

    public String getCnpj() {
        return cnpj;
    }


    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setSede(Endereco sede) {
        this.sede = sede;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("[^0-9]", "");;
    }


    // Retorna as informações da instituição de forma formatada
    @Override
    public String toString(){
        return "\n  Nome Fantasia: " + getNomeFantasia() +
                "\n Razão Social: " + getRazaoSocial() +
                "\n\n   Sede: " + getSede().toString() +
                "\n\n Setor: " + getSetor() +
                "\n CNPJ: " + getCnpj();
    }
}
