public class Main {
    public static void main(String[] args) {
        String input = "5 +1 +2 +3 +4 -6";
        String solution = "3 5 1 5";
        System.out.println("original input:"+input);
        FlipPancakes john = new FlipPancakes(input);
        System.out.println("State of structure:"+john);
        john.flipset(solution);
        System.out.println("State after solution:"+john);
    }
}