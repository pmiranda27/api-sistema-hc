package br.com.fiap.dao;

import br.com.fiap.beans.Endereco;
import br.com.fiap.beans.Paciente;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    public Connection conexao;

    public PacienteDAO() throws SQLException, ClassNotFoundException {
        //this.conexao = new ConexaoFactory().conexao();
    }

    // Insert
    public int cadastrarPaciente(Paciente paciente, Connection conexao) throws SQLException, ClassNotFoundException {
        Endereco enderecoPaciente = paciente.getEndereco();
        String numero = Integer.toString(enderecoPaciente.getNumero());

        float alturaFloat = (float) paciente.getAltura();
        float pesoFloat = (float) paciente.getPeso();

        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement
            ("Insert into Paciente (id_paciente, num_tel, ds_endereco, nm_paciente, num_idade, ds_sexo_paciente, num_altura, num_peso, ds_rg, ds_cpf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, novoId);
        stmt.setString(2, paciente.getTelefone());
        stmt.setString(3, enderecoPaciente.getLogradouro() + " " + numero + " — " + enderecoPaciente.getBairro() + ", " + enderecoPaciente.getCidade() + " - " + enderecoPaciente.getCep());
        stmt.setString(4, paciente.getNomePaciente());
        stmt.setInt(5, paciente.getIdade());
        stmt.setString(6, paciente.getSexo());
        stmt.setFloat(7, alturaFloat);
        stmt.setFloat(8, pesoFloat);
        stmt.setString(9, paciente.getRg());
        stmt.setString(10, paciente.getCpf());

        stmt.executeUpdate();

        stmt.close();

        return novoId;
    }

    // Delete
    public String deletarPaciente (int id, Connection conexao) throws SQLException, ClassNotFoundException {
        ContaPacienteDAO contaPacienteDAO = new ContaPacienteDAO();
        contaPacienteDAO.deletarContaPacientePorIdPaciente(id, conexao);

        ConvenioDAO convenioDAO = new ConvenioDAO();
        convenioDAO.deletarConvenioPorIdPaciente(id, conexao);

        RelatorioMedicoDAO relatorioMedicoDAO = new RelatorioMedicoDAO();
        relatorioMedicoDAO.deletarRelatorioPorIdPaciente(id, conexao);

        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamentoDAO.deletarAgendamentoPorIdPaciente(id, conexao);


        PreparedStatement stmt = conexao.prepareStatement
                ("Delete from Paciente where id_paciente = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Paciente deletado com sucesso!";
    }

    // UpDate
    public String atualizarPaciente(Paciente paciente, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Paciente set nm_paciente = ?, num_idade = ?, num_altura = ?, num_peso = ?, ds_endereco = ?, num_tel = ?,  where id_paciente = ?");

        Endereco enderecoPaciente = paciente.getEndereco();
        String numero = Integer.toString(enderecoPaciente.getNumero());

        float alturaFloat = (float) paciente.getAltura();
        float pesoFloat = (float) paciente.getPeso();

        stmt.setString(1, paciente.getNomePaciente());
        stmt.setInt(2, paciente.getIdade());
        stmt.setFloat(3, alturaFloat);
        stmt.setFloat(4, pesoFloat);
        stmt.setString(5, enderecoPaciente.getLogradouro() + " " + numero + " – " + enderecoPaciente.getBairro() + ", " + enderecoPaciente.getCidade() + " - " + enderecoPaciente.getCep());
        stmt.setString(6, paciente.getTelefone());
        stmt.setInt(7, paciente.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return "Paciente atualizado com sucesso!";
    }

    public boolean atualizarTelefonePaciente(Paciente paciente, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Paciente set num_tel = ? where id_paciente = ?");

        stmt.setString(1, paciente.getTelefone());
        stmt.setInt(2, paciente.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    public boolean atualizarAlturaPaciente(Paciente paciente, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Paciente set num_altura = ? where id_paciente = ?");

        stmt.setFloat(1, paciente.getAltura());
        stmt.setInt(2, paciente.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    public boolean atualizarPesoPaciente(Paciente paciente, Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement
                ("Update Paciente set num_peso = ? where id_paciente = ?");

        stmt.setFloat(1, paciente.getPeso());
        stmt.setInt(2, paciente.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }

        return true;
    }

    public int selecionarIdPorRgPaciente(String rg, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select id_paciente from Paciente where ds_rg = ?");

        stmt.setString(1, rg);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()){
            return -1;
        }else {
            return rs.getInt(1);
        }
    }

    public Paciente selecionarPacientePorId(int identificador, Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Paciente where id_paciente = ?");

        stmt.setInt(1, identificador);

        ResultSet rs = stmt.executeQuery();

        Paciente paciente = new Paciente();

        if(!rs.next()){
            return null;
        }
        else {
            paciente.setId(rs.getInt(1));
            paciente.setNomePaciente(rs.getString(4));
            paciente.setIdade(rs.getInt(5));
            paciente.setSexo(rs.getString(6));
            paciente.setRg(rs.getString(9));
            paciente.setCpf(rs.getString(10));
            paciente.setTelefone(rs.getString(2));
            paciente.setAltura(rs.getFloat(7));
            paciente.setPeso(rs.getFloat(8));

            String enderecoAtual = rs.getString(3);
            // Separar a String em " – "
            String[] partes = enderecoAtual.split(" — ");
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

            paciente.setEndereco(objEndereco);
        }


        return paciente;
    }

    private int gerarNovoId(Connection conexao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_paciente) from Paciente");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    // Select
    public ArrayList<Paciente> selecionarPacientes(Connection conexao) throws SQLException, ClassNotFoundException {
        ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Paciente");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            String enderecoAtual = rs.getString(3);

            Paciente objPaciente = new Paciente();
            objPaciente.setId((rs.getInt(1)));
            objPaciente.setNomePaciente(rs.getString(4));
            objPaciente.setIdade(rs.getInt(5));
            objPaciente.setAltura(rs.getFloat(7));
            objPaciente.setPeso(rs.getFloat(8));
            objPaciente.setSexo(rs.getString(6));
            objPaciente.setRg(rs.getString(9));
            objPaciente.setCpf(rs.getString(10));
            objPaciente.setTelefone(rs.getString(2));


            // Separar a String em " – "
            String[] partes = enderecoAtual.split(" - ");
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

            objPaciente.setEndereco(objEndereco);

            listaPacientes.add(objPaciente);
        }
        return listaPacientes;
    }

}
