package projectzulu.common.dungeon;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.nbt.NBTBase;

import org.lwjgl.util.Point;

public class GUIEditNodeTextField extends GuiTextField {
    private NBTNode selectedNode = null;

    public GUIEditNodeTextField(FontRenderer par1FontRenderer, int xPos, int yPos, int width, int height) {
        super(par1FontRenderer, xPos, yPos, width, height);
    }

    public GUIEditNodeTextField(FontRenderer fontRenderer, int maxTextChars, Point screenSize, Point backgroundSize,
            Point position, Point boxSize) {
        this(fontRenderer, (screenSize.getX() - backgroundSize.getX()) / 2 + position.getX(),
                (screenSize.getY() - backgroundSize.getY()) / 2 + position.getY(), boxSize.getX(), boxSize.getY());
        setupTextField(30000);
    }

    public GUIEditNodeTextField(GUIEditNodeTextField oldTextFields, FontRenderer fontRenderer, int maxTextChars,
            Point screenSize, Point backgroundSize, Point position, Point boxSize) {
        this(fontRenderer, (screenSize.getX() - backgroundSize.getX()) / 2 + position.getX(),
                (screenSize.getY() - backgroundSize.getY()) / 2 + position.getY(), boxSize.getX(), boxSize.getY());

        this.selectedNode = oldTextFields.selectedNode;
        setText(oldTextFields.getText());
        setupTextField(30000);
    }

    private void setupTextField(int maxTextChars) {
        setTextColor(-1);
        setDisabledTextColour(-1);
        setMaxStringLength(maxTextChars);
        setEnableBackgroundDrawing(false);
    }

    public boolean isEnabled() {
        return selectedNode != null;
    }

    public void setSelectedNode(NBTNode selectedNode) {
        this.selectedNode = selectedNode;
        setText(selectedNode.getValue());
    }

    public boolean saveAndClear(NBTTree nodeTree) {
        NBTBase newNBT = selectedNode.createNBTFromString(getText());
        if (newNBT != null) {
            if (selectedNode.getParent() != null) {
                selectedNode.getParent().replaceChild(selectedNode,
                        new NBTNode(newNBT, selectedNode.getParent(), selectedNode.getTagName()));
            } else {
                // Properties name is arbitrary, the top compound in entity NBT is/was typically called properties
                selectedNode = new NBTNode(newNBT, null, "Properties");
            }
            clear();
            return true;
        }
        return false;
    }

    public void clear() {
        selectedNode = null;
        setText("");
    }

    @Override
    public boolean textboxKeyTyped(char keyChar, int keyID) {
        if (selectedNode != null) {
            return super.textboxKeyTyped(keyChar, keyID);
        }
        return false;
    }

    @Override
    public void mouseClicked(int par1, int par2, int par3) {
        if (selectedNode != null) {
            super.mouseClicked(par1, par2, par3);
        }
    }

    @Override
    public void drawTextBox() {
        super.drawTextBox();
    }
}
