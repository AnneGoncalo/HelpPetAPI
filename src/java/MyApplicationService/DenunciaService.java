package MyApplicationService;

import Modelo.Denuncia;
import Negocio.DenunciaBL;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("denuncia")
public class DenunciaService {
   // "http://localhost:8080/TesteWS/rest/denuncia"
   @GET
   public String listarDenuncias() {
       List<Denuncia> denuncias = DenunciaBL.ListarDenuncias();
       
       Gson gson = new Gson();
       String json = gson.toJson(denuncias);
       
       return json;
   } 
   // "http://localhost:8080/TesteWS/rest/denuncia"
   @POST
   public String cadastrarDenuncia(String json) {
       Gson gson = new Gson();
       Denuncia a = gson.fromJson(json, Denuncia.class);
       DenunciaBL.CadastrarDenuncia(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
}
