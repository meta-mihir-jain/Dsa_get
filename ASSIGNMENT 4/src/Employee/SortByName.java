package Employee;

import java.util.Comparator;

class SortByName implements Comparator<Employee> 
{

	// Sorting in ascending order
	public int compare(Employee a, Employee b) 
	{
		return a.empName.compareTo(b.empName);
	}

}