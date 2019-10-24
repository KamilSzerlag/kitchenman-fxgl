import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class BasicGameApp extends GameApplication {

    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(900);
        gameSettings.setTitle("Kitchenman");
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(300, 300)
                //.view("brick.png")
                .with(new Ani)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(500, 200)
                .viewWithBBox(new Circle(30, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();

    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.translateX(5);
                FXGL.getGameState().increment("pixelsMoved", 5);
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-5);
                FXGL.getGameState().increment("pixelsMoved", 5);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translateX(-5);
                FXGL.getGameState().increment("pixelsMoved", 5);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move down") {
            @Override
            protected void onAction() {
                player.translateY(5);
                FXGL.getGameState().increment("pixelsMoved", 5);
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Play Sound") {
            @Override
            protected void onActionBegin() {
                FXGL.play("drop.wav");
            }
        }, KeyCode.K);
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text("THE WALKING RECTANGLE");
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        Text textDistancePixels = new Text("DISTANCE:");
        textDistancePixels.setTranslateX(600);
        textDistancePixels.setTranslateY(100);

        Text distance = new Text();
        distance.setTranslateX(750);
        distance.setTranslateY(100);

        FXGL.getGameScene().addUINode(textPixels);
        FXGL.getGameScene().addUINode(textDistancePixels);
        FXGL.getGameScene().addUINode(distance);
        distance.textProperty().bind(FXGL.getGameState().intProperty("pixelsMoved").asString());

        var brickTexture = FXGL.getAssetLoader().loadTexture("brick.png");
        brickTexture.setTranslateX(50);
        brickTexture.setTranslateY(450);
        FXGL.getGameScene().addUINode(brickTexture);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }
}
