package org.example.modelo;

public class Cliente {
    private final Integer id;

    public Integer getId() {
        return id;
    }

    private final String nome;
    private final String email;
    private final String cpf;

    public Cliente(int id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(String nome, String email, String cpf) {
        this.id = null;
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
        if (email.contains("@")) {
            throw new IllegalArgumentException("Email sem @.");
        }
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
