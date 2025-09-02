package com.example.testtask;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonProperty("departure_date")
    private String departureDate;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("arrival_date")
    private String arrivalDate;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    private String carrier;
    private int stops;
    private int price;

    public String getOrigin() { return origin; }
    public String getOriginName() { return originName; }
    public String getDestination() { return destination; }
    public String getDestinationName() { return destinationName; }
    public String getDepartureDate() { return departureDate; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalDate() { return arrivalDate; }
    public String getArrivalTime() { return arrivalTime; }
    public String getCarrier() { return carrier; }
    public int getStops() { return stops; }
    public int getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (%s) -> %s (%s), carrier: %s, price: %d, stops: %d",
                origin, originName, destination, destinationName, carrier, price, stops);
    }
}