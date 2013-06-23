package projectzulu.common.dungeon;

public enum ButtonIDs {
    NEWENTRY(-1), SAVENCLOSE(-2), DELENTRY(-6), CANCEL(-3), FORWARD(-4), BACKWARDS(-5);

    int index;

    ButtonIDs(int index) {
        this.index = index;
    }

    public static ButtonIDs getButtonByIndex(int index) {
        for (ButtonIDs buttonID : ButtonIDs.values()) {
            if (buttonID.index == index) {
                return buttonID;
            }
        }
        return null;
    }
}
