package br.com.fiap;

import br.com.fiap.beans.ContaPaciente;
import br.com.fiap.beans.Instituicao;
import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.ContaPacienteBO;
import br.com.fiap.bo.InstituicaoBO;
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

@Path("/instituicao")
public class InstituicaoResource {
    private InstituicaoBO instituicaoBO = new InstituicaoBO();

    public InstituicaoResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarInstituicaoRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Instituicao> listaInstituicoes = (ArrayList<Instituicao>) instituicaoBO.selecionarBO();
            return Response.ok(listaInstituicoes).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarInstituicaoRs(Instituicao instituicao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, ParseException {
        try {
            instituicaoBO.cadastrarBO(instituicao);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(instituicao.getRazaoSocial());
            return Response.created(builder.build()).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarInstituicaoRs(Instituicao instituicao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            instituicaoBO.atualizarBO(instituicao);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarInstituicaoRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            instituicaoBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
