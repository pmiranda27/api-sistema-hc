package br.com.fiap.bo;

import br.com.fiap.beans.Agendamento;
import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.AgendamentoDAO;
import br.com.fiap.dao.ContaPacienteDAO;
import br.com.fiap.excecoes.RequestsExcecoes;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class AgendamentoBO {
    AgendamentoDAO agendamentoDAO;
    Connection conexao;

    public AgendamentoBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<Agendamento> selecionarBO(int id) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        return agendamentoDAO.selecionarAgendamentosPorPaciente(id, conexao);
    }

    public int cadastrarBO(Agendamento agendamento) throws ClassNotFoundException, SQLException, ParseException {
        agendamentoDAO = new AgendamentoDAO();

        return agendamentoDAO.cadastrarAgendamento(agendamento, conexao);
    }

    public void atualizarBO (Agendamento agendamento) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        agendamentoDAO.atualizarAgendamento(agendamento, conexao);
    }

    public boolean deletarBO(int id)throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        return agendamentoDAO.deletarAgendamento(id, conexao);
    }
}
