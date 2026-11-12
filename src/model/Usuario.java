package model;

public class Usuario {

    // Atributos do usuário
    private String id;
    private String nome;

    // MÉTODO CONSTRUTOR
    public Usuario(String id, String nome) {

        this.id = id;
        this.nome = nome;
    }

    // GETTERS 
    public String getId() { return id; }

    public String getNome() { return nome; }

    // SETTERS 
    public void setId(String id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    // Método de saída
    @Override
    public String toString() {
        
        return "Usuario [ID=" + id + ", Nome=" + nome + "]";
    }
}
