package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import codigo.Caixa;
import codigo.Garcom;
import codigo.Produto;
	
public class CadProduto {
	private static LinkedList<Produto> produtos = new LinkedList<Produto>();
	private static LinkedList<Garcom> garcons = new LinkedList<Garcom>();
	private static LinkedList<Caixa> caixas = new LinkedList<Caixa>();
	private DataOutputStream outputGarcom;
	private DataInputStream inputGarcom;
	private boolean moreRecordsGarcom = true;
	private DataOutputStream outputProduto;
	private DataInputStream inputProduto;
	private boolean moreRecordsProduto = true;

	public static void main(String[] args) {
		CadProduto c = new CadProduto();
		c.abrirArquivo();
		Produto p = new Produto("Arroz", " no almoço é bom", 15, 3, 2.0);
		c.adicionaProduto(p);
		c.gravarProduto();
		c.iniciarProduto();
		
	}
	public void abrirArquivo() {
		File arquivo = new File("Produtos.txt");
		try {
			if (!arquivo.exists()) {
				// cria um arquivo (vazio)
				arquivo.createNewFile();
				JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public void iniciarProduto() {
	        //Ler os produtos no arquivo
	        try {
	            inputProduto = new DataInputStream(new FileInputStream("Produtos.txt"));
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Falha na Abertura do Arquivo para Leitura", "Erro", JOptionPane.ERROR_MESSAGE);
	            System.exit(1);
	        }

	        //InstanciarProduto
	        String nome;
	        String descricao;
	        int codigo;
	        int quantidadeVendida;
	        double preco;

	        //Carrega os produtos do arquivo para as coleções
	        try {
	            while (moreRecordsProduto) {
	                nome = inputProduto.readUTF();
	                descricao = inputProduto.readUTF();
	                codigo = inputProduto.readInt();
	                quantidadeVendida = inputProduto.readInt();
	                preco = inputProduto.readDouble();

	                Produto temporario = new Produto(nome, descricao, codigo, quantidadeVendida, preco);
	                adicionaProduto(temporario);
	                //linha de teste
	                System.out.println(nome + descricao + codigo + quantidadeVendida + preco);
	            }
	        } catch (EOFException eof) {
	            moreRecordsProduto = false;
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Erro durante leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
	            System.exit(1);
	        }

	        //Fecha o objeto inputProdut
	        try {
	            inputProduto.close();
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Falha no Fechamento do Arquivo durante Leitura", "Erro", JOptionPane.ERROR_MESSAGE);
	            System.exit(1);
	        }


	    }
	// Metodo para adicionar um objeto Produto a LinkedList "produtos"
	public static void adicionaProduto(Produto p) {
		produtos.add(p);
	}
	public void gravarProduto() {
        //Abre arquivo para gravar
        try {
            outputProduto = new DataOutputStream(new FileOutputStream("Produtos.txt", false));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha na Abertura do Arquivo para Gravação", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //Escreve toda a coleção de produtos no arquivo
        try {
            for (Produto a : produtos) {
                outputProduto.writeUTF(a.getNome());
                outputProduto.writeUTF(a.getDescricao());
                outputProduto.writeInt(a.getCodigo());
                outputProduto.writeInt(a.getQuantidadeVendida());
                outputProduto.writeDouble(a.getPreco());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro durante gravação no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //Fecha os arquivos Output produto
        try {
            outputProduto.flush();
            outputProduto.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha no Fechamento do Arquivo - Durante GravaÃ§Ã£o!!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
	

}
