import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int speed = 0;

    private AnimatedTexture texture;
    private AnimationChannel animIdle;
    private AnimationChannel animWalk;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("newdude.png"), 4, 32, 42, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel(FXGL.image("newdude.png"), 4, 32, 42, Duration.seconds(1), 0, 3);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speed * tpf);

        if (speed != 0) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loop()
            }
        }
    }
}
