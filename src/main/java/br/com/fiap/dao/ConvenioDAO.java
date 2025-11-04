package br.com.fiap.dao;

import br.com.fiap.beans.*;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConvenioDAO {
    public Connection conexao;

    public ConvenioDAO() throws SQLException, ClassNotFoundException {
        //this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public int cadastrarConvenio(ConvenioMedico convenio, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Insert into Convenio values (?, ?, ?, ?, ?, ?)");

        java.util.Date dataAgora = new java.util.Date();
        java.sql.Date dataFormatadaSQL = new java.sql.Date(dataAgora.getTime());

        //DATA EM TRES ANOS
        long dataEmTresAnos = dataAgora.getTime() + (3L * 365 * 24 * 60 * 60 * 1000);
        java.sql.Date dataEmTresAnosFormatadaSQL = new java.sql.Date(dataEmTresAnos);

        int novoId = gerarNovoId(conexao);

        stmt.setInt(1, novoId);
        stmt.setString(2, convenio.getOperadora());
        stmt.setString(3, convenio.getNumeroCarteirinha());
        stmt.setDate(4, dataFormatadaSQL);
        stmt.setDate(5, dataEmTresAnosFormatadaSQL);
        stmt.setInt(6, convenio.getIdPaciente());

        stmt.execute();
        stmt.close();

        ContaPacienteDAO contaPacienteDAO = new ContaPacienteDAO();
        ContaPaciente contaPaciente = contaPacienteDAO.selecionarContaPorIdPaciente(convenio.getIdPaciente(), conexao);
        if (contaPaciente != null) {
            contaPaciente.setConvenioMedico(selecionarConvenioPorId(novoId, conexao));
            contaPacienteDAO.atualizarContaPaciente(contaPaciente, conexao);
        }

        return novoId;
    }

    // Delete
    public String deletarConvenio (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        ContaPacienteDAO contaPacienteDao = new ContaPacienteDAO();

        ContaPaciente conta = contaPacienteDao.selecionarContaPorIdConvenio(id, conexao);

        if (conta != null && conta.getConvenioMedico() != null && conta.getConvenioMedico().getId() != 0) {
            conta.setConvenioMedico(null);
            contaPacienteDao.atualizarContaPaciente(conta, conexao);
        }

        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Convenio where id_convenio = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();


        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        return "Convênio Médico deletado com sucesso!";
    }

    // Delete
    public String deletarConvenioPorIdPaciente (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Convenio where fk_id_paciente = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Convênio Médico deletado com sucesso!";
    }

    // UpDate
    public String atualizarConvenio(ConvenioMedico convenio, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Convenio set nm_operadora = ? where id_convenio = ?");

        stmt.setString(1, convenio.getOperadora());
        stmt.setInt(2, convenio.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Convênio Médico atualizado com sucesso!";
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_convenio) from Convenio");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ConvenioMedico selecionarConvenioPorId(int identificador, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Convenio where id_convenio = ?");

        stmt.setInt(1, identificador);

        ResultSet rs = stmt.executeQuery();

        ConvenioMedico convenio = null;
        if(!rs.next()){
            return null;
        }else {
            convenio = new ConvenioMedico();
            convenio.setId(rs.getInt(1));
            convenio.setOperadora(rs.getString(2));
            convenio.setNumeroCarteirinha(rs.getString(3));
            convenio.setDataInicio(rs.getDate(4));
            convenio.setDataValidade(rs.getDate(5));
        }
        return convenio;
    }

    public ConvenioMedico selecionarConvenioPorIdPaciente(int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Convenio where fk_id_paciente = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        ConvenioMedico convenio = null;
        if(!rs.next()){
            return null;
        }else {
            convenio = new ConvenioMedico();
            convenio.setId(rs.getInt(1));
            convenio.setOperadora(rs.getString(2));
            convenio.setNumeroCarteirinha(rs.getString(3));
            convenio.setDataInicio(rs.getDate(4));
            convenio.setDataValidade(rs.getDate(5));
            convenio.setIdPaciente(rs.getInt(6));
            return convenio;
        }
    }

    // Select
    public ArrayList<ConvenioMedico> selecionarConvenios(Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<ConvenioMedico> listaConvenios = new ArrayList<ConvenioMedico>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Convenio");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            ConvenioMedico objConvenioMedico = new ConvenioMedico();
            objConvenioMedico.setId(rs.getInt(1));
            objConvenioMedico.setOperadora(rs.getString(2));

            objConvenioMedico.setNumeroCarteirinha(rs.getString(3));
            objConvenioMedico.setDataInicio(rs.getDate(4));
            objConvenioMedico.setDataValidade(rs.getDate(5));

            objConvenioMedico.setIdPaciente(rs.getInt(6));

            listaConvenios.add(objConvenioMedico);
        }
        return listaConvenios;
    }
}
