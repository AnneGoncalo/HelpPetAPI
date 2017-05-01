package Modelo;

import java.util.Date;

public class Animal {
    private int idAnimal;           // Id do Animal
    private String nome;            // nome do Animal
    private String especie;         // especie do Animal ["Cachorro", "Gato", "Outra"]
    private String tipo;            // tipo do Animal ["Adoção", "Perdido", "Resgate"]
    private String raca;            // raça do Animal ["viralata", "srd", "outra"]
    private String idade;           // idade do Animal ["0-6 meses", "6-12 meses", "1-2 anos", "2-5 anos", "mais de 5 anos"]
    private String sexo;            // sexo do Animal ["m", "f"]
    private String descricao;       // descrição do Animal
    private String localizacao;     // localização do Animal
    private String foto;            // caminho da foto do Animal
    private boolean status;         // status do Animal em relação ao tipo do Animal
    private Date cadastro;          // data do cadastro do Animal
    private Usuario responsavel;    // Usuario que cadastrou o animal

    public Animal() {
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isStatus() {
        return status;
    }
    
    public String getStatusAnimalString() {
        switch (this.tipo) {
            case "Adoção":
                if(this.status)
                    return "Adotado";
                else
                    return "Não Adotado";
            case "Perdido":
                if(this.status)
                    return "Encontrado";
                else
                    return "Perdido";
            case "Resgate":
                if(this.status)
                    return "Resgatado";
                else
                    return "Esperando Resgate";
            default:
                return "Erro";
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    

}
