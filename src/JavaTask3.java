import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JavaTask3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr;

        System.out.println("Step 1: ");
        arr = step1(n);

        System.out.println("\nStep 2: ");
        step2(arr);

        System.out.println("\nStep 3: ");
        step3(arr);
    }

    static int[][] step1(int n) {
        Random rand = new Random();
        int[][] arr = new int[n][n];
        boolean presenceOfPositiveN;
        boolean presenceOfNegativeN;

        System.out.println("Initial matrix: ");

//      Создание матрицы случайных чисел. Создается, пока в матрице не будет максимальных положительного и отрицательных элементов n.
        while (true) {
            presenceOfPositiveN = false;
            presenceOfNegativeN = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = rand.nextInt(2 * n + 1) - n;


                    if (arr[i][j] == n) {
                        presenceOfPositiveN = true;                 // Проверка на макс. положительное n.
                    }
                    if (arr[i][j] == -n) {
                        presenceOfNegativeN = true;                 // Проверка на макс. отрицательное n.
                    }
                }
            }

            if (presenceOfPositiveN && presenceOfNegativeN) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(arr[i][j] + " ");
                    }
                    System.out.println();
                }
                break;                                              // Пока не будет 2-х необходимых n, цикл while будет работать по кругу.
            }
        }

        return arr;
    }

    static int step2(int[][] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] > 0) {                                // Нахождение первого положительного элемента строки.
                    j++;                                            // Переход к след. элементу строки.
                    while (j < arr.length && arr[i][j] <= 0) {      // Сумма считается до момента, пока не встретится еще 1 положительный элем., либо пока не закончится строка.
                        sum += arr[i][j];
                        j++;
                    }
                    break;
                }
            }
        }
        System.out.println("Sum of array elements: " + sum);
        return sum;
    }

    static int[][] step3 (int[][] arr) {

//      Согласно условию step1 у нас с вероятностью 100% максимальный элемент = n = arr.length и он 100% хотя бы 1, но есть.

//      Замена строк и столбцов, сдержащих n на значения, которые в последующем удалятся.
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {

                if (arr[i][j] == arr.length) {              // Проверка на наличие максимально элемента в строке.

                    for (int k = 0; k < arr.length; k++) {
                        if (arr[i][k] != arr.length) {      // Если тут не сделать проверку, то другой максимальный элемент, стоящий в 1-й линии с найденым в строке кода 87
                            arr[i][k] = arr.length + 1;     // сотрется и у нас будет лишняя строка, либо столбец. Замена производилась на элемент n + 1. Т.к. матрица у нас
                        }                                   // со значениями [-n; n], то в матрице не может быть элемента равного n + 1.
                        if (arr[k][j] != arr.length) {
                            arr[k][j] = arr.length + 1;
                        }
                    }

                }

            }
        }

//      Подсчет размерности нашей итоговой матрицы. А именно хоризонтали и вертикали.
        int count;
        int vertical = 0;
        int horizontal = 0;

        for (int i = 0; i < arr.length; i++) {
            count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != arr.length + 1 && arr[i][j] != arr.length) {
                    count++;
                }
            }
            if (count != 0) {
                horizontal = count;
                vertical++;
            }
        }

//      Заполнение итоговой матрицы элементами отличными от (n) и (n + 1).
        if (horizontal != 0 && vertical != 0) {

            int[][] theLastArr = new int[vertical][horizontal];

            vertical = 0;
            for (int i = 0; i < arr.length; i++) {
                horizontal = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] != arr.length + 1 && arr[i][j] != arr.length) {
                        theLastArr[vertical][horizontal] = arr[i][j];
                        horizontal++;
                    }
                }
                if (horizontal != 0) vertical++;
            }

//      Вывод итоговой матрицы.
            System.out.println("Final matrix: ");
            for (int i = 0; i < theLastArr.length; i++) {
                for (int j = 0; j < theLastArr[0].length; j++) {
                    System.out.print(theLastArr[i][j] + " ");
                }
                System.out.println();
            }
            return theLastArr;
        } else {
            System.out.println("No array elements");
            return null;
        }
    }
}
