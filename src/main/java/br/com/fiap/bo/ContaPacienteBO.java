package br.com.fiap.bo;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.ContaPacienteDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ContaPacienteBO {
    ContaPacienteDAO contaPacienteDAO;
    Connection conexao;

    public ContaPacienteBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<ContaPaciente> selecionarBO() throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            return contaPacienteDAO.selecionarContasPacientes(conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return contaPacienteDAO.selecionarContasPacientes(conexao);
                }
            }
            throw e;
        }
    }

    public ContaPaciente selecionarPorEmailBO(String email) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            return contaPacienteDAO.selecionarContaPorEmail(conexao, email);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return contaPacienteDAO.selecionarContaPorEmail(conexao, email);
                }
            }
            throw e;
        }
    }
    public ContaPaciente selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            return contaPacienteDAO.selecionarContaPorId(conexao, id);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return contaPacienteDAO.selecionarContaPorId(conexao, id);
                }
            }
            throw e;
        }
    }

    public int cadastrarBO(ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException, ParseException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            return contaPacienteDAO.cadastrarContaPaciente(contaPaciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return contaPacienteDAO.cadastrarContaPaciente(contaPaciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            contaPacienteDAO.atualizarContaPaciente(contaPaciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    contaPacienteDAO.atualizarContaPaciente(contaPaciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarEmailBO (ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            contaPacienteDAO.atualizarEmailContaPaciente(contaPaciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    contaPacienteDAO.atualizarEmailContaPaciente(contaPaciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarSenhaBO (ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            contaPacienteDAO.atualizarSenhaContaPaciente(contaPaciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    contaPacienteDAO.atualizarSenhaContaPaciente(contaPaciente, conexao);
                }
            }
            throw e;
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        try {
            contaPacienteDAO.deletarContaPacientePorIdPaciente(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    contaPacienteDAO.deletarContaPacientePorIdPaciente(id, conexao);
                }
            }
            throw e;
        }
    }
}
