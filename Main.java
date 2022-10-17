public class Main {
    public static void main(String[] args) {
        String input = "5 +1 +2 +3 +4 -6";
        String solution = "3 5 1 5";
        System.out.println("original input:"+input);
        FlipPancakes john = new FlipPancakes(input);
        System.out.println("State of structure:"+john);
        john.flipset(solution);
        System.out.println("State after solution:"+john+john.isOrdered());
        System.out.println("first: "+FlipPancakes.assertTrue("4 -3 +1 -2 -4","6 4 1 4 3 1 2"));
        System.out.println("second: "+FlipPancakes.assertTrue("3 +1 -3 -2","3 3 2 3"));
        System.out.println("third: "+FlipPancakes.assertTrue("5 +1 +2 +3 +4 -6","3 5 1 5"));
        System.out.println("fourth aka failure: "+FlipPancakes.assertTrue("5 +1 +2 +3 -4 -6","3 5 1 5"));
    }
}