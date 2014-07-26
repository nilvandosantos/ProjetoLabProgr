package codigo;

import control.*;
import java.util.*;
//import interfacegrafica.*;
import java.text.*;

public class Balanco {

    private static double total_arrecadado = 0;

    public void setTotalArrecadado(double adiciona) {
        total_arrecadado = adiciona;
    }

    public double getTotalArrecadado() {
        return total_arrecadado;
    }

    //M�todo para varrer todas as comandas do caixa e registrar o movimento total
    public static String varreComandas(LinkedList<Pedido> com) {
        String movimento = "";
        DecimalFormat d = new DecimalFormat("0.00");

        for (Pedido c : com) {
            movimento = movimento + "C�digo da comanda: " + c.getNumero();
            movimento = movimento + " | Dia: " + c.getData();
            movimento = movimento + " | Hora: " + c.getHora();
            movimento = movimento + " | Valor recebido: " + d.format(c.getPagamentoValorComanda());
            movimento = movimento + "\n";
        }

        return movimento;

    }

    //Metodo respons�vel por pegar a movimenta��o financeira do caixa
    public static String registraBalanco(int indice_caixa) {

        total_arrecadado = 0;

        DecimalFormat d = new DecimalFormat("0.00");

        int indice = 0;
        String def = "";

        for (Caixa box : CoordCaixa.retornaCaixas()) {

            if (indice >= indice_caixa) {
                def = def + varreComandas(CoordPedido.retornaPedido(indice));
            }

            total_arrecadado = total_arrecadado + box.getTotalCaixa();
            indice++;

        }

        if (total_arrecadado == 0) {
            return "Nenhuma movimenta��o financeira realizada at� o momento!";
        } else {
            def = def + "\nTotal arrecadado:                     [" + d.format(total_arrecadado) + "]";
            return def;
        }
    }
    
}