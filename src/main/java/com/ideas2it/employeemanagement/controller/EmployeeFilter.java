/*
 * Copyright (c) 2021 Ideas2it Technologies, Inc. All Rights Reserved.
 */

package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

/**
 * This layer interacts with database and performs the duplicate mobile,emailId,DateofBirth 
 * validation and forwards to servlet
 * 
 * @version  1.0 12 dec 2021
 * @author  Vasanthan A
 */
public class EmployeeFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (null != request.getParameter("mobile_number") && null != request.getParameter("email")) {
			Long contact = Long.parseLong(request.getParameter("mobile_number"));
			String email = request.getParameter("email");
			EmployeeService employeeService = new EmployeeServiceImplementation();
			try {
				if (request.getParameter("type").equals("create")) {
					boolean isMobileNumberDublicate = employeeService.checkDuplicateMobileNumber(contact);
					boolean isEmailDublicate =  employeeService.checkDuplicateEmail(email);
					if(isMobileNumberDublicate | isEmailDublicate) {
						AddressDTO addressDTO = null;
						EmployeeVO employeeVO = new EmployeeVO(request.getParameter("name"),request.getParameter("email"),Long.parseLong(request.getParameter("mobile_number")),
								LocalDate.parse(request.getParameter("date_of_birth")), Float.parseFloat(request.getParameter("salary")));
				        addressDTO = new AddressDTO(request.getParameter("door_number"),request.getParameter("street"),
				        		request.getParameter("city"),request.getParameter("state"),request.getParameter("country"),Integer.parseInt(request.getParameter("pincode")));  
				        Set<AddressDTO> addressList = new HashSet<AddressDTO>();
				        addressList.add(addressDTO);
				        employeeVO.setaddressesDTO(addressList);
						request.setAttribute("employeeVO", employeeVO);
						request.setAttribute("addressDTO", addressDTO);
						request.setAttribute("isMobileNumberDublicate", isMobileNumberDublicate);
						request.setAttribute("isEmailDublicate", isEmailDublicate);
						request.getRequestDispatcher("CreateEmployeeDuplicate.jsp").forward(request, response);
					}else {
						chain.doFilter(request, response);
					}
				}else {
					EmployeeVO employeeVO = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
					boolean isEmailDublicate = false;
					boolean isMobileNumberDublicate = false;
					//boolean invalidDate = false;
					//invalidDate =  employeeService.validateDate(LocalDate.parse(dob));
					if (!(employeeVO.getEmail().equals(email))) {
						isEmailDublicate =  employeeService.checkDuplicateEmail(email);
					}
					if (!(employeeVO.getMobileNumber() == contact)){
						isMobileNumberDublicate = employeeService.checkDuplicateMobileNumber(contact);
					}			    
					if((isMobileNumberDublicate | isEmailDublicate) ) {
						EmployeeVO employeeVOToUpdate = new EmployeeVO(request.getParameter("name"),request.getParameter("email"),Long.parseLong(request.getParameter("mobile_number")),
								LocalDate.parse(request.getParameter("date_of_birth")), Float.parseFloat(request.getParameter("salary")));
						request.setAttribute("empid", employeeVO);
						request.setAttribute("employeeVO", employeeVOToUpdate);
						request.setAttribute("isMobileNumberDublicate", isMobileNumberDublicate);
						request.setAttribute("isEmailDublicate", isEmailDublicate);
						//request.setAttribute("dob", invalidDate);
						request.getRequestDispatcher("UpdateEmployeeDuplicate.jsp").forward(request, response);
					}else {
						chain.doFilter(request, response);
					}
				}
	        } catch (EmployeeManagementException exception) {
	            EmployeeManagementLogger.logger.error(exception);
			}
		}else {
			chain.doFilter(request, response);
		}
	}
}