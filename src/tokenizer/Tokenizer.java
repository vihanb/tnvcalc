package tokenizer;

import token.Token;
import token.TokenType;
import java.util.*;
import java.util.regex.*;

public class Tokenizer {
    public Tokenizer() {
        // Well nothing really needs to happen here anymore
    }

    private int position;
    public Queue<Token> feed(String source) {
        Deque<Token> output = new ArrayDeque<Token>();
        int relativePos = 0;

        while (relativePos < source.length()) {
            // Handle whitespace
            if (source.charAt(relativePos) == ' ') {
                relativePos++; continue;
            }

            Token result = null;
            for (TokenType attempt : TokenType.values()) {
                Pattern regex = attempt.getRegex();
                Matcher m = regex.matcher(source.subSequence(relativePos, source.length()));

                // Match token starting at this index
                if (m.find()) {
                    String matchedString = m.group(0);
                    result = new Token(attempt, matchedString);
                    relativePos += matchedString.length();

                    break;
                }
            }
            if (result == null) { return null; }
            output.add(result);
        }

        return output;
    }
}
