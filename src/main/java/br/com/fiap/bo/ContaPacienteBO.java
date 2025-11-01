package br.com.fiap.bo;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.Paciente;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.ContaPacienteDAO;
import br.com.fiap.dao.PacienteDAO;

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

        return (ArrayList<ContaPaciente>) contaPacienteDAO.selecionarContasPacientes(conexao);
    }

    public String cadastrarBO(ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException, ParseException {
        contaPacienteDAO = new ContaPacienteDAO();

        return contaPacienteDAO.cadastrarContaPaciente(contaPaciente, conexao);
    }

    public void atualizarBO (ContaPaciente contaPaciente) throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        contaPacienteDAO.atualizarContaPaciente(contaPaciente, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        contaPacienteDAO = new ContaPacienteDAO();

        contaPacienteDAO.deletarContaPacientePorIdPaciente(id, conexao);
    }
}
