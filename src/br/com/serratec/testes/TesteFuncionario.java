package br.com.serratec.testes;


import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import br.com.serratec.enums.Parentesco;
import br.com.serratec.exceptions.DependenteExceptions;
import br.com.serratec.exceptions.FuncionarioExceptions;
import br.com.serratec.model.Dependente;
import br.com.serratec.model.Funcionario;


public class TesteFuncionario {

        public static void main(String[] args) {
                String caminho;
                String saida;
                String fileSaida;
                String file;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                Scanner in = new Scanner(System.in);


                System.out.println("Digite o caminho ABSOLUTO para leitura do arquivo");
                caminho = in.nextLine();
                file = caminho.replace("\"", "\\");

                System.out.println("Digite o caminho ABSOLUTO para gravar o novo arquivo");
                saida = in.nextLine();
                fileSaida = saida.replace("\"", "\\");
                
               try {
                	Scanner enter = new Scanner(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                	FileWriter escrita = new FileWriter(fileSaida);
                	
                	Set<Funcionario> funcionarios = new HashSet<Funcionario>();
                	List<Dependente> dependentes = new ArrayList<Dependente>();
                	Funcionario funcionario = null;
                	
                	while(enter.hasNext()) {
                		String l = enter.nextLine();
                		if(!l.isEmpty()) {
                			System.out.println("--Lendo Linha");
                			String[] info = l.split(";");
                			if(funcionario != null) {
                				System.out.println("--Criando dependente");
                				String nomeDep = info[0];
                				String cpfDep = info[1];
                				String dataNascimento = info[2];
                				String parentesco = info[3];
                				if(cpfDep.length() != 11) {
                					throw new FuncionarioExceptions("O dependente "+ nomeDep +" tem o cpf de tamanho inválido e por isso não irá para lista");
                				}else {
                    				Dependente dependente = new Dependente(nomeDep, cpfDep, LocalDate.parse(dataNascimento, formatter), Enum.valueOf(Parentesco.class, parentesco));
                    				funcionario.addDep(dependente, LocalDate.parse(dataNascimento, formatter));
                    				System.out.println("--Fazendo os calculos de inss e imposto de renda");
                        			funcionario.calculaInss();
                        			funcionario.calculaIr();
                    				dependentes.add(dependente);
                    				System.out.println("--Dependente adicionado ao funcionário com sucesso");
                    				
                				}
                				continue;
                				
                			}
                			System.out.println("--Criando funcionário");
                			String nome = info[0];
                			String cpf = info[1];
                			String dataNascimento = info[2];
                			String salario = info[3];
                			if(cpf.length() != 11) {
                				throw new FuncionarioExceptions("O funcionário "+ nome +" tme o cpf inválido e por isso não será incluido a lista");
                			}else {
                    			funcionario = new Funcionario(nome, cpf, LocalDate.parse(dataNascimento, formatter), Double.parseDouble(salario));
                    			funcionarios.add(funcionario);
                    			System.out.println("--funcionário "+ funcionario.getNome() +" adicionado com sucesso a lista");
                    			continue;
                			}
                			                			
                		}else {
                			
                			funcionario = null;
                		}
                	
                	}
                	   
                		for(Funcionario f: funcionarios) {
                    		escrita.write(f.toString());
                    	}

                	escrita.close();
                	enter.close();
               }catch(IOException e) {
            	   e.printStackTrace();
               }catch(DependenteExceptions d) {
            	   	System.out.println(d.getMessage());
               }catch(FuncionarioExceptions f) {
            	   System.out.println(f.getMessage());
               }catch(IndexOutOfBoundsException i) {
            	   System.out.println(i.getMessage());
            	   System.out.println("Há algum erro na divisão das linhas do arquivo, ou o mesmo está com algum campo de informação faltando");
               }catch(InputMismatchException i) {
            	   System.out.println(i.getMessage());
               }
        }

    }