package projectzulu.common.dungeon;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

import org.lwjgl.util.Point;

public class GuiSaveableTextField extends GuiTextField {

    public GuiSaveableTextField(FontRenderer fontRenderer, int xPos, int yPos, int width, int height) {
        super(fontRenderer, xPos, yPos, width, height);
    }

    public GuiSaveableTextField(FontRenderer fontRenderer, int maxTextChars, Point screenSize, Point backgroundSize,
            Point position, Point boxSize) {
        this(fontRenderer, (screenSize.getX() - backgroundSize.getX()) / 2 + position.getX(),
                (screenSize.getY() - backgroundSize.getY()) / 2 + position.getY(), boxSize.getX(), boxSize.getY());
        setupTextField(maxTextChars);
    }

    public GuiSaveableTextField(GuiSaveableTextField oldTextFields, FontRenderer fontRenderer, int maxTextChars,
            Point screenSize, Point backgroundSize, Point position, Point boxSize) {
        this(fontRenderer, (screenSize.getX() - backgroundSize.getX()) / 2 + position.getX(),
                (screenSize.getY() - backgroundSize.getY()) / 2 + position.getY(), boxSize.getX(), boxSize.getY());
        setText(oldTextFields.getText());
        setupTextField(maxTextChars);
    }

    private void setupTextField(int maxTextChars) {
        setTextColor(-1);
        setDisabledTextColour(-1);
        setMaxStringLength(maxTextChars);
        setEnableBackgroundDrawing(false);
    }
}
