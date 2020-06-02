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
package net.rptools.mtscript.symboltable;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Standard implementation of a {@link SymbolTableStack} used for maintaining a stack of symbol
 * tables used for resolving symbols in the script over several scopes.
 */
public class StandardSymbolTableStack implements SymbolTableStack {

  /** {@link ArrayList} used to maintain the stack of {@link SymbolTable}s. */
  private final ArrayList<SymbolTable> stack = new ArrayList<>();

  /** The level for the local scope which is at the top of the stack. */
  private int stackTop;

  /** Factory class used to create new {@link SymbolTable}s. */
  private final SymbolTableFactory symbolTableFactory;

  @Inject
  private StandardSymbolTableStack(SymbolTableFactory factory) {
    symbolTableFactory = factory;
    stackTop = 0;
    stack.add(factory.create(stackTop));
  }

  @Override
  public int getNestingLevel() {
    return stackTop;
  }

  @Override
  public SymbolTable getLocalSymbolTable() {
    return stack.get(stackTop);
  }

  @Override
  public SymbolTable push() {
    stackTop++;
    SymbolTable symbolTable = symbolTableFactory.create(stackTop);
    stack.add(symbolTable);
    return symbolTable;
  }

  @Override
  public SymbolTable pop() {
    SymbolTable symbolTable = stack.get(stackTop);
    stackTop--;
    stack.remove(stackTop);
    return null;
  }

  @Override
  public SymbolTableEntry create(String name) {
    return stack.get(stackTop).create(name);
  }

  @Override
  public Optional<SymbolTableEntry> lookup(String name) {
    for (int i = stackTop; i >= 0; i--) {
      SymbolTable symbolTable = stack.get(i);
      Optional<SymbolTableEntry> lookup = symbolTable.lookup(name);
      if (lookup.isPresent()) {
        return lookup;
      }
    }

    return Optional.empty();
  }

  @Override
  public Optional<SymbolTableEntry> lookupLocal(String name) {
    SymbolTable symbolTable = stack.get(stackTop);
    return symbolTable.lookup(name);
  }

  @Override
  public SymbolTable getSymbolTable(int level) {
    if (level > stackTop) {
      throw new IllegalStateException("Scope Level Does not exist.");
    }

    return stack.get(level);
  }
}
