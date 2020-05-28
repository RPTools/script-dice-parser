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
package net.rptools.mtscript.listener;

import java.util.ArrayList;
import java.util.List;
import net.rptools.mtscript.parser.MTScript2Parser.DiceContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptBodyContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptModuleContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptModuleDefinitionContext;
import net.rptools.mtscript.parser.MTScript2Parser.TextContext;
import net.rptools.mtscript.parser.MTScript2ParserBaseListener;

public class MTScript2TestListener extends MTScript2ParserBaseListener {

  private List<String> textStrings = new ArrayList<>();
  private List<String> inlineRollStrings = new ArrayList<>();
  private List<String> inlineScriptStrings = new ArrayList<>();
  private List<String> scriptModule = new ArrayList<>();
  private String moduleName;
  private String moduleVersion;
  private String moduleDescription;

  public List<String> getTextStrings() {
    return textStrings;
  }

  public List<String> getInlineRollStrings() {
    return inlineRollStrings;
  }

  public List<String> getInlineScriptStrings() {
    return inlineScriptStrings;
  }

  public List<String> getScriptModule() {
    return scriptModule;
  }

  public String getModuleName() {
    return moduleName;
  }

  public String getModuleVersion() {
    return moduleVersion;
  }

  public String getModuleDescription() {
    return moduleDescription;
  }

  @Override
  public void enterText(TextContext ctx) {
    textStrings.add(ctx.getText());
  }

  @Override
  public void enterScriptBody(ScriptBodyContext ctx) {
    inlineScriptStrings.add(ctx.getText());
  }

  @Override
  public void enterScriptModule(ScriptModuleContext ctx) {
    scriptModule.add(ctx.getText());
  }

  @Override
  public void enterDice(DiceContext ctx) {
    inlineRollStrings.add(ctx.getText());
  }

  @Override
  public void enterScriptModuleDefinition(ScriptModuleDefinitionContext ctx) {
    moduleName = ctx.name.getText();
    moduleVersion = ctx.version.getText();
    moduleDescription = ctx.desc.getText();
  }
}
