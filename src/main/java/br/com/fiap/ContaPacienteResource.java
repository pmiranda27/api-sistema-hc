package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.excecoes.RequestsExcecoes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Provider

@Path("/conta_paciente")
public class ContaPacienteResource {
    private ContaPacienteBO contaPacienteBO = new ContaPacienteBO();

    public ContaPacienteResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarContasPacientesRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<ContaPaciente> listaContasPaciente = (ArrayList<ContaPaciente>) contaPacienteBO.selecionarBO();
            return Response.ok(listaContasPaciente).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarContaPacientePorEmailRs(@PathParam("email") String email) throws ClassNotFoundException, SQLException {
        try {
            ContaPaciente contaPaciente = contaPacienteBO.selecionarPorEmailBO(email);
            return Response.ok(contaPaciente).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarContasPacientePorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            ContaPaciente contaPaciente = contaPacienteBO.selecionarPorIdBO(id);
            return Response.ok(contaPaciente).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarContaPacienteRs(ContaPaciente contaPaciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            int novo_id_conta = contaPacienteBO.cadastrarBO(contaPaciente);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_conta)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarContaPacienteRs(ContaPaciente contaPaciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            contaPacienteBO.atualizarBO(contaPaciente);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/email")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEmailContaPacienteRs(ContaPaciente conta, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            contaPacienteBO.atualizarEmailBO(conta);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/senha")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSenhaContaPacienteRs(ContaPaciente conta, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            contaPacienteBO.atualizarSenhaBO(conta);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarContaPacientePorIdPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            contaPacienteBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
