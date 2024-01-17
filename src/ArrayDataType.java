import java.util.ArrayList;

public class ArrayDataType extends InterpreterDataType {
    private ArrayList<InterpreterDataType> value;
    public ArrayDataType() {
        value = new ArrayList<InterpreterDataType>();
    }
    public ArrayDataType(ArrayList<InterpreterDataType> value) {
        this.value = value;
    }
    public ArrayList<InterpreterDataType> getValue() {
        return value;
    }
    public void setValue(ArrayList<InterpreterDataType> value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return null;
    }
    @Override
    public void FromString(String input) {
    }
}
