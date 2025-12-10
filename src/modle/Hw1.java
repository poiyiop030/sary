import java.util.Arrays;
import java.util.Random;

public class DataStructureAssignment {

    // =================================================================================================
    // 1. Array Solutions (Questions 1-4)
    // =================================================================================================

    /**
     * Q1: Write a program to clone an array.
     * @param originalArray The array to be cloned.
     * @return A new array that is a clone of the original.
     */
    public static int[] cloneArray(int[] originalArray) {
        // Using the built-in clone() method for simplicity and efficiency
        return originalArray.clone();
    }

    /**
     * Q2: Write a program in Java to remove a random element from an array.
     * This implementation creates a new array of size n-1 and copies elements,
     * skipping the random element.
     * @param arr The array to modify.
     * @return The new array with a random element removed.
     */
    public static int[] removeRandomElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        Random rand = new Random();
        int indexToRemove = rand.nextInt(arr.length);
        System.out.println("Removing element at index: " + indexToRemove + " (Value: " + arr[indexToRemove] + ")");

        int[] newArr = new int[arr.length - 1];
        int newArrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != indexToRemove) {
                newArr[newArrIndex++] = arr[i];
            }
        }
        return newArr;
    }

    /**
     * Q3: Write a program in Java to remove a specific element from an array.
     * Similar to Q2, it creates a new array, skipping the first occurrence of the specified element.
     * @param arr The array to modify.
     * @param elementToRemove The value of the element to remove.
     * @return The new array with the specific element removed.
     */
    public static int[] removeSpecificElement(int[] arr, int elementToRemove) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int indexToRemove = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elementToRemove) {
                indexToRemove = i;
                break; // Remove only the first occurrence
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Element " + elementToRemove + " not found.");
            return arr.clone(); // Return a copy of the original array
        }

        System.out.println("Removing first occurrence of " + elementToRemove + " at index: " + indexToRemove);
        int[] newArr = new int[arr.length - 1];
        int newArrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != indexToRemove) {
                newArr[newArrIndex++] = arr[i];
            }
        }
        return newArr;
    }

    /**
     * Q4: Write a Java program to reverse an array.
     * Uses the two-pointer approach to reverse the array in-place.
     * @param arr The array to be reversed.
     */
    public static void reverseArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // Swap elements at start and end
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    // =================================================================================================
    // 2. Singly Linked List Implementation (Questions 5, 6, 7, 8, 9)
    // =================================================================================================

    static class SinglyNode {
        int data;
        SinglyNode next;

        public SinglyNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        SinglyNode head;

        public SinglyLinkedList() {
            this.head = null;
        }

        // Helper method to add a node to the end
        public void add(int data) {
            SinglyNode newNode = new SinglyNode(data);
            if (head == null) {
                head = newNode;
            } else {
                SinglyNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        // Helper method to print the list
        public void printList() {
            SinglyNode current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }

        /**
         * Q5: Write a function to concatenate two linked lists.
         * Appends the second list to the end of the first list.
         * @param list2 The second list to concatenate.
         */
        public void concatenate(SinglyLinkedList list2) {
            if (this.head == null) {
                this.head = list2.head;
            } else {
                SinglyNode current = this.head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = list2.head;
            }
            // Clear the head of the second list to prevent dangling references if list2 is still used
            list2.head = null;
        }

        /**
         * Q6: Write a function to rotate a linked list to the right by k places, where k is a non-negative integer.
         * @param k The number of places to rotate to the right.
         */
        public void rotateRight(int k) {
            if (head == null || k <= 0) return;

            // 1. Find the length and the last node
            SinglyNode oldTail = head;
            int length = 1;
            while (oldTail.next != null) {
                oldTail = oldTail.next;
                length++;
            }

            // k might be larger than the length of the list
            k = k % length;
            if (k == 0) return;

            // 2. Make the list circular
            oldTail.next = head;

            // 3. Find the new tail: (length - k) steps from the head
            SinglyNode newTail = head;
            for (int i = 0; i < length - k - 1; i++) {
                newTail = newTail.next;
            }

            // 4. Set the new head and break the circle
            head = newTail.next;
            newTail.next = null;
        }

        /**
         * Q7: Write a function to search for element in singly linked list and return its position.
         * Q8: Write a function to find the index of a given data value in a linked list. If the data value is not found in the linked list, return -1.
         * (Combining Q7 and Q8 as they are very similar, returning the 0-based index/position).
         * @param data The data value to search for.
         * @return The 0-based index of the element, or -1 if not found.
         */
        public int searchElement(int data) {
            SinglyNode current = head;
            int position = 0;
            while (current != null) {
                if (current.data == data) {
                    return position;
                }
                current = current.next;
                position++;
            }
            return -1;
        }

        /**
         * Q9: Write a function to remove at specific position from singly linked list.
         * @param position The 0-based index of the node to remove.
         * @return true if removal was successful, false otherwise.
         */
        public boolean removeAtPosition(int position) {
            if (head == null || position < 0) {
                return false;
            }

            // Case 1: Remove the head (position 0)
            if (position == 0) {
                head = head.next;
                return true;
            }

            // Case 2: Remove a node in the middle or end
            SinglyNode current = head;
            SinglyNode previous = null;
            int count = 0;

            while (current != null && count < position) {
                previous = current;
                current = current.next;
                count++;
            }

            // If position is out of bounds
            if (current == null) {
                return false;
            }

            // Skip the current node
            previous.next = current.next;
            return true;
        }
    }

    // =================================================================================================
    // 3. Doubly Linked List Implementation (Questions 10, 11, 12)
    // =================================================================================================

    static class DoublyNode {
        int data;
        DoublyNode next;
        DoublyNode prev;

        public DoublyNode(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        DoublyNode head;
        DoublyNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        // Helper method to add a node to the end
        public void add(int data) {
            DoublyNode newNode = new DoublyNode(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        // Helper method to print the list
        public void printList() {
            DoublyNode current = head;
            while (current != null) {
                System.out.print(current.data + " <-> ");
                current = current.next;
            }
            System.out.println("null");
        }

        /**
         * Q10: Write a function to remove duplicates elements from doubly linked list.
         * Uses a temporary set to keep track of seen elements.
         */
        public void removeDuplicates() {
            if (head == null) return;

            java.util.HashSet<Integer> seen = new java.util.HashSet<>();
            DoublyNode current = head;

            while (current != null) {
                if (seen.contains(current.data)) {
                    // Duplicate found, remove the current node
                    DoublyNode nextNode = current.next;
                    DoublyNode prevNode = current.prev;

                    if (prevNode != null) {
                        prevNode.next = nextNode;
                    } else {
                        // Current is the head
                        head = nextNode;
                    }

                    if (nextNode != null) {
                        nextNode.prev = prevNode;
                    } else {
                        // Current is the tail
                        tail = prevNode;
                    }
                    // Move current to the next node
                    current = nextNode;
                } else {
                    // Not a duplicate, add to set and move to next
                    seen.add(current.data);
                    current = current.next;
                }
            }
        }

        /**
         * Q11: Write a function to traverse a doubly linked list in reverse and print all the elements.
         */
        public void traverseReverse() {
            DoublyNode current = tail;
            while (current != null) {
                System.out.print(current.data + " <-> ");
                current = current.prev;
            }
            System.out.println("null (Reverse Traversal)");
        }

        /**
         * Q12: Write a function to search for an element in a doubly linked list.
         * @param data The data value to search for.
         * @return The 0-based index of the element, or -1 if not found.
         */
        public int searchElement(int data) {
            DoublyNode current = head;
            int position = 0;
            while (current != null) {
                if (current.data == data) {
                    return position;
                }
                current = current.next;
                position++;
            }
            return -1;
        }
    }

    // =================================================================================================
    // 4. Circular Linked List Implementation (Questions 13, 14, 15, 16)
    // =================================================================================================

    static class CircularNode {
        int data;
        CircularNode next;

        public CircularNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class CircularLinkedList {
        CircularNode head;

        public CircularLinkedList() {
            this.head = null;
        }

        // Helper method to add a node to the end (maintaining circularity)
        public void add(int data) {
            CircularNode newNode = new CircularNode(data);
            if (head == null) {
                head = newNode;
                head.next = head; // Point to itself
            } else {
                CircularNode current = head;
                while (current.next != head) {
                    current = current.next;
                }
                current.next = newNode;
                newNode.next = head;
            }
        }

        // Helper method to print the list
        public void printList() {
            if (head == null) {
                System.out.println("Circular List is empty.");
                return;
            }
            CircularNode current = head;
            do {
                System.out.print(current.data + " -> ");
                current = current.next;
            } while (current != head);
            System.out.println("(Head: " + head.data + ")");
        }

        /**
         * Q13: Write a function to insert a node at a specific position in a circular linked list.
         * @param data The data to insert.
         * @param position The 0-based index to insert at.
         */
        public void insertAtPosition(int data, int position) {
            CircularNode newNode = new CircularNode(data);

            if (position < 0) {
                System.out.println("Invalid position.");
                return;
            }

            if (head == null) {
                if (position == 0) {
                    head = newNode;
                    head.next = head;
                } else {
                    System.out.println("List is empty, cannot insert at position > 0.");
                }
                return;
            }

            // Case 1: Insert at head (position 0)
            if (position == 0) {
                CircularNode last = head;
                while (last.next != head) {
                    last = last.next;
                }
                newNode.next = head;
                last.next = newNode;
                head = newNode;
                return;
            }

            // Case 2: Insert in the middle or end
            CircularNode current = head;
            int count = 0;
            while (current.next != head && count < position - 1) {
                current = current.next;
                count++;
            }

            if (count != position - 1) {
                System.out.println("Position out of bounds.");
                return;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        /**
         * Q14: Write a function to delete a node from a specific position in a circular linked list.
         * @param position The 0-based index of the node to delete.
         * @return true if deletion was successful, false otherwise.
         */
        public boolean deleteAtPosition(int position) {
            if (head == null || position < 0) {
                return false;
            }

            // Find the last node
            CircularNode last = head;
            while (last.next != head) {
                last = last.next;
            }

            // Case 1: Only one node in the list
            if (head.next == head) {
                if (position == 0) {
                    head = null;
                    return true;
                } else {
                    return false;
                }
            }

            // Case 2: Delete the head (position 0)
            if (position == 0) {
                head = head.next;
                last.next = head;
                return true;
            }

            // Case 3: Delete a node in the middle or end
            CircularNode current = head;
            CircularNode previous = null;
            int count = 0;

            // Stop when current.next == head to avoid infinite loop and handle last element
            while (current.next != head && count < position) {
                previous = current;
                current = current.next;
                count++;
            }

            // If position is out of bounds (reached the end of the list before the position)
            if (count != position) {
                return false;
            }

            // Skip the current node
            previous.next = current.next;
            return true;
        }

        /**
         * Q15: Write a function to search for an element in a circular linked list.
         * @param data The data value to search for.
         * @return The 0-based index of the element, or -1 if not found.
         */
        public int searchElement(int data) {
            if (head == null) return -1;

            CircularNode current = head;
            int position = 0;
            do {
                if (current.data == data) {
                    return position;
                }
                current = current.next;
                position++;
            } while (current != head);

            return -1;
        }

        /**
         * Q16: Write a function to split a circular linked list into two halves.
         * Uses the slow/fast pointer technique.
         * @return An array containing the two new CircularLinkedLists.
         */
        public CircularLinkedList[] splitList() {
            if (head == null) {
                return new CircularLinkedList[]{new CircularLinkedList(), new CircularLinkedList()};
            }

            CircularNode slow = head;
            CircularNode fast = head;

            // Find the middle node (slow will point to the last node of the first half)
            while (fast.next != head && fast.next.next != head) {
                fast = fast.next.next;
                slow = slow.next;
            }

            // If the list has an even number of elements, fast.next will be the head.
            // If the list has an odd number of elements, fast.next.next will be the head.
            // In both cases, slow is the end of the first half.

            // First half: head to slow
            CircularLinkedList list1 = new CircularLinkedList();
            list1.head = head;
            CircularNode head2 = slow.next; // Head of the second half

            // Break the circle for the first list
            slow.next = head;

            // Second half: head2 to the original tail (which is last.next)
            CircularLinkedList list2 = new CircularLinkedList();
            list2.head = head2;

            // Find the original tail (which is the node before the original head)
            CircularNode originalTail = head2;
            while (originalTail.next != head) {
                originalTail = originalTail.next;
            }

            // Break the circle for the second list
            originalTail.next = head2;

            // If the list had only one element, slow.next is head, head2 is head.
            if (head == head2) {
                list1.head = head;
                list2.head = null;
                slow.next = head; // Restore single node circularity
            } else {
                // Correct the circularity for the first list
                CircularNode last1 = slow;
                last1.next = list1.head;

                // Correct the circularity for the second list
                CircularNode last2 = originalTail;
                last2.next = list2.head;
            }

            return new CircularLinkedList[]{list1, list2};
        }
    }

    // =================================================================================================
    // 5. Main Method for Demonstration
    // =================================================================================================

    public static void main(String[] args) {
        System.out.println("--- Data Structure Assignment Solutions (Java) ---");

        // -----------------------------------------------------------------
        // Array Demonstrations (Q1-Q4)
        // -----------------------------------------------------------------
        System.out.println("\n[1. Array Solutions (Q1-Q4)]");
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("Original Array: " + Arrays.toString(arr));

        // Q1: Clone Array
        int[] clonedArr = cloneArray(arr);
        clonedArr[0] = 99; // Modify clone to prove it's a deep copy
        System.out.println("Q1 (Clone): Cloned Array (Modified): " + Arrays.toString(clonedArr));
        System.out.println("Original Array (Unchanged): " + Arrays.toString(arr));

        // Q2: Remove Random Element
        int[] arrAfterRandomRemoval = removeRandomElement(arr);
        System.out.println("Q2 (Remove Random): Array after removal: " + Arrays.toString(arrAfterRandomRemoval));

        // Q3: Remove Specific Element
        int[] arrWithDuplicates = {1, 5, 10, 5, 20};
        System.out.println("Array with Duplicates: " + Arrays.toString(arrWithDuplicates));
        int[] arrAfterSpecificRemoval = removeSpecificElement(arrWithDuplicates, 5);
        System.out.println("Q3 (Remove Specific '5'): Array after removal: " + Arrays.toString(arrAfterSpecificRemoval));

        // Q4: Reverse Array
        int[] arrToReverse = {1, 2, 3, 4, 5};
        System.out.println("Array to Reverse: " + Arrays.toString(arrToReverse));
        reverseArray(arrToReverse);
        System.out.println("Q4 (Reverse): Reversed Array: " + Arrays.toString(arrToReverse));

        // -----------------------------------------------------------------
        // Singly Linked List Demonstrations (Q5-Q9)
        // -----------------------------------------------------------------
        System.out.println("\n[2. Singly Linked List Solutions (Q5-Q9)]");
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.add(1); list1.add(2); list1.add(3);
        System.out.print("List 1: "); list1.printList();

        // Q5: Concatenate
        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.add(4); list2.add(5); list2.add(6);
        System.out.print("List 2: "); list2.printList();
        list1.concatenate(list2);
        System.out.print("Q5 (Concatenate): List 1 after concatenation: "); list1.printList();

        // Q6: Rotate Right by k=2
        list1.rotateRight(2);
        System.out.print("Q6 (Rotate Right by 2): List 1 after rotation: "); list1.printList(); // Should be 5 -> 6 -> 1 -> 2 -> 3 -> 4 -> null

        // Q7 & Q8: Search Element
        int searchData = 2;
        int position = list1.searchElement(searchData);
        System.out.println("Q7/Q8 (Search '"+searchData+"'): Found at position: " + position);

        // Q9: Remove at Position
        int removePos = 3;
        list1.removeAtPosition(removePos);
        System.out.print("Q9 (Remove at pos "+removePos+"): List 1 after removal: "); list1.printList(); // Should remove '2'

        // -----------------------------------------------------------------
        // Doubly Linked List Demonstrations (Q10-Q12)
        // -----------------------------------------------------------------
        System.out.println("\n[3. Doubly Linked List Solutions (Q10-Q12)]");
        DoublyLinkedList dList = new DoublyLinkedList();
        dList.add(10); dList.add(20); dList.add(10); dList.add(30); dList.add(20); dList.add(40);
        System.out.print("Original Doubly List: "); dList.printList();

        // Q10: Remove Duplicates
        dList.removeDuplicates();
        System.out.print("Q10 (Remove Duplicates): List after removal: "); dList.printList();

        // Q11: Traverse Reverse
        System.out.print("Q11 (Traverse Reverse): "); dList.traverseReverse();

        // Q12: Search Element
        int searchDataD = 30;
        int positionD = dList.searchElement(searchDataD);
        System.out.println("Q12 (Search '"+searchDataD+"'): Found at position: " + positionD);

        // -----------------------------------------------------------------
        // Circular Linked List Demonstrations (Q13-Q16)
        // -----------------------------------------------------------------
        System.out.println("\n[4. Circular Linked List Solutions (Q13-Q16)]");
        CircularLinkedList cList = new CircularLinkedList();
        cList.add(100); cList.add(200); cList.add(300); cList.add(400);
        System.out.print("Original Circular List: "); cList.printList();

        // Q13: Insert at Position (Insert 50 at pos 0, Insert 250 at pos 3)
        cList.insertAtPosition(50, 0);
        System.out.print("Q13 (Insert 50 at pos 0): "); cList.printList(); // Should be 50 -> 100 -> 200 -> 300 -> 400 -> (Head: 50)
        cList.insertAtPosition(250, 3);
        System.out.print("Q13 (Insert 250 at pos 3): "); cList.printList(); // Should be 50 -> 100 -> 200 -> 250 -> 300 -> 400 -> (Head: 50)

        // Q15: Search Element
        int searchDataC = 250;
        int positionC = cList.searchElement(searchDataC);
        System.out.println("Q15 (Search '"+searchDataC+"'): Found at position: " + positionC);

        // Q14: Delete at Position (Delete at pos 1, which is 100)
        cList.deleteAtPosition(1);
        System.out.print("Q14 (Delete at pos 1): "); cList.printList(); // Should be 50 -> 200 -> 250 -> 300 -> 400 -> (Head: 50)

        // Q16: Split List
        CircularLinkedList[] splitLists = cList.splitList();
        System.out.println("Q16 (Split List):");
        System.out.print("  First Half: "); splitLists[0].printList(); // Should be 50 -> 200 -> 250 -> (Head: 50)
        System.out.print("  Second Half: "); splitLists[1].printList(); // Should be 300 -> 400 -> (Head: 300)
    }
}
