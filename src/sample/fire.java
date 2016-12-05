package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Created by Timkabo on 23.04.2016.
 */
public class fire {
    private ImageView graphics = new ImageView();
    private int frameCounter = 0;
    public boolean jumping = false;
    public ImageView getGraphics(){return graphics;}
    Ellipse bounds;
    public Ellipse getBounds(){return bounds;}

    public fire(Image frames) {
        graphics = new ImageView(frames);
        this.bounds = new Ellipse(100, 11.5);
        graphics.setImage(frames);
        bounds.setFill(Color.TRANSPARENT);
        bounds.setStroke(Color.BLACK);
        bounds.setStroke(Color.YELLOW);
        bounds.centerXProperty().bind(graphics.translateXProperty().add(100));
        bounds.centerYProperty().bind(graphics.translateYProperty().add(12.0));
        bounds.rotateProperty().bind(graphics.rotateProperty());
    }
}
