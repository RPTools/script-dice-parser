package net.rptools.mtscript.ast;


/**
 * Abstract syntax tree node that represents the modulus operator.
 */
public class ModulusNode extends BinaryOperatorNode {

  /**
   * Creates a new node which represents a modulus operation.
   *
   * @param left the value on the left hand side of the modulus operator
   * @param right the value on the right hand side of the module operator..
   */
  public ModulusNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
