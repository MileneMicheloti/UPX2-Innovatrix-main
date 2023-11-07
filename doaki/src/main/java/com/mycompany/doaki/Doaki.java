package com.mycompany.doaki;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.doaki.modelo.conexao.Conexao;
import com.mycompany.doaki.modelo.conexao.ConexaoMysql;
import com.mycompany.doaki.modelo.dominio.Categoria;

/**
 *
 * @author bre-r
 */
public class Doaki {

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new ConexaoMysql();
        Categoria categoria = new Categoria(null, "Bebida", "Leite");
        String inserirSQL = "INSERT INTO categoria(nome, descricao) values(?, ?)";
        PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(inserirSQL);

        String sql = "Select * from categoria";

        ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

        while (result.next()) {
            System.out.println(result.getString("nome"));
        }

    }
}
