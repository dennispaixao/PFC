/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.AutorizacaoDeAcesso;

import br.com.artenativa.model.Usuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dennis
 */
public class AcessoAdministrativo {
    
    
    public static boolean checarAcesso( HttpSession sessaoUsuario){
        Usuario user = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
        return user.getNivel() == PerfilDeAcesso.valueOf("ADMINISTRADOR"); //true or false
    }
    
    
}
