package parserANTLR; // Generated from /home/jperezr/Documents/LOGIN/Compilation et Interpretation/TP - Interpreter/src/Pcf.g4 by ANTLR 4.13.2

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
		T__0=1, T__1=2, FUN=3, CONST=4, OP=5, IFZ=6, THEN=7, ELSE=8, FIX=9, LET=10, 
		IN=11, VAR=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "FUN", "CONST", "OP", "IFZ", "THEN", "ELSE", "FIX", "LET", 
			"IN", "VAR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'='", "'fun'", null, null, "'ifz'", "'then'", "'else'", 
			"'fix'", "'let'", "'in'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "FUN", "CONST", "OP", "IFZ", "THEN", "ELSE", "FIX", 
			"LET", "IN", "VAR", "WS"
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
		"\u0004\u0000\rT\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003(\b\u0003\n\u0003\f\u0003+\t"+
		"\u0003\u0003\u0003-\b\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000bL\b\u000b"+
		"\n\u000b\f\u000bO\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0000\u0000"+
		"\r\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u0001\u0000"+
		"\u0006\u0001\u000019\u0001\u000009\u0003\u0000*+--//\u0001\u0000az\u0002"+
		"\u000009az\u0003\u0000\t\n\r\r  V\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0001\u001b"+
		"\u0001\u0000\u0000\u0000\u0003\u001e\u0001\u0000\u0000\u0000\u0005 \u0001"+
		"\u0000\u0000\u0000\u0007,\u0001\u0000\u0000\u0000\t.\u0001\u0000\u0000"+
		"\u0000\u000b0\u0001\u0000\u0000\u0000\r4\u0001\u0000\u0000\u0000\u000f"+
		"9\u0001\u0000\u0000\u0000\u0011>\u0001\u0000\u0000\u0000\u0013B\u0001"+
		"\u0000\u0000\u0000\u0015F\u0001\u0000\u0000\u0000\u0017I\u0001\u0000\u0000"+
		"\u0000\u0019P\u0001\u0000\u0000\u0000\u001b\u001c\u0005-\u0000\u0000\u001c"+
		"\u001d\u0005>\u0000\u0000\u001d\u0002\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005=\u0000\u0000\u001f\u0004\u0001\u0000\u0000\u0000 !\u0005f\u0000"+
		"\u0000!\"\u0005u\u0000\u0000\"#\u0005n\u0000\u0000#\u0006\u0001\u0000"+
		"\u0000\u0000$-\u00050\u0000\u0000%)\u0007\u0000\u0000\u0000&(\u0007\u0001"+
		"\u0000\u0000\'&\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001"+
		"\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000"+
		"+)\u0001\u0000\u0000\u0000,$\u0001\u0000\u0000\u0000,%\u0001\u0000\u0000"+
		"\u0000-\b\u0001\u0000\u0000\u0000./\u0007\u0002\u0000\u0000/\n\u0001\u0000"+
		"\u0000\u000001\u0005i\u0000\u000012\u0005f\u0000\u000023\u0005z\u0000"+
		"\u00003\f\u0001\u0000\u0000\u000045\u0005t\u0000\u000056\u0005h\u0000"+
		"\u000067\u0005e\u0000\u000078\u0005n\u0000\u00008\u000e\u0001\u0000\u0000"+
		"\u00009:\u0005e\u0000\u0000:;\u0005l\u0000\u0000;<\u0005s\u0000\u0000"+
		"<=\u0005e\u0000\u0000=\u0010\u0001\u0000\u0000\u0000>?\u0005f\u0000\u0000"+
		"?@\u0005i\u0000\u0000@A\u0005x\u0000\u0000A\u0012\u0001\u0000\u0000\u0000"+
		"BC\u0005l\u0000\u0000CD\u0005e\u0000\u0000DE\u0005t\u0000\u0000E\u0014"+
		"\u0001\u0000\u0000\u0000FG\u0005i\u0000\u0000GH\u0005n\u0000\u0000H\u0016"+
		"\u0001\u0000\u0000\u0000IM\u0007\u0003\u0000\u0000JL\u0007\u0004\u0000"+
		"\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MK\u0001\u0000"+
		"\u0000\u0000MN\u0001\u0000\u0000\u0000N\u0018\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000PQ\u0007\u0005\u0000\u0000QR\u0001\u0000\u0000"+
		"\u0000RS\u0006\f\u0000\u0000S\u001a\u0001\u0000\u0000\u0000\u0004\u0000"+
		"),M\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}