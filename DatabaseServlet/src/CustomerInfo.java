

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Properties;
/**
 * Servlet implementation class CustomerInfo
 */
@WebServlet("/CustomerInfo")
public class CustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String message; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");

		 try{
			
       	 String url = "jdbc:oracle:thin:testuser/password@localhost"; 
       	 Class.forName("oracle.jdbc.driver.OracleDriver");
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	        Connection conn = DriverManager.getConnection(url,props);
	        //creating connection to Oracle database using JDBC              

              PreparedStatement ps=conn.prepareStatement("select * from demo_customers where customer_id=2 ");
             
              ResultSet rs=ps.executeQuery();                 

              /* Printing column names */

             // ResultSetMetaData rsmd=rs.getMetaData();

              while(rs.next())

                 {

                    message=rs.getString("cust_first_name") + rs.getString("cust_last_name") ;  

              }

             // System.out.println("message="+message);
             
              request.setAttribute("message", message);
              getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);

       }catch (Exception e2)

         {

           System.out.println("No connection");

         }


	}


	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
}
}