import java.util.Random;
import java.util.Scanner;

public class AssertPancake {

    /**
     * This is my testing main function
     * @param args
     */
    public static void main(String[] args) {
        Random rng = new Random(System.currentTimeMillis());
        String input;
        FlipPancakes temp;
        String output;
//        String input1 = "5 +1 +2 +3 +4 -6";
//        String solution1 = "3 5 1 5";
//        String input2 = "4 -3 +1 -2 -4";
//        String solution2 = "6 4 1 4 3 1 2";
//        String input3 = "3 +1 -3 -2";
//        String solution3 = "3 3 2 3";
//        String input4 = "6 -6 +5 +12 -1 +7 -2";
//        String solution4 = "11 4 6 5 6 3 1 5 1 2 4 2";
//        String input5 = "6 -3 +2 +5 -4 -1 +6";
//        String solution5 = "7 1 4 3 4 3 1 5";
//        String input6 = "6 +3 -4 -1 +2 -5 -6";
//        String input7 = "6 -3 -5 -6 -2 +4 +1";
//        String input8 = "5 -3 -2 -4 -5 -1";
//        String input9 = "6 -4 -5 -6 -2 -3 -1";
        String input10 = "5 -4 +1 -2 -5 +3";
        String input11 = "6 +3 +5 -1 +4 -2 -6";
        String input12 = "5 -1 -4 +5 -2 +3";
        String input13 = "6 +1 +3 +2 -4 -5 -6";
//        input = input9;
//        temp = new FlipPancakes(input);
//        output = temp.getResult();
//        System.out.println("test input: " + input + " " + AssertPancake.assertTrue(input, output) + " " + output);
//        return;
        final int testcount = 10000;
        double successes = 0;
        for (int i = 0; i < testcount; i++) {
            input = (new AssertPancake(rng)).toString();
            temp = new FlipPancakes(input);
            output = temp.getResult();
            boolean result = AssertPancake.assertTrue(input, output);
            if (result) {successes+=1;}
            if (!result) {
                System.out.println(result + "\t" + input + " : " + output);
            }
        }
        double rate = successes / testcount;
        System.out.println("success rate: " + rate);
    }

    public static boolean assertTrue(String argument, String solution){
        AssertPancake data = new AssertPancake(argument);
        data.flipset(solution);
        return data.isOrdered();
    }

    Stack<Integer> _stack;

    /**
     * generates a pancake stack based on input string
     * @param input
     */
    public AssertPancake(String input){
        _stack = new ArrayStack<>();
        Stack<Integer> tempstack = new ArrayStack<>();
        Scanner in = new Scanner(input);
        int length = in.nextInt();
        for(int i = 0; i < length; i++){
            tempstack.push(in.nextInt());
        }
        while(!tempstack.isEmpty()){
            _stack.push(tempstack.pop());
        }
    }

    /**
     * generates random pancake stack
     */
    public AssertPancake(Random r){
        //random array generation, unique magnitudes and nonzero.
        int[] arr = new int[r.nextInt(3) + 4];
        for(int i : arr){
            i = 0;
        }
        int fillsize = arr.length;
        while (fillsize > 0){
            int indexpick = r.nextInt(fillsize); //when this is zero push into first zero entry
            int i = 0;
            while(indexpick > 0 || arr[i] != 0){
                if (arr[i] == 0) {
                    indexpick--;
                }
                i++;
            }
            if (r.nextInt() % 2 == 1){
                arr[i] = fillsize;
            } else {
                arr[i] = -fillsize;
            }
            fillsize--;
        }
        //push into stack
        _stack = new ArrayStack<>();
        for(int i : arr){
            _stack.push(i);
        }
    }

    public void flip(int count) {
        Queue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < count; i++) {
            queue.enqueue(_stack.pop());
        }
        for (int i = 0; i < count; i++) {
            _stack.push(-1 * queue.dequeue());
        }
    }

    public void flipset(String solution) {
        Scanner in = new Scanner(solution);
        int length = in.nextInt();
        for(int i = 0; i < length; i++){
            flip(in.nextInt());
        }
    }

    public boolean isOrdered(){
        Stack<Integer> tempstack = new ListStack<>();
        boolean retval = true;//assume true
        int lastval = 0;
        while (!_stack.isEmpty()){
            int temp = _stack.pop();
            tempstack.push(temp);
            if (temp < lastval){// last pancake is larger than the one benieth it, or burnt up.
                retval = false;//proven false
                break;
            }
            lastval = temp;
        }
        while(!tempstack.isEmpty()){
            _stack.push(tempstack.pop());
        }
        return retval;
    }

    /**
     * Prints a potential input string representation of current state and preserves data.
     *
     * @return String conforming to pankaken input standards
     */
    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        ret.append(_stack.size());
        Stack<Integer> tempstack = new ListStack<>();
        while (!_stack.isEmpty()){
            ret.append(' ');
            int temp = _stack.pop();
            tempstack.push(temp);
            if (temp > 0){
                ret.append('+');
            } else if (temp < 0) {
                ret.append('-');
            }
            ret.append(java.lang.Math.abs(temp));// JAVA STANDARD ABSOLUTE VALUE FUNCTION
        }
        while(!tempstack.isEmpty()){
            _stack.push(tempstack.pop());
        }
        return ret.toString();
    }

}
