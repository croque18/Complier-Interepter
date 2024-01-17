import java.util.ArrayList;

public class IfNode extends StatementNode {
    private BooleanCompareNode condition;
    private ArrayList<StatementNode> StatementList = new ArrayList<StatementNode>();
    private IfNode NextIf;

    public IfNode() {
    }

    public IfNode(BooleanCompareNode condition, ArrayList<StatementNode> StatementList, IfNode NextIf) {
        this.condition = condition;
        this.StatementList = StatementList;
        this.NextIf = NextIf;
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

    public IfNode getNextIf() {
        return NextIf;
    }

    public void setNextIf(IfNode nextIf) {
        NextIf = nextIf;
    }

    @Override
    public String toString() {
        String result = "if (" + condition + ") {";
        for (StatementNode statement : StatementList) {
            result += " " + statement;
        }
        result += " }";
        if (NextIf != null) {
            result += " " + NextIf;
        }
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}
