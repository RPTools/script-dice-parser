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
package net.rptools.mtscript.types.impl;

import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;

/** Concrete class for MapTool Script Type */
public class StandardMTSType implements MTSType {

  /** The symbol table entry for the type. */
  private final SymbolTableEntry symbolTableEntry;

  private final Object defaultValue;

  /**
   * Creates a new {@code StandardMTSType} object.
   *
   * @param entry the {@link SymbolTableEntry} for this type.
   * @param defaultValue the default "uninitialized" value for this type.
   */
  StandardMTSType(SymbolTableEntry entry, Object defaultValue) {
    symbolTableEntry = entry;
    this.defaultValue = defaultValue;
  }

  @Override
  public SymbolTableEntry getSymbolTableEntry() {
    return symbolTableEntry;
  }

  @Override
  public String getName() {
    return symbolTableEntry.getName();
  }

  @Override
  public Object getDefaultValue() {
    return defaultValue;
  }
}
