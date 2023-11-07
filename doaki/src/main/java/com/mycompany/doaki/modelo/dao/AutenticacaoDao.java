package com.mycompany.doaki.modelo.dao;

import com.mycompany.doaki.modelo.dominio.Usuario;
import com.mycompany.doaki.modelo.dominio.Perfil;
import com.mycompany.doaki.view.modelo.LoginDto;
import com.mycompany.doaki.modelo.exception.NegocioException;
import javax.swing.JOptionPane;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AutenticacaoDao {
    private final UsuarioDao usuarioDao;

    public AutenticacaoDao() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean temPermissao(Usuario usuario) {
        try {
            permissao(usuario);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuário sem permissão", 0);
            return false;
        }
    }

    private void permissao(Usuario usuario) {
        if (!Perfil.ADMIN.equals(usuario.getPerfil())) {
            throw new NegocioException("Sem permissão para essa ação");
        }

    }

    public Usuario login(LoginDto login) {
        Usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());

        if (usuario == null || !usuario.isEstado())
            return null;

        if (usuario.isEstado() && validarSenha(usuario.getSenha(), login.getSenha()))
            ;
        {
            return usuario;
        }

    }

    // private boolean validarSenha(String senhaUsuario, String senhaLogin) {
    // return senhaUsuario.equals(senhaLogin);
    // }

    // usando Spring Security
    private boolean validarSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senhaLogin, senhaUsuario);
    }
}
