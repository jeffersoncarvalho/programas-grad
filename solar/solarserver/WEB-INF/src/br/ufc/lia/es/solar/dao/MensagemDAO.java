package br.ufc.lia.es.solar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.ufc.lia.es.solar.model.MensagemModel;

public class MensagemDAO extends DAO{

	public MensagemDAO(DataSource ds) {
		super(ds);
		
	}
	
	public List listByDisciplinaCodigo(String disciplinaCodigo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MensagemModel> list = new ArrayList<MensagemModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT id, disciplina_codigo, autor_login, autor_nome, conteudo, data " +
	      			   " FROM MENSAGEM_MSG " +
	      			   " WHERE disciplina_codigo = '" + disciplinaCodigo +"'";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	MensagemModel msgModel = new MensagemModel();
	    	msgModel.setId(rs.getInt(1));
	    	msgModel.setDisciplinaCodigo(disciplinaCodigo);
	    	msgModel.setAutorLogin(rs.getString(3));
	    	msgModel.setAutorNome(rs.getString(4));
	    	msgModel.setConteudo(rs.getString(5));
	    	msgModel.setData(rs.getString(6));
	        list.add(msgModel);
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      rollback(conn);
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
	        return list;
	  }
	
	public void insert(MensagemModel mensagem) throws SQLException {
	    String sql;
	    sql = "INSERT INTO MENSAGEM_MSG (disciplina_codigo, autor_login, autor_nome, conteudo, data) VALUES (?, ?, ?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, mensagem.getDisciplinaCodigo());
	      pstmt.setString(2, mensagem.getAutorLogin());
	      pstmt.setString(3, mensagem.getAutorNome());
	      pstmt.setString(4, mensagem.getConteudo());
	      pstmt.setString(5, mensagem.getData());
	      pstmt.executeUpdate();
	      pstmt.close();
	      conn.commit();
	    } catch (SQLException sqle) {
	       
	      close(pstmt);
	      rollback(conn);
	      sqle.printStackTrace();
	      throw sqle;
	    } finally {
	    	close(conn);
	    }
	}
}