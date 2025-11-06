package br.com.fiap;

import br.com.fiap.beans.Agendamento;
import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.excecoes.RequestsExcecoes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Provider

@Path("/agendamento")
public class AgendamentoResource {
    private AgendamentoBO agendamentoBO = new AgendamentoBO();

    public AgendamentoResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarAgendamentosPorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Agendamento> listaAgendamentos = agendamentoBO.selecionarBO(id);
            return Response.ok(listaAgendamentos).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarAgendamentoRs(Agendamento agendamento, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            int novo_id_agendamento = agendamentoBO.cadastrarBO(agendamento);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_agendamento)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAgendamentoRs(Agendamento agendamento, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            agendamentoBO.atualizarBO(agendamento);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarAgendamentoRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            boolean operacaoBemSucedida = agendamentoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
