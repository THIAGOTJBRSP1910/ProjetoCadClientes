package com.procadproduto.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		String consulta = "UPDATE tbproduto SET nome=?,descricao=?,fabricante=?,quantidade=?,preco=? WHERE id=?";//Trazer o resultado da tabela
		
		pst = con.prepareStatement(consulta);
		
		pst.setString(1, produto.getNome());
		pst.setString(2, produto.getDescricao());
		pst.setString(3, produto.getFabricante());
		pst.setInt(4, produto.getQuantidade());
		pst.setDouble(5, produto.getPreco());
		pst.setInt(6, produto.getId());
		
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
		public String deletar(Produto produto) {
			String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");//Realiza a conexão do driver
			
			String consulta = "DELETE FROM tbproduto WHERE id=?";//Trazer o resultado da tabela
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, produto.getId());
			
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Deletado com sucesso";
			else
				msg = "Não foi possivel deletar";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar deletar: "+ex.getMessage();//mostrar mensagem de erro
		}
		catch(Exception e) {
			msg = "Erro inesperado:"+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
		}
		
		
		return msg;
	}
		public List<Produto> PesquisarPorNome(String nome) {
			
			List<Produto> lista = new ArrayList<Produto>();
			
			try {
				//carregar o drive de comunicação com o banco de dados
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				
				//Chamar o gerenciador de driver
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");
				
				//Vamos criar a consulta para selecionar os clientes por nome
				String consulta = "Select * from tbproduto where nome=?";
				
				pst = con.prepareStatement(consulta);
				
				pst.setString(1, nome);
				
				//Vamos executar a consulta e guardar o resultado na variavel rs
				rs = pst.executeQuery();
				
				/*
				 * Vamos pegar um cliente por vez que está no rs e adiciona-lo
				 * a lista de clientes para, então retorna-la
				 */
				while(rs.next()) {
					lista.add(new Produto(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5)));
				}//Fim do while
				
			}//Fim do try
			
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {con.close();} catch(Exception e) {e.printStackTrace();}
			}
			
			
			return lista;
		}
		public Produto PesquisarPorId(int id) {
			Produto produto = new Produto();//array é um do lado do outro com"," lista é um embaixo do outro
		
		try {
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");
			
			//Vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbproduto where id=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1,id);
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que está no rs e adiciona-lo
			 * a lista de clientes para, então retorna-la
			 */
			if(rs.next()) {
				produto.setId(rs.getInt(0));
				produto.setNome(rs.getString(1));
				produto.setDescricao(rs.getString(2));
				produto.setFabricante(rs.getString(3));
				produto.setQuantidade(rs.getInt(4));
				produto.setPreco(rs.getDouble(5));
			}
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return produto;
	}
		public List<Produto> PesquisarTodos() {
			List<Produto> lista = new ArrayList<Produto>();
		
		try {
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtodb?serverTimezone=UTC","root","");
			
			//Vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbproduto";
			
			pst = con.prepareStatement(consulta);
			
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que está no rs e adiciona-lo
			 * a lista de clientes para, então retorna-la
			 */
			while(rs.next()) {
				lista.add(new Produto(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5)));
			}//Fim do while
			
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return lista;
	}
}
