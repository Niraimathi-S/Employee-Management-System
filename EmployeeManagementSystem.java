/*
 * EmployeeManagementSystem.java
 * 
 * Copyrights (C) IdeasIT
 */

import com.ideas2it.employeemanagement.view.EmployeeView;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

/*
 * This class create an obeject for the view class and calls it.
 * 
 * Copyrights (C) IdeasIT
 */
public class EmployeeManagementSystem {

    /**
     * main() method calls the view class
     *
     */
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.showOptions(); 
    }
}

