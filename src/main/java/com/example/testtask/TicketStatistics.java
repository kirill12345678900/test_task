package com.example.testtask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.*;

public class TicketStatistics {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yy ")
            .appendValue(ChronoField.HOUR_OF_DAY, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendPattern(":mm")
            .toFormatter();

    public static Map<String, Duration> calculateMinFlightTimeByCarrier(List<Ticket> tickets) {
        Map<String, Duration> minDurations = new HashMap<>();

        for (Ticket ticket : tickets) {
            try {
                LocalDateTime departure = LocalDateTime.parse(
                        ticket.getDepartureDate() + " " + ticket.getDepartureTime(), FORMATTER);
                LocalDateTime arrival = LocalDateTime.parse(
                        ticket.getArrivalDate() + " " + ticket.getArrivalTime(), FORMATTER);

                if (arrival.isBefore(departure)) {
                    System.err.println("Ошибка: время прилета раньше вылета для билета: " + ticket);
                    continue;
                }

                Duration flightTime = Duration.between(departure, arrival);

                minDurations.merge(ticket.getCarrier(), flightTime,
                        (current, newVal) -> newVal.compareTo(current) < 0 ? newVal : current);

            } catch (Exception e) {
                System.err.println("Ошибка анализа даты билета: " + ticket + " - " + e.getMessage());
            }
        }

        return minDurations;
    }

    public static PriceStats calculatePriceStats(List<Ticket> tickets) {
        List<Integer> prices = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        if (prices.isEmpty()) return new PriceStats(0, 0, 0);

        double average = prices.stream().mapToInt(Integer::intValue).average().orElse(0);

        double median;
        int size = prices.size();
        if (size % 2 == 0) {
            median = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            median = prices.get(size / 2);
        }

        double difference = average - median;
        return new PriceStats(average, median, difference);
    }
}