
package br.com.artenativa.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dennis
 */

public interface ICommand {
        
    public HttpServletRequest executar(HttpServletRequest request, HttpServletResponse response);

}
