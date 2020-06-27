package br.ufpb.disciplina.entities;

public class Disciplina {

	private int id;
	private String nome;
	private double nota;


    public Disciplina() {
    }

    public Disciplina(String nome, double nota) {

        this.nome = nome;
        this.nota = nota;
    }


    public Disciplina(int id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    } 
}