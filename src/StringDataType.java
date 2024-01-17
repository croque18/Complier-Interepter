public class StringDataType extends InterpreterDataType {
    private String value;
    public StringDataType() {
    }
    public StringDataType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }
    @Override
    public void FromString(String input) {
        value = input;
    }
}
