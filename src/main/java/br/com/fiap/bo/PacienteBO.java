package br.com.fiap.bo;

import br.com.fiap.beans.Paciente;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.PacienteDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteBO {
    PacienteDAO pacienteDAO;
    Connection conexao;

    public PacienteBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<Paciente> selecionarBO() throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        try {
            return pacienteDAO.selecionarPacientes(conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return pacienteDAO.selecionarPacientes(conexao);
                }
            }
            throw e;
        }
    }

    public int selecionarIdPorRgBO(String rg) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        try {
            return pacienteDAO.selecionarIdPorRgPaciente(rg, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return pacienteDAO.selecionarIdPorRgPaciente(rg, conexao);
                }
            }
            throw e;
        }
    }

    public Paciente selecionarPorId(int id) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        try {
            return pacienteDAO.selecionarPacientePorId(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return pacienteDAO.selecionarPacientePorId(id, conexao);
                }
            }
            throw e;
        }
    }

    public int cadastrarBO(Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        try {
            return pacienteDAO.cadastrarPaciente(paciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return pacienteDAO.cadastrarPaciente(paciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        try {
            pacienteDAO.atualizarPaciente(paciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    pacienteDAO.atualizarPaciente(paciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarTelefoneBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.atualizarTelefonePaciente(paciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    pacienteDAO.atualizarTelefonePaciente(paciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarAlturaBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.atualizarAlturaPaciente(paciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    pacienteDAO.atualizarAlturaPaciente(paciente, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarPesoBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.atualizarPesoPaciente(paciente, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    pacienteDAO.atualizarPesoPaciente(paciente, conexao);
                }
            }
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.deletarPaciente(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    pacienteDAO.deletarPaciente(id, conexao);
                }
            }
        }
    }
}
