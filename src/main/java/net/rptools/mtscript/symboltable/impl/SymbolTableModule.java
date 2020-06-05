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
package net.rptools.mtscript.symboltable.impl;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableEntryFactory;
import net.rptools.mtscript.symboltable.SymbolTableFactory;
import net.rptools.mtscript.symboltable.SymbolTableStack;

/** Class for configuring dependency injection for google guice for symbol tables. */
public class SymbolTableModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SymbolTableStack.class).to(StandardSymbolTableStack.class);

    install(
        new FactoryModuleBuilder()
            .implement(SymbolTableEntry.class, StandardSymbolTableEntry.class)
            .build(SymbolTableEntryFactory.class));
    install(
        new FactoryModuleBuilder()
            .implement(SymbolTable.class, StandardSymbolTable.class)
            .build(SymbolTableFactory.class));
  }
}
