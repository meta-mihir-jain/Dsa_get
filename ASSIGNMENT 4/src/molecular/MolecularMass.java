package molecular;
import java.util.*;
public class MolecularMass 
{
	//Return Molecular Mass of the mentioned Atom
	public static int getMass(char character)
	{
	   if (character == 'C')
		return 12;
	   if (character == 'H')
		return 1;
	   if (character == 'O')
		return 16;
	   System.out.println(character);
	   throw new AssertionError("Invalid Input");
	}
	
	//Return Molecular Mass of a Compound
	public static int molecularMass(String compound) 
	{  
	    char[] charArray = compound.toCharArray();
	    int result = 0;
	    int index = 0;
	    while(index < charArray.length)
	    {
	    	if (charArray[index] == '(')
	    	{
	    	    int tempResult1 =  0;
	    	    index += 1;
	            while (index  < charArray.length && charArray[index] != ')')
	            {
	            	tempResult1 += getMass(charArray[index]);
	                index += 1;    
	                if((index)  < charArray.length && (charArray[index] >= '0' && charArray[index] <= '9') )
	                {
		           String number1 = "";
		              while(index  < charArray.length && (charArray[index] >= '0' && charArray[index] <= '9'))
		             {
		                 number1 += charArray[index];
		                 index += 1;
		              }
		              //indentation
		           index -= 1;
		           tempResult1 = tempResult1 - getMass(charArray[index-1]) + getMass(charArray[index-1]) * (Integer.parseInt(number1));
		           index++;  
	                }
	            }
	            if((index + 1)  < charArray.length && (charArray[index + 1] >= '0' && charArray[index + 1] <= '9') )
	            {
	               String number1 = "";
	               index += 1;
	               while(index  < charArray.length && (charArray[index] >= '0' && charArray[index] <= '9'))
	               {
	        	   number1 += charArray[index];
	        	   index += 1;
	               }
	               index -= 1;
	               tempResult1 *= (Integer.parseInt(number1));
	            } 
	            result += tempResult1;
	        }
	        else{
	            int tempResult2 = 0;
	            tempResult2 +=  getMass(charArray[index]);
	            if((index + 1)  < charArray.length && (charArray[index + 1] >= '0' && charArray[index + 1] <= '9') )
	            {
	               String number2 = "";
	               index += 1;
	               while(index  < charArray.length && (charArray[index] >= '0' && charArray[index] <= '9')){
	        	   number2 += charArray[index];
	                   index += 1;
	               }
	               index -= 1;
	               tempResult2 *= (Integer.parseInt(number2));
	            }
	            result += tempResult2;
	        }
	    	index += 1;
	    }   
	    return result;
	}
	
	public static void main(String[] args) 
	{
		Scanner scan_instance = new Scanner(System.in);
		System.out.println("Enter Formula of an organic compound : ");
		String compoundString = scan_instance.next().toUpperCase();
		System.out.println("Molecular Mass of a compound is : " + molecularMass(compoundString));
		scan_instance.close();
	}
}