package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.Medico;
import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.bo.MedicoBO;
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

@Path("/medico")
public class MedicoResource {
    private MedicoBO medicoBO = new MedicoBO();

    public MedicoResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarMedicosRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Medico> listaMedicos = medicoBO.selecionarBO();
            return Response.ok(listaMedicos).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarMedicoPorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            Medico medico = medicoBO.selecionarPorIdBO(id);
            return Response.ok(medico).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarContaPacienteRs(Medico medico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            medicoBO.cadastrarBO(medico);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(medico.getId()));
            return Response.created(builder.build()).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarContaPacienteRs(Medico medico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            medicoBO.atualizarBO(medico);
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
            medicoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
