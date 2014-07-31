package codigo;

import java.text.*;
/**
 * Esta classe tem como objetivo armazenar os dados do funcionario garçom,tais dados estão
 * contem as suas respectivas caracteristicas como a sua gorjeta atraves de um codigo que será gerado apos 
 * concluir o cadastro,nome do garçom e o total de gorjetas que o mesmo irá receber.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 * 
 * @version 1.0
 *  
 *  
 *
 */
public class Garcom {

    private String nome;
    private int codigo;
    private double gorjeta;
    private double totalGorjeta;
    private static int codigoDeTodosOsGarcons = 1000;

    public Garcom(String nome) {
        this.nome = nome;
        this.codigo = codigoDeTodosOsGarcons;
        codigoDeTodosOsGarcons++;
        gorjeta = 0;
        totalGorjeta = 0;
    }
    
    public Garcom(String nome, int codigo, double gorjeta, double totalGorjeta) {
        this.nome = nome;
        this.codigo = codigo;
        this.gorjeta = gorjeta;
        this.totalGorjeta = totalGorjeta;
        codigoDeTodosOsGarcons = codigo+1;
        
    }

    //Construtor utilizado para criar um novo objet para consultar garçons
    public Garcom() {
        this.nome = "";
        this.codigo = 0;
        gorjeta = 0;
        totalGorjeta = 0;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }  

    public double getGorjeta() {
        return gorjeta;
    }

    public void setGorjeta(double gorjeta) {
        this.gorjeta = gorjeta;
    }
    /**
     * Metodo para retorno do nome  do garçom 
     * @return String nome.
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getTotalGorjeta() {
        return totalGorjeta;
    }

    public void setTotalGorjeta(double gorjeta) {
        this.totalGorjeta = totalGorjeta + gorjeta;
    }
/**
 * Este metodo calcula tanto o parcial da gorjeta do garçom como o total da mesma,tal valor 
 * é equivalente a 10% do pedido.
 * @param valor double- valor retirado do pedido de cada mesa 
 * @return double calc_gorjeta.
 */
   
    public double calculaGorjeta(double valor) {
        double calc_gorjeta = valor * 0.1;
        totalGorjeta = totalGorjeta + calc_gorjeta;
        return calc_gorjeta;
    }
/**
 * Este metodo  imprimi informações relativas ao garçom,
 *  com a gorjeta formatada para 2 casas decimais
 */
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return "Nome: " + nome + "\nCódigo: " + codigo + "\nTotal de gorjeta acumulada: " + d.format(totalGorjeta);
    }
}