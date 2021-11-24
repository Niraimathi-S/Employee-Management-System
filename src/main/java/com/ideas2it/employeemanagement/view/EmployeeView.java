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
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;

/**
 * Deals with user interaction and is responsible for
 * displaying the output to the user.
 * 
 * @author Niraimathi S
 * @version 1.0
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
     * Creates a new employee and its address with the validated inputs.
     * 
     */
    private void createNewEmployee() {
        System.out.println("\nplease enter Employee Details ");
        EmployeeVO returnedEmployeeVO;
        List<AddressDTO> addresses = new ArrayList<AddressDTO>();
        EmployeeVO employeeVO = new EmployeeVO(getName(), getEmail(), 
                getMobileNumber(), getDateOfBirth(), getSalary());

        System.out.println("\nplease enter Employee Address details");
        AddressDTO addressDTO = new AddressDTO(getDoorNumber(),
                  getStreet(), getCity(), getState(),getCountry(),getPinCode());
 
        addresses.add(addressDTO);
        employeeVO.setaddressesDTO(addresses);
        returnedEmployeeVO = employeeController
                  .createEmployee(employeeVO); 
        if (null != returnedEmployeeVO) {
            System.out.println("Employee Created\nEmployee Id:"
                               +returnedEmployeeVO.getEmployeeId());
        } else {
            System.out.println("Employee not Created\n");
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

        while (isMobileNumberDublicate) {
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
        while (isEmailDublicate) {
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
     * @return dateOfBirth-validated date of birth of the employee
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
     * Gets and checks if the given door number is valid.
     *
     * @return doorNumber-validated door number of the address.
     */
    private String getDoorNumber() {
        String pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+";
        StringBuilder errorMessage = new StringBuilder("Invalid door number!\n")
            .append("Door number should only contain alphabets, numbers,single")
            .append(",\nspace and some special character(/-)\nPlease")
            .append(" reenter door number.\nDoor Number:");

        System.out.print("Door Number:");
        String doorNumber = scanner.nextLine();

        return validateInput(doorNumber, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given street name and number is valid.
     *
     * @return street-validated street name and number of the address.
     */
    private String getStreet() {
        String pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}";
        StringBuilder errorMessage = new StringBuilder("Invalid street name!\n")
            .append("Should only contain alphabets, numbers,single")
            .append(",\nspace and some special character(.,-)\nPlease")
            .append(" reenter Street name and number\nStreet Name & Number:");

        System.out.print("Street Name & Number:");
        String street = scanner.nextLine();

        return validateInput(street, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given city or district name is valid.
     *
     * @return city-validated city name of the address.
     */
    private String getCity() {
        String pattern = "([a-zA-Z]{3,}([\\s,]{1}[a-zA-Z]+)*){1,100}";
        StringBuilder errorMessage = new StringBuilder("Invalid input\n")
            .append("Should only contain alphabets,single")
            .append(",\nspace and \",\" \nPlease")
            .append(" reenter city or district.\nCity/District:");

        System.out.print("City/District:");
        String city = scanner.nextLine();

        return validateInput(city, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given state name is valid.
     *
     * @return state-validated state name of the address.
     */
    private String getState() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){1,100}";
        StringBuilder errorMessage = new StringBuilder("Invalid state name\n")
            .append("Should only contain alphabets and single space")
            .append("\nPlease reenter state\nState:");

        System.out.print("State:");
        String state = scanner.nextLine();

        return validateInput(state, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given country name is valid.
     *
     * @return country-validated country name of the address.
     */
    private String getCountry() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){1,100}";
        StringBuilder errorMessage = new StringBuilder("Invalid Country name!")
            .append("\nShould only contain alphabets and single space")
            .append("\nPlease reenter country\nCountry:");

        System.out.print("Country:");
        String country = scanner.nextLine();

        return validateInput(country, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given pincode is valid.
     *
     * @return pincode-validated pincode of the address.
     */
    private Integer getPinCode() {
        String pattern = "[1-9][0-9]{5}";
        StringBuilder errorMessage = new StringBuilder("Invalid pincode")
            .append("!\nPincode must only be numeric with 6 digits and should")
            .append(" not start with 0\n\nPlease reenter pincode\n")
            .append("\nPinCode:");

        System.out.print("PinCode:");
        String pinCode = scanner.nextLine();

        pinCode = validateInput(pinCode, pattern, errorMessage);
        return (Integer.parseInt(pinCode));   
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
                    case 1: deleteAllEmployees(); 
                            break;
                    case 2: deleteOneEmployee();
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
    private void deleteAllEmployees() {
        if (!employeeController.isRecordsEmpty()) {
            if (employeeController.deleteAllEmployee()) { 
                System.out.println("All Employee details are deleted.");
            }
        } else {
            System.out.println("Sorry. No records Found to delete.");
        }
        System.out.println("-----------------------");
    }

    /**
     * Deletes one Employees and address using the 
     * employee id retrived from the user.
     *
     * @param employeeVO-employeeVO to delete employee details.
     */
    private void deleteOneEmployeeDetails(EmployeeVO employeeVO) {
        StringBuilder menu = new StringBuilder("Delete Single Employee ")
                .append("Menu\n1.Delete Employee\n2.Delete Address")
                .append("\n3.Return to Delete menu");
        int choice;
        do {
            System.out.print(menu);
            choice = getChoice(3);
            switch (choice) {
                case 1: System.out.println(employeeController
                            .deleteOneEmployee(employeeVO) 
                                ? "Employee Deleted"
                                : " Employee Not Deleted"); 
                        choice = 3;
                        break;
                case 2: deleteAddress(employeeVO.getEmployeeId());
                        choice = 3;
                        break;
                case 3: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
        } while (3 != choice);
        System.out.println("-----------------------\n");
    }

    /**
     * Deletes one Employees using the employee id retrived from the user.
     *
     */
    private void deleteOneEmployee() {
        StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Employee Id\nor Press -1 to return to main menu");
        boolean isRecordDeleted = true;
        String employeeIdAsString = " ";
        while (isRecordDeleted) {
            System.out.println(errorMessage);
            try {
                employeeIdAsString = scanner.nextLine();
                if ( -1 == Integer.parseInt(employeeIdAsString)) {
                    return;
                } else {
                    int idToDelete = getEmployeeId(employeeIdAsString);
                    EmployeeVO employeeVO = employeeController
                                           .getEmployeeById(idToDelete);
                    if (null != employeeVO ) {
                       deleteOneEmployeeDetails(employeeVO);
                       isRecordDeleted = false;
                    } else {
                       System.out.println("No such entry exist");
                       isRecordDeleted = true;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Employee Id must be an integer"+exception);
                isRecordDeleted = true;
            }
        }
    }

    /**
     * Deletes one Employee's  address using the employee id retrived 
     * from the user.
     *
     * @param employeeId-employee id to delete employee address.
     */
    private void deleteAddress(int employeeId) {
        EmployeeVO employeeVO = employeeController.getEmployeeById(employeeId);
        List<AddressDTO> addresses 
            = employeeVO.getaddressesDTO();
        int addressId = 1;
        int idToDelete;
        if (!addresses.isEmpty()) {
            for (AddressDTO address:addresses){
                System.out.println("\nAddressId\t :" + addressId + address);
                addressId++;
            }
            System.out.println("Enter address id to delete.\nAddress Id:");
            addressId = Integer.parseInt(scanner.nextLine());
            if ((addressId-1) < addresses.size()) {
                addresses.remove(addressId-1);
            System.out.println("address"+addresses);
                employeeVO.setaddressesDTO(addresses);
                System.out.println(employeeController
                        .updateAllFields(employeeVO)
                        ? "Address deleted" : "Address not deleted");
            }
         } else {
            System.out.println("No address to delete");
        }
    }

    /**
     * Adds address, Updates single or all fields of an Employee 
     * according to user's choice.
     *
     */
    private void updateEmployeeDetails() {
        if (employeeController.isRecordsEmpty()) {
            System.out.println("No records found!");
        } else {
            StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Employee Id\nor Press -1 to return to main menu");
            StringBuilder menu = new StringBuilder("\nUPDATE MENU\n---------\n")
                .append("1.Update All fields of an Employee\n2.Update single ")
                .append("field of an Employee \n3.Add Address\n4.Return to Main")
                .append(" menu \n\nplease Select one\n\nchoice:");
            boolean isRecordExist = true;
            String employeeIdAsString = " ";
            while (isRecordExist) {
                System.out.print(errorMessage);
                try {
                    employeeIdAsString = scanner.nextLine(); 
                    if ( -1 == Integer.parseInt(employeeIdAsString)) {
                        return;
                    } else {
                        Integer employeeId = getEmployeeId(employeeIdAsString);
                        if (employeeController.isEmployeeExist(employeeId)) {
                            int choice;
                            do {
                                System.out.print(menu);
                                choice = getChoice(4);
                                switch (choice) {
                                    case 1: updateAllFields(employeeId);
                                            break;
                                    case 2: updateSingleField(employeeId);
                                            break;
                                    case 3: addAddress(employeeId);
                                            break;
                                    case 4: break;
                                    default: System.out.println("Wrong option");
                                             break;
                                }
                            } while (4 != choice);
                            isRecordExist = false;
                        } else {
                            System.out.println("No such record found.");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
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
        EmployeeVO employeeVO = employeeController.getEmployeeById(employeeId);
        employeeVO.setEmployeeId(employeeId);
        employeeVO.setName(getName());
        employeeVO.setEmail(getEmail());
        employeeVO.setMobileNumber(getMobileNumber());
        employeeVO.setDateOfBirth(getDateOfBirth());
        employeeVO.setSalary(getSalary()); 
        System.out.println(employeeController
                .updateAllFields(employeeVO)
                ? "Employee updated" : "Employee not updated");
    }

    /**
     * Updates separate fields of a single Employee.
     *
     * @param employeeId-employeeId to update the details
     */
    private void updateSingleField(int employeeId) {
        EmployeeVO employeeVO = employeeController.getEmployeeById(employeeId);
        StringBuilder menu = new StringBuilder("\nUPDATE SINGLE FIELD MENU\n") 
            .append("----------------------\n1.Update Name\n2.Update Email ID") 
            .append("\n3.Update mobile number\n4.Update Date of Birth")
            .append("\n5.Update Salary\n6.Return to Update Menu\n\n")
            .append("please Select one\n\nYour choice :");
        int choice;
        AddressDTO addressDTO = new AddressDTO();
        do {
            System.out.print(menu);
            choice = getChoice(6);
 
            switch (choice) {
                case 1: employeeVO.setName(getName());
                        break;
                case 2: employeeVO.setEmail(getEmail());
                        break;
                case 3: employeeVO.setMobileNumber(getMobileNumber());
                        break;
                case 4: employeeVO.setDateOfBirth(getDateOfBirth());
                        break;
                case 5: employeeVO.setSalary(getSalary());
                        break;
                case 6: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
        } while (6 != choice);
        System.out.println(employeeController
                .updateAllFields(employeeVO)
                ? "Employee updated" : "Employee not updated");
    }

    /**
     * Add a new address to the Employee.
     *
     * @param employeeId-employeeId to add new address to the employee.
     */
    private void addAddress(int employeeId) {
        EmployeeVO employeeVO = employeeController.getEmployeeById(employeeId);
        AddressDTO addressDTO = new AddressDTO(
            getDoorNumber(), getStreet(), getCity(), getState(),getCountry(),
            getPinCode());
        List<AddressDTO> addresses = employeeVO.getaddressesDTO();
        addresses.add(addressDTO);
        employeeVO.setaddressesDTO(addresses);
        System.out.println(employeeController.updateAllFields(employeeVO) 
                           ? "Address Added" : "Address not added");
    }

    /**
     * Displays single or all Employee to the user.
     *
     */
    private void viewDetails() {
        List<EmployeeVO> employeeDetails 
                    = new ArrayList<>(employeeController.viewAllEmployee());
        System.out.println("view flow"+employeeDetails);
        if (employeeDetails.isEmpty()) {
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
                    case 1: viewAllEmployeeDetails(employeeDetails);
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
        EmployeeVO employeeVO;
        List<AddressDTO> addresses;
        boolean isRecordExist = true;
        while (isRecordExist) {
            System.out.println(menu);
            try {
                employeeIdAsString = scanner.nextLine(); 
                if ( -1 == Integer.parseInt(employeeIdAsString)) {
                    return;
                } else {
                    int employeeId = getEmployeeId(employeeIdAsString);
                    employeeVO = employeeController.viewOneEmployee(employeeId);
                    if (null != employeeVO) {
                        System.out.println("Employee Details\n--------------\n" 
                                           + employeeVO);
                        System.out.println("\nEmployee Address\n-------------");
                        addresses 
                                = new ArrayList<>(employeeVO.getaddressesDTO());
                        for (AddressDTO address:addresses){
                            System.out.println(address);
                        }
                        isRecordExist = false;
                    } else {
                        System.out.println("\nNo such entry exist ");
                        isRecordExist = true;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Employee Id must be an integer"+exception);
                isRecordExist = true;
            }
        }
        System.out.println("-----------------------");
    }

    /**
     * Views all Employees details to the user.
     *
     */
    private void viewAllEmployeeDetails(List<EmployeeVO> employeeDetails) {
        if (employeeDetails.isEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            List<AddressDTO> addresses;
            for (EmployeeVO value:employeeDetails){
                System.out.println(value);
                addresses = new ArrayList<>(value.getaddressesDTO());
                        for (AddressDTO address:addresses){
                            System.out.println(address);
                        }
                System.out.println("-----------------------");   
            } 
            System.out.println("-----------------------");   
        }
    }
}
