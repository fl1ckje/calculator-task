import java.util.TreeMap;
import java.util.HashMap;

/**
 * Вспомогательный класс для конвертации между арабскими и римскими числами
 */
class NumberConverter {
    /*
    TreeMap для арабских чисел и HashMap для римских.

    При конвертации в римские доступ к элементам происходит по порядку (исходя из порядка нумерации). В этом случае
    чаще поиск значений по ключам происходит упорядоченно. По своей эффективности для доступа к отсортированным
    коллекциям типа "словарь" больше подходит TreeMap.

    Когда происходит конвертация в арабские числа, значения мы берём чаще всего вразброс. Тут уже наоборот, больше
    подходит хеш-таблица, в которой порядок вставки значений в таблицу имеет случайный порядок, в неотсортированном виде
    */
    private static final TreeMap<Integer, String> ARABIC_NUMS = new TreeMap<>();
    private static final HashMap<String, Integer> ROMAN_NUMS = new HashMap<>();

    static {
        ARABIC_NUMS.put(1, "I");
        ARABIC_NUMS.put(4, "IV");
        ARABIC_NUMS.put(5, "V");
        ARABIC_NUMS.put(9, "IX");
        ARABIC_NUMS.put(10, "X");
        ARABIC_NUMS.put(40, "XL");
        ARABIC_NUMS.put(50, "L");
        ARABIC_NUMS.put(90, "XC");
        ARABIC_NUMS.put(100, "C");

        ROMAN_NUMS.put("I", 1);
        ROMAN_NUMS.put("II", 2);
        ROMAN_NUMS.put("III", 3);
        ROMAN_NUMS.put("IV", 4);
        ROMAN_NUMS.put("V", 5);
        ROMAN_NUMS.put("VI", 6);
        ROMAN_NUMS.put("VII", 7);
        ROMAN_NUMS.put("VIII", 8);
        ROMAN_NUMS.put("IX", 9);
        ROMAN_NUMS.put("X", 10);
    }


    /**
     * Возвращает строку с числом в римской системе счисления
     *
     * @param arabicNumber целочисленное значение в арабской системе счисления
     * @return String
     */
    public String toRoman(int arabicNumber) {
        /*
        натуральные числа записываются за счёт повторения цифр.
        Если большая цифра стоит перед меньшей, то они складываются,
        если меньшая стоит перед большей, то меньшая вычитается из большей
         */
        int n = ARABIC_NUMS.floorKey(arabicNumber);
        if (arabicNumber == n) {
            return ARABIC_NUMS.get(arabicNumber);
        } else {
            return ARABIC_NUMS.get(n) + toRoman(arabicNumber - n);
        }
    }

    /**
     * Возвращает целочисленное значение в арабской системе счисления
     *
     * @param romanNumber строка с числом в римской системе счисления
     * @return int
     */
    public int toArabic(String romanNumber) {
        return ROMAN_NUMS.get(romanNumber);
    }
}
