package br.com.fiap.dao;

import br.com.fiap.beans.Instituicao;
import br.com.fiap.beans.Medico;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {
    public Connection conexao;

    public InstituicaoDAO instituicaoDAO = new InstituicaoDAO();

    public MedicoDAO() throws SQLException, ClassNotFoundException {
        //this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public String cadastrarMedico(Medico medico, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Insert into Medico values (?, ?, ?, ?, ?, ?, ?)");

        int id_medico = gerarNovoId(conexao);

        stmt.setInt(1, id_medico);
        stmt.setString(2, medico.getNomeMedico());
        stmt.setString(3, medico.getSexo());
        stmt.setString(4, medico.getSetorMedico());
        stmt.setInt(5, medico.getCargaHoraria());
        stmt.setDouble(6, medico.getValorHora());

        stmt.setInt(7, 1);

        stmt.execute();
        stmt.close();

        conexao.close();
        return "Médico Cadastrado com sucesso!";
    }

    // Delete
    public String deletarMedico (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Medico where id_medico = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Medico deletado com sucesso!";
    }

    // UpDate
    public String atualizarMedico(Medico medico, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Medico set nm_medico = ?, ds_setor_medico = ?, num_carga_horaria = ?, vl_hora = ? where id_medico = ?");


        stmt.setString(1, medico.getNomeMedico());
        stmt.setString(2, medico.getSetorMedico());
        stmt.setInt(3, medico.getCargaHoraria());
        stmt.setDouble(4, medico.getValorHora());
        System.out.println("IDDDDD: "+ medico.getId());
        stmt.setInt(5, medico.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Medico atualizado com sucesso!";
    }

    public Medico selecionarMedicoPorId(int identificador, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Medico where id_medico = ?");

        stmt.setInt(1, identificador);

        ResultSet rs = stmt.executeQuery();

        Medico medico = new Medico();

        if(!rs.next()){
            return null;
        }
        else {
            medico.setId(rs.getInt(1));
            medico.setNomeMedico(rs.getString(2));
            medico.setSexo(rs.getString(3));
            medico.setSetorMedico(rs.getString(4));
            medico.setCargaHoraria(rs.getInt(5));
            medico.setValorHora(rs.getFloat(6));

            Instituicao instituicao = instituicaoDAO.selecionarInstituicaoPorId(rs.getInt(7), conexao);

            medico.setEmpregador(instituicao);
        }

        return medico;
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_medico) from Medico");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    // Select
    public ArrayList<Medico> selecionarMedicos(Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Medico");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Medico objMedico = new Medico();
            objMedico.setId(rs.getInt(1));
            objMedico.setNomeMedico(rs.getString(2));
            objMedico.setSexo(rs.getString(3));
            objMedico.setSetorMedico(rs.getString(4));
            objMedico.setCargaHoraria(rs.getInt(5));
            objMedico.setValorHora(rs.getInt(6));


            int idInstituicao = rs.getInt(7);
            Instituicao instituicao = instituicaoDAO.selecionarInstituicaoPorId(idInstituicao, conexao);
            objMedico.setEmpregador(instituicao);

            listaMedicos.add(objMedico);
        }
        return listaMedicos;
    }

}
