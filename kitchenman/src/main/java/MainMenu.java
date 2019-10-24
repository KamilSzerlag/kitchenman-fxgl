import com.almasb.fxgl.app.FXGLMenu;
import com.almasb.fxgl.app.MenuType;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends FXGLMenu {

    public MainMenu(@NotNull MenuType type) {
        super(type);
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding, @NotNull Runnable runnable) {
        return new Button(stringBinding.get());
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull String name, @NotNull Runnable runnable) {
        return new Button(name);
    }

    @NotNull
    @Override
    protected Node createBackground(double v, double v1) {
        return new Rectangle(v, v1, Color.AQUA);
    }

    @NotNull
    @Override
    protected Node createProfileView(@NotNull String profileName) {
        return new Text(profileName);
    }

    @NotNull
    @Override
    protected Node createTitleView(@NotNull String title) {
        return new Text(title);
    }

    @NotNull
    @Override
    protected Node createVersionView(@NotNull String version) {
        return new Text(version);
    }
}
