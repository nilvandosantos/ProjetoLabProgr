
package codigo;

/** 
*Classe abstrata que vai modelar o pagamento da conta.
* Será herdada por Cheque, Cartao e Dinheiro.
* @author Marcos Lucas,Nayara,Nilvando
* @version 1.0
*/

public abstract class Pagamento {

    protected double valor_pedido;
    protected double valor_recebido;


    public Pagamento(double valor_pedido, double valor_recebido) {
        this.valor_pedido = valor_pedido;
        this.valor_recebido = valor_recebido;
    }

    /**
     * metodo abstrato que sera implementado na classe Dinheiro, Cheque e Cartao
     * 
     */
    public abstract double calculaPagamento();

    public double getValorPedido() {
        return valor_pedido;
    }

    public void setValorpedido(double valor_pedido) {
        valor_pedido = valor_pedido;
    }

    public double getValorRecebido() {
        return valor_recebido;
    }

    public void setValorRecebido(double valor_recebido) {
        valor_recebido = valor_recebido;
    }
}