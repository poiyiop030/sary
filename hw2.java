package HomeWork_Tow;

import java.util.*;

/*
====================================================
        Data Structure - Assignment 2
        Name   : Mohammed Sari
        Group  : (اكتب مجموعتك هنا)
====================================================


*/

public class Home_tow {
    public static void main(String[] args) {

        /*
        ====================================================
                        Question 1
           Reverse a String using Stack
        ====================================================
        */

//        String text = "DATASTRUCTURE";
//        System.out.println(StackQuestions.reverseString(text));



        /*
        ====================================================
                        Question 2
          Sort a Stack using another Stack
        ====================================================
        */

//        Stack<Integer> stack = new Stack<>();
//        stack.push(3);
//        stack.push(1);
//        stack.push(4);
//        stack.push(2);
//
//        StackQuestions.sortStack(stack);
//        System.out.println(stack);



        /*
        ====================================================
                        Question 3
           Reverse elements of a Queue
        ====================================================
        */

        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);

        QueueQuestions.reverseQueue(queue);
        System.out.println(queue);



        /*
        ====================================================
                        Question 4
           Priority Queue (Smallest Dequeue First)
        ====================================================
        */
        /*
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);

        while (!pq.isEmpty())
            System.out.print(pq.poll() + " ");
        */


        /*
        ====================================================
                        Question 5
        Merge Two Sorted Queues into One Sorted Queue
        ====================================================
        */
        /*
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(1);
        q1.add(3);
        q1.add(5);

        Queue<Integer> q2 = new LinkedList<>();
        q2.add(2);
        q2.add(4);
        q2.add(6);

        Queue<Integer> merged =
                QueueQuestions.mergeSortedQueues(q1, q2);

        System.out.println(merged);
        */

    }
}

/*
====================================================
                Stack Questions
====================================================
*/
class StackQuestions {

    // Question 1: Reverse string using stack
    static String reverseString(String text) {
        Stack<Character> stack = new Stack<>();

        for (char c : text.toCharArray())
            stack.push(c);

        String reversed = "";
        while (!stack.isEmpty())
            reversed += stack.pop();

        return reversed;
    }

    // Question 2: Sort stack using another stack
    static void sortStack(Stack<Integer> stack) {
        Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            int temp = stack.pop();

            while (!tempStack.isEmpty() && tempStack.peek() > temp)
                stack.push(tempStack.pop());

            tempStack.push(temp);
        }

        while (!tempStack.isEmpty())
            stack.push(tempStack.pop());
    }
}

/*
====================================================
                Queue Questions
====================================================
*/
class QueueQuestions {

    // Question 3: Reverse queue
    static void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty())
            stack.push(queue.poll());

        while (!stack.isEmpty())
            queue.add(stack.pop());
    }

    // Question 5: Merge two sorted queues
    static Queue<Integer> mergeSortedQueues(
            Queue<Integer> q1, Queue<Integer> q2) {

        Queue<Integer> result = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek() <= q2.peek())
                result.add(q1.poll());
            else
                result.add(q2.poll());
        }

        while (!q1.isEmpty())
            result.add(q1.poll());

        while (!q2.isEmpty())
            result.add(q2.poll());

        return result;
    }
}
