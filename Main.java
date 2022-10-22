public class Main {
    public static void main(String[] args) {
        String input = "5 +1 +2 +3 +4 -6";
        String solution = "3 5 1 5";
        System.out.println("original input:"+input);
        AssertPancake john = new AssertPancake(input);
        System.out.println("State of structure:"+john);
        john.flipset(solution);
        System.out.println("State after solution:"+john+john.isOrdered());
        System.out.println("first: "+ AssertPancake.assertTrue("4 -3 +1 -2 -4","6 4 1 4 3 1 2"));
        System.out.println("second: "+ AssertPancake.assertTrue("3 +1 -3 -2","3 3 2 3"));
        System.out.println("third: "+ AssertPancake.assertTrue("5 +1 +2 +3 +4 -6","3 5 1 5"));
        System.out.println("fourth aka failure: "+ AssertPancake.assertTrue("5 +1 +2 +3 -4 -6","3 5 1 5"));
        System.out.println("fifth: "+ AssertPancake.assertTrue("6 -6 +5 +12 -1 +7 -2","11 4 6 5 6 3 1 5 1 2 4 2"));
    }
}