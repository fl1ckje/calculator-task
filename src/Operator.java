/**
 * Представляет множество операторов
 */
enum Operator {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    /*
    напрямую символы операторов нельзя использовать в качестве значений,
    поэтому реализовано это поле, получающее строку в зависимости от значения
    */
    private final String value;

    Operator(String value) {
        this.value = value;
    }

    /**
     * Получает значение перечисления при нахождении по полю Operator.value
     * Если значения перечисления со значением параметра value нет, то оператор отсутствует
     *
     * @param value значение для проверки с полем <code>Operator.value</code>
     * @return значение перечисления Operator
     */
    public static Operator fromString(String value) {
        for (Operator op : Operator.values()) {
            if (op.value.equals(value)) {
                return op;
            }
        }
        throw new IllegalArgumentException("такой оператор не предусмотрен");
    }
}
