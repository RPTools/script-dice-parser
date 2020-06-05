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
package net.rptools.mtscript.executor.impl;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import net.rptools.mtscript.executor.RuntimeCallStack;
import net.rptools.mtscript.executor.RuntimeStack;
import net.rptools.mtscript.executor.StackFrame;
import net.rptools.mtscript.executor.StackFrameFactory;
import net.rptools.mtscript.executor.StackMemory;
import net.rptools.mtscript.executor.StackMemoryFactory;
import net.rptools.mtscript.executor.StackMemoryLocation;
import net.rptools.mtscript.executor.StackMemoryLocationFactory;


/**
 * {@link com.google.inject.Module} used to define Google guice dependency injection for Script
 * execution.
 */
public class ExecutorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(RuntimeStack.class).toProvider(RuntimeStackProvider.class);
    bind(RuntimeCallStack.class).toProvider(RuntimeCallStackProvider.class);

    install(
        new FactoryModuleBuilder()
            .implement(StackMemory.class, StandardStackMemory.class)
            .build(StackMemoryFactory.class));
    install(
        new FactoryModuleBuilder()
            .implement(StackFrame.class, StandardStackFrame.class)
            .build(StackFrameFactory.class));
    install(
        new FactoryModuleBuilder()
            .implement(StackMemoryLocation.class, StandardStackMemoryLocation.class)
            .build(StackMemoryLocationFactory.class));
  }
}
