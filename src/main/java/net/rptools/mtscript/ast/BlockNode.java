package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.List;

public class BlockNode implements ASTNode {
  private final List<BlockStatementNode> body;

  public BlockNode(List<BlockStatementNode> body) {
    this.body = requireNonNull(body, "body");
  }

  public List<BlockStatementNode> getBody() {
    return body;
  }
}
