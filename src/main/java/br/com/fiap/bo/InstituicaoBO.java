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

        return (ArrayList<Instituicao>) instituicaoDAO.selecionarInstituicoes(conexao);
    }

    public Instituicao selecionarPorIdBO(int id) throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        return instituicaoDAO.selecionarInstituicaoPorId(id, conexao);
    }

    public String cadastrarBO(Instituicao instituicao) throws ClassNotFoundException, SQLException, ParseException {
        instituicaoDAO = new InstituicaoDAO();

        return instituicaoDAO.criarInstituicao(instituicao, conexao);
    }

    public void atualizarBO (Instituicao instituicao) throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        instituicaoDAO.atualizarInstituicao(instituicao, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        instituicaoDAO = new InstituicaoDAO();

        instituicaoDAO.deletarInstituicao(id, conexao);
    }
}
