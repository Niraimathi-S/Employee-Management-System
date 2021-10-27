/*
 * Copyrights (C) IdeasIT.
 */

import com.ideas2it.employeemanagement.view.EmployeeView;

/**
 * Shows the available options in the main menu of the 
 * employee management system. 
 *
 * We can create, update, view and delete a employee
 * detail using the options shown in the main menu.
 * 
 */
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.showMainMenu(); 
    }
}
