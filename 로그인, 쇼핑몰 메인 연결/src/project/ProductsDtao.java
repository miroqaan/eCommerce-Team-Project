package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDtao {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	String dbName="picdata";
	String tableName="pic1";
	String id[];
	String name[];
	String price[];
	String category[];
	String imgFile[];

	
	public ProductsDtao() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}
	
	public void connect() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=UTF-8&serverTimezone=UTC","root","mysql");
		st=con.createStatement();
	}
	public void searchCandK(String category,String keyword) throws SQLException{
		String url="select * from "+tableName+" where category='"+category+"' and name like '%"+keyword+"%'";
		if(category.equals("ÀüÃ¼")) {
			url="select * from "+tableName+" where name like '%"+keyword+"%'";
		}
		rs=st.executeQuery(url);
		boolean a=rs.next();
		int i=0;
		while(a) {
			i++;
			if(rs.next()==false) {
				break;
			}
		}
		id=new String [i];
		name=new String [i];
		price=new String [i];
		this.category=new String [i];
		imgFile=new String [i];
		rs=st.executeQuery(url);
		a=rs.next();
		i=0;
		while(a) {
			id[i]=rs.getString("id");
			name[i]=rs.getString("name");
			price[i]=rs.getString("price");
			this.category[i]=rs.getString("category");
			imgFile[i]=rs.getString("imgFile");
			i++;
			if(rs.next()==false) {
				break;
			}
		}
	}
	public void searchId(String productId) throws SQLException{
		String url="select * from "+tableName+" where id="+productId;
		id=new String [1];
		name=new String [1];
		price=new String [1];
		this.category=new String [1];
		imgFile=new String [1];
		rs=st.executeQuery(url);
		rs.next();
			id[0]=rs.getString("id");
			name[0]=rs.getString("name");
			price[0]=rs.getString("price");
			this.category[0]=rs.getString("category");
			imgFile[0]=rs.getString("imgFile");			
	}

	public String[] getId() {
		return id;
	}

	public String[] getName() {
		return name;
	}

	public String[] getPrice() {
		return price;
	}

	public String[] getCategory() {
		return category;
	}

	public String[] getImgFile() {
		return imgFile;
	}
	public int getLength() {
		return id.length;
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
