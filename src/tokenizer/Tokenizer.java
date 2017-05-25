package tokenizer;

import java.util.Stack;
import java.util.regex.*;

public class Tokenizer {
    public Tokenizer() {
        // Well nothing really needs to happen here anymore
    }

    private int position;
    public Stack<Token> feed(String source) {
        Stack<Token> output = new Stack<Token>();
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
            output.push(result);
        }

        return output;
    }
}
