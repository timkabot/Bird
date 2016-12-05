package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Created by Timkabo on 23.04.2016.
 */
public class dragon {
    private ImageView graphics = new ImageView();
    private Image frames[];
    private int frameCounter = 0;
    public boolean jumping = false;
    public ImageView getGraphics(){return graphics;}
    Ellipse bounds;
    public Ellipse getBounds(){return bounds;}

    public dragon(Image[] frames)
    {
        this.frames = frames;
        this.bounds = new Ellipse(100,11.5);
        graphics.setImage(frames[0]);
        bounds.setFill(Color.TRANSPARENT);
        bounds.setStroke(Color.BLACK);
        bounds.centerXProperty().bind(graphics.translateXProperty().add(100));
        bounds.centerYProperty().bind(graphics.translateYProperty().add(12.0));
        bounds.rotateProperty().bind(graphics.rotateProperty());
    }
    public void refreshDragon()
    {
        graphics.setImage(frames[frameCounter++]);
        if(frameCounter==4)
        {
            frameCounter = 0;
        }
    }
}
