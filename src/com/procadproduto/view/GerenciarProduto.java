package com.procadproduto.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.procadproduto.dominio.Produto;
import com.procadproduto.persistencia.CRUDProduto;

public class GerenciarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblDescrição;
	private JTextField txtDescricao;
	private JLabel lblFabricante;
	private JTextField txtFabricante;
	private JLabel lblQuantidade;
	private JTextField txtQuantidade;
	private JLabel lblPreco;
	private JTextField txtPreco;
	private Produto produto;
	private CRUDProduto crud;
	private JScrollPane scrollPane;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarProduto frame = new GerenciarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarProduto() {
		setTitle("GerenciarProdutos");
		
		produto = new Produto();
		crud = new CRUDProduto();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 11, 48, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(10, 30, 96, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		lblNome = new JLabel("Nome Do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 60, 118, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 81, 350, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblDescrição = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrição.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrição.setBounds(10, 112, 75, 14);
		contentPane.add(lblDescrição);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 131, 350, 60);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFabricante.setBounds(10, 202, 75, 14);
		contentPane.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setBounds(10, 219, 350, 20);
		contentPane.add(txtFabricante);
		txtFabricante.setColumns(10);
		
		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(10, 250, 83, 14);
		contentPane.add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(10, 272, 149, 20);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreco.setBounds(174, 250, 48, 14);
		contentPane.add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(174, 272, 186, 20);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Passar os dados do formulario para o objeto produto
				produto.setNome(txtNome.getText());
				produto.setDescricao(txtDescricao.getText());
				produto.setFabricante(txtFabricante.getText());
				produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				String retorno = crud.cadastrar(produto);
				JOptionPane.showMessageDialog(null, retorno);
				txtNome.setText("");
				txtDescricao.setText("");
				txtFabricante.setText("");
				txtQuantidade.setText("");
				txtPreco.setText("");
				
			}
		});
		btnCadastrar.setBounds(10, 303, 89, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do Produto");
				produto.setNome(txtNome.getText());
				produto.setDescricao(txtDescricao.getText());
				produto.setFabricante(txtFabricante.getText());
				produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				produto.setId(Integer.parseInt(id));
				String retorno = crud.cadastrar(produto);
				JOptionPane.showMessageDialog(null, retorno);
				txtNome.setText("");
				txtDescricao.setText("");
				txtFabricante.setText("");
				txtQuantidade.setText("");
				txtPreco.setText("");
				id="0";
				
			}
		});
		btnAtualizar.setBounds(109, 303, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do produto para apagar");
				
				produto.setId(Integer.parseInt(id));
				
				JOptionPane.showMessageDialog(null, crud.deletar(produto));
				
			}
		});
		btnDeletar.setBounds(208, 303, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setBounds(304, 303, 89, 23);
		contentPane.add(btnPesquisar);
		
		carregarDados();
	}
		private void carregarDados() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 335, 383, 113);
		contentPane.add(scrollPane);
		
		String[] colunas = {"id","nome","descricao","fabricante","quantidade","preco"};
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(colunas,0);
		
		for(Produto p : crud.PesquisarTodos()) {
			modelo.addRow(new Object[] {
					p.getId(),
					p.getNome(),
					p.getDescricao(),
					p.getFabricante(),
					p.getQuantidade(),
					p.getPreco()
			});
		}
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		
	}
}
