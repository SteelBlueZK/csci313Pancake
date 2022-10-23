public class Main {
    public static void main(String[] args) {
        String input1 = "5 +1 +2 +3 +4 -6";
        String solution1 = "3 5 1 5";
        String input2 = "4 -3 +1 -2 -4";
        String solution2 = "6 4 1 4 3 1 2";
        String input3 = "3 +1 -3 -2";
        String solution3 = "3 3 2 3";
        String input4 = "6 -6 +5 +12 -1 +7 -2";
        String solution4 = "11 4 6 5 6 3 1 5 1 2 4 2";
        System.out.println("first: "+ AssertPancake.assertTrue(input1, solution1));
        System.out.println("second: "+ AssertPancake.assertTrue(input2, solution2));
        System.out.println("third: "+ AssertPancake.assertTrue(input3, solution3));
        System.out.println("fourth aka failure: "+ AssertPancake.assertTrue("5 +1 +2 +3 -4 -6","3 5 1 5"));
        System.out.println("fifth: "+ AssertPancake.assertTrue(input4, solution4));

        String input;
        FlipPancakes temp;
        String output;

        input = input1;
        temp = new FlipPancakes(input);
        output = temp.getResult();
        System.out.println("test1: " + AssertPancake.assertTrue(input, output) + " " + output);
        input = input2;
        temp = new FlipPancakes(input);
        output = temp.getResult();
        System.out.println("test2: " + AssertPancake.assertTrue(input, output) + " " + output);
        input = input3;
        temp = new FlipPancakes(input);
        output = temp.getResult();
        System.out.println("test3: " + AssertPancake.assertTrue(input, output) + " " + output);
        input = input4;
        temp = new FlipPancakes(input);
        output = temp.getResult();
        System.out.println("test4: " + AssertPancake.assertTrue(input, output) + " " + output);
    }
}
