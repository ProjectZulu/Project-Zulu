package projectzulu.common.dungeon.spawner.tag;

import projectzulu.common.dungeon.spawner.tag.keys.Key;


public class TypeValuePair {

    private final Key type;
    private final Object value;

    public static TypeValuePair createPair(Key type, Object value) {
        return new TypeValuePair(type, value);
    }

    public TypeValuePair(Key type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Key getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}