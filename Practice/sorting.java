import java.util.*;
import java.math.*;

public class sorting {
    public static void main(String[] args) {
	try {
	    int num = Integer.valueOf(args[0]);
	    int[] arr = new int[num];
    
	    Random r = new Random();
	    for (int i = 0; i < arr.length; i++) {
		arr[i] = Math.abs(r.nextInt()); 
	    }
	    //print(arr);
	    int[] sortedArr = mergesort(arr);
	    //print(sortedArr);
	    if (checkSorted(sortedArr)) {
		System.out.println("Sorted!"); 
	    } else {
		System.out.println("Not Sorted.."); 
	    }
	} catch (Exception e) {
	    System.out.println("Please call this program with an integer as the first and only argument.");
	}	
    }
  
    public static boolean checkSorted(int[] arr) {
	int prev = arr[0];
	for (int num : arr) {
	    if (num < prev) return false;
	    prev = num;
	}
	return true;
    } 
    
    public static void print(int[] arr) {
	for (int num : arr) System.out.print(num + " ");
	System.out.print("\n");
    }
  
    public static int[] mergesort(int[] arr) {
	//Base case
	if (arr.length == 1) return arr;
    
	//Split
	int mid = arr.length / 2;
    
	int[] left = new int[mid];
	int[] right = new int[arr.length - mid];
	for (int l = 0; l < mid; l++) {
	    left[l] = arr[l]; 
	}
	for (int i = mid, r = 0; i < arr.length; i++, r++) {
	    right[r] = arr[i]; 
	}
    
	left = mergesort(left);
	right = mergesort(right);
    
	return merge(left, right);
    }
  
    public static int[] merge(int[] left, int[] right) {
	int[] merged = new int[left.length + right.length];
    
	int l = 0;
	int r = 0;
	int finger = 0;
	while (l < left.length && r < right.length) {
	    if (left[l] < right[r]) {
		merged[finger] = left[l];
		l++;
	    } else {
		merged[finger] = right[r];
		r++;
	    }
	    finger++;
	}
	while (l < left.length) {
	    merged[finger] = left[l];
	    l++;
	    finger++;
	}
	while (r < right.length) {
	    merged[finger] = right[r];
	    r++;
	    finger++;
	}
	return merged;
    }
}
