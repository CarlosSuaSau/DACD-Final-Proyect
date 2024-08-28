package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EventStorer {
    private String path;

    public EventStorer(String path) {
        this.path = path;
    }

    public void store(String jsonEvent) throws ParseException, IOException {
        String path = buildPath(jsonEvent);
        File directory = new File(path).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(jsonEvent);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("ERROR: " + e);
        }
    }

    public String buildPath(String eventJson) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(eventJson);
        String ss = jsonNode.get("ss").asText();
        String instant = jsonNode.get("instant").asText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(instant);
        String formattedDate = sdf.format(date);

        return String.format("eventstore/prediction.Weather/%s/%s.events", ss, formattedDate);
    }
}
