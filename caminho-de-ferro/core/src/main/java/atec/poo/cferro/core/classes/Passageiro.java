package atec.poo.cferro.core.classes;

import java.time.LocalDate;

public abstract class Passageiro {
    private String nome;
    private int next_id;
    private LocalDate dataNascimento;

    public Passageiro(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.next_id = 1;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return next_id;
    }

    public void setId(int id) {
        this.next_id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
