/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.model.EmployeeVO;

/**
 * EmployeeView class deals with user interaction and is responsible for
 * displaying the output to the user.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * Shows the main menu to the user.
     */
    public void showMainMenu() {
        int choice = 0;
        StringBuilder menu = new StringBuilder("EMPLOYEE MANAGEMENT SYSTEM\n")
            .append("--------------------------\n1.Create\n2.Update\n3.View\n") 
            .append("4.Delete\n5.Exit\n\nPlease choose an option\nYour choice");
        do {
            System.out.print(menu);
            choice = getChoice(5);
            switch (choice) {
                case 1: createNewEmployee();
                        break;
                case 2: updateEmployeeDetails();
                        break;
                case 3: viewDetails();
                        break;
                case 4: deleteDetails();
                        break;
                case 5: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
            System.out.println("-----------------------");   
        } while (5 != choice);
    }

    /**
     * Creates a new employee with the validated inputs.
     * 
     */
    private void createNewEmployee() {
        System.out.println("\nplease enter Employee Details ");
        System.out.print("Employee ID:");
        String employeeIdAsString = scanner.nextLine();
        Integer employeeId = getEmployeeId(employeeIdAsString);
       
        if(!employeeController.isEmployeeExist(employeeId)) {
            System.out.println(employeeController.createEmployee(employeeId, 
                    new EmployeeVO(employeeId, getName(), getEmail(), 
                                   getMobileNumber(), getDateOfBirth(),
                                   getSalary())) ? "Employee Created"
                                   : "Employee not created");
        } else {
            System.out.println("There is already a record exist with same ID");
        }
    }

    /**
     * Gets total number of choice and validate it.
     *
     * @param noOfChoices-total number of choices
     * @return choice-validated choice.
     */
    public int getChoice(int noOfChoices) {
        int choice = 0;
        boolean isChoiceValid = true;
        while (isChoiceValid) {      
            try {
                choice = Integer.parseInt(scanner.nextLine());
                isChoiceValid = false;
            } catch (Exception exception) {
                System.out.println("choice must be an integer\n"
                        + "Please Reenter the choice: ");
                isChoiceValid = true;
            }
        }
        return choice;
    }

    /**
     * Checks the validity of the input.
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @param errorMessage- error message to display.
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
     * Gets and check the employee Id's validity.
     *
     * @param employeeId-employee id to validate.
     * @return employeeId-validated ID of the employee
     */
    private Integer getEmployeeId(String employeeId) {
        String pattern = "[1-9][0-9]{0,4}";
        
        StringBuilder errorMessage = new StringBuilder("Invalid EmployeeID")
            .append("!\nEmployee ID must only be numeric with maximum")
            .append(" of 5 digits\n\nPlease reenter Employee ID\n")
            .append("\nEmployeeID:");
        employeeId = validateInput(employeeId, pattern, errorMessage);
        return (Integer.parseInt(employeeId));   
    }

    /**
     * Gets and checks if the given name is valid.
     *
     * @return name-validated name of the employee
     */
    private String getName() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}";
        System.out.print("Name:");
        String name = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid name! \n")
            .append("Name shouldn't contain special characters")
            .append(",\nnumbers and more than one blankspace\nPlease")
            .append(" reenter Employee name\nName:");
        return validateInput(name, pattern, errorMessage);
    }

    /**
     * Gets and checks if the employee mobile number given by user is valid. 
     *
     * @return mobileNumber-validated phone number of the employee
     */
    private long getMobileNumber() {
        String pattern = "^[6-9][0-9]{9}";
        boolean isMobileNumberDublicate = true;
        StringBuilder errorMessage = new StringBuilder("\nInvalid mobile")
            .append("Number!!\nShould contain 10 digits")
            .append("\nAnd must start with numbers 6 to 9\n")
            .append("\nPlease reenter Employee mobile Number\n")
            .append("\nMobile Number:");
        long mobileNumberAsLong = 0;
        String mobileNumber = "";

        while(isMobileNumberDublicate) {
            System.out.print("Mobile Number:");
            mobileNumber = scanner.nextLine();
            mobileNumber = validateInput(mobileNumber, 
                                         pattern, errorMessage); 

            mobileNumberAsLong = Long.parseLong(mobileNumber);
        
            if (employeeController
                    .checkDuplicateMobileNumber(mobileNumberAsLong)) {
                System.out.println("Number already exist! Give a new one");
                isMobileNumberDublicate = true;
            } else {
                isMobileNumberDublicate = false;
            }
        }
        return (mobileNumberAsLong); 
    }

    /**
     * Gets & checks whether the Employee salary is valid or not. 
     *
     * @return salary-validated salary of the employee
     */
    private float getSalary() {
        String pattern = "[0-9]+([\\.][0-9]{0,4})?";
        System.out.print("Salary:");
        String salary = scanner.nextLine();

        StringBuilder errorMessage = new StringBuilder("Invalid Input")
            .append("!!\nSalary should not contains 0-4 digits after")
            .append(" decimal\n\nPlease reenter salary\n")
            .append("\nSalary:");
        salary = validateInput(salary, pattern, errorMessage);
        return (Float.parseFloat(salary));   
    }

    /**
     * Gets and checks if the Employee Email Id is valid or not. 
     *
     * @return email-validated email Id of the employee
     */
    private String getEmail() {
        String pattern = "[a-zA-Z][\\w&&[^_]]{2,}" 
                + "([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+" 
                + "([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}";
        StringBuilder errorMessage = new StringBuilder("Invalid email Id")
            .append("\nHint:\nEmail must start with letter\n")
            .append("Must contain @ and a domain name\n")
            .append("No special character immediately before @")
            .append("\n\nPlease reenter Email ID\n")
            .append("\nEmail ID:");
        String email = "";
        boolean isEmailDublicate = true;
        while(isEmailDublicate) {
        System.out.print("Email ID:");
        email = scanner.nextLine();
        email = validateInput(email, pattern, errorMessage);
        if (employeeController.checkDuplicateEmail(email)) {
                System.out.println("This EmailID already exist!Give a new one");
                isEmailDublicate = true;
            } else {
                isEmailDublicate = false;  
            }
        }
        return email;   
    }

    /**
     * Gets and checks if Employee Date of birth is valid or not. 
     *
     * @return dateOfBirth 	validated date of birth of the employee
     */
    private LocalDate getDateOfBirth() {
        String promptStatement = "Date Of Birth(Enter in(dd-MM-yyyy)format):";
        String dateAsString = " ";
        LocalDate dateOfBirth = LocalDate.now();
        LocalDate today = LocalDate.now();
        boolean isValid = true;
        while (isValid) {
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
                        if (( 18 > age ) || ( 60 < age )) {
                            System.out.println("Employee should be 18-60 " 
                                               + "years old");
                        } else {
                            isValid = false;
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
     * Deletes single and all Employees detail according to the user's choice.
     *
     */
    private void deleteDetails() {
        if (employeeController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nDELETE MENU\n--------\n")
                .append("1.Delete All Employee details")
                .append("\n2.Delete single Employee Detail")
                .append("\n3.Return to Main menu\n\n")
                .append("Please Select one\n\nYour choice :");
            int choice;
            do {
                System.out.print(menu);
                choice = getChoice(3);
 
                switch (choice) {
                    case 1: deleteAllEmployeeDetails(); 
                            break;
                    case 2: deleteOneEmployeeDetails();
                            break;
                    case 3: break;
                    default: System.out.println("Please enter valid option");
                             break;
                }
                System.out.println("-----------------------");
            } while (3 != choice);
        }
    }

    /**
     * Deletes all Employees from the list.
     *
     */
    private void deleteAllEmployeeDetails() {
        if (!employeeController.isRecordsEmpty()) {
            employeeController.deleteAllEmployee();
            if (employeeController.isRecordsEmpty()) { 
                System.out.println("All Employee details are deleted.");
            }
        } else {
            System.out.println("Sorry. No records Found to delete.");
        }
        System.out.println("-----------------------");
    }

    /**
     * Deletes one Employees using the employee id retrived from the user.
     *
     */
    private void deleteOneEmployeeDetails() {
        StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Employee Id\nor Press -1 to return to main menu");
        boolean isRecordExist = true;
        String employeeIdAsString = " ";
        while(isRecordExist) { 
            System.out.println(errorMessage);
            try {
                employeeIdAsString = scanner.nextLine();
        
                if ( -1 == Integer.parseInt(employeeIdAsString)) {
                    return;
                } else {
                    int idToDelete = getEmployeeId(employeeIdAsString);
                    if (employeeController.isEmployeeExist(idToDelete)) {
                        employeeController.deleteOneEmployee(idToDelete);
                        if (!employeeController.isEmployeeExist(idToDelete)) {
                            System.out.println("Deleted successfully.");
                            isRecordExist = false;
                        } else {
                            System.out.println("Entry Not deleted");
                        }
                    } else {
                        System.out.println("No such entry exist ");
                        isRecordExist = true;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Employee Id must be an integer");
                isRecordExist = true;
            }
        }
        System.out.println("-----------------------\n");
    }

    /**
     * Updates single or all fields of an Employee according to user's choice.
     *
     */
    private void updateEmployeeDetails() {
        if (employeeController.isRecordsEmpty()) {
            System.out.println("No records found!");
        } else {
            StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Employee Id\nor Press -1 to return to main menu");
            boolean isRecordExist = true;
            String employeeIdAsString = " ";
            while(isRecordExist) {
                System.out.print(errorMessage);
                try {
                    employeeIdAsString = scanner.nextLine(); 
                    if ( -1 == Integer.parseInt(employeeIdAsString)) {
                        return;
                    } else {
                        Integer employeeId = getEmployeeId(employeeIdAsString);
                        if (employeeController.isEmployeeExist(employeeId)) {
                            StringBuilder menu = new StringBuilder("\nUPDATE ")
                                .append("MENU\n---------\n1.Update All fields ")
                                .append("of an Employee\n2.Update single field")
                                .append(" of an Employee \n3.Return to Main ")
                                .append("menu\n\nplease Select one\n\nchoice:");
                            int choice;
                            do {
                                System.out.print(menu);
                                choice = getChoice(3);
                                switch (choice) {
                                    case 1: updateAllFields(employeeId);
                                            break;
                                    case 2: updateSingleField(employeeId);
                                            break;
                                    case 3: break;
                                    default: System.out.println("Wrong option");
                                             break;
                                }
                            } while (3 != choice);
                            isRecordExist = false;
                        } else {
                            System.out.println("No such record found.");
                        }
                    }
                } catch (Exception exception) {
                    System.out.println("Employee Id must be an integer");
                }
            }
        }
    }

    /**
     * Updates all fields of a single employee at once.
     *
     * @param employeeid-employeeid to update.
     */
    private void updateAllFields(int employeeId) {
        System.out.println(employeeController.updateAllFields(employeeId, 
                new EmployeeVO(employeeId, getName(), getEmail(), 
                               getMobileNumber(), getDateOfBirth(),
                               getSalary())) ? "Employee updated"
                                   : "Employee not updated");
        System.out.println("-----------------------");
    }

    /**
     * Updates separate fields of a single Employee.
     *
     * @param employeeId-employeeId to update the details
     */
    private void updateSingleField(int employeeId) {
        StringBuilder menu = new StringBuilder("\nUPDATE SINGLE FIELD MENU\n") 
            .append("----------------------\n1.Update Name\n2.Update Email ID") 
            .append("\n3.Update mobile number\n4.Update Date of Birth")
            .append("\n5.Update Salary\n6.Return to Update Menu\n\n")
            .append("please Select one\n\nYour choice :");
        int choice;
        do {
            System.out.print(menu);
            choice = getChoice(6);
 
            switch (choice) {
                case 1: updateName(employeeId);
                        break;
                case 2: updateEmail(employeeId);
                        break;
                case 3: updateMobileNumber(employeeId);
                        break;
                case 4: updateDateOfBirth(employeeId);
                        break;
                case 5: updateSalary(employeeId);
                        break;
                case 6: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
        } while (6 != choice);
    }

    /**
     * Updates name of a single Employee.
     *
     * @param employeeId-employeeId to update the Employee name.
     */
    private void updateName(int employeeId) {
        employeeController.updateName(employeeId,getName());
        System.out.print("-----------------------");
    }

    /**
     * Updates Email ID of a single Employee.
     *
     * @param employeeId-employeeId to update the email of given employeeID.
     */
    private void updateEmail(int employeeId) {
        employeeController.updateEmail(employeeId, getEmail());
        System.out.print("-----------------------");
    }

    /**
     * Updates mobile number of a Employee.
     *
     * @param employeeId-employeeId to update the mobileNumber
     */
    private void updateMobileNumber(int employeeId) {
        employeeController.updateMobileNumber(employeeId, getMobileNumber());
        System.out.print("-----------------------");
    }

    /**
     * Updates Date Of Birth of the Employee.
     *
     * @param employeeId-employeeId to update the Date of birth 
     */
    private void updateDateOfBirth(int employeeId) {
        employeeController.updateDateOfBirth(employeeId, getDateOfBirth());
        System.out.print("-----------------------");
    }

    /**
     * Updates Salary of the Employee.
     *
     * @param employeeId-employeeId to update the salary of given employeeID
     */
    private void updateSalary(int employeeId) {
        employeeController.updateSalary(employeeId, getSalary());
        System.out.print("-----------------------");
    }

    /**
     * Displays single or all Employee to the user.
     *
     */
    private void viewDetails() {
        if (employeeController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nVIEW MENU\n----------\n")
                .append("1.View All Employee details\n2.View single Employee") 
                .append("detail\n3.Return to Main menu")
                .append("\n\nplease Select one\n\nYour choice :");
                       
            int choice;
            do {
                System.out.print(menu);
                choice = getChoice(3);
                switch (choice) {
                    case 1: viewAllEmployeeDetails();
                            break;
                    case 2: viewOneEmployeeDetails();
                            break;
                    case 3: break;
                    default: System.out.println("Please enter valid option");
                             break;
                }
            } while (3 != choice);
        }
    }

    /**
     * Displays single employee details to the user.
     *
     */
    private void viewOneEmployeeDetails() {
        StringBuilder menu = new StringBuilder("Please enter Employee Id\n")
                .append("or Press -1 to return to main menu");
        String employeeIdAsString = " ";
        boolean isRecordExist = true;
        while(isRecordExist) {
            System.out.println(menu);
            try {
                employeeIdAsString = scanner.nextLine(); 
                if ( -1 == Integer.parseInt(employeeIdAsString)) {
                    return;
                } else {
                    int employeeId = getEmployeeId(employeeIdAsString);
                    if (employeeController.isEmployeeExist(employeeId)) {
                        System.out.println(employeeController
                                           .viewOneEmployee(employeeId));
                        isRecordExist = false;
                    } else {
                        System.out.println("\nNo such entry exist ");
                        isRecordExist = true;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Employee Id must be an integer");
                isRecordExist = true;
            }
        }
        System.out.println("-----------------------");
    }

    /**
     * Views all Employees details to the user.
     *
     */
    private void viewAllEmployeeDetails(){
        if (employeeController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            List<EmployeeVO> employeeDetails 
                    = new ArrayList<>(employeeController.viewAllEmployee());
            for (EmployeeVO value:employeeDetails){
                System.out.println(value);
            } 
            System.out.println("-----------------------");   
        }
    }
}
