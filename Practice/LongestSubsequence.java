import java.util.*;
import java.lang.*;

public class LongestSubsequence {

    // For simplicity assuming all words and s are lowercased
    public static String solution(String s, String[] words) {
	// Could sort words in O(nlog(n)) time to move the longest words to the front
	// lS is longest Subsequence
	int lS = 0;
	int sLength = s.length();
	String result = "";

	// Loop through all of the words and check if their
	// first character is contained within s
	for (String w : words) {
	    int i = 0;

	    // minor optimization
	    // if the current word is shorter
	    // than our current longest subsequenece
	    // then this word can not be longer than
	    // the current longest subsequence
	    if (w.length() < lS || w.length() == 0) continue;
	    
	    // Loop through each character in the word, s
	    // only move onto the next char in w if
	    // it is found in s
	    for (char c : s.toCharArray()) {
		if (c == w.charAt(i)) {
		    i++;
		}
		if (i >= w.length()) {
		    break;
		}
	    }

	    // If i reaches the end of the word and the word
	    // is longer than the previous subsequence found
	    // then w is our new running champion
	    if (i > lS && i == w.length()) {
		lS = i;
		result = w;
	    }

	    // if we found a word identical to s then we know we can not
	    // possibly do better so just skip the rest of the words
	    if (lS == sLength) break;
	}

	return result;

    }

    public static void main(String[] args) {
	String[] words = new String[8];
	words[0] = "hourse";
	words[1] = "dog";
	words[2] = "shoe";
	words[3] = "gazebo";
	words[4] = "elephant";
	words[5] = "hous";
	words[6] = "";
	words[7] = "lioness";

	String s = "house";

	String result = solution(s, words);
	System.out.println(result);

    }




}
