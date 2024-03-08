package main;

import vista.Login;

public class Principal {

    public static void main(String[] args) {
        
        GeneradorAdmin generarUsuarioAdmin = new GeneradorAdmin(); 
        
        Login pantallaLogin = new Login();
        pantallaLogin.setLocationRelativeTo(null);
        pantallaLogin.setVisible(true);

    }

}
