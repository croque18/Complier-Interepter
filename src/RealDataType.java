public class RealDataType extends InterpreterDataType {
    private double value;
    public RealDataType() {
    }
    public RealDataType(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
    @Override
    public String toString() {
        return Double.toString(value);
    }
    @Override
    public void FromString(String input) {
        value = Double.parseDouble(input);
    }
}
