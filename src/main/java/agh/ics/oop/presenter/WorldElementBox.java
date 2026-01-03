package agh.ics.oop.presenter;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.WorldElement;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class WorldElementBox {

    private final Image image;
    private final String labelText;

    public WorldElementBox(WorldElement element) {
        String url = getClass()
                .getResource("/" + element.getImageName()).toExternalForm();

        this.image = new Image(url, 20, 20, true, false);

        this.labelText = element instanceof Animal
                ? element.getPosition().toString()
                : "Grass";
    }

    public void draw(GraphicsContext graphics, double centerX, double centerY) {
        double imgX = centerX - image.getWidth() / 2;
        double imgY = centerY - image.getHeight() / 2 - 6;

        graphics.drawImage(image, imgX, imgY);

        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.TOP);
        graphics.setFont(Font.font(10));
        graphics.setFill(Color.BLACK);

        graphics.fillText(labelText, centerX, centerY + image.getHeight() / 2 - 5);
    }
}


