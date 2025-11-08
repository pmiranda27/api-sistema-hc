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

        try {
            return convenioMedicoDAO.selecionarConvenios(conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return convenioMedicoDAO.selecionarConvenios(conexao);
                }
            }
            throw e;
        }
    }

    public ConvenioMedico selecionarPorIdPacienteBO(int id) throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        try {
            return convenioMedicoDAO.selecionarConvenioPorIdPaciente(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return convenioMedicoDAO.selecionarConvenioPorIdPaciente(id, conexao);
                }
            }
            throw e;
        }
    }

    public int cadastrarBO(ConvenioMedico convenioMedico) throws ClassNotFoundException, SQLException, ParseException {
        convenioMedicoDAO = new ConvenioDAO();

        try {
            return convenioMedicoDAO.cadastrarConvenio(convenioMedico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return convenioMedicoDAO.cadastrarConvenio(convenioMedico, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (ConvenioMedico convenioMedico) throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        try {
            convenioMedicoDAO.atualizarConvenio(convenioMedico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    convenioMedicoDAO.atualizarConvenio(convenioMedico, conexao);
                }
            }
            throw e;
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        convenioMedicoDAO = new ConvenioDAO();

        try {
            convenioMedicoDAO.deletarConvenio(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    convenioMedicoDAO.deletarConvenio(id, conexao);
                }
            }
            throw e;
        }
    }
}
