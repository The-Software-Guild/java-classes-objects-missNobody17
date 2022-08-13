import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your age?");
        int age = Integer.parseInt(scanner.nextLine());
        int maxHeartRate = 220 - age;
        int minTargetHeartRate = (int) Math.round(0.5 * maxHeartRate);
        int maxTargetHeartRate = (int) Math.round(0.85 * maxHeartRate);
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + minTargetHeartRate + " - " + maxTargetHeartRate + " beats per minute");
    }
}
