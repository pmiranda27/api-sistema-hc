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

        return relatorioMedicoDAO.selecionarRelatoriosPorPaciente(id, conexao);
    }

    public int cadastrarBO(RelatorioMedico relatorioMedico) throws ClassNotFoundException, SQLException, ParseException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        return relatorioMedicoDAO.cadastrarRelatorio(relatorioMedico, conexao);
    }

    public void atualizarBO (RelatorioMedico relatorioMedico) throws ClassNotFoundException, SQLException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        relatorioMedicoDAO.atualizarRelatorio(relatorioMedico, conexao);
    }

    public void deletarBO(int id)throws ClassNotFoundException, SQLException {
        relatorioMedicoDAO = new RelatorioMedicoDAO();

        relatorioMedicoDAO.deletarRelatorio(id, conexao);
    }
}
