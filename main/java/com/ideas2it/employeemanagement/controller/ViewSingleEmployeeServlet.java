package com.ideas2it.employeemanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

@WebServlet(urlPatterns = { "/ViewSingleEmployeeServlet" })
public class ViewSingleEmployeeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
		int employeeId = 0;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	request.setAttribute("returnedEmployee", employeeVO);
        	request.getRequestDispatcher("SingleEmployeeDisplay.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}

}