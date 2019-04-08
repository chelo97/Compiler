package compiler;

public class Token {
    public enum Type {
        L_CORCHETE,
        R_CORCHETE,
        L_LLAVE,
        R_LLAVE,
        COMA,
        DOS_PUNTOS,
        LITERAL_CADENA,
        LITERAL_NUM,
        PR_TRUE,
        PR_FALSE,
        PR_NULL,
        EOF
    }

    public final Type type;
    public final String value;

    public Token(final Type type, final String value) {
        this.type = type;
        this.value = value;
    }

    private String typeStringValue(Type type) {
        switch(type) {
            case L_CORCHETE:
                return "L_CORCHETE";
            case R_CORCHETE:
                return "R_CORCHETE";
            case L_LLAVE:
                return "L_LLAVE";
            case R_LLAVE:
                return "R_LLAVE";
            case COMA:
                return "COMA";
            case DOS_PUNTOS:
                return "DOS_PUNTOS";
            case LITERAL_CADENA:
                return "LITERAL_CADENA";
            case LITERAL_NUM:
                return "LITERAL_NUM";
            case PR_TRUE:
                return "PR_TRUE";
            case PR_FALSE:
                return "PR_FALSE";
            case PR_NULL:
                return "PR_NULL";
            case EOF:
                return "EOF";
            default:
                return "ERROR";
        }
    }

    public String toString() {
        return String.format("Type: %s, Value: %s", typeStringValue(type), value);
    }
}