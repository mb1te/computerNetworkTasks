# Практические задания по дисциплине "Компьютерные сети" (7 семестр)

## Содержание
1. [Решение вводных задач на Java](#task1)
2. [Разработка элементарных приложений на языке Java](#task2)
3. [Разработка многопоточных приложений на языке Java](#task3)
4. [Разработка многопоточных приложений на языке Java. Синхронизация потоков](#task4)

### Задание 1. Решение вводных задач на Java. <a name="task1"></a>

Решение вводных задач на Java из раздела Introduction:

- [Welcome To Java](https://www.hackerrank.com/challenges/welcome-to-java/problem)
- [Java Stdin and Stdout I](https://www.hackerrank.com/challenges/java-stdin-and-stdout-1/problem)
- [Java If-Else](https://www.hackerrank.com/challenges/java-if-else/problem)
- [Java Stdin and Stdout II](https://www.hackerrank.com/challenges/java-stdin-stdout/problem)
- [Java Output Formatting](https://www.hackerrank.com/challenges/java-output-formatting/problem)
- [Java Loops I](https://www.hackerrank.com/challenges/java-loops-i/problem)
- [Java Loops II](https://www.hackerrank.com/challenges/java-loops/problem)
- [Java Datatypes](https://www.hackerrank.com/challenges/java-datatypes/problem)
- [Java End-of-file](https://www.hackerrank.com/challenges/java-end-of-file/problem)
- [Java Static Initializer Block](https://www.hackerrank.com/challenges/java-static-initializer-block/problem)
- [Java Int to String](https://www.hackerrank.com/challenges/java-int-to-string/problem)
- [Java Date and Time](https://www.hackerrank.com/challenges/java-date-and-time/problem)
- [Java Currency Formatter](https://www.hackerrank.com/challenges/java-currency-formatter/problem)

### Задание 2. Разработка элементарных приложений на языке Java. <a name="task2"></a>

Разработать класс, решающий квадратные уравнения. Коэффициенты квадратного трехчлена или передаются в качестве параметров класса, в противном случае вводятся из консоли. Коэффициенты могут быть любыми действительными числами.

Требования к решению:
1. В решении должен присутствовать класс "Квадратное уравнение"
2. В этом классе должны присутствовать два конструктора: первый с тремя параметрами, а второй с одним параметром - сканнером. В первом случае передаются коэффициенты уравнения и сохраняются в классе. Во втором случае коэффициенты нужно читать из полученного сканера. Допустимо во второй конструктор передавать сканер, из которого будут читаться коэффициенты.
3. Решение должно корректно обрабатывать случаи равенства нулю некоторых из коэффициентов уравнения, в частности a=0

### Задание 3. Разработка многопоточных приложений на языке Java. <a name="task3"></a>

Разработать многопоточное приложение, позволяющее в интервале 100000000 — 400000000 найти все числа, которые одновременно делятся на 11, 13, 17. Выяснить различие во времени выполнения программы для случая одного потока и трех потоков.

Дополнительное задание: Предусмотрите возможность указать количество создаваемых потоков при запуске приложения

### Задание 4. Разработка многопоточных приложений на языке Java. Синхронизация потоков. <a name="task4"></a>

Разработать приложение, решающее задачу о синхронизации производитель-потребитель. Дано два потока, один из потоков должен передать другому последовательность целых чисел длины n, например, квадратов натуральных чисел. Другой поток должен принять и распечатать эту последовательность.

Решить задачу двумя способами:

1. С использованием класса BlockingQueue
2. С использованием общих переменных и методов wait/notify
