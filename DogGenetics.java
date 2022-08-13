import java.util.Scanner;
import java.util.Random;

public class DogGenetics {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Random rGen = new Random();

        String dogName;
        int startPercent = 100;
        int breedPercent = 0;

        System.out.println("What is your dog's name?");
        dogName = inputReader.nextLine();

        String[] breeds = {"St. Bernard", "Dramatic RedNosed Asian Pug", "Common Cur", "Chihuahua", "King Doberman"};

        System.out.println("Well then, I have this highly reliable report on  " + dogName + "\'s prestigious background right here.");
        System.out.println(dogName + " is: ");

        for (int i = 0; i < 5; i++) {
            String breed = "";
            breed = breeds[i];
            if (i != 4) {
                breedPercent = rGen.nextInt(startPercent);
                startPercent -= breedPercent;
            } else {
                breedPercent = startPercent;

            }
            System.out.println(breedPercent + "% " + breed);

        }
        System.out.println("Wow, that\'s QUITE the dog!");
    }

}