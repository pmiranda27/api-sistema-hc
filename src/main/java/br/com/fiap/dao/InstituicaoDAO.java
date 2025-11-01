package br.com.fiap.dao;

import br.com.fiap.beans.Endereco;
import br.com.fiap.beans.Instituicao;
import br.com.fiap.beans.Medico;
import br.com.fiap.beans.Paciente;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO {
    public Connection conexao;

    public InstituicaoDAO() throws SQLException, ClassNotFoundException {
//        this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public String criarInstituicao(Instituicao instituicao, Connection conexao) throws SQLException, ClassNotFoundException {
        Endereco enderecoInstituicao = instituicao.getSede();
        String numero = Integer.toString(enderecoInstituicao.getNumero());

        PreparedStatement stmt = conexao.prepareStatement("Insert into Instituicao (id_instituicao, nm_razao_social, nm_fantasia, ds_setor, num_cnpj, ds_endereco) VALUES (?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, gerarNovoId(conexao));
        stmt.setString(2, instituicao.getRazaoSocial());
        stmt.setString(3, instituicao.getNomeFantasia());
        stmt.setString(4, instituicao.getSetor());
        stmt.setString(5, instituicao.getCnpj());
        stmt.setString(6,
                enderecoInstituicao.getLogradouro() + " " + numero + " — " +
                        enderecoInstituicao.getBairro() + ", " +
                        enderecoInstituicao.getCidade() + " - " +
                        enderecoInstituicao.getCep()
        );

        stmt.execute();
        stmt.close();

        return "Instituição cadastrada com sucesso!";
    }

    // Delete
    public String deletarInstituicao (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Instituicao where id_instituicao = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Instituição deletada com sucesso!";
    }

    // UpDate
    public String atualizarInstituicao(Instituicao instituicao, Connection conexao) throws SQLException, ClassNotFoundException {
        Endereco enderecoInstituicao = instituicao.getSede();
        String numero = Integer.toString(enderecoInstituicao.getNumero());

        PreparedStatement stmt = conexao.prepareStatement
                ("Update Instituicao set nm_razao_social = ?, nm_fantasia = ?, ds_setor = ?, ds_endereco = ?  where id_instituicao = ?");

        stmt.setString(1, instituicao.getRazaoSocial());
        stmt.setString(2, instituicao.getNomeFantasia());
        stmt.setString(3, instituicao.getSetor());
        stmt.setString(4, enderecoInstituicao.getLogradouro() + " " + numero + " — " + enderecoInstituicao.getBairro() + ", " + enderecoInstituicao.getCidade() + " - " + enderecoInstituicao.getCep());
        stmt.setInt(5, instituicao.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Instituição atualizada com sucesso!";
    }

    public Instituicao selecionarInstituicaoPorId(int identificador, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Instituicao where id_instituicao = ?");

        stmt.setInt(1, identificador);

        ResultSet rs = stmt.executeQuery();

        Instituicao instituicao = new Instituicao();
        if(!rs.next()){
            return null;
        }
        else {
            instituicao.setId(rs.getInt(1));
            instituicao.setRazaoSocial(rs.getString(2));
            instituicao.setNomeFantasia(rs.getString(3));
            instituicao.setSetor(rs.getString(4));
            instituicao.setCnpj(rs.getString(5));

            String enderecoAtual = rs.getString(6);
            // Separar a String em " – "
            String[] partes = enderecoAtual.split(" — ");
            String logradouroNumero = partes[0]; // "ABCDEFGH 123"
            String resto = partes[1]; // "HIJKL, MNOPQRS - 01234-567"

            // Separar logradouro e numero (considerando que número está sempre no final)
            int ultimoSpaceIndex = logradouroNumero.lastIndexOf(" ");
            String logradouro = logradouroNumero.substring(0, ultimoSpaceIndex); // "ABCDEFGH"
            String numero = logradouroNumero.substring(ultimoSpaceIndex + 1);    // "123"

            // Separar bairro e o resto por ", "
            String[] partsRest = resto.split(", ");
            String bairro = partsRest[0];        // "HIJKL"
            String cidadeCep = partsRest[1];     // "MNOPQRS - 01234-567"

            // Separar cidade e cep por " - "
            String[] partsCidadeCep = cidadeCep.split(" - ");
            String cidade = partsCidadeCep[0];   // "MNOPQRS"
            String cep = partsCidadeCep[1];      // "01234-567"

            Endereco objEndereco = new Endereco();
            objEndereco.setLogradouro(logradouro);
            objEndereco.setNumero(Integer.parseInt(numero));
            objEndereco.setBairro(bairro);
            objEndereco.setCidade(cidade);
            objEndereco.setCep(cep);

            instituicao.setSede(objEndereco);
        }

        return instituicao;
    }

    public int selecionarIdPorCnpjInstituicao(Instituicao instituicao, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select id_instituicao from Instituicao where num_cnpj = ?");

        stmt.setString(1, instituicao.getCnpj());

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()){
            return -1;
        }else{
            return rs.getInt(1);
        }
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_instituicao) from Instituicao");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();

        return ultimoId + 1;
    }

    // Select
    public ArrayList<Instituicao> selecionarInstituicoes(Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<Instituicao> listaInstituicoes = new ArrayList<Instituicao>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Instituicao");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            String enderecoAtual = rs.getString(6);

            Instituicao objInstituicao = new Instituicao();
            objInstituicao.setId((rs.getInt(1)));
            objInstituicao.setRazaoSocial(rs.getString(2));
            objInstituicao.setNomeFantasia(rs.getString(3));
            objInstituicao.setSetor(rs.getString(4));
            objInstituicao.setCnpj(rs.getString(5));

            // Separar a String em " – "
            System.out.println("ABUUUUUASFASDF: " + enderecoAtual);
            String[] partes = enderecoAtual.split(" — ");
            System.out.println("ABUUUUU: " + partes[0]);
            System.out.println("ABUUUUU: " + partes[1]);
            String logradouroNumero = partes[0];  // "ABCDEFGH 123"
            String resto = partes[1];              // "HIJKL, MNOPQRS - 01234-567"

            // Separar logradouro e numero (considerando que número está sempre no final)
            int ultimoSpaceIndex = logradouroNumero.lastIndexOf(" ");
            String logradouro = logradouroNumero.substring(0, ultimoSpaceIndex); // "ABCDEFGH"
            String numero = logradouroNumero.substring(ultimoSpaceIndex + 1);    // "123"

            // Separar bairro e o resto por ", "
            String[] partsRest = resto.split(", ");
            String bairro = partsRest[0];        // "HIJKL"
            String cidadeCep = partsRest[1];     // "MNOPQRS - 01234-567"

            // Separar cidade e cep por " - "
            String[] partsCidadeCep = cidadeCep.split(" - ");
            String cidade = partsCidadeCep[0];   // "MNOPQRS"
            String cep = partsCidadeCep[1];      // "01234-567"

            Endereco objEndereco = new Endereco();
            objEndereco.setLogradouro(logradouro);
            objEndereco.setNumero(Integer.parseInt(numero));
            objEndereco.setBairro(bairro);
            objEndereco.setCidade(cidade);
            objEndereco.setCep(cep);

            objInstituicao.setSede(objEndereco);

            listaInstituicoes.add(objInstituicao);
        }

        return listaInstituicoes;
    }
}
