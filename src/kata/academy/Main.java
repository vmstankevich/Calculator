package kata.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static String [] values = new String[3];
    public static void main(String[] args) throws IOException {
        //Вводим строку в консоль, разделяем ее методом split(" "), заполняем массив values разбивая на строки.
        String str;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str = reader.readLine();

        values = str.split(" "); // разделяем строку на массив строк
        // предполагается что символы вводятся через пробел

        isValid(values);

        // Если строка соответствует условию, проверяем из каких цифр она состоит
        // вычисляется результат и выводится на экран
        if (isRoman(values[0]) && isRoman(values[2])) {
            System.out.println(convertArabicToRoman(calc(convertRomanToArabic(values[0]), convertRomanToArabic(values[2]))));
            System.exit(0);
        }
        try {
            System.out.println(calc(Integer.parseInt(values[0]), Integer.parseInt(values[2])));
        } catch (Exception e) {
            System.out.println("Неверный формат строки");
        }

        //вызывается метод калькулятор
        //результат выводится на экран
    }

    //Создаем метод isValid, проверяет введенные данные на соответсвие условиям, иначе бросает исключение.
    public static boolean isValid(String [] values) throws IOException {
        boolean isValid = true;

        // если введенная строка содержит более двух операндов, или любой другой неподходящий формат
        if (values.length != 3) {
            isValid = false;
            System.out.println("Неверный формат строки");
            throw new IOException();
        }

        // если строка содержит операнды разных типов
        if ((isRoman(values[0]) && !isRoman(values[2])) | (!isRoman(values[0]) && isRoman(values[2]))) {
            isValid = false;
            System.out.println("В строке присутствуют два операнда разных типов");
            throw new IOException();
        }
        return isValid;
    }

    public static boolean isRoman(String input) {
        boolean exists = true;
        try {
            RomanNumeral.valueOf(input);
        } catch (IllegalArgumentException e) {
            exists = false;
        }
        return exists;
    }

    public static int convertRomanToArabic(String roman) {
        RomanNumeral num = RomanNumeral.valueOf(roman);
        return num.getArabic();
    }

    public static String convertArabicToRoman(int arabic) {
        RomanNumeral num = RomanNumeral.getRoman(arabic);
        return num.toString();
    }

    /*Метод принимает на вход два аргумента и выполняет операции
    сложения, вычитания, умножения и деления с двумя числами:
    a + b, a - b, a * b, a / b
     */
    public static int calc(int firstOperand, int secondOperand) {
        int result = 0;
        switch (values[1]) {
            case ("+"):
                result = firstOperand + secondOperand;
                break;
            case ("-"):
                result = firstOperand - secondOperand;
                break;
            case ("*"):
                result = firstOperand * secondOperand;
                break;
            case ("/"):
                result = firstOperand / secondOperand;
                break;
        }
        return result;
    }
}
