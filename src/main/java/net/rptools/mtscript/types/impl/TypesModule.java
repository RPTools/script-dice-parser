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

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeFactory;

/** Class for configuring dependency injection for google guice for script types. */
public class TypesModule extends AbstractModule {

  @Override
  protected void configure() {
    install(
        new FactoryModuleBuilder()
            .implement(MTSType.class, StandardMTSType.class)
            .build(MTSTypeFactory.class));
  }
}
