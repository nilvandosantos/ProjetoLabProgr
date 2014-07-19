package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import codigo.Garcom;
import codigo.Produto;

/**
 * Esta classe tem como objetivo cadastrar o Objeto Produto em sua respectiva
 * lista.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 * 
 * @version 1.0
 * 
 */
public class CadProduto {
	private static LinkedList<Produto> produtos = new LinkedList<Produto>();
	private DataOutputStream outputProduto;
	private DataInputStream inputProduto;
	private boolean moreRecordsProduto = true;

	public CadProduto() {
		iniciarProduto();
	}

	/**
	 * Este metodo tem como função verificar se já existe um produto com o mesmo
	 * nome cadastrado no sistema.
	 * 
	 * @param nome
	 *            do produto.
	 * @return boolean isExist.
	 */

	public boolean isExistProduto(String nome) {

		boolean isExist = false;

		for (Produto cadastrado : produtos) {

			if (cadastrado.getNome().equalsIgnoreCase(nome)) {
				isExist = true;
			}
		}

		return isExist;
	}

	/**
	 * Metodo tem como função abrir o arquivo do tipo TXT e cria-lo,caso o mesmo
	 * ja tenha sido gerado ele apenas irá ler o arquivo e istanciará o objeto.
	 */
	public void iniciarProduto() {
		File arquivo = new File("Produtos.txt");
		try {
			if (!arquivo.exists()) {
				// cria um arquivo (vazio)
				arquivo.createNewFile();
				JOptionPane.showMessageDialog(null,
						"Arquivo criado com sucesso!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Ler os produtos no arquivo se ocorrer algum erro durante a leitura
		 * mensagem de erro sera apresentado
		 */
		try {
			inputProduto = new DataInputStream(new FileInputStream(
					"Produtos.txt"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// InstanciarProduto
		String nome;
		String descricao;
		int codigo;
		int quantidadeVendida;
		double preco;

		// Carrega os produtos do arquivo para as listas.
		try {
			while (moreRecordsProduto) {
				nome = inputProduto.readUTF();
				descricao = inputProduto.readUTF();
				codigo = inputProduto.readInt();
				quantidadeVendida = inputProduto.readInt();
				preco = inputProduto.readDouble();

				Produto temporario = new Produto(nome, descricao, codigo,
						quantidadeVendida, preco);
				adicionaProduto(temporario);
			}
		} catch (EOFException eof) {
			moreRecordsProduto = false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante leitura do arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Fecha o objeto Produto inputProdut
		try {
			inputProduto.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo durante Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}

	/**
	 * Metodo para adicionar um objeto Produto a LinkedList "produtos".
	 * @param p Adicionar um produto.
	 */
	
	public static void adicionaProduto(Produto p) {
		produtos.add(p);
	}
	
	/**
	 * Metodo para abrir o arquivo e grava-lo,caso ocorra algum erro uma mensagem será retornada.
	 * 
	 */
	public void gravarProduto() {
		
		try {
			outputProduto = new DataOutputStream(new FileOutputStream(
					"Produtos.txt", false));
			JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Gravação", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Escreve toda a lista de produtos no arquivo TXT.
		try {
			for (Produto a : produtos) {
				outputProduto.writeUTF(a.getNome());
				outputProduto.writeUTF(a.getDescricao());
				outputProduto.writeInt(a.getCodigo());
				outputProduto.writeInt(a.getQuantidadeVendida());
				outputProduto.writeDouble(a.getPreco());
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante gravação no arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		/*
		 * Fecha os arquivos Output garcom,caso ocorra algum erro uma mensagem será retornada.
		 */
		try {
			outputProduto.flush();
			outputProduto.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo - Durante Gravação!!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

}
