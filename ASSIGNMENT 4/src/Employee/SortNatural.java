package Employee;

import java.util.Comparator;

class SortNatural implements Comparator<Employee> 
{

	// Sorting in ascending order
	public int compare(Employee a, Employee b)
	{
		return a.empId - b.empId;
	}

}