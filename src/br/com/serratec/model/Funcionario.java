package br.com.serratec.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.serratec.exceptions.FuncionarioExceptions;
import br.com.serratec.interfaces.Caculos;

public class Funcionario extends Pessoa implements Caculos {
    private double salarioBruto;
    private double descontoInss;
    private double descontoIR;
    private double salarioLiquido;
    private double contaIr;
    private List<Dependente> dependentes = new ArrayList<Dependente>();
		

    @Override
    public void calculaInss() {
        // TODO Auto-generated method stub
        if (salarioBruto <= 1751.81) {
            descontoInss = salarioBruto * 0.08;
        } else if (salarioBruto >= 1751.82 && salarioBruto <= 2919.72) {
            descontoInss = salarioBruto * 0.09;
        } else if (salarioBruto >= 2919.73 && salarioBruto <= 5839.45) {
            descontoInss = salarioBruto * 0.11;
        } else {
            descontoInss = 5839.456 * 0.11;
        }
    }

    @Override
    public void calculaIr() {
        // TODO Auto-generated method stub
        contaIr = salarioBruto - descontoInss - (189.59 * dependentes.size());
        if (contaIr <= 1903.98) {
            salarioLiquido = salarioBruto - descontoInss;
        } else if (contaIr >= 1903.99 && contaIr <= 2826.65) {
            descontoIR = contaIr * 0.075 - 142.80;
            salarioLiquido = salarioBruto - descontoIR - descontoInss;
        } else if (contaIr >= 2826.66 && contaIr <= 3751.05) {
            descontoIR = contaIr * 0.15 - 354.80;
            salarioLiquido = salarioBruto - descontoIR - descontoInss;
        } else if (contaIr >= 3751.06 && contaIr <= 4664.68) {
            descontoIR = contaIr * 0.225 - 636.13;
            salarioLiquido = salarioBruto - descontoIR - descontoInss;
        } else {
            descontoIR = contaIr * 0.275 - 869.36;
            salarioLiquido = salarioBruto - descontoIR - descontoInss;
        }
    }

    public void verificaCpf(List<Funcionario> funcionarios, String cpf, String nome) {
    	for(Funcionario f: funcionarios) {
    		if(f.getCpf().equals(cpf)) {
			throw new FuncionarioExceptions("O cpf do funcionário "+ nome +" está na lista");
    		}
    	}
    }
    
   


    public void addDep(Dependente dep, LocalDate dataNascimento){
        if(LocalDate.now().getYear() - dataNascimento.getYear() >= 18) {
        	System.out.println("O dependente já passou dos 18 e por isso não foi adicionado");
        }else {
        	dependentes.add(dep);
        }
    }

    @Override
    public String toString() {
        return nome + ";" + cpf + ";" + new DecimalFormat("#####.##").format(descontoInss) + ";" + new DecimalFormat("#####.##").format(descontoIR) + ";" + new DecimalFormat("#####.##").format(salarioLiquido) + ";\r";
    }

    public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
        super(nome, cpf, dataNascimento);
        this.salarioBruto = salarioBruto;
    }



    

    

    

    
}