
package main;

import controlador.Ctrl_Usuario;
import modelo.Usuario;
import seguridad.hash;

public class GeneradorAdmin {
    private Usuario primerUsuario;
    private Ctrl_Usuario ctrlUsuario = new Ctrl_Usuario();
    
    public GeneradorAdmin(){   
        primerUsuario = new Usuario( "adm", "nombreAdm", "apellidoAdm", "dniAdm", "Administrador", "000-000-000");
        hashearPassword();
        if (ctrlUsuario.existeUsuario("adm") == false){
            ctrlUsuario.registrarUsuario(primerUsuario); 
        }
    }
    
    
    private void hashearPassword(){
        String password = "1234";
        String passwordHashed = hash.sha1(password);
        primerUsuario.setContrasena(passwordHashed);
    }
           
    
    
}
