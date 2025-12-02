// Generated from C:/Users/leand/IdeaProjects/COMPIL/src/Pcf.g4 by ANTLR 4.13.2
package parserANTLR;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PcfLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, FUN=5, IFZ=6, THEN=7, ELSE=8, FIX=9, LET=10, 
		IN=11, PLUS=12, MINUS=13, MULT=14, DIV=15, CONST=16, VAR=17, WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "FUN", "IFZ", "THEN", "ELSE", "FIX", 
			"LET", "IN", "PLUS", "MINUS", "MULT", "DIV", "CONST", "VAR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'->'", "'('", "')'", "'fun'", "'ifz'", "'then'", "'else'", 
			"'fix'", "'let'", "'in'", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "FUN", "IFZ", "THEN", "ELSE", "FIX", "LET", 
			"IN", "PLUS", "MINUS", "MULT", "DIV", "CONST", "VAR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PcfLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Pcf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0012k\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000fW\b\u000f\n\u000f\f\u000fZ\t\u000f\u0003"+
		"\u000f\\\b\u000f\u0001\u0010\u0001\u0010\u0005\u0010`\b\u0010\n\u0010"+
		"\f\u0010c\t\u0010\u0001\u0011\u0004\u0011f\b\u0011\u000b\u0011\f\u0011"+
		"g\u0001\u0011\u0001\u0011\u0000\u0000\u0012\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012\u0001\u0000\u0005\u0001\u000019\u0001\u000009\u0001\u0000az\u0002"+
		"\u000009az\u0003\u0000\t\n\r\r  n\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0001%\u0001\u0000\u0000\u0000\u0003\'\u0001\u0000\u0000"+
		"\u0000\u0005*\u0001\u0000\u0000\u0000\u0007,\u0001\u0000\u0000\u0000\t"+
		".\u0001\u0000\u0000\u0000\u000b2\u0001\u0000\u0000\u0000\r6\u0001\u0000"+
		"\u0000\u0000\u000f;\u0001\u0000\u0000\u0000\u0011@\u0001\u0000\u0000\u0000"+
		"\u0013D\u0001\u0000\u0000\u0000\u0015H\u0001\u0000\u0000\u0000\u0017K"+
		"\u0001\u0000\u0000\u0000\u0019M\u0001\u0000\u0000\u0000\u001bO\u0001\u0000"+
		"\u0000\u0000\u001dQ\u0001\u0000\u0000\u0000\u001f[\u0001\u0000\u0000\u0000"+
		"!]\u0001\u0000\u0000\u0000#e\u0001\u0000\u0000\u0000%&\u0005=\u0000\u0000"+
		"&\u0002\u0001\u0000\u0000\u0000\'(\u0005-\u0000\u0000()\u0005>\u0000\u0000"+
		")\u0004\u0001\u0000\u0000\u0000*+\u0005(\u0000\u0000+\u0006\u0001\u0000"+
		"\u0000\u0000,-\u0005)\u0000\u0000-\b\u0001\u0000\u0000\u0000./\u0005f"+
		"\u0000\u0000/0\u0005u\u0000\u000001\u0005n\u0000\u00001\n\u0001\u0000"+
		"\u0000\u000023\u0005i\u0000\u000034\u0005f\u0000\u000045\u0005z\u0000"+
		"\u00005\f\u0001\u0000\u0000\u000067\u0005t\u0000\u000078\u0005h\u0000"+
		"\u000089\u0005e\u0000\u00009:\u0005n\u0000\u0000:\u000e\u0001\u0000\u0000"+
		"\u0000;<\u0005e\u0000\u0000<=\u0005l\u0000\u0000=>\u0005s\u0000\u0000"+
		">?\u0005e\u0000\u0000?\u0010\u0001\u0000\u0000\u0000@A\u0005f\u0000\u0000"+
		"AB\u0005i\u0000\u0000BC\u0005x\u0000\u0000C\u0012\u0001\u0000\u0000\u0000"+
		"DE\u0005l\u0000\u0000EF\u0005e\u0000\u0000FG\u0005t\u0000\u0000G\u0014"+
		"\u0001\u0000\u0000\u0000HI\u0005i\u0000\u0000IJ\u0005n\u0000\u0000J\u0016"+
		"\u0001\u0000\u0000\u0000KL\u0005+\u0000\u0000L\u0018\u0001\u0000\u0000"+
		"\u0000MN\u0005-\u0000\u0000N\u001a\u0001\u0000\u0000\u0000OP\u0005*\u0000"+
		"\u0000P\u001c\u0001\u0000\u0000\u0000QR\u0005/\u0000\u0000R\u001e\u0001"+
		"\u0000\u0000\u0000S\\\u00050\u0000\u0000TX\u0007\u0000\u0000\u0000UW\u0007"+
		"\u0001\u0000\u0000VU\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000"+
		"\u0000ZX\u0001\u0000\u0000\u0000[S\u0001\u0000\u0000\u0000[T\u0001\u0000"+
		"\u0000\u0000\\ \u0001\u0000\u0000\u0000]a\u0007\u0002\u0000\u0000^`\u0007"+
		"\u0003\u0000\u0000_^\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000"+
		"a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b\"\u0001\u0000\u0000"+
		"\u0000ca\u0001\u0000\u0000\u0000df\u0007\u0004\u0000\u0000ed\u0001\u0000"+
		"\u0000\u0000fg\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0006\u0011\u0000\u0000"+
		"j$\u0001\u0000\u0000\u0000\u0005\u0000X[ag\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}