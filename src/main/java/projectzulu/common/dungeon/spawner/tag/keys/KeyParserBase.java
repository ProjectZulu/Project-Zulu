package projectzulu.common.dungeon.spawner.tag.keys;

import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public abstract class KeyParserBase extends KeyParser {

    public final Key key;
    public final boolean isInvertable;
    public final KeyType isChainable;

    public KeyParserBase(Key key, boolean isInvertable, KeyType isChainable) {
        this.key = key;
        this.isInvertable = isInvertable;
        this.isChainable = isChainable;
    }

    @Override
    public boolean isMatch(String string) {
        if (string == null) {
            return false;
        }

        Character character = isFirstSpecial(string);
        if (isSpecialCharValid(character)) {
            string = string.substring(1);
            character = isFirstSpecial(string);
            if (isSpecialCharValid(character)) {
                string = string.substring(1);
            }
        }

        return string.equalsIgnoreCase(key.key);
    }

    protected final Character isFirstSpecial(String string) {
        return isIndexSpecial(string, 0);
    }

    protected final Character isIndexSpecial(String string, int index) {
        if (string.length() <= index) {
            return null;
        }
        if (string.startsWith("&") || string.startsWith("|") || string.startsWith("!")) {
            return string.charAt(index);
        }
        return null;
    }

    protected final boolean isSpecialCharValid(Character character) {
        if (character == null) {
            return false;
        }
        if (character.equals('&') || character.equals('|')) {
            return getKeyType() == KeyType.CHAINABLE || getKeyType() == KeyType.PARENT;
        } else if (character.equals('!')) {
            return isInvertable;
        }
        return false;
    }

    @Override
    public boolean isInverted(String string) {
        Character first = isIndexSpecial(string, 0);
        Character second = isIndexSpecial(string, 1);
        if ((first != null && first.equals('!')) || (second != null && second.equals('!'))) {
            return true;
        }
        return false;
    }

    public Operand parseOperand(String[] parseable) {
        return parseOperand(parseable[0]);
    }

    @Override
    public Operand parseOperand(String parseable) {
        Operand operand = Operand.OR;
        if (parseable.charAt(0) == '&' || parseable.charAt(1) == '&') {
            operand = Operand.AND;
        }
        return operand;
    }

    @Override
    public boolean isInvertable() {
        return isInvertable;
    }

    @Override
    public KeyType getKeyType() {
        return isChainable;
    }
}