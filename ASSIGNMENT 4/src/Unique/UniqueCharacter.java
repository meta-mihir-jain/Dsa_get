package Unique;
import java.util.*;
class UniqueCharacter{
    private static HashMap<String, Integer> hashMap  = new HashMap<>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Integer calcUniqueCharacter(String inputString){
        List tempList = new ArrayList();
        if(hashMap.containsKey(inputString))
        {
	   return hashMap.get(inputString);
        }
	else{
	   for(int i=0;i<inputString.length();i++)
	   {
	       if(tempList.contains(inputString.charAt(i)))
	       {
		  continue;
	       }
	       else
	       {
		  tempList.add(inputString.charAt(i));
	       }
	   }
	   hashMap.put(inputString, tempList.size());
	}
	return tempList.size();
     }
	
     @SuppressWarnings("static-access")
	public static void main(String args[])
     {
	 @SuppressWarnings("resource")
	Scanner scan_instance = new Scanner(System.in);
	 UniqueCharacter object = new UniqueCharacter();
	 System.out.println("For terminate the program press -1 ");
	 System.out.println("Enter String :");
	 String inputString = scan_instance.next();
	 if(inputString.equals("-1"))
	    System.exit(0);
	 else
	 {
	    while(!inputString.equals("-1")){
		System.out.println("Number of unique charcter in String is : " + object.calcUniqueCharacter(inputString));
		inputString = scan_instance.next();
	    }
	 }   
     }
}