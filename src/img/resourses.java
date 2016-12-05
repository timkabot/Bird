package img;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Timkabo on 23.04.2016.
 */
public class resourses {
    public Image dragonIMGS [] = new Image[4];
    public Image sh = new Image("/img/ship.png");
    public ImageView sh2 = new ImageView(sh);
    public resourses()
    {
        try {
            for (int j=0;j<2;j++)
            for (int i = 0; i < dragonIMGS.length; i++)
            {
                dragonIMGS[i] = new Image(getClass().getResourceAsStream("frame-" + (i+1) + ".png"));
            }
        } catch (Exception e)
        {
            System.out.println("Problem in loading resources");
        }
    }
}
