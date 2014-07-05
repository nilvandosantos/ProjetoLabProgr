
 /* CLASSE PAGAMENTO
 *
 * Classe abstrata que vai modelar o pagamento da conta.
 * Será herdada por Cheque, Cartao e Dinheiro.
 */
package codigo;

public abstract class Pagamento {

    protected double valor_comanda;
    protected double valor_recebido;


    public Pagamento(double valor_comanda, double valor_recebido) {//Construtor
        this.valor_comanda = valor_comanda;
        this.valor_recebido = valor_recebido;
    }

    //metodo abstrato que sera implementado na classe Dinheiro, Cheque e Cartao
    public abstract double calculaPagamento();

    public double getValorComanda() {
        return valor_comanda;
    }

    public void setValorComanda(double valor_comanda) {
        valor_comanda = valor_comanda;
    }

    public double getValorRecebido() {
        return valor_recebido;
    }

    public void setValorRecebido(double valor_recebido) {
        valor_recebido = valor_recebido;
    }
}