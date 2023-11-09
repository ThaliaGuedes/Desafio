package org.example.modelo;

public class Vendedor {
    private final Integer id;
    private String nome;
    private String email;
    private String cpf;

    public Integer getId() {
        return id;
    }
    public Vendedor(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.id = null;
    }
    public Vendedor(int id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
    public void validarEmail(){
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email sem @.");
        }
    }


    @Override
    public String toString() {
        return "Vendedor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

}
