package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.Paciente;
import br.com.fiap.beans.RelatorioMedico;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.bo.PacienteBO;
import br.com.fiap.bo.RelatorioMedicoBO;
import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.excecoes.RequestsExcecoes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Provider

@Path("/relatorio_medico")
public class RelatorioMedicoResource {
    private RelatorioMedicoBO relatorioMedicoBO = new RelatorioMedicoBO();

    public RelatorioMedicoResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Path("/paciente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRelatoriosMedicosPorIdPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            ArrayList<RelatorioMedico> listaRelatorios = relatorioMedicoBO.selecionarBO(id);
            return Response.ok(listaRelatorios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarRelatorioMedicoRs(RelatorioMedico relatorio, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            int novo_id_relatorio = relatorioMedicoBO.cadastrarBO(relatorio);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_relatorio)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRelatorioMedicoRs(RelatorioMedico relatorio, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            relatorioMedicoBO.atualizarBO(relatorio);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRelatorioMedicoPorIdPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            relatorioMedicoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
