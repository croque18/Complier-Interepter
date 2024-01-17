public class BooleanDataType extends InterpreterDataType {
    private boolean value;
    public BooleanDataType() {
    }
    public BooleanDataType(boolean value) {
        this.value = value;
    }
    public boolean getValue() {
        return value;
    }
    @Override
    public String toString() {
        return Boolean.toString(value);
    }
    @Override
    public void FromString(String input) {
        value = Boolean.parseBoolean(input);
    }
}
