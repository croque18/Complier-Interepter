public class CharacterDataType extends InterpreterDataType {
    private char value;
    public CharacterDataType() {
    }
    public CharacterDataType(char value) {
        this.value = value;
    }
    public char getValue() {
        return value;
    }
    @Override
    public String toString() {
        return Character.toString(value);
    }
    @Override
    public void FromString(String input) {
        value = input.charAt(0);
    }
}
