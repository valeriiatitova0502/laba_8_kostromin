package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1 задание ===");
        List<String> list = new ArrayList<>(Arrays.asList("Лера", "Цапля", "Шоколад", "Ролтон", "БигЛанч", "Шоколад", "БигЛанч", "Я", "Лера", "Котик"));

        String result = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Группирует элементы потока по их значениям
                .entrySet().stream() // Преобразует набор пар ключ-значение из предыдущего шага в поток
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList()))) // Преобразует ключи в список
                .entrySet().stream() // Преобразует набор пар ключ-значение в поток
                .max(Map.Entry.comparingByKey()) // ищет слово с максимальным количеством повторов
                .map(e -> e.getValue().stream().sorted(Comparator.reverseOrder()).collect(Collectors.joining(", "))) // Сортирует результаты и преобразует их в строку
                .orElse("");

        System.out.println(result);

        System.out.println("=== 2 задание ===");
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("Картошка", 24, 150, Product.Country.BELORUS),
                new Product("Конина", 43, 79, Product.Country.KAZAHSTAN),
                new Product("Верблюжатина", 18, 90, Product.Country.KAZAHSTAN),
                new Product("Окрошка", 50, 75, Product.Country.RUSSIA),
                new Product("Квас", 26, 67, Product.Country.RUSSIA)
        ));
        printNExpensive(products, 3);
    }

    public static void printNExpensive(List<Product> products, int n) {
        System.out.println(
                products.stream()
                        .sorted(Comparator.comparingInt(Product::getPrice)
                                .reversed()).limit(n).sorted((Comparator.comparingInt(Product::getQuantity)).reversed()).map(Product::getName)
                        .collect(Collectors.joining(", ", n + " самых дорогих продуктов на складе: ", ";")));
    }
}
