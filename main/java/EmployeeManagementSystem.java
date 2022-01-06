/*
 * Copyrights (C) IdeasIT.
 */
import java.util.Scanner;

import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.employeemanagement.view.ProjectView;

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
        ProjectView projectView = new ProjectView();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        StringBuilder menu = new StringBuilder("EMPLOYEE-PROJECT MANAGEMENT")
            .append(" SYSTEM\n--------------------------\n1.Employee management ") 
            .append("system\n2.Project management system\n3.Exit\nPlease  ")
            .append("choose an option\nYour choice: ");
        do {
            System.out.print(menu);
            choice = employeeView.getChoice(3);
            switch (choice) {
                case 1: employeeView.showMainMenu();
                        break;
                case 2: projectView.showMainMenu();
                        break;
                case 3: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
            System.out.println("-----------------------");   
        } while (3 != choice);
    }
}
