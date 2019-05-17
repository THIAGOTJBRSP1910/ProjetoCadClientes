package com.procadproduto.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.procadproduto.dominio.Produto;

/**
 * <b>CRUDProduto</b><br>
 * Essa classe permite manipular as informaçoes do produto. Aqui voce
 * encontrará os seguintes comandos:
 * <ul>
 *  <li>Cadastro</li>
 *  <li>Pesquisar por nome e por id</li>
 *  <li>Atualizar</li>
 *  <li>Deletar</li>
 *  </ul>
 * @author thiago.jbezerra
 *
 */

public class CRUDProduto {
	
	private Connection con = null;//Estabelecer a comunicação com o banco de dados
	private ResultSet rs = null;//Guardar os retornos dos selects no banco de dados
	private PreparedStatement pst = null;//Executa as consultas de SQL
	
	
		public String cadastrar(Produto produto) {
		
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");//Realiza a conexão do driver
			
			String consulta = "INSERT INTO tbproduto(nome,descricao,fabricante,quantidade,preco)values(?,?,?,?)";//Trazer o resultado da tabela
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.setString(3, produto.getFabricante());
			pst.setInt(4, produto.getQuantidade());
			pst.setDouble(5, produto.getPreco());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Cadastro realizo com sucesso";
			else
				msg = "Não foi possivel cadastrar";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar cadastrar: "+ex.getMessage();//mostrar mensagem de erro
		}
		catch(Exception e) {
			msg = "Erro inesperado:"+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
		}
		
		return msg;
		
	}

		public String atualizar(Produto produto) {
			String msg = "";
	
	//Criação dos objetos para a conexao com o banco de dados
	try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");//Realiza a conexão do driver
		
		String consulta = "UPDATE tbproduto SET nome=?,descricao=?,fabricante=?,idade=? WHERE id=?";//Trazer o resultado da tabela
		
		pst = con.prepareStatement(consulta);
		
		pst.setString(1, produto.getNome());
		pst.setString(2, produto.getDescricao());
		pst.setString(3, produto.getFabricante());
		pst.setInt(4, produto.getId());//terminar daqui TJ
		pst.setInt(5, produto.getId());
		
		int r = pst.executeUpdate();
		
		
		if(r > 0)
			msg = "Atualizado com sucesso";
		else
			msg = "Não foi possivel atualizar";
		
	}
	catch(SQLException ex) {
		msg = "Erro ao tentar atualizar: "+ex.getMessage();//mostrar mensagem de erro
	}
	catch(Exception e) {
		msg = "Erro inesperado:"+e.getMessage();
	}
	finally {
		try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
	}
	
	
	return msg;
}
		
}
