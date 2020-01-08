package net.rptools.mtscript.ast;

public class NullLiteralNode extends LiteralNode<Object> {
  public NullLiteralNode() {
    super("");
  }

  @Override
  public Object getValue() {
    return null;
  }
}
