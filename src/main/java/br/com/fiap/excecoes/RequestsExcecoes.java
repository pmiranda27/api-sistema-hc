package br.com.fiap.excecoes;

import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class RequestsExcecoes {
    public RequestsExcecoes(){
        
    }
    
    static public Response ExcecoesConexao(Exception exception){
        if (exception instanceof SQLException sqlExcecao){
            String mensagemErro = sqlExcecao.getMessage();
            int codigoErro = sqlExcecao.getErrorCode();
            switch(codigoErro){
                case 1: // ORA-00001
                    return Response.status(Response.Status.CONFLICT)
                            .entity("Violação de chave primária — registro duplicado.")
                            .build();

                case 1400: // ORA-01400
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Campo obrigatório não pode ser nulo.")
                            .build();

                case 2292: // ORA-02292
                    return Response.status(Response.Status.CONFLICT)
                            .entity("Violação de integridade referencial (chave estrangeira).")
                            .build();

                case 1722: // ORA-01722
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Tipo de dado inválido para a operação.")
                            .build();

                case 907: // ORA-00907
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Erro de sintaxe SQL na consulta.")
                            .build();

                case 2391: // ORA-02391
                    return Response.status(Response.Status.TOO_MANY_REQUESTS)
                            .entity("Limite de sessões simultâneas atingido.")
                            .build();

                case 20001:
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Nenhum registro encontrado")
                            .build();
                default:
                    throw new IllegalStateException("Unexpected value: " + exception);
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro interno inesperado: " + exception.getMessage())
                .build();
    }
}
