package br.com.fiap.beans;

public class Endereco {
    // ATRIBUTOS DO ENDEREÇO
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String cep;

    //CONSTRUTORES
    public Endereco(){

    }
    public Endereco(String logradouro, int numero, String bairro, String cidade, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }


    //GETTERS
    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }


    //SETTERS
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    // Retorna as informações do Endereço de forma formatada
    @Override
    public String toString(){
        return "\nLogradouro: " + getLogradouro() +
                "\nNúmero: " + getNumero() +
                "\nBairro: " + getBairro() +
                "\nCidade: " + getCidade() +
                "\nCEP: " + getCep();
    }
}
