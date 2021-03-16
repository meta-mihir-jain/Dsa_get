package conse;

import java.io.IOException;


public class Main 
{
	public static void main(String[] args) throws IOException
	{
		Counselling counselling = new Counselling();
		
		counselling.readFile("student.xlsx", "college.xlsx");
		counselling.intializePrograms();		
		counselling.allocate();
		counselling.save();
		
	}
}