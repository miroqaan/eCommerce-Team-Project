package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PocketDtao {

	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	String dbName="db";
	String tableName="pocket";
	String id;
	String pocketIm[];
	String productId[];
	String productCount[];
	String contents;
	
	public PocketDtao(String id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.id=id;
		
	}
	
	public void connect() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=UTF-8&serverTimezone=UTC","root","1111");
		st=con.createStatement();
	}
	public void setPocket() throws SQLException {
		rs=st.executeQuery("select poproducts from "+tableName+" where id='"+id+"'");
		boolean a=rs.next();
		contents=rs.getString("poproducts");
			if(!rs.getString("poproducts").equals("null")){
				pocketIm=rs.getString("poproducts").split("#");
				productId=new String[pocketIm.length];
				productCount=new String[pocketIm.length];
				for(int i=0;i<pocketIm.length;i++) {
					String po[]=pocketIm[i].split("@");
					productId[i]=po[0];
					productCount[i]=po[1];
				}
		     }else {
				productId=new String[1];
				productCount=new String[1];
				productId[0]="null";
				productCount[0]="null";
			}
	}
	public String[] getProductId(){
		return productId;	
	
	}
	public String[] getProductCount(){
		return productCount;	
		
	}
	public String getContents() {
		return contents;
	}
	public void updatePocketIm(String PocketIm) throws SQLException {
		st.execute("UPDATE "+tableName+" SET poproducts = '"+PocketIm+"' WHERE id = '"+id+"'");
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
