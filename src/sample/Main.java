package sample;

import img.resourses;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    double FPS_30 = 30;
    int score = 0,last_score = 0;
    AudioClip music = new AudioClip(this.getClass().getResource("/game.mp3").toExternalForm());
    TranslateTransition jump;
    TranslateTransition fall;
    TranslateTransition fly;
    dragon Dragon;
    Group men = new Group();
    ScoreLabel scoreLabel = new ScoreLabel(1800, 0);
    boolean gameOver = false;
    int  height = 400;
    resourses res = new resourses();
    fire SHIP ;
    Group root = new Group();
    Image bg = new Image("/img/background.gif");
    ImageView background = new ImageView(bg);
    Image l = new Image("/img/lost.png");
    ImageView lost = new ImageView(l);
    Timeline gameLoop;
    Scene menu = new Scene(men,1980,1020);
    Scene main = new Scene(root, 1980, 1020);
    @Override

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Flying Dragon");
        primaryStage.setScene(main);
        primaryStage.show();
        initGame();
        root.setOnMouseClicked(e -> {
            jumpDragon();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    private void jumpDragon()
    {
        jump.setByY(-70);
        jump.setCycleCount(1);
        Dragon.jumping = true;
        fall.stop();
        jump.stop();
        jump.play();
        Dragon.refreshDragon();
        Dragon.refreshDragon();
        jump.setOnFinished((finishedEvent) ->
                {
                    Dragon.jumping=false;
                    fall.play();
                }
        );
    }
    void initializeGame()
    {
        Dragon.getGraphics().setFitWidth(200);
        Dragon.getGraphics().setFitHeight(200);
        Dragon.getGraphics().setTranslateX(400);
        Dragon.getGraphics().setTranslateY(150);
        SHIP.getGraphics().setFitHeight(100);
        SHIP.getGraphics().setFitWidth(100);
        SHIP.getGraphics().setTranslateX(1800);
        Dragon.jumping=false;
        fall.play();
        add_ship();
        root.getChildren().addAll(Dragon.getGraphics(),scoreLabel);
        gameLoop.play();
    }

    boolean intersects()
    {
        Path p1 = (Path) Shape.intersect(Dragon.getBounds(), SHIP.getBounds());
        return p1.getElements().isEmpty();
    }
    void add_ship()
    {
        root.getChildren().add(SHIP.getGraphics());
    }
    void change_ship()
    {
        fly.stop();
        double x = Math.random() * 900;
        SHIP.getGraphics().setTranslateY(x);
        SHIP.getGraphics().setTranslateX(1800);
        fly.play();
    }
    void initGame()
    {
        music.setCycleCount(-1);
        music.play();
        gameOver = false;
        root.getChildren().clear();
        root.getChildren().add(background);
        background.setFitWidth(1920);
        background.setFitHeight(1080);
        Dragon = new dragon(res.dragonIMGS);
        SHIP = new fire(res.sh);
        //rotator = new RotateTransition(Duration.millis(200), Dragon.getGraphics());
        jump = new TranslateTransition(Duration.millis(200), Dragon.getGraphics());
        fall = new TranslateTransition((Duration.millis(5 * height)), Dragon.getGraphics());
        jump.setInterpolator(Interpolator.LINEAR);
        fall.setByY(height + 300);

        Dragon.getGraphics().setRotationAxis(Rotate.Z_AXIS);
        fly = new TranslateTransition(Duration.millis(8*height), SHIP.getGraphics() );
        fly.setByX(-1900);
        fly.play();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if(!intersects())
                {
                    last_score = score;
                    score = 0;
                    scoreLabel.setText("Score: " + score);
                    change_ship();
                }
                else if(SHIP.getGraphics().getTranslateX()<100)
                {
                    score++;
                    scoreLabel.setText("Score: " + score);
                    change_ship();
                }
            }
        }
        ));
        gameLoop.setCycleCount(-1);
        initializeGame();
    }
}
