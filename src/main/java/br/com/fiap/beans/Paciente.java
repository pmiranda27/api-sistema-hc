package br.com.fiap.beans;

import java.util.ArrayList;

public class Paciente {
    // ATRIBUTOS DO PACIENTE
    private int id;
    private String nomePaciente;
    private int idade;
    private String sexo;
    private double altura;
    private double peso;
    private String rg;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private Prescricao prescricao;

    //CONSTRUTORES
    public Paciente(){

    }
    public Paciente(String nomePaciente, int idade, String sexo, double altura, double peso, String rg, String cpf, String telefone, Endereco endereco) {
        this.nomePaciente = nomePaciente;
        this.idade = idade;
        this.sexo = sexo;

        // se o valor do parametro altura for maior que 100, ele foi colocado em centimetros
            // o atributo é medido em metros
        if (altura > 100) {
            // dividiremos por 100 para converter para metros
            this.altura = altura / 100;
        }else {
            // caso ele nao estiver maior que 100, ele foi colocado em metros mesmo, pode manter
            this.altura = altura;
        }
        this.peso = peso;
        // Remove tudo que nao seja numero do rg
        this.rg = rg.replaceAll("[^0-9]", "");;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    //GETTERS

    public int getId() {
        return id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Prescricao getPrescricao() {
        return prescricao;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(double altura) {
        // se o valor do parametro altura for maior que 100, ele foi colocado em centimetros
            // o atributo é medido em metros
        if (altura > 100) {
            // dividiremos por 100 para converter para metros
            this.altura = altura / 100;
        }else {
            // caso ele nao estiver maior que 100, ele foi colocado em metros mesmo, pode manter
            this.altura = altura;
        }
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRg(String rg) {
        this.rg = rg.replaceAll("[^0-9]", "");;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setPrescricao(Prescricao prescricao) {
        this.prescricao = prescricao;
    }


    // Retorna as informações do paciente de forma formatada
    @Override
    public String toString(){
        return "Nome: " + getNomePaciente() +
                "\nIdade: " + getIdade() +
                "\nSexo: " + getSexo() +
                "\nAltura: " + getAltura() + "m" +
                "\nPeso: " + getPeso() + "kg" +
                "\nRG: " + getRg() +
                "\nCPF: " + getCpf() +
                "\nTelefone: " + getTelefone() +
                "\n\nEndereço: " + getEndereco().toString();
    }
}
