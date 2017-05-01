package Negocio;

import Modelo.Animal;
import Modelo.Usuario;

public class AnimalBL {
    
    public static String CadastrarAnimal(Animal animal){
        
        //Validações
        
        return "Animal Cadastrado com sucesso!";
    }
    
    public static String CadastrarAnimal(String nome, String especie, String tipo, 
            String idade, String raca, String sexo, String descricao, 
            String localizacao, String foto, Usuario responsavel){
        
        // nome
        if(nome.length() < 3 || nome.length() > 30)
            return "O nome deve possui no mínimo 3 caracteres";
        
        // especie
        if(!especie.equals("Cachorro") || !especie.equals("Gato") || !especie.equals("Outra") )
            return "Especie invalida";
        
        // tipo
        if(!tipo.equals("Adoção") || !tipo.equals("Perdido") || !tipo.equals("Resgate"))
            return "Tipo inválido";
        
        return "Animal Cadastrado com sucesso!";
    }
    
}
