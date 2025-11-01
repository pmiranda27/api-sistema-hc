package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.bo.PacienteBO;
import br.com.fiap.dao.PacienteDAO;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarContaPacienteRs(ContaPaciente contaPaciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            contaPacienteBO.cadastrarBO(contaPaciente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(contaPaciente.getEmail());
            return Response.created(builder.build()).build();
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
