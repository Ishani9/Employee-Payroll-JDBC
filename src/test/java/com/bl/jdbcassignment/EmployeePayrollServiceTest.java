package com.bl.jdbcassignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bl.jdbcassignment.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {

	public static EmployeePayrollService employeePayrollService;
	public static List<EmployeePayrollData> employeePayrollData;
	
	@BeforeClass
	public static void setUp() {
		employeePayrollService = new EmployeePayrollService();
		employeePayrollData = employeePayrollService.readEmployeeData(IOService.DB_IO);
	}

	@Test
	public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
		assertEquals(4, employeePayrollData.size());
	}
	
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDb() throws SQLException
	{
		employeePayrollService.updateEmployeePayrollSalary("Bill", 200000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Bill");
		assertEquals(4, employeePayrollData.size());
		assertTrue(result);
	}
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
		List<EmployeePayrollData> employeeByDateList = null;
		LocalDate start = LocalDate.of(2018, 3, 3);
		LocalDate end = LocalDate.of(2020, 6, 12);
		employeeByDateList = employeePayrollService.getEmployeeByDate(start, end);
		assertEquals(3, employeeByDateList.size());
	}
}

