package queue;
import java.util.Scanner;
public class Main_q 
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Scanner scan_instance = new Scanner(System.in);
		queue queu =  new queue();
		while(true)
		{
			System.out.println("1. Add to queue");
			System.out.println("2. remove from queue");
			System.out.println("3. is Queue full");
			System.out.println("4. is Queue empty");
			System.out.println("5. Display Queue");
			System.out.println("6. Exit");
			int choice = scan_instance.nextInt();
			try
			{
			switch(choice)
			{
			
			case 1:
				System.out.println("Enter number");
				int number = scan_instance.nextInt();
				queu.enqueue(number);
				break;
			case 2:
				System.out.println(queu.deQueue());
				break;
			case 3:
				System.out.println(queu.isFull());
				break;
			case 4:
				System.out.println(queu.isEmpty());
				break;
			case 5:
				queu.display();
				break;
			default:
				return;
			}
			}
			catch(Exception e)
			{
				System.out.println("error");
			}
		}	
	}
}