

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.PocketDtao;

/**
 * Servlet implementation class pocketSave
 */
@WebServlet("/pocketSave")
public class pocketSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pocketSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id=request.getParameter("id");
		String products="";
		PocketDtao pd=null;
		Enumeration pram = request.getParameterNames();
		int i=0;
		pram.nextElement();
		pram.nextElement();
		while(pram.hasMoreElements()){
			
			String name=(String)pram.nextElement();
			if(Integer.parseInt(request.getParameter(name))!=0) {
				if(i!=0){
					products+="#";
				}
			products += name+"@"+request.getParameter(name);
			i++;
			}
			
		}
		if(i==0) {products="null";}
		System.out.println(products);
		try {
			pd=new PocketDtao(id);
			pd.connect();
			pd.updatePocketIm(products);
			pd.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pd.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(request.getParameter("returnUrl"));
			response.sendRedirect(request.getParameter("returnUrl"));
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
