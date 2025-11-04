package br.com.fiap.dao;

import br.com.fiap.beans.Medico;
import br.com.fiap.beans.Paciente;
import br.com.fiap.beans.RelatorioMedico;

import java.sql.*;
import java.util.ArrayList;

public class RelatorioMedicoDAO {
    public Connection conexao;

    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public RelatorioMedicoDAO() throws SQLException, ClassNotFoundException {
        //this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public int cadastrarRelatorio(RelatorioMedico relatorio, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Insert into Relatorio_Medico values (?, ?, ?, ?, ?)");

        int novoId = gerarNovoId(conexao);

        java.sql.Date dataFormatadaSQL = new java.sql.Date(relatorio.getDataRelatorio().getTime());

        stmt.setInt(1, novoId);
        stmt.setDate(2, dataFormatadaSQL);
        stmt.setString(3, relatorio.getDescricaoRelatorio());
        stmt.setInt(4, relatorio.getMedicoRelator().getId());
        stmt.setInt(5, relatorio.getPaciente().getId());

        stmt.execute();
        stmt.close();

        return novoId;
    }

    // Delete
    public String deletarRelatorio (int id, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Relatorio_Medico where id_relatorio_medico = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Relatório deletado com sucesso!";
    }
    // Delete
    public String deletarRelatorioPorIdPaciente (int id, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Relatorio_Medico where fk_id_paciente = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Relatório deletado com sucesso!";
    }

    // UpDate
    public String atualizarRelatorio(RelatorioMedico relatorio, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Relatorio_Medico set ds_relatorio = ? where id_relatorio_medico = ?");

        stmt.setString(1, relatorio.getDescricaoRelatorio());
        stmt.setInt(2, relatorio.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Relatório atualizado com sucesso!";
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_relatorio_medico) from Relatorio_Medico");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ArrayList<RelatorioMedico> selecionarRelatoriosPorPaciente(int id, Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<RelatorioMedico> listaRelatoriosMedicos = new ArrayList<RelatorioMedico>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Relatorio_Medico where fk_id_paciente = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            RelatorioMedico objRelatorio = new RelatorioMedico();
            objRelatorio.setId(rs.getInt(1));
            objRelatorio.setDataRelatorio(rs.getDate(2));
            objRelatorio.setDescricaoRelatorio(rs.getString(3));

            Medico medico = medicoDAO.selecionarMedicoPorId(rs.getInt(4), conexao);
            Paciente paciente = pacienteDAO.selecionarPacientePorId(rs.getInt(5), conexao);

            objRelatorio.setMedicoRelator(medico);
            objRelatorio.setPaciente(paciente);

            listaRelatoriosMedicos.add(objRelatorio);
        }
        return listaRelatoriosMedicos;
    }
}
