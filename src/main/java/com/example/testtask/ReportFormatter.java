package com.example.testtask;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;

public class ReportFormatter {

    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance(new Locale("ru", "RU"));

    static {
        PRICE_FORMATTER.setMinimumFractionDigits(2);
        PRICE_FORMATTER.setMaximumFractionDigits(2);
    }

    public static void printFlightTimes(Map<String, Duration> minFlightTimes) {
        System.out.println("Минимальное время полета для каждого перевозчика:");
        minFlightTimes.forEach((carrier, duration) -> {
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            System.out.printf("  %-5s %02d:%02d%n", carrier + ":", hours, minutes);
        });
        System.out.println();
    }

    public static void printPriceStats(PriceStats stats) {
        System.out.println("Статистика цен:");
        System.out.printf("  %-15s %s%n", "Средняя цена:", PRICE_FORMATTER.format(stats.average()));
        System.out.printf("  %-15s %s%n", "Медиана:", PRICE_FORMATTER.format(stats.median()));
        System.out.printf("  %-15s %s%n", "Разница:", PRICE_FORMATTER.format(stats.difference()));
    }
}
