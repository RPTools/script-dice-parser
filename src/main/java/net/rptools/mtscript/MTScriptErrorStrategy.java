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

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

public class MTScriptErrorStrategy extends BailErrorStrategy {

  @Override
  public void reportError(Parser recognizer, RecognitionException e) {
    String errorString =
        "Error:\n"
            + "\tExpected:\n"
            + "\t\t"
            + e.getExpectedTokens().toString(recognizer.getVocabulary())
            + "\tFound:\n"
            + "\t\t"
            + recognizer.getVocabulary().getDisplayName(e.getOffendingToken().getType());
    System.err.println(errorString);
    super.reportError(recognizer, e);
  }
}
