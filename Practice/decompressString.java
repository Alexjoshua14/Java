import java.util.*;

public class decompressString {
    public static String decompress(String input) {
	System.out.println("Decompressing " + input);

	int ptr = 0;
	int length = input.length();
	String decompressed = "";
	
	while (ptr < length) {
	    String character = input.substring(ptr, ptr + 1);
	    String string = "";
	    String num = "";
	    int numOfTimes = 1;
	    boolean decompressMe = false;
	    //Keeps track of whether we had a bracket opened within another bracket
	    //in our input string 
	    int bracketOpened = 0;
	    
	    while (ptr < length - 1 && character.matches("[0-9]")) {
		num += character;
		ptr++;
		character = input.substring(ptr, ptr + 1);
	    }
	    
	    if (!num.equals("")) {
		numOfTimes = Integer.valueOf(num);
	    }
	    if (character.equals("[")) {
		ptr++;
	    } else {
		string += num;
	    }
 
	    while (ptr < length) {
		character = input.substring(ptr, ptr + 1);
		if (character.equals("[")) bracketOpened++;		
		if (character.equals("]")) {
		    if (bracketOpened > 0) {
			bracketOpened--;
		    } else {
			break;
		    }
		}
		string += character;
		ptr++;
	    }
	    if (character.equals("]")) {
		ptr++;
	    }
	    if (string.contains("[//[//]]")) {
		string = decompress(string);
	    }
	    for (int i = 0; i < numOfTimes; i++) {
		decompressed += string;
	    }
	}
	return decompressed;
    }

    /**
     * This class decompresses a string that has been compressed in the form
     * 'number[string]number[string]...number[string]' 
     * i.e., 3[abc]2[a]5[s]def -> abcabcabcaasssssdef
     *       10[a] -> aaaaaaaaaa
     */
    public static void main(String[] args) {
	String input = "";
	try {
	    input = args[0];
	} catch (Exception e) {
	    System.out.println("Please include the input string as the first and only parameter, inpute should be in form 'number[string]number[string]...number[string]'.");
	    System.exit(1);
	}

	String decompressedString = decompress(input);
	System.out.println("Decompressed string: " + decompressedString);
    }

}
