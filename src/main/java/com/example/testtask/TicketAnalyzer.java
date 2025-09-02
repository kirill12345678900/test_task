package com.example.testtask;

import java.util.List;
import java.util.stream.Collectors;

public class TicketAnalyzer {

    public static void main(String[] args) {
        try {
            String filePath = args[0];
            TicketData ticketData = TicketParser.parseJsonFile(filePath);

            List<Ticket> relevantTickets = ticketData.getTickets().stream()
                    .filter(t -> "VVO".equals(t.getOrigin()) && "TLV".equals(t.getDestination()))
                    .collect(Collectors.toList());

            if (relevantTickets.isEmpty()) {
                System.out.println("Билеты по маршруту не найдены VVO -> TLV");
                return;
            }

            var minFlightTimes = TicketStatistics.calculateMinFlightTimeByCarrier(relevantTickets);
            ReportFormatter.printFlightTimes(minFlightTimes);

            var stats = TicketStatistics.calculatePriceStats(relevantTickets);
            ReportFormatter.printPriceStats(stats);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}