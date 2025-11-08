package br.com.fiap.bo;

import br.com.fiap.beans.Agendamento;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.AgendamentoDAO;

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

        try {
            return agendamentoDAO.selecionarAgendamentosPorPaciente(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return agendamentoDAO.selecionarAgendamentosPorPaciente(id, conexao);
                }
            }
        }
        return new ArrayList<>();
    }

    public int cadastrarBO(Agendamento agendamento) throws ClassNotFoundException, SQLException, ParseException {
        agendamentoDAO = new AgendamentoDAO();

        try {
            return agendamentoDAO.cadastrarAgendamento(agendamento, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return agendamentoDAO.cadastrarAgendamento(agendamento, conexao);
                }
            }
        }
        return 0;
    }

    public void atualizarBO (Agendamento agendamento) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        try {
            agendamentoDAO.atualizarAgendamento(agendamento, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    agendamentoDAO.atualizarAgendamento(agendamento, conexao);
                }
            }
        }
    }

    public boolean deletarBO(int id)throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        try {
            return agendamentoDAO.deletarAgendamento(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return agendamentoDAO.deletarAgendamento(id, conexao);
                }
            }
        }
        return false;
    }
}
