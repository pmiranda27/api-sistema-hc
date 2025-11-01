package br.com.fiap.bo;

import br.com.fiap.beans.ConvenioMedico;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.ConvenioDAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ConvenioMedicoBO {
    ConvenioDAO convenioMedicoDAO;
    Connection conexao;

    public ConvenioMedicoBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<ConvenioMedico> selecionarBO() throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        return (ArrayList<ConvenioMedico>) convenioMedicoDAO.selecionarConvenios(conexao);
    }

    public String cadastrarBO(ConvenioMedico convenioMedico) throws ClassNotFoundException, SQLException, ParseException {
        convenioMedicoDAO = new ConvenioDAO();

        return convenioMedicoDAO.cadastrarConvenio(convenioMedico, conexao);
    }

    public void atualizarBO (ConvenioMedico convenioMedico) throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        convenioMedicoDAO.atualizarConvenio(convenioMedico, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        convenioMedicoDAO.deletarConvenio(id, conexao);
    }
}
