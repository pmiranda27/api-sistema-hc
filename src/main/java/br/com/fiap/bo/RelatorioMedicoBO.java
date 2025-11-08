package br.com.fiap.bo;

import br.com.fiap.beans.RelatorioMedico;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.RelatorioMedicoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class RelatorioMedicoBO {
    RelatorioMedicoDAO relatorioMedicoDAO;
    Connection conexao;

    public RelatorioMedicoBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<RelatorioMedico> selecionarBO(int id) throws ClassNotFoundException, SQLException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        try {
            return relatorioMedicoDAO.selecionarRelatoriosPorPaciente(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioMedicoDAO.selecionarRelatoriosPorPaciente(id, conexao);
                }
            }
            throw e;
        }
    }

    public int cadastrarBO(RelatorioMedico relatorioMedico) throws ClassNotFoundException, SQLException, ParseException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        try {
            return relatorioMedicoDAO.cadastrarRelatorio(relatorioMedico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioMedicoDAO.cadastrarRelatorio(relatorioMedico, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (RelatorioMedico relatorioMedico) throws ClassNotFoundException, SQLException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        try {
            relatorioMedicoDAO.atualizarRelatorio(relatorioMedico, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioMedicoDAO.atualizarRelatorio(relatorioMedico, conexao);
                }
            }
            throw e;
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        try {
            relatorioMedicoDAO.deletarRelatorio(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioMedicoDAO.deletarRelatorio(id, conexao);
                }
            }
            throw e;
        }
    }
}
