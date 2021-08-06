package me.swinxy;

import net.sourceforge.jwbf.core.actions.HttpActionClient;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class SWinxyTheBot {

	private static final String ENWIKI_URL = "https://en.wikipedia.org/w/";

	public static void main(String[] args) {
		HttpActionClient client = HttpActionClient
				.builder()
				.withUrl(ENWIKI_URL)
				.withUserAgent("SWinxyTheBot", "0.1-SNAPSHOT", "wikipedia:en:User:SWinxy")
				.withRequestsPerUnit(10, TimeUnit.MINUTES)
				.build();

		MediaWikiBot bot = new MediaWikiBot(client);

		Article article = bot.getArticle("Minecraft");
		String text = article.getText();

		if (allowBots(text, "SWinxyTheBot")) {
			System.out.println(RemoveEnglishCiteParameter.apply(text));
		}
	}


	/**
	 * Stolen from wikipedia:en:Template:Bots.
	 * Checks to see if the bot may edit the page.
	 * @param text article text
	 * @param user username of the bot
	 * @return true if the article allows editing from the bot
	 */
	@Contract(pure = true)
	public static boolean allowBots(@NotNull String text, String user) {
		return !text.matches("(?si).*\\{\\{(nobots|bots\\|(allow=none|deny=([^}]*?" + user + "[^}]*?|all)|optout=all))}}.*");
	}
}