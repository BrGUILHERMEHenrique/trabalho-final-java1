package br.com.serratec.model;

import java.time.LocalDate;

import br.com.serratec.enums.Parentesco;
import br.com.serratec.enums.Sexo;

public class Dependente extends Pessoa {
    private Parentesco parentesco;
    private int ano = LocalDate.now().getYear();
    private int idade = (ano - dataNascimento.getYear());


    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
        super(nome, cpf, dataNascimento);
        this.parentesco = parentesco;
    }

    
}