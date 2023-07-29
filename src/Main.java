import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //ввод
            String input = scanner.nextLine().trim();

            //обработка
            String output = calc(input);

            //вывод
            System.out.println(output);
        } catch (Exception exception) {
            System.out.printf("throws Exception //т.к. %s", exception.getMessage());
        }
    }

    public static String calc(String input) throws Exception {

        //получение массива лексем из строки мат. выражения с разделением через пробел
        String[] inputTokens = input.split(" ");

        //создание объекта класса для валидации выражения и вычислений
        Calculator calculator = new Calculator();

        //все операции над выражением и возврат результата в случае успеха
        return calculator.calculate(inputTokens);
    }
}