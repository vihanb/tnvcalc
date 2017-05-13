package tokenizer;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.*;

public class Tokenizer {
    public Tokenizer() {
        // Well nothing really needs to happen here anymore
    }

    private int position;
    public List<Token> feed(String source) {
        ArrayList<Token> output = new ArrayList<>();

        while (!source.isEmpty()) {
            Token result = null;
            for (TokenType attempt : TokenType.values()) {
                Pattern regex = attempt.getRegex();
                Matcher m = regex.matcher(source);

                // Match token starting at this index
                if (m.find()) {
                    String matchedString = m.group(0);
                    result = new Token(attempt, matchedString);
                    source = source.substring(matchedString.length());

                    break;
                }
            }
            if (result == null) { return null; }
            output.add(result);
        }

        return output;
    }
}
