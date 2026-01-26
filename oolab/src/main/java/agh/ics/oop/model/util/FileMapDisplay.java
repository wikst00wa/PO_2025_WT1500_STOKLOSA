package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

import java.io.IOException;

public class FileMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter("map" + worldMap.getId() + ".log", true)) {
            fileWriter.write(message + "\n");
            fileWriter.write(worldMap.toString() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
