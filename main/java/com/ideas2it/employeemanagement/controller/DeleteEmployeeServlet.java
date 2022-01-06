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

/**
 * Servlet implementation class DeleteEmployeeServlet
 */
@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idToDelete = 0;
        boolean isRecordDeleted = false;
		EmployeeVO employeeVO = null;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        	idToDelete = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(idToDelete);
        	if (null != employeeVO) {
        		isRecordDeleted = employeeService.deleteOneEmployee(employeeVO);
        	}
        	request.setAttribute("isRecordDeleted", isRecordDeleted);
        	request.setAttribute("returnedEmployee", employeeVO);
        	request.getRequestDispatcher("Deleted.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
}
