import java.util.regex.Pattern;

/**
 * Класс для валидации математических выражений и их вычислений
 */

class Calculator {
    private final NumberConverter numberConverter;

    /**
     * Создаёт экземпляр конвертера чисел
     */
    public Calculator() {
        this.numberConverter = new NumberConverter();
    }


    /**
     * @param tokens массив лексем выражения (например, ["1", "+", "2"])
     * @return строковое представление результата вычисления согласно использованной системе счисления
     * @throws Exception если пользователь допустил ошибку при вводе выражения
     */
    public String calculate(String[] tokens) throws Exception {
        if (tokens.length < 3) {
            throw new Exception("строка не является математической операцией");
        }

        if (tokens.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор (+, -, /, *)");
        }

        //проверка на наличие чисел с плавающей точкой
        {
            String floatRegex = "[0-9]*\\.[0-9]?+";
            if (Pattern.matches(floatRegex, tokens[0]) ||
                    Pattern.matches(floatRegex, tokens[2])) {
                throw new Exception("калькулятор умеет работать только с целыми числами");
            }
        }

        int result;

        //попытка спарсить операнды и посчитать как арабские числа
        try {
            result = operate(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[2]),
                    Operator.fromString(tokens[1]));

            return String.valueOf(result);

        } catch (NumberFormatException numberFormatException) {
            //попытка спарсить операнды в арабские и посчитать как римские числа
            try {
                result = operate(numberConverter.toArabic(tokens[0]),
                        numberConverter.toArabic(tokens[2]),
                        Operator.fromString(tokens[1]));

                if (result < 0) {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }

                return numberConverter.toRoman(result);

            } catch (NullPointerException nullPointerException) {
                throw new Exception("используются одновременно разные системы счисления");
            }
        }
    }

    private int operate(int operand1, int operand2, Operator operator) throws Exception {
        // проверка значений операндов на попадание в заданный диапазон
        int MAX_VALUE = 10;
        int MIN_VALUE = 1;

        if (operand1 < MIN_VALUE || operand1 > MAX_VALUE ||
                operand2 < MIN_VALUE || operand2 > MAX_VALUE) {
            throw new Exception("значения операндов не могут быть меньше 1 или больше 10");
        }

        // получение нужного лямбда-выражения и его же выполние для получения результата
        return action(operator).execute(operand1, operand2);
    }

    /**
     * Возвращает лямбда-выражение математического действия, которое можно сразу же выполнить
     *
     * @param operator строка-оператор, в зависимости от которой выполняется нужное действие
     * @return лямбда-выражение мат. действия
     */
    private Operation action(Operator operator) {
        return switch (operator) {
            case ADD -> (a, b) -> a + b;
            case SUB -> (a, b) -> a - b;
            case MUL -> (a, b) -> a * b;
            case DIV -> (a, b) -> a / b;
        };
    }
}
