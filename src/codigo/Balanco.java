package codigo;

import control.*;
import java.util.*;
//import interfacegrafica.*;
import java.text.*;

public class Balanco {

    private static double total_arrecadado = 0;

    /**
     * Esta classe Balanço é responsável pela movimentação do caixa exibindo dia e hora que
     *cada conta foi fechada 
     * @author Marco Lucas,Nayara,Nilvando.
     * @version 1.0
     *
     */

    public void setTotalArrecadado(double adiciona) {
        total_arrecadado = adiciona;
    }

    public double getTotalArrecadado() {
        return total_arrecadado;
    }

    /**
     * Método para varrer todos os pedidos do caixa e registrar o movimento total
     * 
     * @param com LinkedList
     * @return movimento
     * @see Pedido
     * @version 1.0
     */
    public static String varrepedidos(LinkedList<Pedido> com) {
        String movimento = "";
        DecimalFormat d = new DecimalFormat("0.00");
//For-each para varrer a LinkedList Pedido 
        for (Pedido c : com) {
            movimento = movimento + "Código da pedido: " + c.getNumero();
            movimento = movimento + " | Dia: " + c.getData();
            movimento = movimento + " | Hora: " + c.getHora();
            movimento = movimento + " | Valor recebido: " + d.format(c.getPagamentoValorPedido());
            movimento = movimento + "\n";
        }

        return movimento;

    }

    /**
     * Metodo responsável adquirir a movimentação financeira do caixa
     * @param indice_caixa codigo
     * @return
     */
    public static String registraBalanco(int indice_caixa) {

        total_arrecadado = 0;

        DecimalFormat d = new DecimalFormat("0.00");

        int indice = 0;
        String def = "";
       //Varrer a coleção do caixa
        for (Caixa box : CoordCaixa.retornaCaixas()) {
        	//Se existir um indice maior será impresso na tela
            if (indice >= indice_caixa) {
                def = def + varrepedidos(CoordPedido.retornaPedido(indice));
            }

            total_arrecadado = total_arrecadado + box.getTotalCaixa();
            indice++;

        }
        //Se o total do dia for diferente de zero sera exibido uma mensagem informando o valor.
        if (total_arrecadado == 0) {
            return "Nenhuma movimentação financeira realizada até o momento!";
        } else {
            def = def + "\nTotal arrecadado:                     [" + d.format(total_arrecadado) + "]";
            return def;
        }
    }
    
}