package com.mycompany.doaki.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycompany.doaki.modelo.conexao.Conexao;
import com.mycompany.doaki.modelo.conexao.ConexaoMysql;
import com.mycompany.doaki.modelo.dominio.*;

public class UsuarioDao {
    private final Conexao conexao;

    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }

    public String salvar(Usuario usuario) { // usuario dando erro aaaa
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome,usuario,senha,perfil,estado)VALUES (?,?,?,?,?)";

        Usuario usuarioTemp = buscarUsuarioPeloUsuario(usuario.getUsuario());

        if (usuarioTemp != null) {// aqui é quando ele vê que o usuário já existe no banco de dados conforme
                                  // informado pelo proprio usuario
            return String.format("Error: Usuario %s ja existe no banco de dados", usuario.getUsuario());
        }

        try {
            PreparedStatement prepareStatement = conexao.obterConexao().prepareStatement(sql);

            preencherValoresNoPreparedStatement(prepareStatement, usuario);

            int resultado = prepareStatement.executeUpdate();

            return resultado == 1 ? "Usuario adicionado com sucesso" : "Nao foi possivel adicionar usuario";
        } catch (SQLException e) {
            return String.format("Erro; %s", e.getMessage());
        }

    }

    private String editar(Usuario usuario) { // vai entrar aqui caso escolhenmos o editar
        String sql = "UPADTE usuario SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?"; // faz
                                                                                                                   // a
                                                                                                                   // consulta
                                                                                                                   // sql
        try {
            PreparedStatement prepareStatement = conexao.obterConexao().prepareStatement(sql); // PreparedStatement é
                                                                                               // uma interface no Java
                                                                                               // que representa uma
                                                                                               // instrução SQL
                                                                                               // precompilada. Ela é
                                                                                               // usada para executar
                                                                                               // consultas SQL
                                                                                               // parametrizadas no
                                                                                               // banco de dados.

            preencherValoresNoPreparedStatement(prepareStatement, usuario);

            int resultado = prepareStatement.executeUpdate();

            return resultado == 1 ? "Usuario editado com sucesso" : "Nao foi possivel editar usuario";
        } catch (SQLException e) {
            return String.format("Erro; %s", e.getMessage());
        }
    }

    private void preencherValoresNoPreparedStatement(PreparedStatement prepareStatement, Usuario usuario)
            throws SQLException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCrypt = passwordEncoder.encode(usuario.getSenha());

        prepareStatement.setString(1, usuario.getNome());
        prepareStatement.setString(2, usuario.getUsuario());
        prepareStatement.setString(3, senhaCrypt);
        prepareStatement.setString(4, usuario.getPerfil().name());
        prepareStatement.setBoolean(5, usuario.isEstado());

        if (usuario.getId() != 0) {
            prepareStatement.setLong(6, usuario.getId());

        }
    }

    // metodo listar todos

    public List<Usuario> buscarTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery(sql);

            while (result.next()) {
                usuarios.add(getUsuario(result));

            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: ", e.getMessage()));
        }
        return usuarios;
    }

    private Usuario getUsuario(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsuario(result.getString("usuario"));
        usuario.setSenha(result.getString("senha"));
        usuario.setPerfil(Perfil.valueOf(result.getString("perfil")));
        usuario.setEstado(result.getBoolean("Estado"));

        return usuario;
    }

    // esse metodo abaixo será utilizado para fazer o login, ou seja, vai buscar as
    // informações dadas pelo usuário no banco para garantir que ela existe;

    public Usuario buscarUsuarioPeloId(Long id) {
        String sql = String.format("SELECT * FROM usuario WHERE id = %d", id);
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery(sql);

            if (result.next()) {
                return getUsuario(result);

            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: ", e.getMessage()));
        }
        return null;
    }

    public Usuario buscarUsuarioPeloUsuario(String usuario) {
        String sql = String.format("SELECT * FROM usuario WHERE usuario = '%s'", usuario);
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery(sql);

            if (result.next()) {
                return getUsuario(result);

            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: ", e.getMessage()));
        }
        return null;
    }
}