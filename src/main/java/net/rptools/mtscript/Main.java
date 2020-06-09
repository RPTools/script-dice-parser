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
package net.rptools.mtscript;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.rptools.mtscript.injection.ScriptModule;
import net.rptools.mtscript.interpreter.executor.impl.ExecutorModule;
import net.rptools.mtscript.interpreter.runtimestack.impl.RuntimeStackModule;
import net.rptools.mtscript.parser.visitor.impl.ParserVisitorModule;
import net.rptools.mtscript.symboltable.impl.SymbolTableModule;
import net.rptools.mtscript.types.impl.TypesModule;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

  private boolean dumpSymbolTable = true; // false;
  private boolean module = false;
  private String filename;

  public static void main(String[] args) throws IOException, ParseException {
    Main main = new Main();
    main.run(args);
  }

  private void run(String[] args) throws IOException, ParseException {
    parseArgs(args);

    Injector injector =
        Guice.createInjector(
            new ScriptModule(),
            new RuntimeStackModule(),
            new ExecutorModule(),
            new SymbolTableModule(),
            new ParserVisitorModule(),
            new TypesModule());
    MapToolScript mapToolScript = injector.getInstance(MapToolScriptImpl.class);
    String script = new String(Files.readAllBytes(Paths.get(filename)));
    if (module) {
      mapToolScript.parseModule(script);
    } else {
      mapToolScript.parseScript(script);
    }

    if (dumpSymbolTable) {
      mapToolScript.printSymbolTable();
    }
  }

  private void parseArgs(String[] args) throws ParseException {
    Options options = new Options();
    options.addOption("s", false, "Dump SymbolTable details");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    if (cmd.hasOption("s")) {
      dumpSymbolTable = true;
    }

    filename = cmd.getArgs()[0];
  }
}
