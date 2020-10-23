import java.util.*;

public class SinglyLinkedListReversal {
    
  class SinglyLinkedListNode {
    int value;
    SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data) {
      this.value = data;
      this.next = null;
    }
  }

    public SinglyLinkedListNode head = null;
    public SinglyLinkedListNode tail = null;

    public SinglyLinkedListNode addNode(int data) {
	  
      SinglyLinkedListNode node = new SinglyLinkedListNode(data);
      
      if(head == null) {
        head = node;
	tail = node;
      } else {
	tail.next = node;
	tail = node;  
      }
      return node;
    }
      
    private void printList() {
      SinglyLinkedListNode finger = head;
	
      while (finger != null) {
	System.out.print(finger.value + " ");
	finger = finger.next;
      }
      System.out.print("\n");
    }

    private SinglyLinkedListNode reverse() {
    //Note that it might've been better to name finger lastNewHead
      
      SinglyLinkedListNode finger = head;
      SinglyLinkedListNode newHead = head.next;
      SinglyLinkedListNode temp;
      tail = head;
      tail.next = null;

      if (head == null) return head;

      while (newHead != null) {
        temp = newHead.next;
	newHead.next = finger;
	finger = newHead;
	newHead = temp;
      }
      head = finger;

      return finger;

    }
  

  //Input will be series of ints that are to be linked
  //args[0] should be length of list
  public static void main(String[] args) {
    if (args[0] == null) {
       System.out.println("Please include list data as a series of ints!");
       System.exit(1);
    }


    SinglyLinkedListReversal sll = new SinglyLinkedListReversal();

    int length = Integer.parseInt(args[0]);
    
    for (int i = 1; i < length + 1; i++) {
      sll.addNode(Integer.parseInt(args[i]));
    }

    sll.printList();
    sll.reverse();
    sll.printList();
  }


  

}
