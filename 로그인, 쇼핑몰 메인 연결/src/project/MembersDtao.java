package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MembersDtao {

	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	String dbName="db";
	String tableName="members";
		
	public MembersDtao() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}
	public void connect() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=UTF-8&serverTimezone=UTC","root","1111");
		st=con.createStatement();	
	}
	public String getName(String id) throws SQLException {
		rs=st.executeQuery("select * from "+tableName+" where id='"+id+"'");
		rs.next();
		return rs.getString("name");
	}
	public void close() throws SQLException {
		if(rs!=null)
		rs.close();
		if(st!=null)
		st.close();
		if(con!=null)
		con.close();
	}
	
	
	
	
}
