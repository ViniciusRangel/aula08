package com.eib.agenda.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import com.eib.agenda.modelo.vo.Contato;

public class ContatoDAO extends AbstractDAO<Contato> {
	private static final String SQL_INSERT = "insert into contato (nome,endereco,email,tel) values(?,?,?,?);";
	private static final String SQL_DELETE = "delete from contato where id = ? ;";
	private static final String SQL_UPDATE = "update contato set nome = ? ,endereco = ?,email =? ,tel = ? where id = ?;";
	private static final String SQL_SELECT = "select * from contato where id = ? ;";
	private static final String SQL_SELECTALL = "select * from contato;";

	@Override
	public int insert(Contato arg1) {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER,SQL_PASS);

			stm = conn.prepareStatement(SQL_INSERT);
			stm.setString(1, arg1.getNome());
			stm.setString(2, arg1.getEndereco());
			stm.setString(3, arg1.getEmail());
			stm.setString(4, arg1.getTelefone());

			r = stm.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			try {
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}

	@Override
	public int update(Contato arg1) {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER,SQL_PASS);

			stm = conn.prepareStatement(SQL_UPDATE);
			stm.setString(1, arg1.getNome());
			stm.setString(2, arg1.getEndereco());
			stm.setString(3, arg1.getEmail());
			stm.setString(4, arg1.getTelefone());
			stm.setInt(5, arg1.getId());

			r = stm.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			try {
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}

	@Override
	public int delete(Contato arg1) {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER,SQL_PASS);

			stm = conn.prepareStatement(SQL_DELETE);
			stm.setInt(1, arg1.getId());
			
			r = stm.executeUpdate();
	
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			try {
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}

	@Override
	public Contato select(int id) {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Contato c = new Contato();
		
		try {
			Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER,SQL_PASS);

			stm = conn.prepareStatement(SQL_SELECT);
			stm.setInt(1, id);
		
			rs = stm.executeQuery();
			
			if(rs.next()){
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("tel"));
				c.setEmail(rs.getString("email"));
			}
			
			
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}

	@Override
	public Collection<Contato> select() {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		Collection<Contato> list = new ArrayList<Contato>();
	
		try {
			Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER,SQL_PASS);

			stm = conn.prepareStatement(SQL_SELECTALL);
		
		
			rs = stm.executeQuery();
			
			while(rs.next()){
				Contato c = new Contato();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("tel"));
				c.setEmail(rs.getString("email"));
				
				list.add(c);
			}
			
			
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				stm.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
