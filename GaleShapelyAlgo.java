import java.lang.*;
import java.util.*;

public class GaleShapelyAlgo {

    /**
     * Gale-Shapely Algorithm is a stable matching algorithm used for a 
     * variety of matching scenarios.
     * One of the more notable uses is matching Med school students to hospitals
     * The input should consist of an integer n, indicating the number 
     * of item1 or item2. The second value should be a file location containing 
     * the rankings.
     **/
    public static void main(String args[]) {
	int n = 3;
	try {
	    n = Integer.valueOf(args[0]);
	    //String file = args[1];
	    
	    //Open file and retrive data
	} catch (Exception e) {
	    System.out.println(e);
	      System.exit(1);
	}

	Random ints = new Random();
	int[][] testSet = new int[n][n];
	
	for (int i = 0; i < n; i++) {
	    HashSet<Integer> currRow = new HashSet<Integer>();
	    int value = ints.nextInt(n);
	    testSet[i][0] = value;
	    currRow.add(value);
	    //System.out.print(value + " ");
	    for (int j = 1; j < n; j++) {
		value = ints.nextInt(n);
		while (currRow.contains(value)) {
		    value = ints.nextInt(n);
		}
		currRow.add(value);
		testSet[i][j] = value;
		//System.out.print(value + " ");
	    }
	    //System.out.print("\n");
	}

	ArrayList<ArrayList<student>> hospitalRankings =
	    new ArrayList<ArrayList<student>>();
	ArrayList<student> students = new ArrayList<student>();
	for (int i = 0; i < n; i++) {
	    student s = new student(testSet[i]);
	    students.add(s);
	}

	ArrayList<hospital> hospitals =
	    new ArrayList<hospital>();
	ArrayList<String> hospitalNames = createTestSet(n);
	
	for (int i = 0; i < n; i++) {
	    ArrayList<student> rankings = new ArrayList<student>();
	    HashSet<Integer> currRow = new HashSet<Integer>();
	    int value = ints.nextInt(n);
	    currRow.add(value);
	    rankings.add(students.get(value));
	    //System.out.print(value + " ");
	    for (int j = 1; j < n; j++) {
		value = ints.nextInt(n);
		while (currRow.contains(value)) {
		    value = ints.nextInt(n);
		}
		currRow.add(value);
	        rankings.add(students.get(value));
		//System.out.print(value + " ");
	    }
	    hospital h = new hospital(hospitalNames.get(i), i, rankings);
	    hospitals.add(h);
	    //System.out.print("\n");
	}

	GSAlgo(hospitals);
	int num = 0;
	for (student s : students) {
	    System.out.println(num + ": " + hospitalNames.get(s.currentMatch));
	    num++;
	}
    }


    //This will return a hashtable the key will be the first item type
    //and the value will be it's corresponding match.
    private static void GSAlgo(ArrayList<hospital> allHospitals) {
	int n = allHospitals.size();
	Hashtable<Integer, hospital> allIndexedHospitals =
	    new Hashtable<Integer, hospital>();
       
	LinkedList<hospital> hospitals = new LinkedList<hospital>();
	for (hospital h : allHospitals) {
	    hospitals.add(h);
	    allIndexedHospitals.put(h.value, h);
	}

	
	while (hospitals.size() > 0) {
	    
	    hospital h = hospitals.remove();
	    student s = h.nextPreference();

	    if (!s.matched) {
		s.tradeUp(h.value);
	    } else if (s.prefers(h.value)) {
		hospital freedHospital = allIndexedHospitals.get(s.tradeUp(h.value));
		hospitals.add(freedHospital);
	    } else {
		//reject
		hospitals.add(h);
	    }
	}

	//By now each student object should contain it's optimal match
    }

    /**
     * Creates a somewhat predetermined test set.
     * Could instead use a file of hospital names to extract n names.
     */
    private static ArrayList<String> createTestSet(int num) {
	ArrayList<String> testSet = new ArrayList<String>();

	switch(num) {
	default:
	    
	case 10:
	    testSet.add("John Hopkins Hospital");
	case 9:
	    testSet.add("Mayo Clinic");
	case 8:
	    testSet.add("Cedars-Sinai Medical Center");
	case 7:
	    testSet.add("Cleveland Clinic");
	case 6:
	    testSet.add("Memorial Sloan Kettering Cancer Center");
	case 5:
	    testSet.add("Massachusetts General Hospital");
	case 4:
	    testSet.add("UCSF Medical Center");
	case 3:
	    testSet.add("University of Texas MD Anderson Cancer Center");
	case 2:
	    testSet.add("Wills Eye Hospital");
	case 1:
	    testSet.add("UCLA Medical Center");
	}
	return testSet;
	
    }

}


/**
 * Student object containing relevant fields and methods.
 **/
class student {
    boolean matched;
    Integer currentMatch;
    Hashtable<Integer, Integer> preferences;

    public student(int[] hospitals) {
	this.matched = false;
	this.preferences = new Hashtable<Integer, Integer>();

	int rank = 0;
	//System.out.print("\n");
	for (Integer h : hospitals) {
	    this.preferences.put(h, rank);
	    rank++;
	    //System.out.print(h + ": " + rank + " ~ ");
	}
	//System.out.print("\n");
    }

    public boolean prefers(Integer offer) {
	if (this.matched == false || this.currentMatch == null) {
	    return true;
	}
	Integer rank1 = this.preferences.get(this.currentMatch);
	Integer rank2 = this.preferences.get(offer);
	if (rank2 < rank1) {
	    return true;
	}
	return false;
    }

    public Integer tradeUp(Integer newMatch) {
	this.matched = true;
	Integer h = this.currentMatch;
	this.currentMatch = newMatch;
	return h;
    }
}

/**
 * hospital object containing relevant fields and methods
 **/
class hospital {
    LinkedList<student> preferences = new LinkedList<student>();
    Integer value;
    String name;

    public hospital(String name, Integer value, ArrayList<student> rankings) {
	this.name = name;
	this.value = value;
	for(int i = 0; i < rankings.size(); i++) {
	    this.preferences.add(rankings.get(i));
	}
    }
    
    public hospital(Integer value, ArrayList<student> rankings) {
	this.value = value;
	for(int i = 0; i < rankings.size(); i++) {
	    this.preferences.add(rankings.get(i));
	}
    }

    public student nextPreference() {
	return this.preferences.poll();
    }

}
