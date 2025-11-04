package br.com.fiap.dao;

import br.com.fiap.beans.*;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ContaPacienteDAO {
    public Connection conexao;

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final ConvenioDAO convenioDAO = new ConvenioDAO();

    public ContaPacienteDAO() throws SQLException, ClassNotFoundException {
        //this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public int cadastrarContaPaciente(ContaPaciente conta, Connection conexao) throws SQLException, ParseException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Insert into Conta_Paciente values (?, ?, ?, ?, ?)");

        int novoId = gerarNovoId(conexao);

        stmt.setInt(1, novoId);
        stmt.setString(2, conta.getEmail());
        stmt.setString(3, conta.getSenha());
        stmt.setInt(4, conta.getPaciente().getId());
        if (conta.getConvenioMedico().getId() != 0){
            stmt.setInt(5, conta.getConvenioMedico().getId());
        }
        else {
            stmt.setNull(5, Types.INTEGER);
        }

        stmt.execute();
        stmt.close();

        return novoId;
    }

    // Delete
    public String deletarContaPacientePorIdPaciente(int id, Connection conexao) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Conta_Paciente where fk_id_paciente = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Conta deletada com sucesso!";
    }

    // UpDate
    public String atualizarContaPaciente(ContaPaciente conta, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Conta_Paciente set ds_email = ?, ds_senha = ?, fk_id_paciente = ?, fk_id_convenio = ? where id_conta_paciente = ?");

        stmt.setString(1, conta.getEmail());
        stmt.setString(2, conta.getSenha());
        stmt.setInt(3, conta.getPaciente().getId());
        if (conta.getConvenioMedico() == null || conta.getConvenioMedico().getId() == 0){
            stmt.setNull(4, Types.INTEGER);
        }else {
            stmt.setInt(4, conta.getConvenioMedico().getId());
        }
        stmt.setInt(5, conta.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Conta atualizada com sucesso!";
    }

    public boolean atualizarEmailContaPaciente(ContaPaciente conta, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Conta_Paciente set ds_email = ? where id_conta_paciente = ?");

        System.out.println("EMAILLL: " +conta.getEmail());
        stmt.setString(1, conta.getEmail());
        System.out.println("Id: " +conta.getId());
        stmt.setInt(2, conta.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            System.out.println("Nao acho");
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    public boolean atualizarSenhaContaPaciente(ContaPaciente conta, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Conta_Paciente set ds_senha = ? where id_conta_paciente = ?");

        stmt.setString(1, conta.getSenha());
        stmt.setInt(2, conta.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_conta_paciente) from Conta_Paciente");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1);
        }

        stmt.close();
        return ultimoId + 1;
    }

    // Select
    public ContaPaciente selecionarContaPorEmail(Connection conexao, String email) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Conta_Paciente where ds_email = ?");

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        ContaPaciente conta = null;
        while (rs.next()){
            conta = new ContaPaciente();
            conta.setId(rs.getInt(1));
            conta.setEmail(rs.getString(2));
            conta.setSenha(rs.getString(3));
            conta.setPaciente(pacienteDAO.selecionarPacientePorId(rs.getInt(4), conexao));
            conta.setConvenioMedico(convenioDAO.selecionarConvenioPorId(rs.getInt(5), conexao));
        }

        return conta;
    }
    // Select
    public ContaPaciente selecionarContaPorIdPaciente(int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Conta_Paciente where fk_id_paciente = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        ContaPaciente conta = null;
        while (rs.next()){
            conta = new ContaPaciente();
            conta.setId(rs.getInt(1));
            conta.setEmail(rs.getString(2));
            conta.setSenha(rs.getString(3));
            conta.setPaciente(pacienteDAO.selecionarPacientePorId(rs.getInt(4), conexao));
            conta.setConvenioMedico(convenioDAO.selecionarConvenioPorId(5, conexao));
        }

        return conta;
    }
    // Select
    public ContaPaciente selecionarContaPorIdConvenio(int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Conta_Paciente where fk_id_convenio = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        ContaPaciente conta = null;
        while (rs.next()){
            conta = new ContaPaciente();
            conta.setId(rs.getInt(1));
            conta.setEmail(rs.getString(2));
            conta.setSenha(rs.getString(3));
            conta.setPaciente(pacienteDAO.selecionarPacientePorId(rs.getInt(4), conexao));
            conta.setConvenioMedico(convenioDAO.selecionarConvenioPorId(5, conexao));
        }

        return conta;
    }
    // Select
    public ContaPaciente selecionarContaPorId(Connection conexao, int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Conta_Paciente where id_conta_paciente = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        ContaPaciente conta = null;
        while (rs.next()){
            conta = new ContaPaciente();
            conta.setId(rs.getInt(1));
            conta.setEmail(rs.getString(2));
            conta.setSenha(rs.getString(3));
            conta.setPaciente(pacienteDAO.selecionarPacientePorId(rs.getInt(4), conexao));
            conta.setConvenioMedico(convenioDAO.selecionarConvenioPorId(rs.getInt(5), conexao));
        }

        return conta;
    }

    // Select
    public ArrayList<ContaPaciente> selecionarContasPacientes(Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<ContaPaciente> listaContas = new ArrayList<ContaPaciente>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Conta_Paciente");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            ContaPaciente conta = new ContaPaciente();
            conta.setId(rs.getInt(1));
            conta.setEmail(rs.getString(2));
            conta.setSenha(rs.getString(3));
            conta.setPaciente(pacienteDAO.selecionarPacientePorId(rs.getInt(4), conexao));
            conta.setConvenioMedico(convenioDAO.selecionarConvenioPorIdPaciente(rs.getInt(5), conexao));

            listaContas.add(conta);
        }

        return listaContas;
    }
}
