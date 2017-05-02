package Negocio;

import DAO.DenunciaDAO;
import Modelo.Denuncia;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DenunciaBL {
    
    public static String CadastrarDenuncia(Denuncia denuncia){
        
        //Validações
        
        try{
        DenunciaDAO.CadastrarDenuncia(denuncia);
        }
        catch(SQLException ex){
            return "Erro!";
        }
        return "Denuncia cadastrada com sucesso!";
    }
    
    public static List<Denuncia> ListarDenuncias(){
        
        try {
            return DenunciaDAO.ListarDenuncias();
        } catch (SQLException ex) {
            Logger.getLogger(DenunciaBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
