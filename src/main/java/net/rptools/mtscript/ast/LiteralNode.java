/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * RPTools Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public abstract class LiteralNode<T> implements ExpressionNode {

  private final T value;

  LiteralNode(T value) {
    this.value = requireNonNull(value);
  }

  public T getValue() {
    return value;
  }

  public static class NullLiteralNode extends LiteralNode<Void> {
    public NullLiteralNode() {
      super(null);
    }
  }

  public static class BooleanLiteralNode extends LiteralNode<Boolean> {
    public BooleanLiteralNode(boolean b) {
      super(b);
    }
  }

  public static class IntegerLiteralNode extends LiteralNode<Integer> {
    public IntegerLiteralNode(int i) {
      super(i);
    }
  }

  public static class NumberLiteralNode extends LiteralNode<Double> {
    public NumberLiteralNode(double d) {
      super(d);
    }
  }

  public static class StringLiteralNode extends LiteralNode<String> {
    public StringLiteralNode(String s) {
      super(s);
    }
  }
}
