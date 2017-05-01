/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.UsuarioDAO;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Ana Gonçalo
 */
@Path("ongs")
public class OngService {
    
    // "http://localhost:8080/TesteWS/rest/ongs"
   @GET
   public String listarPJs(){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(UsuarioDAO.listarOng());
       } catch (SQLException ex) {
           Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}
