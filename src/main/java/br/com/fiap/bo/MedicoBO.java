package br.com.fiap.bo;

import br.com.fiap.beans.Medico;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.MedicoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class MedicoBO {
    MedicoDAO medicoDAO;
    Connection conexao;

    public MedicoBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<Medico> selecionarBO() throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        return (ArrayList<Medico>) medicoDAO.selecionarMedicos(conexao);
    }

    public String cadastrarBO(Medico medico) throws ClassNotFoundException, SQLException, ParseException {
        medicoDAO = new MedicoDAO();

        return medicoDAO.cadastrarMedico(medico, conexao);
    }

    public void atualizarBO (Medico medico) throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        medicoDAO.atualizarMedico(medico, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        medicoDAO.deletarMedico(id, conexao);
    }
}
