package br.com.fiap.bo;

import br.com.fiap.beans.Instituicao;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.InstituicaoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class InstituicaoBO {
    InstituicaoDAO instituicaoDAO;
    Connection conexao;

    public InstituicaoBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<Instituicao> selecionarBO() throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        try {
            return instituicaoDAO.selecionarInstituicoes(conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return instituicaoDAO.selecionarInstituicoes(conexao);
                }
            }
            throw e;
        }

    }

    public Instituicao selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        try {
            return instituicaoDAO.selecionarInstituicaoPorId(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return instituicaoDAO.selecionarInstituicaoPorId(id, conexao);
                }
            }
            throw e;
        }
    }

    public String cadastrarBO(Instituicao instituicao) throws ClassNotFoundException, SQLException, ParseException {
        instituicaoDAO = new InstituicaoDAO();

        try {
            return instituicaoDAO.criarInstituicao(instituicao, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return instituicaoDAO.criarInstituicao(instituicao, conexao);
                }
            }
            throw e;
        }
    }

    public void atualizarBO (Instituicao instituicao) throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        try {
            instituicaoDAO.atualizarInstituicao(instituicao, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    instituicaoDAO.atualizarInstituicao(instituicao, conexao);
                }
            }
            throw e;
        }
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        try {
            instituicaoDAO.deletarInstituicao(id, conexao);
        }
        catch (Exception e){
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    instituicaoDAO.deletarInstituicao(id, conexao);
                }
            }
            throw e;
        }
    }
}
