package parserANTLR;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

public class ReportingPcfLexer extends PcfLexer {
    public ReportingPcfLexer(CharStream input) {
        super(input);
    }
    public void recover(LexerNoViableAltException e) {
        ErrorFlag.setFlag(); // report error
        super.recover(e);
    }
}
