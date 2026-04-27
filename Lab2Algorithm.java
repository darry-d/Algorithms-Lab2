
import java.util.Random;
public class Lab2Algorithm {
    public static void moveZeroesNaive(int[] array) {
        int n = array.length;
        int zeroesMoved = 0;
        for (int i = 0; i < n - zeroesMoved; i++) {

            if (array[i] == 0) {
                for (int j = i; j < n - 1; j++) {
                    array[j] = array[j + 1];


                }
                array[n - 1] = 0;
                zeroesMoved++;
                i--;
            }
        }
    }

    public static int[] moveZeroesGreedy(int[] array) {
        int n = array.length;
        int[] newArray = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            if (array[i] != 0) {
                newArray[index] = array[i];

                index++;
            }
        }
        return newArray;
    }

    public static void moveZeroesOptimal(int[] array) {
        int insertPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                int temp = array[insertPos];
                array[insertPos] = array[i];
                array[i] = temp;
                insertPos++;
            }
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {

            if (random.nextInt(100) < 20) {
                array[i] = 0;
            } else {
                array[i] = random.nextInt(100) + 1;
            }
        }
        return array;
    }

    public static int customHash(String word) {
        int hashSum = 0;
        int M = 997;

        for (int i = 0; i < word.length(); i++) {
            int charCode = word.charAt(i);

            hashSum += charCode * (i + 1);
        }
        return hashSum % M;
    }


    public static void main(String[] args) {
        int size = 100000;
        int[] originalArray = generateRandomArray(size);

        System.out.println("=== Перевірка пам'яті (Space Complexity) ===");

        int[] testArray1 = originalArray.clone();
        System.gc();
        long memoryBefore1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        moveZeroesNaive(testArray1);
        long memoryAfter1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Рівень 1 (Наївний) - Пам'ять: " + Math.max(0, memoryAfter1 - memoryBefore1) + " байт");

        int[] testArray2 = originalArray.clone();
        System.gc();
        long memoryBefore2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int[] result2 = moveZeroesGreedy(testArray2);
        long memoryAfter2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Рівень 2 (Жадібний) - Пам'ять: " + Math.max(0, memoryAfter2 - memoryBefore2) + " байт");

        int[] testArray3 = originalArray.clone();
        System.gc();
        long memoryBefore3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        moveZeroesOptimal(testArray3);
        long memoryAfter3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Рівень 3 (Оптимальний) - Пам'ять: " + Math.max(0, memoryAfter3 - memoryBefore3) + " байт\n");

        System.out.println("=== Перевірка Хешування ===");
        String[] words = new String[1000];
        Random randomWords = new Random();
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            int length = 5 + randomWords.nextInt(5);
            for (int j = 0; j < length; j++) {
                char c = (char) ('a' + randomWords.nextInt(26));
                sb.append(c);
            }
            words[i] = sb.toString();
        }

        int collisions = 0;
        boolean[] seenHashes = new boolean[997];
        for (int i = 0; i < 1000; i++) {
            int currentHash = customHash(words[i]);
            if (seenHashes[currentHash]) {
                collisions++;
            } else {
                seenHashes[currentHash] = true;
            }
        }
        System.out.println("Згенеровано унікальних слів: 1000");
        System.out.println("Просте число М: 997");
        System.out.println("Кількість знайдених колізій: " + collisions);
    }
}