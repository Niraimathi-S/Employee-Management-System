/*
 * EmployeeView.java
 * 
 * Copyrights (C) Ideas2IT
 */
package com.ideas2it.employeemanagement.view;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * View class gets input and view output to the user.
 * 
 * @author Niraimathi S
 * @ version 1.0 12-11-2021
 */
public class EmployeeView {
    private static Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * This method shows the main menu options
     */
    public void showOptions() {
        int choice = 0;
        int choiceToContinue = 0;

        System.out.println();
        StringBuilder menu = new StringBuilder("EMPLOYEE MANAGEMENT SYSTEM\n"); 
        menu.append("--------------------------\n1.Create\n2.Update\n3.View\n"); 
        menu.append("4.Delete\n5.Exit\n\nPlease choose an option\nYour choice");
        do {
            System.out.print(menu);
            choice = getChoice(5);
            switch (choice) {
                case 1:
                    createNewEmployee();
                    break;
                case 2:
                    updateEmployeeDetails();
                    break;
                case 3:
                    viewDetails();
                    break;
                case 4:
                    deleteDetails();
                    break;
                case 5:
                    choiceToContinue = 1;
                    break;
                default:
                    System.out.println("Please enter valid option");
                    break;
            }
            System.out.println("-----------------------");   
        } while (1 != choiceToContinue);
    }

    /**
     * This method creates a new employee 
     * 
     */
    private void createNewEmployee() {
        System.out.println("\nplease enter Employee Details ");
        System.out.print("Employee ID:");
        String employeeIdAsString = scanner.nextLine();
        Integer employeeId = getEmployeeId(employeeIdAsString);
       
        if(!employeeController.checkContainskey(employeeId)) {

            employeeController.createEmployee(employeeId, 
                    new EmployeeVO(employeeId, getName(), getEmail(), 
                                   getContactNumber(), getDateOfBirth(),
                                   getSalary()));
            System.out.println("-----------------------");
        } else {
            System.out.println("There is already a record exist with same ID");
        }
    }

    /**
     * This method get choice for the switch case and validate it
     *
     * @return choice-validated choice for the switch case
     */
    public int getChoice(int noOfChoices) {
        int choice = 0;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            System.out.println("choice must be an integer\n"
                    + "Please Reenter the choice: ");
            return(getChoice(noOfChoices));
        }

        if ((choice <= noOfChoices) && (0 < choice)) {
            return choice;
        } else {
            System.out.println("choice must be between 1 to "+ noOfChoices);
            return (getChoice(noOfChoices));
        }
    }

    /**
     * This method check the validity of the input.
     *
     * @return input-validated Input.
     */
    public String validateInput(String input, String patternToValidate, 
                                StringBuilder errorMessage) { 
        while (!employeeController.validateInput(input, patternToValidate)) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * This method get and check the employee Id's validity.
     *
     * @return employeeId-validated ID of the employee
     */
    private Integer getEmployeeId(String employeeId) {
        String pattern = "[1-9][0-9]{0,4}";
        
        StringBuilder errorMessage = new StringBuilder("Invalid EmployeeID");
        errorMessage.append("!\nEmployee ID must only be numeric with maximum");
        errorMessage.append(" of 5 digits\n\nPlease reenter Employee ID\n");
        errorMessage.append("\nEmployeeID:");
        employeeId = validateInput(employeeId, 
                    pattern, errorMessage);
        return (Integer.parseInt(employeeId));   
    }

    /**
     * This method gets and checks if the given name is valid.
     *
     * @return name-validated name of the employee
     */
    private String getName() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}";
        System.out.print("Name:");
        String name = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid name! \n");
        errorMessage.append("Name shouldn't contain special characters");
        errorMessage.append(",\nnumbers and more than one blankspace\nPlease");
        errorMessage.append(" reenter Employee name\nName:");
        name = validateInput(name, pattern, errorMessage);
        return name;   
    }

    /**
     * This method gets and checks if the employee contact number is valid. 
     *
     * @return contactNumber-validated phone number of the employee
     */
    private long getContactNumber() {
        String pattern = "^[6-9][0-9]{9}";
        System.out.print("ContactNumber:");
        String contactNumber = scanner.nextLine();
        
        StringBuilder errorMessage = new StringBuilder("\nInvalid contact");
        errorMessage.append("Number!!\nShould contain 10 digits");
        errorMessage.append("\nAnd must start with numbers 6 to 9\n");
        errorMessage.append("\nPlease reenter Employee Contact Number\n");
        errorMessage.append("\nContactNumber:");
        contactNumber = validateInput(contactNumber, 
                                      pattern, errorMessage); 

        long contactNumberAsLong = Long.parseLong(contactNumber);
        if (employeeController
            .checkDuplicateContactNumber(contactNumberAsLong)) {
            System.out.println("Number already exist! Give a new one");
            return getContactNumber();  
        }  
        return (contactNumberAsLong); 
    }

    /**
     * This method gets & checks whether the Employee salary is valid or not. 
     *
     * @return salary-validated salary of the employee
     */
    private float getSalary() {
        String pattern = "[0-9]+([\\.][0-9]{0,4})?";
        System.out.print("Salary:");
        String salary = scanner.nextLine();

        StringBuilder errorMessage = new StringBuilder("Invalid Input");
        errorMessage.append("!!\nSalary should not contains 0-4 digits after");
        errorMessage.append(" decimal\n\nPlease reenter salary\n");
        errorMessage.append("\nSalary:");
        salary = validateInput(salary, pattern, errorMessage);
        return (Float.parseFloat(salary));   
    }

    /**
     * This method gets and checks if the Employee Email Id is valid or not. 
     *
     * @return email-validated email Id of the employee
     */
    private String getEmail() {
        String pattern = "[a-zA-Z][\\w&&[^_]]{2,}" 
                + "([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+" 
                + "([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}";

        System.out.print("Email ID:");
        String email = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid email Id");
        errorMessage.append("\nHint:\nEmail must start with letter\n");
        errorMessage.append("Must contain @ and a domain name\n");
        errorMessage.append("No special character immediately before @");
        errorMessage.append("\n\nPlease reenter Email ID\n");
        errorMessage.append("\nEmail ID:");
        email = validateInput(email, pattern, errorMessage);

        if (employeeController.checkDuplicateEmail(email)) {
                System.out.println("This EmailID already exist!Give a new one");
                return getEmail(); 
            }   
        
        return email;   
    }

    /**
     * This method gets and checks if Employee Date of birth is valid or not. 
     *
     * @return dateOfBirth 	validated date of birth of the employee
     */
    private LocalDate getDateOfBirth() {
        String promptStatement = "Date Of Birth(Enter in(dd-MM-yyyy)format):";
        String dateAsString = " ";
        LocalDate dateOfBirth = LocalDate.now();
        LocalDate today = LocalDate.now();

        while (true) {
            System.out.println(promptStatement);
            try {
                dateAsString = scanner.nextLine();
            
                String[] dateArray = dateAsString.split("-");

                String formattedDate  = String.join("-",dateArray[2], 
                                                    dateArray[1], dateArray[0]);

                try {
                    dateOfBirth = LocalDate.parse(formattedDate);
                    if (dateOfBirth.isAfter(today)) {
                        System.out.println("Invalid Date Of birth!!");
                    } else {
                        int age = (int) ChronoUnit.YEARS.between(dateOfBirth, 
                                                             today);
                        if (( age < 18) || (age > 60)) {
                            System.out.println("Employee should be 18-60 " 
                                               + "years old");
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n*not a valid date*\n");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n*not a valid date*\n");
            }
        }
        return dateOfBirth;
    }

    /**
     * This method deletes Employees detail by calling appropriate method
     *
     */
    private void deleteDetails() {
        if (employeeController.checkEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nDELETE MENU\n--------\n");
            menu.append("1.Delete All Employee details");
            menu.append("\n2.Delete single Employee Detail");
            menu.append("\n3.Return to Main menu\n\n");
            menu.append("Please Select one\n\nYour choice :");
            int choice;
            int choiceToRepeat = 0;

            do {
                System.out.print(menu);
                choice = getChoice(3);
 
                switch (choice) {
                    case 1:
                        deleteAllEmployeeDetails(); 
                        break;
                    case 2: 
                        deleteOneEmployeeDetails();
                        break;
                    case 3:
                        choiceToRepeat = 1;
                        break;
                    default:
                        System.out.println("Please enter valid option");
                        break;
                }
                System.out.println("-----------------------");
            } while (0 == choiceToRepeat);
        }
    }

    /**
     * This method deletes all Employees detail 
     *
     */
    private void deleteAllEmployeeDetails() {
        if (!employeeController.checkEmpty()) {
            employeeController.deleteAllEmployee();
            if (employeeController.checkEmpty()) { 
                System.out.println("All Employee details are deleted.");
            }
        } else {
            System.out.println("Sorry. No records Found to delete.");
        }
        System.out.println("-----------------------");
    }

    /**
     * This method deletes one Employees detail 
     *
     */
    private void deleteOneEmployeeDetails() {
        System.out.println("Enter the Employee Id to delete or" 
                           + "\nEnter -1 to return to Delete Menu");
        System.out.print("Employee ID : ");
        String employeeIdAsString = " ";
        try {
            employeeIdAsString = scanner.nextLine();
        
            if ( -1 == Integer.parseInt(employeeIdAsString)) {
                return;
            } else {
                int idToDelete = getEmployeeId(employeeIdAsString);

                if (employeeController.checkContainskey(idToDelete)) {
                    employeeController.deleteOneEmployee(idToDelete);
                    if (!employeeController.checkContainskey(idToDelete)) {
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Entry Not deleted");
                    }
                } else {
                    System.out.println("No such entry exist ");
                    deleteOneEmployeeDetails();
                }
                System.out.println("-----------------------\n");
            }
        } catch (Exception exception) {
            System.out.println("Employee Id must be an integer");
            deleteOneEmployeeDetails();
        }
    }

    /**
     * This method update Employees detail. 
     *
     */
    private void updateEmployeeDetails() {
        if (employeeController.checkEmpty()) {
            System.out.println("No records found!");
        } else {
            System.out.println("Please enter Employee Id\n" 
                                + " or Press -1 to return to main menu");
            String employeeIdAsString = " ";
            try {
                employeeIdAsString = scanner.nextLine(); 
                if ( -1 == Integer.parseInt(employeeIdAsString)) {
                    return;
                } else {
                    Integer employeeId = getEmployeeId(employeeIdAsString);
                    if (employeeController.checkContainskey(employeeId)) {
                        StringBuilder menu = new StringBuilder("\nUPDATE MENU");
                        menu.append("\n---------\n1.Update All fields of an " );
                        menu.append("Employee\n2.Update single field of an");
                        menu.append("Employee \n3.Return to Main menu");
                        menu.append("\n\nplease Select one\n\nYour choice :");
                        int choice;
                        int choiceToRepeat = 0;
                        do {
                            System.out.print(menu);
                            choice = getChoice(3);
 
                            switch (choice) {
                                case 1:
                                    updateAllFields(employeeId);
                                    break;
                                case 2:
                                    updateSingleField(employeeId);
                                    break;
                                case 3:
                                    choiceToRepeat = 1;
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                            System.out.println("-----------------------");
                        } while (0 == choiceToRepeat);
                    } else {
                        System.out.println("No such record found to update");
                        updateEmployeeDetails();
                    }
                }
            } catch (Exception exception) {
                System.out.println("Employee Id must be an integer");
                updateEmployeeDetails();
            }
        }
    }

    /**
     * This method update all detail of an Employee at once.
     *
     */
    private void updateAllFields(int employeeId) {
            employeeController.updateAllFields(employeeId, 
                    new EmployeeVO(employeeId, getName(), getEmail(), 
                                   getContactNumber(), getDateOfBirth(),
                                   getSalary()));
        System.out.println("-----------------------");
    }

    /**
     * This method update separate fields in Employees details.
     *
     * @param employeeId-employeeId to upddate the details
     */
    private void updateSingleField(int employeeId) {
        StringBuilder menu = new StringBuilder("\nUPDATE SINGLE FIELD MENU\n"); 
        menu.append("----------------------\n1.Update Name\n2.Update Email ID"); 
        menu.append("\n3.Update Contact number\n4.Update Date of Birth");
        menu.append("\n5.Update Salary\n6.Return to Update Menu\n\n");
        menu.append("please Select one\n\nYour choice :");
        int choice;
        int choiceToRepeat = 0;
        do {
            System.out.print(menu);
            choice = getChoice(6);
 
            switch (choice) {
                case 1: updateName(employeeId);
                    break;
                case 2: updateEmail(employeeId);
                    break;
                case 3: updateContactNumber(employeeId);
                    break;
                case 4: updateDateOfBirth(employeeId);
                    break;
                case 5: updateSalary(employeeId);
                    break;
                case 6: choiceToRepeat = 1;
                    break;
                default: System.out.println("Please enter valid option");
                    break;
            }
            System.out.println("-----------------------");
        } while (0 == choiceToRepeat);
    }

    /**
     * This method update name of the Employee.
     *
     * @param employeeId-employeeId to update the Employee name.
     */
    private void updateName(int employeeId) {
        employeeController.updateName(employeeId,getName());
        System.out.print("-----------------------");
    }

    /**
     * This method update Email ID of the Employee.
     *
     * @param employeeId-employeeId to update the email of given employeeID.
     */
    private void updateEmail(int employeeId) {
        employeeController.updateEmail(employeeId, getEmail());
        System.out.print("-----------------------");
    }

    /**
     * This method update contact number of the Employee.
     *
     * @param employeeId-employeeId to update the contactnumber
     */
    private void updateContactNumber(int employeeId) {
        employeeController.updateContactNumber(employeeId, getContactNumber());
        System.out.print("-----------------------");
    }

    /**
     * This method update Date Of Birth of the Employee.
     *
     * @param employeeId-employeeId to update the Date of birth 
     */
    private void updateDateOfBirth(int employeeId) {
        employeeController.updateDateOfBirth(employeeId, getDateOfBirth());
        System.out.print("-----------------------");
    }

    /**
     * This method update Salary of the Employee.
     *
     * @param employeeId-employeeId to update the salary of given employeeID
     */
    private void updateSalary(int employeeId) {
        employeeController.updateSalary(employeeId, getSalary());
        System.out.print("-----------------------");
    }

    /**
     * This method display Employees details by calling appropriate methods.
     *
     */
    private void viewDetails() {
        if (employeeController.checkEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nVIEW MENU\n----------\n");
            menu.append("1.View All Employee details\n2.View single Employee"); 
            menu.append("detail\n3.Return to Main menu");
            menu.append("\n\nplease Select one\n\nYour choice :");
                       
            int choice;
            int choiceToRepeat = 0;
            do {
                System.out.print(menu);
                choice = getChoice(3);
 
                switch (choice) {
                    case 1:
                        viewAllEmployeeDetails();
                        break;
                    case 2:
                        viewOneEmployeeDetails();
                        break;
                    case 3:
                        choiceToRepeat = 1;
                        break;
                    default:
                        System.out.println("Please enter valid option");
                        break;
                }
                System.out.println("-----------------------");
            } while (0 == choiceToRepeat);
        }
    }

    /**
     * This method is to view single Employee details
     *
     */
    private void viewOneEmployeeDetails() {
        System.out.println("Please enter Employee Id\n" 
                                + " or Press -1 to return to main menu");
        String employeeIdAsString = " ";
        try {
            employeeIdAsString = scanner.nextLine(); 
            if ( -1 == Integer.parseInt(employeeIdAsString)) {
                return;
            } else {
                int employeeId = getEmployeeId(employeeIdAsString);
                if (employeeController.checkContainskey(employeeId)) {
                    //Employee value = Employees.get(employeeid);
                    EmployeeVO value = employeeController
                                       .viewOneEmployee(employeeId);
                    System.out.println("Employee ID\t:" + employeeId + "\nName" 
                            + "\t\t:"+ value.getName() + "\nEmail\t\t:"   
                            + value.getEmail() + "\nContact number\t:"  
                            + value.getContactNumber() + "\nDate Of birth\t:"  
                            + value.getDateOfBirth() + "\nSalary\t\t:" 
                            + value.getSalary());
                    System.out.println("-----------------------");
                } else {
                    System.out.println("\nNo such entry exist ");
                    viewOneEmployeeDetails();
                    System.out.println("-----------------------");
                }
            }
        } catch (Exception exception) {
            System.out.println("Employee Id must be an integer" + exception);
            viewOneEmployeeDetails();
        }
    }

    /**
     * This method is to view all Employee details
     *
     */
    private void viewAllEmployeeDetails(){
        if (employeeController.checkEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            List<EmployeeVO> employeeDetails 
                    = new ArrayList<>(employeeController.viewAllEmployee());
            for (EmployeeVO value:employeeDetails){
               
                System.out.println("Employee ID\t:" + value.getEmployeeId()  
                        + "\nName\t\t:" + value.getName() + "\nEmail\t\t:"  
                        + value.getEmail()  + "\nContact number\t:"  
                        + value.getContactNumber() + "\nDate Of birth\t:" 
                        + value.getDateOfBirth() + "\nSalary\t\t:" 
                        + value.getSalary() + "\n");
            } 
            System.out.println("-----------------------");   
        }
    }
}
