import java.util.ArrayList;

public class RepeatNode extends StatementNode{
    private BooleanCompareNode condition;
    private ArrayList<StatementNode> StatementList = new ArrayList<StatementNode>();

    public RepeatNode() {
    }

    public RepeatNode(BooleanCompareNode condition, ArrayList<StatementNode> StatementList) {
        this.condition = condition;
        this.StatementList = StatementList;
    }

    public BooleanCompareNode getCondition() {
        return condition;
    }

    public void setCondition(BooleanCompareNode condition) {
        this.condition = condition;
    }

    public ArrayList<StatementNode> getStatementList() {
        return StatementList;
    }

    public void setStatementList(ArrayList<StatementNode> statementList) {
        StatementList = statementList;
    }

    @Override
    public String toString() {
        String result = "repeat {";
        for (StatementNode statement : StatementList) {
            result += " " + statement;
        }
        result += " } until (" + condition + ")";
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}
