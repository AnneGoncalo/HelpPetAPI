package DAO;

import Modelo.Animal;
import Modelo.Usuario;
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

public class AnimalDAO {
  
    public static String CadastrarAnimal(Animal animal) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Animal(nomeAnimal, especie, tipoAnimal, "
                + "sexo, idade, raca, descricaoAnimal, fotoAnimal, localizacao, "
                + "statusAnimal, dataCadastro, idUsuario) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            
            pstmt.setString(1, animal.getNome());
            pstmt.setString(2, animal.getEspecie());
            pstmt.setString(3, animal.getTipo());
            pstmt.setString(4, animal.getSexo());
            pstmt.setString(5, animal.getIdade());
            pstmt.setString(6, animal.getRaca());
            pstmt.setString(7, animal.getDescricao());
            pstmt.setString(8, animal.getFoto());
            pstmt.setString(9, animal.getLocalizacao());
            pstmt.setBoolean(10, false);
            pstmt.setDate(11, (Date) animal.getCadastro());
            pstmt.setInt(12, animal.getResponsavel().getIdUsuario());
            
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static void EditarAnimal(Animal animal) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "UPDATE Animal SET especie = ?, nomeAnimal = ?, sexo = ?, idade = ?, raca = ?, "
                + "descricaoAnimal = ?, fotoAnimal = ?, statusAnimal = ?, localizacao = ? WHERE idAnimal = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, animal.getEspecie());
            pstmt.setString(2, animal.getNome());
            pstmt.setString(3, animal.getSexo());
            pstmt.setString(4, animal.getIdade());
            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getDescricao());
            pstmt.setString(7, animal.getFoto());
            pstmt.setBoolean(8, animal.isStatus());
            pstmt.setString(9, animal.getLocalizacao());
            pstmt.setInt(10, animal.getIdAnimal());
            
            pstmt.executeUpdate();
            System.out.println("Resultado editar DAO");
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        } 
    }
    
    public static void ExcluirAnimal(int idAnimal) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "DELETE FROM Animal WHERE idAnimal = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idAnimal);
            
            pstmt.executeUpdate();
            System.out.println("Resultado excluir DAO");
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
    }
    
    public static List<Animal> ListarAnimaisNaoAdotados() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Animal> lista = new ArrayList();
        String sql= "SELECT * FROM Animal "
                + "INNER JOIN Usuario ON Usuario.idUsuario = Animal.idUsuario "
                + "WHERE statusAnimal = 0";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Usuario resp = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), null, rs.getString("dataNascimento"),
                        rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                
                Animal a = new Animal();
                a.setIdAnimal(rs.getInt("idAnimal"));
                
                a.setNome(rs.getString("nomeAnimal"));
                a.setEspecie(rs.getString("especie"));
                a.setTipo(rs.getString("tipoAnimal"));
                
                a.setRaca(rs.getString("raca"));
                a.setIdade(rs.getString("idade"));
                a.setSexo(rs.getString("sexo"));
                
                a.setDescricao(rs.getString("descricaoAnimal"));
                a.setFoto(rs.getString("fotoAnimal"));
                a.setLocalizacao(rs.getString("localizacao"));
                
                a.setCadastro(rs.getDate("dataCadastro"));
                a.setStatus(rs.getBoolean("statusAnimal"));
                a.setResponsavel(resp);
                
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        System.out.println("Animal dao: " + lista.size());
        return lista;
    }
    
    public static List<Animal> ListarPorUsuario(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Animal> lista = new ArrayList();
        String sql= "SELECT * FROM Animal INNER JOIN Usuario ON Usuario.idUsuario = Animal.idUsuario WHERE Animal.idUsuario = ?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Usuario resp = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), null, rs.getString("dataNascimento"),
                        rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                
                Animal a = new Animal();
                a.setIdAnimal(rs.getInt("idAnimal"));
                
                a.setNome(rs.getString("nomeAnimal"));
                a.setEspecie(rs.getString("especie"));
                a.setTipo(rs.getString("tipoAnimal"));
                
                a.setRaca(rs.getString("raca"));
                a.setIdade(rs.getString("idade"));
                a.setSexo(rs.getString("sexo"));
                
                a.setDescricao(rs.getString("descricaoAnimal"));
                a.setFoto(rs.getString("fotoAnimal"));
                a.setLocalizacao(rs.getString("localizacao"));
                
                a.setCadastro(rs.getDate("dataCadastro"));
                a.setStatus(rs.getBoolean("statusAnimal"));
                a.setResponsavel(resp);
                
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        System.out.println("Animal DAO : qtd " + lista.size());
        return lista;
    }
}
