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
package net.rptools.mtscript.util.impl;

import net.rptools.mtscript.util.MTScriptConstants;

/** Classes that provides constants used through out the parser and execution. */
public class MTScriptConstantsImpl implements MTScriptConstants {

  /** Prefix for internal symbols. */
  private static final String INTERNAL_STRING_PREFIX = "@";

  /** */
  private static final String ENTRY_POINT_SYMBOL_NAME = "entry point";

  @Override
  public String getInternalSymbolPrefix() {
    return INTERNAL_STRING_PREFIX;
  }

  @Override
  public String getEntryPointSymbolName() {
    return INTERNAL_STRING_PREFIX + ENTRY_POINT_SYMBOL_NAME;
  }
}
