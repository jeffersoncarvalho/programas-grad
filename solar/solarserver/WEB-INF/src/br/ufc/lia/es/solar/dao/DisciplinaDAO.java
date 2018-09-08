package br.ufc.lia.es.solar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.ufc.lia.es.solar.model.DisciplinaModel;

public class DisciplinaDAO extends DAO{

	public DisciplinaDAO(DataSource ds) {
		super(ds);
		
	}
	
	public DisciplinaModel retrieve (String disciplina_codigo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DisciplinaModel disciplinaModel = new DisciplinaModel();
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT nome, horario, professor_login, descricao" +
	      			   " FROM DISCIPLINA_DIS "+
	      			   "WHERE codigo = '" + disciplina_codigo +"'";
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	        disciplinaModel.setNome( rs.getString(1));
	        disciplinaModel.setHorario(rs.getString(2));
	        disciplinaModel.setProfessor_login(rs.getString(3));
	        disciplinaModel.setDescricao(rs.getString(4));
	        disciplinaModel.setCodigo(disciplina_codigo);
	        return disciplinaModel;
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
	        return null;
	}
	
	public void insert(DisciplinaModel disciplinaModel) throws SQLException {
	    String sql;
	    sql = "INSERT INTO DISCIPLINA_DIS (codigo, nome, horario, professor_login, descricao) VALUES (?, ?, ?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, disciplinaModel.getCodigo());
	      pstmt.setString(2, disciplinaModel.getNome());
	      pstmt.setString(3, disciplinaModel.getHorario());
	      pstmt.setString(4, disciplinaModel.getProfessor_login());
	      pstmt.setString(5, disciplinaModel.getDescricao());
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
	
	public List list( ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<DisciplinaModel> list = new ArrayList<DisciplinaModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT codigo, nome" +
	      			   " FROM DISCIPLINA_DIS ";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	DisciplinaModel disciplinaModel = new DisciplinaModel();
	    	disciplinaModel.setCodigo(rs.getString(1));
	    	disciplinaModel.setNome(rs.getString(2));
	        list.add(disciplinaModel);
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
}
