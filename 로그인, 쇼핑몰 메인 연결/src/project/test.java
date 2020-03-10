package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String dbName="db";
		String tableName="products";
		String id[];
		String name[];
		String price[];
		String category[];
		String imgFile[];
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=UTF-8&serverTimezone=UTC","root","mysql");
			st=con.createStatement();
			rs=st.executeQuery("select * from products where category='반지'");
			boolean a=rs.next();
			int i=0;
			while(a) {
				i++;
				if(rs.next()==false) {
					break;
				}
			}
		System.out.println(i);
		id=new String[i];
		rs=st.executeQuery("select * from products where category='반지'");
		a=rs.next();
		 i=0;
		while(a) {
			id[i]=rs.getString("id");
			i++;
			if(rs.next()==false) {
				break;
			}
		}
		System.out.println(id[0]);
		System.out.println(id[1]);
	
	}
		
		
	

}
