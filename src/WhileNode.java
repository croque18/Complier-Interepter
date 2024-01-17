import java.util.ArrayList;

public class WhileNode extends StatementNode{
    private BooleanCompareNode condition;
    private ArrayList<StatementNode> StatementList = new ArrayList<StatementNode>();

    public WhileNode() {
    }

    public WhileNode(BooleanCompareNode condition, ArrayList<StatementNode> StatementList) {
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

    @Override
    public StatementNode[] getConstantList() {
        return new StatementNode[0];
    }

    public void setStatementList(ArrayList<StatementNode> statementList) {
        StatementList = statementList;
    }

    @Override
    public String toString() {
        String result = "while (" + condition + ") {";
        for (StatementNode statement : StatementList) {
            result += " " + statement;
        }
        result += " }";
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}
