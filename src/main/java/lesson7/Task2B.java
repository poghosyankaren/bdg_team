package lesson7;

public class Task2B {

    private static int counter;
    private volatile static boolean flag;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) counter++;
            flag = !flag;
        }).start();
        new Thread(() -> {
            while (!flag) {
                Thread.onSpinWait();
            }
            System.out.println(counter);
        }).start();
    }
}
