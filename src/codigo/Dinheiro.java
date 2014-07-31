
package codigo;
/** 
* Classe que herda de Pagamento, se responsabiliza por pagamentos em dinheiro.
* 
*/

public class Dinheiro extends Pagamento {

    private double troco;

    public Dinheiro(double valor_pedido, double valor_recebido) {
        super(valor_pedido, valor_recebido);
        troco = valor_recebido - valor_pedido;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

   /**
    * Metodo calcula o troco que sera recebido do total do pedido
    */
    public double calculaPagamento() {
        return troco =  valor_recebido - valor_pedido;
    }
}