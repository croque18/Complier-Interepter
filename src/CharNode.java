public class CharNode {
    private char value;

    public CharNode() {
    }

    public CharNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
