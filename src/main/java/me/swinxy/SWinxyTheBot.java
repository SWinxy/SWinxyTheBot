package me.swinxy;

import net.sourceforge.jwbf.core.actions.HttpActionClient;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

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
		System.out.println(RemoveEnglishCiteParameter.apply(article.getText()));
	}
}