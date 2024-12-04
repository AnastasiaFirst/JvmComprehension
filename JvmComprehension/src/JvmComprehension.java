public class JvmComprehension { // Загрузка JvmComprehension в Metaspace

    public static void main(String[] args) { //Создание нового фрейма на стеке
        int i = 1;                      // 1. Создается новая переменная i на стеке
        Object o = new Object();        // 2. Создается экземпляр объекта в куче; заводим ссылку o на стеке - данный экземпляр может быть удален сборщиком мусора
        Integer ii = 2;                 // 3. Создается переменная на стеке
        printAll(o, i, ii);             // 4. В момент вызова метода printAll создается фрейм в стеке
        System.out.println("finished"); // 7. Создается новый фрейм на стеке
    }

    private static void printAll(Object o, int i, Integer ii) { // В методе объявляется ссылка o и переменная ii на стеке
        Integer uselessVar = 700;                   // 5. Создается новая переменная uselessVar на стеке
        System.out.println(o.toString() + i + ii);  // 6. Создается новый фрейм на стеке, куда передаем ссылку на o, i и ii
    }
}

// ClassLoaders:
// Application ClassLoader делегирует запрос Platform ClassLoader, а Platform ClassLoader, в свою очередь, делегирует запрос Bootstrap ClassLoader
// Поскольку наш класс JvmComprehension не входит в стандартную библиотеку JAVA, то Bootstrap ClassLoader выполнит поиск
// в Bootstrap classpath (JDK/JRE/LIB), не находит его и делегирует загрузку далее в Platform ClassLoader.
// Platform ClassLoader ищет класс в Platform ClassLoader (JDK/JRE/LIB/EXT). Класс доступен, далее запрос делегируется
// Application ClassLoader.
//