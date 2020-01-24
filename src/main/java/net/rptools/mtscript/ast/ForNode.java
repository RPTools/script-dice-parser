package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public class ForNode implements StatementNode {

    private final ForControl control;
    private final ASTNode body;

    public ForNode(ForControl control, ASTNode body) {
        this.control = control;
        this.body = requireNonNull(body, "body");
    }

    public ForControl geControl() { return control; }
    public ASTNode getBody() { return body; }

    public static abstract class ForControl {
        // TODO Define common interface
    }

    private static class BasicControl extends ForControl {

        private final StatementNode init;
        private final ExpressionNode condition;
        private final StatementNode step;

        BasicControl(StatementNode init, ExpressionNode condition, StatementNode step) {
            this.init = init;
            this.condition = condition;
            this.step = step;
        }

        public StatementNode getInit() { return init; }
        public ExpressionNode getCondition() { return condition; }
        public StatementNode getStep() { return step; }
    }

    // FIXME implement loop over collection syntax
}
