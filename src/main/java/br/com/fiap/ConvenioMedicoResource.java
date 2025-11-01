package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.ConvenioMedico;
import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.bo.ConvenioMedicoBO;
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

@Path("/convenio_medico")
public class ConvenioMedicoResource {
    private ConvenioMedicoBO convenioMedicoBO = new ConvenioMedicoBO();

    public ConvenioMedicoResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionaConvenioMedicoRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<ConvenioMedico> listaConvenios = (ArrayList<ConvenioMedico>) convenioMedicoBO.selecionarBO();
            return Response.ok(listaConvenios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarConvenioMedicoRs(ConvenioMedico convenioMedico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            convenioMedicoBO.cadastrarBO(convenioMedico);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(convenioMedico.getNumeroCarteirinha());
            return Response.created(builder.build()).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarConvenioMedicoRs(ConvenioMedico convenioMedico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            convenioMedicoBO.atualizarBO(convenioMedico);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarConvenioMedicoPorIdPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            convenioMedicoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
