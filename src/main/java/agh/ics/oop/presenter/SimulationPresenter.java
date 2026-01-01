package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField directionsInputTextField;
    @FXML
    private Button startButton;
    @FXML
    private Label moveInfoLabel;
    @FXML
    private Canvas mapGrid;

    private static final int CELL_SIZE = 40;
    private static final int CELL_SIZE_OFFSET = CELL_SIZE / 2;
    private static final int BORDER_WIDTH = 2;
    private static final int BORDER_OFFSET = BORDER_WIDTH / 2;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

public void drawMap() {
    GraphicsContext graphics = mapGrid.getGraphicsContext2D();

    // Pobranie granic mapy
    Boundary bounds = map.getCurrentBounds();
    int cols = bounds.upperRightCorner().getX() - bounds.lowerLeftCorner().getX() + 1;
    int rows = bounds.upperRightCorner().getY() - bounds.lowerLeftCorner().getY() + 1;

    int xOffset = 1;
    int yOffset = 1;

    // Zmiana wymiarów Canvas
    mapGrid.setWidth((cols + xOffset) * CELL_SIZE + BORDER_WIDTH);
    mapGrid.setHeight((rows + yOffset) * CELL_SIZE + BORDER_WIDTH);

    clearGrid();

    graphics.setStroke(Color.BLACK);
    graphics.setLineWidth(BORDER_WIDTH);

    graphics.strokeLine(BORDER_OFFSET, 0, BORDER_OFFSET, mapGrid.getHeight()); // lewa
    graphics.strokeLine(0, BORDER_OFFSET, mapGrid.getWidth(), BORDER_OFFSET);

    // Rysowanie siatki
    for (int i = 0; i <= cols; i++) {
        double x = (i + xOffset) * CELL_SIZE + BORDER_OFFSET;
        graphics.strokeLine(x, 0, x, mapGrid.getHeight());
    }
    for (int i = 0; i <= rows; i++) {
        double y = (i + yOffset) * CELL_SIZE + BORDER_OFFSET;
        graphics.strokeLine(0, y, mapGrid.getWidth(), y);
    }

    // Nagłówki osi
    configureFont(graphics, 15, Color.BLACK);
    graphics.fillText("Y/X", CELL_SIZE_OFFSET + BORDER_OFFSET, CELL_SIZE_OFFSET + BORDER_OFFSET);

    int v = bounds.upperRightCorner().getY();
    for (int y = 0; y < rows; y++) {
        double pixelY = (y + yOffset) * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET;
        graphics.fillText(String.valueOf(v - y), CELL_SIZE_OFFSET + BORDER_OFFSET, pixelY);
    }

    v = bounds.lowerLeftCorner().getX();
    for (int x = 0; x < cols; x++) {
        double pixelX = (x + xOffset) * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET;
        graphics.fillText(String.valueOf(v + x), pixelX, CELL_SIZE_OFFSET + BORDER_OFFSET);
    }

    // Rysowanie elementów mapy
    configureFont(graphics, 30, Color.RED);

    for (WorldElement element : map.getElements()) {
        map.objectAt(element.getPosition())
                .filter(obj -> obj.equals(element))   // jeśli to samo obiektowo
                .ifPresent(obj -> {
                    int ex = element.getPosition().getX() - bounds.lowerLeftCorner().getX() + xOffset;
                    int ey = bounds.upperRightCorner().getY() - element.getPosition().getY() + yOffset;
                    graphics.fillText(
                            obj.toString(),
                            ex * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET,
                            ey * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET
                    );
                });
    }
}


    private void clearGrid() {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    }

    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    private void configureFont(GraphicsContext graphics, int size, Color black) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setFont(new Font("Arial", size));
        graphics.setFill(black);
    }

    @FXML
    public void onSimulationStartClicked() {
        String directionList = directionsInputTextField.getText();
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 3));
        List<MoveDirection> directions = OptionsParser.parseOptions(directionList.split(" "));
        AbstractWorldMap map = new GrassField(10);
        map.addListener(this);
        setWorldMap(map);

        map.addListener((changedMap, message) -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.println(timestamp + " " + message);
        });

        Simulation simulation = new Simulation(positions, directions, map);

        new Thread(() -> {
            for (Animal animal : simulation.getAnimals()) {

                Platform.runLater(() -> {
                    try {
                        map.place(animal);
                    } catch (IncorrectPositionException e) {
                        e.printStackTrace();
                    }
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Thread(simulation).start();

        }).start();
    }
}
