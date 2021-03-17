package VCMD;

import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		VCMD vcmd = new VCMD();
		while(true){
			System.out.print("C:"+vcmd.getPath()+">");
			String[] cmd = sc.nextLine().split(" ");
			if(cmd.length == 2)
				vcmd.run(cmd[0], cmd[1]);
			else
				vcmd.run(cmd[0], null);
			
		}
	}
}