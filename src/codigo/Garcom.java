
 /* CLASSE GARCOM
 */
package codigo;

import java.text.*;

public class Garcom {

    private String nome;
    private int codigo;
    private double gorjeta;
    private double totalGorjeta;
    private static int codigoDeTodosOsGarcons = 1000;

    //Construtor
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

    //Construtor utilizado para criar um novo objeto
    //para consultar garçons
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

    //metodo que calcula a gorjeta do garçom
    //(10% do valor da comanda) e o total de gorjeta do garçom
    public double calculaGorjeta(double valor) {
        double calc_gorjeta = valor * 0.1;
        totalGorjeta = totalGorjeta + calc_gorjeta;
        return calc_gorjeta;
    }

    //metodo para imprimir informações relativas ao garçom,
    //com a gorjeta formatada para 2 casas decimais
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return "Nome: " + nome + "\nCódigo: " + codigo + "\nTotal de gorjeta acumulada: " + d.format(totalGorjeta);
    }
}