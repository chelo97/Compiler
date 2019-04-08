package compiler;

public class JsonLexer extends Lexer {
    public JsonLexer(String input) {
        super(input);
    }

    private Token lexString() {
        acceptRunUntil("\"");
        String value = value();
        //necesitamos ignorar la " del final
        next();
        ignore();
        return new Token(Token.Type.LITERAL_CADENA, value);
    }

    private Token lexNumber() {
        accept("+-");
        String digits = "0123456789";
        /*if (accept("0") && accept("xX")) {
            digits = "0123456789abcdefABCDEF";
        }*/

        acceptRun(digits);

        if (accept(".")) {
            acceptRun(digits);
        }

        if (accept("eE")) {
            accept("+-");
            acceptRun("0123456789");
        }

        return new Token(Token.Type.LITERAL_NUM, value());
    }

    private Token lexNull() {
        if (acceptInSequence("null")) {
            return new Token(Token.Type.PR_NULL, value());
        }

        return null;
    }

    private Token lexBoolean() {
        if (acceptInSequence("false")) {
            return new Token(Token.Type.PR_FALSE, value());
        }
        if (acceptInSequence("true")) {
            return new Token(Token.Type.PR_TRUE, value());
        }
        return null;
    }

    public Token nextToken() {
        if (peek() == null) return null;

        //remove white spaces
        this.acceptRun(" \t\r\n");
        this.ignore();

        String n = next();

        switch (n) {
            case "\"":
                ignore();
                return lexString();
            case "f":
            case "t":
                backup();
                return lexBoolean();
            case "n":
                backup();
                return lexNull();
            case "{":
                return new Token(Token.Type.L_LLAVE, value());
            case "}":
                return new Token(Token.Type.R_LLAVE, value());
            case "[":
                return new Token(Token.Type.L_CORCHETE, value());
            case "]":
                return new Token(Token.Type.R_CORCHETE, value());
            case ":":
                return new Token(Token.Type.DOS_PUNTOS, value());
            case ",":
                return new Token(Token.Type.COMA, value());
            case "\\Z":
                return new Token(Token.Type.EOF, value());
            default:
                if ("+-1234567890".contains(n)) {
                    backup();
                    return lexNumber();
                }
        }


        return null;
    }
}
