/*
 * EmployeeManagementSystem.java
 * 
 * Copyrights (C) IdeasIT.
 */

import com.ideas2it.employeemanagement.view.EmployeeView;

/*
 * This class create an object for the view class and calls the showoption()
 * method, which inturn shows the available options in the main menu of the 
 * employee management system. 
 * We can create, update, view and delete a employee
 * detail using the options shown in the main menu.
 * 
 * Copyrights (C) IdeasIT
 */
public class EmployeeManagementSystem {

    /**
     * main() method calls the EmployeeView class, which shows the main menu.
     *
     */
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.showOptions(); 
    }
}
