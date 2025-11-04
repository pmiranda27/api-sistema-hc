package br.com.fiap.dao;

import br.com.fiap.beans.*;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class AgendamentoDAO {
    public Connection conexao;

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final InstituicaoDAO instituicaoDAO = new InstituicaoDAO();

    public AgendamentoDAO() throws SQLException, ClassNotFoundException {
//        this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public int cadastrarAgendamento(Agendamento agendamento, Connection conexao) throws SQLException, ParseException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Insert into Agendamento values (?, ?, ?, ?, ?, ?)");

        java.util.Date dataAgora = new java.util.Date();
        java.sql.Date dataFormatadaSQL = new java.sql.Date(dataAgora.getTime());

        int novoId = gerarNovoId(conexao);

        stmt.setInt(1, novoId);
        stmt.setDate(2, dataFormatadaSQL);
        stmt.setString(3, agendamento.getHorario());
        stmt.setInt(4, agendamento.getMedicoResponsavel().getId());
        stmt.setInt(5, agendamento.getLocal().getId());
        stmt.setInt(6, agendamento.getPaciente().getId());

        stmt.execute();
        stmt.close();

        return novoId;
    }

    // Delete
    public boolean deletarAgendamento (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Agendamento where id_agendamento = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    // Delete
    public String deletarAgendamentoPorIdPaciente (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Agendamento where fk_id_paciente = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Agendamento deletado com sucesso!";
    }

    // Update
    public String atualizarAgendamento(Agendamento agendamento, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Agendamento set fk_id_instituicao = ?, fk_id_medico = ?, hr_consulta = ? where id_agendamento = ?");

        stmt.setInt(1, agendamento.getLocal().getId());
        stmt.setInt(2, agendamento.getMedicoResponsavel().getId());
        stmt.setString(3, agendamento.getHorario());
        stmt.setInt(4, agendamento.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Agendamento atualizado com sucesso!";
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_agendamento) from Agendamento");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    // Select
    public ArrayList<Agendamento> selecionarAgendamentosPorPaciente(int id, Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Agendamento where fk_id_paciente = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Agendamento objAgendamento = new Agendamento();
            objAgendamento.setId(rs.getInt(1));
            objAgendamento.setData(rs.getDate(2));
            objAgendamento.setHorario(rs.getString(3));

            Medico medico = medicoDAO.selecionarMedicoPorId(rs.getInt(4), conexao);
            Instituicao instituicao = instituicaoDAO.selecionarInstituicaoPorId(rs.getInt(5), conexao);
            Paciente paciente = pacienteDAO.selecionarPacientePorId(rs.getInt(6), conexao);

            objAgendamento.setMedicoResponsavel(medico);
            objAgendamento.setLocal(instituicao);
            objAgendamento.setPaciente(paciente);

            listaAgendamentos.add(objAgendamento);
        }

        return listaAgendamentos;
    }
}
