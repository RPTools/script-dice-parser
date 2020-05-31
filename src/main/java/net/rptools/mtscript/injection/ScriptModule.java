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
package net.rptools.mtscript.injection;

import com.google.inject.AbstractModule;
import net.rptools.mtscript.MTScriptErrorStrategy;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.GenericASTNode;
import net.rptools.mtscript.symboltable.SymbolTableFactory;
import net.rptools.mtscript.symboltable.SymbolTableFactoryClass;
import org.antlr.v4.runtime.ANTLRErrorStrategy;

public class ScriptModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();
    bind(ASTNode.class).to(GenericASTNode.class);
    bind(ANTLRErrorStrategy.class).to(MTScriptErrorStrategy.class);
    bind(SymbolTableFactory.class).to(SymbolTableFactoryClass.class);
  }
}
