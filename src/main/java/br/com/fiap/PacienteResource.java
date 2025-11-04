package br.com.fiap;

import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.PacienteBO;
import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.excecoes.RequestsExcecoes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/paciente")
public class PacienteResource {
    private PacienteBO pacienteBO = new PacienteBO();

    public PacienteResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarPacientesRs() throws ClassNotFoundException, SQLException {
        try {
            ArrayList<Paciente> listaPacientes =  (ArrayList<Paciente>) pacienteBO.selecionarBO();
            return Response.ok(listaPacientes).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/rg/{rg}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarIdPacientePorRgRs(@PathParam("rg") String rg) throws ClassNotFoundException, SQLException {
        try {
            int idPaciente =  pacienteBO.selecionarIdPorRgBO(rg);
            return Response.ok(idPaciente).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarPacientePorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            Paciente paciente =  pacienteBO.selecionarPorId(id);
            return Response.ok(paciente).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPacienteRs(Paciente paciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            int novo_id_paciente = pacienteBO.cadastrarBO(paciente);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_paciente)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPacienteRs(Paciente paciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            pacienteBO.atualizarBO(paciente);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/telefone")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarTelefonePacienteRs(Paciente paciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            pacienteBO.atualizarTelefoneBO(paciente);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/altura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAlturaPacienteRs(Paciente paciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            pacienteBO.atualizarAlturaBO(paciente);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/peso")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPesoPacienteRs(Paciente paciente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            pacienteBO.atualizarPesoBO(paciente);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarPacienteRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
        try {
            pacienteBO.deletarBO(id);
            return Response.ok().build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
