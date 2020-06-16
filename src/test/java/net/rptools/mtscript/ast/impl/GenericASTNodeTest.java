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
package net.rptools.mtscript.ast.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Map;
import java.util.Random;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.types.MTSType;
import org.junit.jupiter.api.Test;

class GenericASTNodeTest {

  private final Random random = new Random();

  @Test
  public void creation() {
    for (var nodeType : ASTNodeType.values()) {
      GenericASTNode node = new GenericASTNode(nodeType);

      assertEquals(nodeType, node.getType());
    }
  }

  @Test
  public void parent() {
    for (var nodeType : ASTNodeType.values()) {
      GenericASTNode parent = new GenericASTNode(nodeType);
      for (var nodeType2 : ASTNodeType.values()) {
        GenericASTNode node = new GenericASTNode(nodeType2);
        node.setParent(parent);
        assertEquals(parent, node.getParent());
      }
    }
  }

  @Test
  public void children() {
    for (var nodeType : ASTNodeType.values()) {
      GenericASTNode parent = new GenericASTNode(nodeType);
      assertTrue(parent.getChildren().isEmpty());

      int num = random.nextInt(100);
      int numberAdded = 0;
      for (int i = 0; i < num; i++) {
        for (var nodeType2 : ASTNodeType.values()) {
          GenericASTNode node = new GenericASTNode(nodeType2);
          parent.addChild(node);

          assertEquals(parent, node.getParent());
          assertTrue(parent.getChildren().contains(node));

          numberAdded++;
        }
      }

      assertEquals(numberAdded, parent.getChildren().size());
    }
  }

  @Test
  public void attribute() {
    for (var att : ASTAttributeKey.values()) {
      GenericASTNode node = new GenericASTNode(ASTNodeType.values()[0]);
      assertTrue(node.getAttribute(att).isEmpty());
      assertTrue(node.getAttribute(att, Integer.class).isEmpty());
      assertTrue(node.getAttribute(att, List.class).isEmpty());
      assertTrue(node.getAttribute(att, Map.class).isEmpty());
      assertTrue(node.getAttribute(att, String.class).isEmpty());

      int num = random.nextInt(200) + 1;
      Integer intTest = num;
      node.setAttribute(att, intTest);
      assertEquals((Integer) node.getAttribute(att).get(), intTest);
      assertEquals(node.getAttribute(att, Integer.class).get(), intTest);

      String stringTest = " ".repeat(num);
      node.setAttribute(att, stringTest);
      assertEquals((String) node.getAttribute(att).get(), stringTest);
      assertEquals(node.getAttribute(att, String.class).get(), stringTest);
    }
  }

  @Test
  public void mtsType() {
    int num = random.nextInt(100) + 1;
    for (int i = 0; i < num; i++) {
      MTSType mtsType = mock(MTSType.class);
      GenericASTNode node = new GenericASTNode(ASTNodeType.values()[0]);

      node.setMTSType(mtsType);

      assertEquals(mtsType, node.getMTSType());
    }
  }
}
