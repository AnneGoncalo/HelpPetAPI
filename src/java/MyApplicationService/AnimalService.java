package MyApplicationService;

import DAO.AnimalDAO;
import Modelo.Animal;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("animal")
public class AnimalService {
   // "http://localhost:8080/TesteWS/rest/animal"
   @GET
   public String listar()
   {
       List<Animal> animais = null;
       try {
           animais = AnimalDAO.ListarAnimaisNaoAdotados();
       } catch (SQLException ex) {
           Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       Gson gson = new Gson();
       String json = gson.toJson(animais);
       
       return json;
   }
   
   // "http://localhost:8080/TesteWS/rest/animal/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(AnimalDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
   
   // "http://localhost:8080/TesteWS/rest/animal"
   @POST
   public String cadastrarAnimal(String json) 
   {
       Gson gson = new Gson();
       Animal a = gson.fromJson(json, Animal.class);
       
       Negocio.AnimalBL.CadastrarAnimal(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
   
   // "http://localhost:8080/TesteWS/rest/animal"
   @PUT
   public String editarAnimal(String json) 
   {
       Gson gson = new Gson();
       Animal a = gson.fromJson(json, Animal.class);
       
       try {
           AnimalDAO.EditarAnimal(a);
       } catch (SQLException ex) {
           Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
   
   // "http://localhost:8080/TesteWS/rest/animal"
   @DELETE
   @Path("{idAnimal}")
   public String excluirAnimal(@PathParam("idAnimal") int idAnimal) 
   {
       Gson gson = new Gson();
       //Animal a = gson.fromJson(json, Animal.class);
       
       System.out.println("Deu certo " + idAnimal);
       
       try {
           AnimalDAO.ExcluirAnimal(idAnimal);
       } catch (SQLException ex) {
           Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       String jsonSaida = gson.toJson(idAnimal);
       return jsonSaida;
       
   }
}
