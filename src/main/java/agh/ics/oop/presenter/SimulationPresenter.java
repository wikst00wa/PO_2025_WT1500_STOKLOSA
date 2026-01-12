package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
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

    public void drawMap(WorldMap map) {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        int oldWidth = map.getCurrentBounds().upperRightCorner().getX() - map.getCurrentBounds().lowerLeftCorner().getX();
        int oldHeight = map.getCurrentBounds().upperRightCorner().getY() - map.getCurrentBounds().lowerLeftCorner().getY();
        mapGrid.setWidth((oldWidth + 1) * CELL_SIZE + BORDER_WIDTH);
        mapGrid.setHeight((oldHeight + 1) * CELL_SIZE + BORDER_WIDTH);
        clearGrid();
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(BORDER_WIDTH);
        for (int x = 0; x < mapGrid.getWidth() + 1; x += CELL_SIZE) {
            graphics.strokeLine(x + BORDER_OFFSET, 0, x + BORDER_OFFSET, mapGrid.getHeight());
        }

        for (int y = 0; y < mapGrid.getHeight() + 1; y += CELL_SIZE) {
            graphics.strokeLine(0, y + BORDER_OFFSET,  mapGrid.getWidth(), y + BORDER_OFFSET);
        }

        // Nagłówki osi
        configureFont(graphics, 15, Color.BLACK);
        graphics.fillText("Y/X", CELL_SIZE_OFFSET + BORDER_OFFSET, CELL_SIZE_OFFSET + BORDER_OFFSET);

        int v = map.getCurrentBounds().upperRightCorner().getY();
        for (int y = CELL_SIZE + BORDER_OFFSET + CELL_SIZE_OFFSET; y < mapGrid.getHeight() + 1; y += CELL_SIZE) {
            graphics.fillText(String.valueOf(v), CELL_SIZE_OFFSET + BORDER_OFFSET, y);
            v -= 1;
        }

        v = map.getCurrentBounds().lowerLeftCorner().getX();
        for (int x = CELL_SIZE + BORDER_OFFSET + CELL_SIZE_OFFSET; x < mapGrid.getWidth() + 1; x += CELL_SIZE) {
            graphics.fillText(String.valueOf(v), x, CELL_SIZE_OFFSET + BORDER_OFFSET);
            v += 1;
        }

        // Zawartość mapy
        int x;
        int y;
        configureFont(graphics, 30, Color.RED);
        for (WorldElement element : map.getElements()) {
            if (map.objectAt(element.getPosition()).equals(element)) {
                x = element.getPosition().getX() - map.getCurrentBounds().lowerLeftCorner().getX() + 1;
                y = map.getCurrentBounds().upperRightCorner().getY() - element.getPosition().getY() + 1;
                graphics.fillText(element.toString(), x * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET, y * CELL_SIZE + CELL_SIZE_OFFSET + BORDER_OFFSET);
            }
        }
    }

    private void clearGrid() {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    }

    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
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

        Simulation simulation = new Simulation(positions, directions, map);

        new Thread(simulation).start();
    }
}
