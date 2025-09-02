package com.example.testtask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TicketParser {
    public static TicketData parseJsonFile(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), TicketData.class);
    }
}