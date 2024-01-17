public class IntegerDataType extends InterpreterDataType {
    private int value;
    public IntegerDataType() {
    }
    public IntegerDataType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return Integer.toString(value);
    }
    @Override
    public void FromString(String input) {
        value = Integer.parseInt(input);
    }
}
