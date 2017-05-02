package DAO;

import Modelo.Denuncia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DenunciaDAO {
    
    public static String CadastrarDenuncia(Denuncia denuncia) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Denuncia(tituloDenuncia, tipoDenuncia, "
                + "descricaoDenuncia, fotoDenuncia, localizacao )"
                + "values(?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            
            pstmt.setString(1, denuncia.getTitulo());
            pstmt.setString(2, denuncia.getTipo());
            pstmt.setString(3, denuncia.getDescricao());
            pstmt.setString(4, denuncia.getFoto());
            pstmt.setString(5, denuncia.getLocalizacao());
            pstmt.setDate(6, (Date) denuncia.getCadastro());
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DenunciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static List<Denuncia> ListarDenuncias() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Denuncia> lista = new ArrayList();
        String sql= "SELECT * FROM Denuncia";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Denuncia d = new Denuncia();
                d.setIdDenuncia(rs.getInt("idDenuncia"));
                d.setTitulo(rs.getString("tituloDenuncia"));
                d.setTipo(rs.getString("tipoDenuncia"));
                d.setDescricao(rs.getString("descricaoDenuncia"));
                d.setFoto(rs.getString("fotoDenuncia"));
                d.setLocalizacao(rs.getString("localizacao"));
                d.setCadastro(rs.getDate("dataDenuncia"));
                lista.add(d);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DenunciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
}
