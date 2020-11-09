import java.util.Random;

public class Utility {

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }
}
 //metoda Random cu interval inclus