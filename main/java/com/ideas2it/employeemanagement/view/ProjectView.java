/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.view;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.ListIterator;
import java.util.Scanner;


import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;

/**
 * Deals with user interaction and is responsible for
 * displaying the output to the user.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class ProjectView {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
    
    /**
     * Shows the main menu to the user.
     */
    public void showMainMenu() {
        int choice = 0;
        StringBuilder menu = new StringBuilder("PROJECT MANAGEMENT SYSTEM\n")
            .append("--------------------------\n1.Create\n2.Update\n3.View\n") 
            .append("4.Delete\n5.Assign/Unassign Employee\n6.Exit\n")
            .append("\nPlease choose an option\nYour choice");
        do {
            System.out.print(menu);
            choice = getChoice(5);
            switch (choice) {
                case 1: createNewProject();
                        break;
                case 2: updateProjectDetails();
                        break;
                case 3: viewDetails();
                        break;
                case 4: deleteDetails();
                        break;
                case 5: if (projectController.isRecordsEmpty()) {
                            System.out.println("Sorry. No records Found.");
                            break;
                        } else {
                            assignOrUnassignEmployee();
                            break;
                        }
                case 6: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
            System.out.println("-----------------------");   
        } while (6 != choice);
    }

    /**
     * Creates a new Project with the validated inputs.
     * 
     */
    private void createNewProject() {
        System.out.println("\nplease enter Project Details ");
        ProjectDTO returnedProjectDTO;

        ProjectDTO projectDTO = new ProjectDTO(getName(), getDomainName(), 
                 getStartDate(), getManager());

        /*returnedProjectVO = ProjectController
                  .createProject(new AddressDTO(getDoorNumber(),
                  getStreet(), getCity(), getState(),getCountry(),getPinCode(),
                  ProjectVO)); */
        returnedProjectDTO = projectController.createProject(projectDTO);

        if (null != returnedProjectDTO) {
            System.out.println("Project Created\nProject Id:"
                               +returnedProjectDTO.getProjectId());
        } else {
            System.out.println("Project not Created\n");
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
            } catch (NumberFormatException exception) {
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
        while (!projectController.validateInput(input, patternToValidate)) {
            System.out.print(errorMessage);
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Gets and check the Project Id's validity.
     *
     * @param ProjectId-Project id to validate.
     * @return ProjectId-validated ID of the Project
     */
    private Integer getProjectId(String projectIdAsString) {
        String pattern = "([1-9][0-9]{0,4})|(\\-1)";
        int projectId = 0;
        StringBuilder errorMessage = new StringBuilder("Invalid ProjectID")
            .append("!\nProject ID must only be numeric with maximum")
            .append(" of 5 digits\n\nPlease reenter Project ID\n")
            .append("\nProjectID:");
        projectIdAsString = validateInput(projectIdAsString, pattern, errorMessage);
        try {
            projectId = Integer.parseInt(projectIdAsString);
        } catch (NumberFormatException exception) {
            System.out.println("Project Id must be an integer");
        }
        return projectId;   
    }

    /**
     * Gets and checks if the given name is valid.
     *
     * @return name-validated name of the Project
     */
    private String getName() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}";
        System.out.print("Name:");
        String name = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid name! \n")
            .append("Name shouldn't contain special characters")
            .append(",\nnumbers and more than one blankspace\nPlease")
            .append(" reenter Project name\nName:");
        return validateInput(name, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given domain name is valid.
     *
     * @return name-validated name of the Project
     */
    private String getDomainName() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}";
        System.out.print("Domain Name:");
        String domainName = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid name! \n")
            .append("Name shouldn't contain special characters")
            .append(",\nnumbers and more than one blankspace\nPlease")
            .append(" reenter Project Domain name\nDomain Name:");
        return validateInput(domainName, pattern, errorMessage);
    }

    /**
     * Gets and checks if the given domain name is valid.
     *
     * @return name-validated name of the Project
     */
    private String getManager() {
        String pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}";
        System.out.print("Manager:");
        String manager = scanner.nextLine();
        StringBuilder errorMessage = new StringBuilder("Invalid name! \n")
            .append("Name shouldn't contain special characters")
            .append(",\nnumbers and more than one blankspace\nPlease")
            .append(" reenter Project manager name\nName:");
        return validateInput(manager, pattern, errorMessage);
    }


    /**
     * Gets and checks if Project Date of birth is valid or not. 
     *
     * @return startDate-validated start date of the Project
     */
    private LocalDate getStartDate() {
        String promptStatement = "Project start date"
                                 + "(Enter in(dd-MM-yyyy)format):";
        String dateAsString = " ";
        LocalDate startDate = LocalDate.now();
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
                    startDate = LocalDate.parse(formattedDate);
                    if (startDate.isAfter(today)) {
                        System.out.println("Invalid Date Of birth!!");
                    } else {
                        isValid = false;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("\n*not a valid date*\n");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n*not a valid date*\n");
            }
        }
        return startDate;
    }

    /**
     * Deletes single and all Projects detail according to the user's choice.
     *
     */
    private void deleteDetails() {
        if (projectController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nDELETE MENU\n--------\n")
                .append("1.Delete All Project details")
                .append("\n2.Delete single Project Detail")
                .append("\n3.Return to Main menu\n\n")
                .append("Please Select one\n\nYour choice :");
            int choice;
            do {
                System.out.print(menu);
                choice = getChoice(3);
 
                switch (choice) {
                    case 1: deleteAllProjects(); 
                            break;
                    case 2: deleteOneProject();
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
     * Deletes all Projects from the list.
     *
     */
    private void deleteAllProjects() {
        if (!projectController.isRecordsEmpty()) {
            if (projectController.deleteAllProject()) { 
                System.out.println("All Project details are deleted.");
            }
        } else {
            System.out.println("Sorry. No records Found to delete.");
        }
        System.out.println("-----------------------");
    }

    /**
     * Deletes one Projects and address using the 
     * Project id retrived from the user.
     *
     * @param ProjectId-Project id to delete Project details.
     */
    private void deleteOneProjectDetails(int projectId) {
        StringBuilder menu = new StringBuilder("Delete Single Project ")
                .append("Menu\n1.Delete Project\n")
                .append("\n2.Return to Delete menu");
        ProjectDTO projectDTO = projectController.viewOneProject(projectId);
        int choice;
        do {
            System.out.print(menu);
            choice = getChoice(2);
            switch (choice) {
                case 1: System.out.println(projectController
                            .deleteOneProject(projectDTO) 
                                ? "Project Deleted"
                                : " Project Not Deleted"); 
                        break;
                case 2: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
        } while (2 != choice);
        System.out.println("-----------------------\n");
    }

    /**
     * Deletes one Projects using the Project id retrived from the user.
     *
     */
    private void deleteOneProject() {
        StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Project Id\nor Press -1 to return to main menu");
        boolean isRecordDeleted = true;
        String projectIdAsString = " ";
        while (isRecordDeleted) {
            System.out.println(errorMessage);
            try {
                projectIdAsString = scanner.nextLine();
                if ( -1 == Integer.parseInt(projectIdAsString)) {
                    return;
                } else {
                    int idToDelete = getProjectId(projectIdAsString);
                    if (projectController.isProjectExist(idToDelete)) {
                       deleteOneProjectDetails(idToDelete);
                       isRecordDeleted = false;
                    } else {
                       System.out.println("No such entry exist");
                       isRecordDeleted = true;
                    }
                }
            } catch (NumberFormatException exception) {
                System.out.println("Project Id must be an integer");
                isRecordDeleted = true;
            }
        }
    }

    /**
     * Updates single or all fields of an Project according to user's choice.
     *
     */
    private void updateProjectDetails() {
        if (projectController.isRecordsEmpty()) {
            System.out.println("No records found!");
        } else {
            StringBuilder errorMessage = new StringBuilder("Please enter ")
                .append("Project Id\nor Press -1 to return to main menu");
            StringBuilder menu = new StringBuilder("\nUPDATE MENU\n---------\n")
                .append("1.Update All fields of an Project\n2.Update single ")
                .append("field of an Project \n3.Return to Main")
                .append(" menu \n\nplease Select one\n\nchoice:");
            boolean isRecordExist = true;
            int projectId;
            String projectIdAsString = " ";
            while (isRecordExist) {
                System.out.print(errorMessage);
                try {
                    projectId = getProjectId(scanner.nextLine());
                    if ( -1 == projectId) {
                //return;
                isRecordExist = false;
                    } else {
                        projectId = getProjectId(projectIdAsString);
                        if (projectController.isProjectExist(projectId)) {
                            int choice;
                            do {
                                System.out.print(menu);
                                choice = getChoice(4);
                                switch (choice) {
                                    case 1: updateAllFields(projectId);
                                            break;
                                    case 2: updateSingleField(projectId);
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
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * Updates all fields of a single Project at once.
     *
     * @param Projectid-Projectid to update.
     */
    private void updateAllFields(int projectId) {
        ProjectDTO projectDTO = projectController.getProjectById(projectId);
        //AddressDTO addressDTO = new AddressDTO();

        projectDTO.setProjectId(projectId);
        projectDTO.setName(getName());
        projectDTO.setDomainName(getDomainName());
        projectDTO.setStartDate(getStartDate());
        projectDTO.setManager(getManager()); 
        projectController.updateAllFields(projectDTO);
        //System.out.println(projectController
              //  .updateAllFields(projectDTO)
               // ? "Project updated" : "Project not updated");
    }

    /**
     * Updates separate fields of a single Project.
     *
     * @param ProjectId-ProjectId to update the details
     */
    private void updateSingleField(int projectId) {
        ProjectDTO projectDTO = projectController.getProjectById(projectId);
        StringBuilder menu = new StringBuilder("\nUPDATE SINGLE FIELD MENU\n") 
            .append("----------------------\n1.Update Project Name\n2.Update ") 
            .append("Domain Name\n3.Update project start date\n4.Update project")
            .append(" Manager\n5.Return to Update Menu\n\n")
            .append("please Select one\n\nYour choice :");
        int choice;
        do {
            System.out.print(menu);
            choice = getChoice(6);
 
            switch (choice) {
                case 1: projectDTO.setName(getName());
                        break;
                case 2: projectDTO.setDomainName(getDomainName());
                        break;
                case 3: projectDTO.setStartDate(getStartDate());
                        break;
                case 4: projectDTO.setManager(getManager());
                        break;
                case 5: break;
                default: System.out.println("Please enter valid option");
                         break;
            }
        } while (6 != choice);
        projectController.updateAllFields(projectDTO);

    }

    /**
     * Displays single or all Project to the user.
     *
     */
    private void viewDetails() {
        if (projectController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            StringBuilder menu = new StringBuilder("\nVIEW MENU\n----------\n")
                .append("1.View All Project details\n2.View single Project") 
                .append("detail\n3.Return to Main menu")
                .append("\n\nplease Select one\n\nYour choice :");
                       
            int choice;
            do {
                System.out.print(menu);
                choice = getChoice(3);
                switch (choice) {
                    case 1: viewAllProjectDetails();
                            break;
                    case 2: viewOneProjectDetails();
                            break;
                    case 3: break;
                    default: System.out.println("Please enter valid option");
                             break;
                }
            } while (3 != choice);
        }
    }

    /**
     * Displays single Project details to the user.
     *
     */
    private void viewOneProjectDetails() {
        StringBuilder menu = new StringBuilder("Please enter Project Id\n")
                .append("or Press -1 to return to main menu");
        ProjectDTO projectDTO;
        Set<EmployeeVO> employees = new HashSet<EmployeeVO>();
        boolean isRecordExist = true;
        int projectId = 0;
        while (isRecordExist) {
            System.out.println(menu);
            projectId = getProjectId(scanner.nextLine());
            if ( -1 == projectId) {
                //return;
                isRecordExist = false;
            } else {
                //projectId = getProjectId(scanner.nextLine());
                if (projectController.isProjectExist(projectId)) {
                    projectDTO = projectController.viewOneProject(projectId);
                    System.out.println("Project Details\n--------------\n" 
                                        + projectDTO);
                    if ((!projectDTO.getEmployeesVO().isEmpty()) || (null != projectDTO)) {
                    employees = projectDTO.getEmployeesVO();
                    for (EmployeeVO employee:employees){
                        System.out.println(employee);
                    }
                    }
                    isRecordExist = false;
                } else {
                     System.out.println("\nNo such entry exist");
                     isRecordExist = true;
                }
            }
        }
        System.out.println("-----------------------");
    }

    /**
     * Views all Projects details to the user.
     *
     */
    private void viewAllProjectDetails(){
        if (projectController.isRecordsEmpty()) {
            System.out.println("Sorry. No records Found.");
        } else {
            List<ProjectDTO> projectDetails 
                    = new ArrayList<>(projectController.viewAllProject());
            List<EmployeeVO> employees;
            for (ProjectDTO value:projectDetails){
                System.out.println(value);
                employees = new ArrayList<>(value.getEmployeesVO());
                for (EmployeeVO employee:employees){
                    System.out.println(employee);
                }
            } 
            System.out.println("-----------------------");   
        }
    }

    private void assignOrUnassignEmployee() {
        StringBuilder menu = new StringBuilder("\nASSIGN AND UNASSIGN MENU")
                .append("\n----------\n1.Assign Employee\n2.Unassign Employee") 
                .append("\n3.Exit\n\nplease Select one\n\nYour choice :");
        StringBuilder inputMenu = new StringBuilder("Please enter Project Id\n")
                .append("or Press -1 to return to main menu");
        int choice;
        int projectId;
        ProjectDTO projectDTO;
        boolean isRecordExist = true;
        List<ProjectDTO> projectDetails 
                = new ArrayList<>(projectController.viewAllProject());
        for (ProjectDTO value:projectDetails){
            System.out.println("\nProject Id    :" + value.getProjectId()
                               + "\tProject Name  :" + value.getName());
        }
        while (isRecordExist) {
            System.out.println(inputMenu);
            projectId = getProjectId(scanner.nextLine());
            if (-1 == projectId) {
                isRecordExist = false; 
            } else if (projectController.isProjectExist(projectId)) {
                projectDTO = projectController.viewOneProject(projectId);
                System.out.println("Project Details\n--------------\n" 
                                    + projectDTO);
                isRecordExist = false;
                do {
                    System.out.print(menu);
                    choice = getChoice(3);
                    switch (choice) {
                        case 1: assignEmployee(projectId);
                                break;
                        case 2: unAssignEmployee(projectId);
                                break;
                        case 3: break;
                        default: System.out.println("Please enter valid option");
                                 break;
                    }
                } while (3 != choice);
            } else {
                System.out.println("\nNo such entry exist");
                isRecordExist = true;
            }
        }

    }

    private int[] getEmployeeIds() {
        String pattern = "([1-9][0-9]*)([,]?[1-9][0-9]*)*";
        StringBuilder errorMessage = new StringBuilder("Invalid Input!\nEmployee ")
                .append("Id should be separated with only commas(,)\nPlease")
                .append(" reenter employee IDs\n Employee IDS:");
        System.out.println("Please enter Employee Ids to assign/unassign"
                           + "\n Employee IDS:");
        String idsAsString;
        int i = 0;
        //boolean isInputValid = true;
        //do {
            idsAsString = validateInput(scanner.nextLine(), pattern, errorMessage);
            int employeeids[] = new int[idsAsString.length()];
            String[] idStringArray = idsAsString.split(",");
            try {
               for (i = 0; i < idsAsString.length(); i++) {
                   employeeids[i] = Integer.parseInt(idStringArray[i]);
                   //isInputValid = true;
               }
            } catch (NumberFormatException exception) {
               System.out.println("Invalid input! Error occured while Parsing");
                   //isInputValid = false;
            } finally {
               return employeeids;
            }
        
       // } while ((i == idsAsString.length()) && (isInputValid = true));
        //return employeeids;
    }


    private void assignEmployee(int projectId) {
        ProjectDTO projectDTO = projectController.getProjectById(projectId);
        Set<EmployeeVO> assignedEmployees = projectDTO.getEmployeesVO();
        List<EmployeeVO> employeesToAssign = new ArrayList<EmployeeVO>();
        int noOfAssignedEmployees = assignedEmployees.size();

        if ( (null == assignedEmployees) || assignedEmployees.isEmpty()) {
            System.out.println("No Employees assigned to this project");
            assignedEmployees = new HashSet<EmployeeVO>();
        } else { 
            System.out.println("Employees assigned to this project are:\n");
            for (EmployeeVO employee:assignedEmployees){
                System.out.println(employee);
            }
        }
        int employeeIds[] = getEmployeeIds();
        if((null != employeeIds)){
            employeesToAssign = projectController.getEmployeeDTOs(employeeIds);
            employeesToAssign.removeAll(assignedEmployees);
            for (EmployeeVO employeeVO:employeesToAssign){
                assignedEmployees.add(employeeVO);
            }
            if (assignedEmployees.size() != noOfAssignedEmployees) {
                projectDTO.setEmployeesVO(assignedEmployees);
                //System.out.println(projectController.updateAllFields(projectDTO) 
                  //     ? "Employees Assigned" 
                      // : "Trying to assign non-existent employee!"
                    //   + " Employees Not Assigned.");
                projectController.updateAllFields(projectDTO);
            }
        }
    }

    private void unAssignEmployee(int projectId) {
        ProjectDTO projectDTO = projectController.getProjectById(projectId);
        Set<EmployeeVO> assignedEmployees;
        List<EmployeeVO> employeesToUnAssign = new ArrayList<EmployeeVO>();
        assignedEmployees = projectDTO.getEmployeesVO();
        int noOfAssignedEmployees = assignedEmployees.size();

        if (assignedEmployees.isEmpty() || (null == assignedEmployees)) {
            System.out.println("No Employees assigned to this project");
            assignedEmployees = new HashSet<EmployeeVO>();
        } else { 
            System.out.println("Employees assigned to this project are:\n");
            for (EmployeeVO employee:assignedEmployees){
                System.out.println(employee);
            }
        }
        int employeeIds[] = getEmployeeIds();
        if((null != employeeIds)){
           for (EmployeeVO employeeVO:employeesToUnAssign){
                assignedEmployees.add(employeeVO);
            }
            employeesToUnAssign = projectController.getEmployeeDTOs(employeeIds);
            assignedEmployees.removeAll(employeesToUnAssign);
            if (assignedEmployees.size() != noOfAssignedEmployees) {
                projectDTO.setEmployeesVO(assignedEmployees);
               // System.out.println(projectController.updateAllFields(projectDTO) 
                 //          ? "Employees UnAssigned" 
                   //        : "Trying to assign non-existent employee!"
                     //      + " Employees Not Assigned.");
                
                projectController.updateAllFields(projectDTO);
            }
        }
    }
}
