package EMp;

import java.util.Scanner;
class Employees implements Comparable<Employees> 
{
	private String name;
	private int age;
	private double salary;

	//Employee Constructor to initialize employee details
	public Employees(String name, int age, double salary) 
	{
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	//Method to get age of the employee
	public int getAge()
	{
		return age;
	}

	//Method to get salary of the employee
	public double getSalary() 
	{
		return salary;
	}

	//overriding toString() method
	public String toString()
	{
		return name + "\t" + age + "\t " + salary;
	}
	@Override
	public int compareTo(Employees employee)
	{
		if (salary < employee.getSalary())
			return 1; 
		else if (salary == employee.getSalary()) 
		{
			if (age > employee.getAge())
				return 1;
			return -1;
		}
		return -1;
	}
}

class LinkedList 
{

	class Node 
	{
		Employees employee;
		Node next;

		Node(Employees employee)
		{
			this.employee = employee;
			next = null;
		}
	}

	public Node head;
	public LinkedList() 
	{
		head = null;
	}

	void insert(Employees passedEmployee)
	{
		Node newnode = new Node(passedEmployee);
		if (head == null) 
		{
			head = newnode;
			return;
		}
		newnode.next = head;
		head = newnode;
	}

	public void traverse() 
	{
		Node tempHead = head;
		System.out.println("\nName    Age \t Salary\n");
		while (tempHead != null)
		{
			System.out.println(tempHead.employee);
			tempHead = tempHead.next;
		}
	}
	
	//Method to make partition to perform quick sort
	Node paritionLast(Node start, Node end) 
	{
		if (start == end || start == null || end == null)
			return start;
		Node pivot_prev = start;
		Node curr = start;
		Employees pivot = end.employee;
		// Loop for finding the node to swap with pivot
		while (start != end) {
			if (start.employee.compareTo(pivot) < 1)
			{
				pivot_prev = curr;
				Employees temp = curr.employee;
				curr.employee = start.employee;
				start.employee = temp;
				curr = curr.next;
			}
			start = start.next;
		}
		Employees temp = curr.employee;
		curr.employee = pivot;
		end.employee = temp;
		return pivot_prev;
	}
	//Method to implement quick sort
	public void sort(Node start, Node end)
	{
		if (start == null || start == end || start == end.next)
			return;

		// Partitioning perform
		Node pivot_prev = paritionLast(start, end);
		sort(start, pivot_prev);

		
		if (pivot_prev != null && pivot_prev == start)
			sort(pivot_prev.next, end);

		
		else if (pivot_prev != null && pivot_prev.next != null)
			sort(pivot_prev.next.next, end);
	}
	
	static Scanner sc = new Scanner(System.in);


	public static Employees insertEmployee() {
		System.out.print("Enter employee name :");
		String name = sc.nextLine();
		System.out.print("Enter employee age :");
		int age = sc.nextInt();
		System.out.print("Enter employee salary :");
		double salary = sc.nextDouble();
		return new Employees(name, age, salary);
	}

	public static void main(String[] args) {
		LinkedList employees = new LinkedList();
		Employees employee;
		System.out.println("Enter Number of Employees : ");
		int noOfEmployee=sc.nextInt();
		sc.nextLine();
		int i=0;
		while(noOfEmployee > 0){
			System.out.println("\nEnter Detail of Employee " + (i+1) + "\n");
			employee = insertEmployee();
			employees.insert(employee);
			sc.nextLine(); //Used to clear buffer
			noOfEmployee--;
			i++;
		} 
		Node endNode = employees.head;
                while (endNode.next != null)
                	endNode = endNode.next;
		employees.sort(employees.head, endNode);
		System.out.println("Sorted List.");	
		employees.traverse();
	}
}