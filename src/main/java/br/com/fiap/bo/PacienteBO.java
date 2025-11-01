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

    public boolean cadastrarBO(Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.cadastrarPaciente(paciente, conexao);
    }

    public void atualizarBO (Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.atualizarPaciente(paciente, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        pacienteDAO.deletarPaciente(id, conexao);
    }
}
