package com.fujitsu.loginReg.Controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


import com.fujitsu.loginAndRegister.DAO.UserRegistraionDAO;
import com.fujitsu.loginAndRegister.model.User;
/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRegistraionDAO dao;


		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int phone=Integer.parseInt(request.getParameter("phone"));
		String Address=request.getParameter("Address");


		//generating userid Randomly
		Random random=new Random();
		int id=random.nextInt(999);

		User newUser=new User(id,username,password,phone,Address);
		//System.out.println("New User"+newUser);

		//Save user in database
		dao=new UserRegistraionDAO();
		boolean result = dao.registerUser(newUser);

		if(result) {
			System.out.println("User registered Succesfully");
			response.sendRedirect("welcome.jsp");
		}
		else {
			System.err.println("Internal server error");
			response.sendRedirect("error.jsp");
		}
	}

}
