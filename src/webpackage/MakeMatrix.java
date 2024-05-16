package webpackage;

import java.io.IOException;
import java.util.List;

public class MakeMatrix {

    public static List<List<String>> makeMatrix(int depth, int userDepth, String url, List<List<String>> matrix, List<String> nodes) throws IOException {
        if (depth < userDepth) {
            List<String> newUrls = GetUrls.getUrls(url);
            if (!nodes.contains(url)) {
                nodes.add(url);
                matrix.add(newUrls);
            }
            for (String currUrl : newUrls) {
                makeMatrix(depth + 1, userDepth, currUrl, matrix, nodes);
            }
        }
        return matrix;
    }
}
