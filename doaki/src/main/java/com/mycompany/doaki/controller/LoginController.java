package com.mycompany.doaki.controller;

import com.mycompany.doaki.modelo.dao.AutenticacaoDao;
import com.mycompany.doaki.modelo.dominio.Usuario;
import com.mycompany.doaki.view.formulario.Login;
import com.mycompany.doaki.view.modelo.LoginDto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private final Login login;
    private AutenticacaoDao autenticacaoDao;

    public LoginController(Login login) {
        this.login = login;
        this.autenticacaoDao = new AutenticacaoDao();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String acao = ae.getActionCommand().toLowerCase();

        switch (acao) {
            case "login":
                login();
                break;
            case "cancelar":
                cancelar();
                break;
        }
    }

    private void login() {
        String usuario = this.login.getTxtLoginUsuario().getText();
        String senha = this.login.getTxtLoginSenha().getText();

        if (usuario.equals("") || senha.equals("")) {
            this.login.getLabelLoginMensagem().setText("Usuario e senha devem ser preenchidos.");
            return;
        }
        LoginDto loginDto = new LoginDto(usuario, senha);
        
        Usuario usuarioTemp = this.autenticacaoDao.login(loginDto);
        
        if(usuarioTemp != null){
            JOptionPane.showConfirmDialog(null, usuarioTemp.getNome());
            limpaCampos();
        } else {
            this.login.getLabelLoginMensagem().setText("Usuario e senha incorretos");
        }
    }

    private void cancelar() {
        int confirmar = JOptionPane.showConfirmDialog(login, "Tema certeza que deseja sair?", "Sair do sistema", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }
    
    private void limpaCampos(){
        this.login.getTxtLoginUsuario().setText("");
        this.login.getTxtLoginSenha().setText("");
        this.login.getLabelLoginMensagem().setText("");
    }
}
