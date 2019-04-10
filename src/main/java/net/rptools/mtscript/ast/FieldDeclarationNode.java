package net.rptools.maptool.ast;

/**
 * An AST Node for declaration of one or more fields.
 */
class FieldDeclarationNode implements ASTNode {

  private final String type; // TODO Replace this with a type enum, perhaps
  private final Map<String, ASTNode> fields;

  /**
   * Creates a FieldDeclarationNode for the declaration of one or more
   * fields.
   *
   * @param type the type.
   * @param fields the fields.
   */
  FieldDeclarationNode(String type, Map<String, ASTNode> fields) {
    this.type = type;
    this.fields = fields;
  }

  /**
   * Returns the type.
   *
   * @return the type.
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the fields.
   * @return the fields.
   */
  public Map<String, ASTNode> getFields() {
    return fields;
  }
}
