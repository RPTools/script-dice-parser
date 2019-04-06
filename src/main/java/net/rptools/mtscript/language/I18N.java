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
package net.rptools.mtscript.language;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class is the front-end for all string handling. The goal is for all text to be external from
 * the application source so that translations can be done without editing source code. To that end,
 * this class will locate the <b>i18n.properties</b> file for the current locale and read the string
 * values from it, returning the results.
 *
 * <p>As MapTool uses a base name for the string and extensions for alternate pieces (such as <code>
 * action.loadMap</code> as the base and <code>action.loadMap.accel</code> as the menu accelerator
 * key) there are different methods used to return the different components.
 *
 * <p>The ResourceBundle name is <b>net.rptools.maptool.language.i18n</b>.
 *
 * @author tcroft
 */
public class I18N {
  private static final ResourceBundle BUNDLE;
  private static final Logger log = LogManager.getLogger(I18N.class);

  private static Enumeration<String> keys;

  static {
    BUNDLE = ResourceBundle.getBundle("net.rptools.inventory.language.i18n");
  }

  /**
   * Returns the String that results from a lookup within the properties file.
   *
   * @param key the component to search for
   * @return the String found or <code>null</code>
   */
  public static String getString(String key, ResourceBundle bundle) {
    try {
      return bundle.getString(key);
    } catch (MissingResourceException e) {
      return null;
    }
  }

  /**
   * Returns the String that results from a lookup within the properties file.
   *
   * @param key the component to search for
   * @return the String found or <code>null</code>
   */
  public static String getString(String key) {
    try {
      return BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return null;
    }
  }

  /**
   * Returns the text associated with the given key after removing any menu mnemonic. So for the key
   * <b>action.loadMap</b> that has the value "&Load Map" in the properties file, this method
   * returns "Load Map".
   *
   * @param key the component to search for
   * @return the String found with mnemonics removed, or the input key if not found
   */
  public static String getText(String key) {
    String value = getString(key);
    if (value == null) {
      log.debug("Cannot find key '" + key + "' in properties file.");
      return key;
    }
    return value.replaceAll("\\&", "");
  }

  /**
   * Functionally identical to {@link #getText(String key)} except that this one bundles the
   * formatting calls into this code. This version of the method is truly only needed when the
   * string being retrieved contains parameters. In MapTool, this commonly means the player's name
   * or a filename. See the "Parameterized Strings" section of the <b>i18n.properties</b> file for
   * example usage. Full documentation for this technique can be found under {@link
   * MessageFormat#format}.
   *
   * @param key the <code>propertyKey</code> to use for lookup in the properties file
   * @param args parameters needed for formatting purposes
   * @return the formatted String
   */
  public static String getText(String key, Object... args) {
    // If the key doesn't exist in the file, the key becomes the format and
    // nothing will be substituted; it's a little extra work, but is not the normal case
    // anyway.
    String msg = MessageFormat.format(getText(key), args);
    return msg;
  }

  /** Returns all matching keys when given a string regular expression. */
  public static List<String> getMatchingKeys(String regex) {
    return getMatchingKeys(Pattern.compile(regex));
  }

  /** Returns all matching keys when given a compiled regular expression pattern. */
  public static List<String> getMatchingKeys(Pattern regex) {
    Enumeration<String> keys = BUNDLE.getKeys();

    List<String> menuItemKeys = new LinkedList<String>();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement();
      if (regex.matcher(key).find()) {
        menuItemKeys.add(key);
      }
    }
    return menuItemKeys;
  }
}
