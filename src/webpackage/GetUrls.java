package webpackage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetUrls {

    public static List<String> getUrls(String url) throws IOException {
        List<String> urls = new ArrayList<>();

        Connection con = Jsoup.connect(url);

        try {
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                Elements links = doc.select("a[href]");

                for (Element link : links) {
                    String href = link.absUrl("href");

                    if (isValidUrl(href) && !href.equals(url)) {
                        urls.add(href);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }

    private static boolean isValidUrl(String url) {
        return url != null && !url.isEmpty() && url.startsWith("http");
    }
}
