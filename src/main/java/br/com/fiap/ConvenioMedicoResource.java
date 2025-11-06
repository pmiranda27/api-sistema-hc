package br.com.fiap;

import br.com.fiap.beans.ConvenioMedico;
import br.com.fiap.bo.ConvenioMedicoBO;
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
    public Response selecionarConveniosMedicosRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<ConvenioMedico> listaConvenios = convenioMedicoBO.selecionarBO();
            return Response.ok(listaConvenios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/paciente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionaConvenioMedicoPorIdPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            ConvenioMedico convenio = convenioMedicoBO.selecionarPorIdPacienteBO(id);
            if(convenio == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(convenio).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarConvenioMedicoRs(ConvenioMedico convenioMedico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            int novo_id_convenio = convenioMedicoBO.cadastrarBO(convenioMedico);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_convenio)
                    .build();
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
    public Response deletarConvenioMedicoRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            convenioMedicoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
