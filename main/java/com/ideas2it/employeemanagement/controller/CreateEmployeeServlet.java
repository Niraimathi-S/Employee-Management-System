package com.ideas2it.employeemanagement.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

@WebServlet(urlPatterns = { "/CreateEmployeeServlet" })
public class CreateEmployeeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		AddressDTO addressDTO = null;
		EmployeeVO employeeVO = new EmployeeVO(req.getParameter("name"),req.getParameter("email"),Long.parseLong(req.getParameter("mobile_number")),
				LocalDate.parse(req.getParameter("date_of_birth")), Float.parseFloat(req.getParameter("salary")));
        addressDTO = new AddressDTO(req.getParameter("door_number"),req.getParameter("street"),
        		req.getParameter("city"),req.getParameter("state"),req.getParameter("country"),Integer.parseInt(req.getParameter("pincode")));  
        Set<AddressDTO> addressList = new HashSet<AddressDTO>();
        addressList.add(addressDTO);
        employeeVO.setaddressesDTO(addressList);
        EmployeeService employeeService = new EmployeeServiceImplementation();
        EmployeeVO returnedEmployee = null;
        try {
        	returnedEmployee = employeeService.createEmployee(employeeVO);
        	req.setAttribute("returnedEmployee", returnedEmployee);
        	req.getRequestDispatcher("createEmployee.jsp").forward(req, res);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
}
