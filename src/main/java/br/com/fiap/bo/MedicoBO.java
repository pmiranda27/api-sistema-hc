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

        try {
            return medicoDAO.selecionarMedicos(conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return medicoDAO.selecionarMedicos(conexao);
                }
            }
            throw e;
        }
    }

    public Medico selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        try {
            return medicoDAO.selecionarMedicoPorId(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return medicoDAO.selecionarMedicoPorId(id, conexao);
                }
            }
            throw e;
        }
    }

    public String cadastrarBO(Medico medico) throws ClassNotFoundException, SQLException, ParseException {
        medicoDAO = new MedicoDAO();

        try {
            return medicoDAO.cadastrarMedico(medico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return medicoDAO.cadastrarMedico(medico, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (Medico medico) throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        try {
            medicoDAO.atualizarMedico(medico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    medicoDAO.atualizarMedico(medico, conexao);
                }
            }
            throw e;
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        medicoDAO = new MedicoDAO();

        try{
            medicoDAO.deletarMedico(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    medicoDAO.deletarMedico(id, conexao);
                }
            }
            throw e;
        }
    }
}
