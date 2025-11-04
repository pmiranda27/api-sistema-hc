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

        return (ArrayList<Paciente>) pacienteDAO.selecionarPacientes(conexao);
    }

    public int selecionarIdPorRgBO(String rg) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        return  pacienteDAO.selecionarIdPorRgPaciente(rg, conexao);
    }

    public Paciente selecionarPorId(int id) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        return  pacienteDAO.selecionarPacientePorId(id, conexao);
    }

    public int cadastrarBO(Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.cadastrarPaciente(paciente, conexao);
    }

    public void atualizarBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.atualizarPaciente(paciente, conexao);
    }

    public void atualizarTelefoneBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.atualizarTelefonePaciente(paciente, conexao);
    }

    public void atualizarAlturaBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.atualizarAlturaPaciente(paciente, conexao);
    }

    public void atualizarPesoBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.atualizarPesoPaciente(paciente, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.deletarPaciente(id, conexao);
    }
}
