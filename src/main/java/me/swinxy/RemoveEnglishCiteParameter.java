package me.swinxy;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Citations on the enwiki don't need to have things like <code>{{Cite ...|language=en-US}}</code>.
 * This class is tasked with removing unnecessary language parameters in citations.
 * @apiNote This doesn't check for being in citations or in templates,
 * so it's best to keep the stuff into mainspace.
 */
public class RemoveEnglishCiteParameter {

	/**
	 * Replaces English language identifiers in the given article text with a regex expression.
	 * Matches <code>lang</code> and <code>language</code> and <code>=&lt;en language identifier&gt;</code>
	 *
	 * Whitespace:
	 * <code>([ \n\t]*)</code>
	 * Looks for any whitespace, tab, or line break in the article.
	 *
	 * Language tag:
	 * <code>|([ \n\t]*)lang(uage)? *= *</code>
	 * Finds the specific language tag with an equals sign.
	 * Does not care if it's in a citation. TODO?
	 *
	 * <code>(en|EN|english|English)</code>
	 * Matches the language code or full spelling of the name.
	 *
	 * <code>-?([a-zA-Z]{2})?</code>
	 * Checks for optional dialect (eg American English or British English).
	 * @param article full text of the article
	 * @return replaced article text
	 */
	@NotNull
	@Contract(pure = true)
	public static String apply(@NotNull String article) {
		return article.replaceAll("([ \n\t]*)\\|([ \n\t]*)lang(uage)? *= *(en|EN|english|English)-?([a-zA-Z]{2})?", "");
	}
}
