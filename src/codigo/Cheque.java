
 package codigo;
 /**
  * Esta é uma subclasse da classe Pagamento, usada quando o cliente usa o cheque
  * como forma de pagamento;
  *@author Marcos Lucas,Nayara,Nilvando
  *
  */
public class Cheque extends Pagamento {

    private String data;

    public Cheque(double valor_pedido, double valor_recebido, String a) {
        super(valor_pedido, valor_recebido);
        data = a;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public double calculaPagamento() {

        throw new UnsupportedOperationException("Not supported yet.");

    }
}