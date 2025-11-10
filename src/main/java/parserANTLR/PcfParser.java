// Generated from /home/jperezr/Documents/LOGIN/Compilation et Interpretation/TP - Interpreter/src/Pcf.g4 by ANTLR 4.13.2
package parserANTLR;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PcfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, FUN=5, IFZ=6, THEN=7, ELSE=8, FIX=9, LET=10, 
		IN=11, PLUS=12, MINUS=13, MULT=14, DIV=15, CONST=16, VAR=17, WS=18;
	public static final int
		RULE_term = 0, RULE_letTerm = 1, RULE_funTerm = 2, RULE_addTerm = 3, RULE_mulTerm = 4, 
		RULE_appTerm = 5, RULE_atom = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"term", "letTerm", "funTerm", "addTerm", "mulTerm", "appTerm", "atom"
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

	@Override
	public String getGrammarFileName() { return "Pcf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PcfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public LetTermContext letTerm() {
			return getRuleContext(LetTermContext.class,0);
		}
		public FunTermContext funTerm() {
			return getRuleContext(FunTermContext.class,0);
		}
		public AddTermContext addTerm() {
			return getRuleContext(AddTermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_term);
		try {
			setState(17);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				letTerm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				funTerm();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(16);
				addTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetTermContext extends ParserRuleContext {
		public LetTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letTerm; }
	 
		public LetTermContext() { }
		public void copyFrom(LetTermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FixContext extends LetTermContext {
		public TerminalNode FIX() { return getToken(PcfParser.FIX, 0); }
		public TerminalNode VAR() { return getToken(PcfParser.VAR, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FixContext(LetTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterFix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitFix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitFix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LetContext extends LetTermContext {
		public TerminalNode LET() { return getToken(PcfParser.LET, 0); }
		public TerminalNode VAR() { return getToken(PcfParser.VAR, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode IN() { return getToken(PcfParser.IN, 0); }
		public LetContext(LetTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfZeroContext extends LetTermContext {
		public TerminalNode IFZ() { return getToken(PcfParser.IFZ, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode THEN() { return getToken(PcfParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(PcfParser.ELSE, 0); }
		public IfZeroContext(LetTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterIfZero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitIfZero(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitIfZero(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetTermContext letTerm() throws RecognitionException {
		LetTermContext _localctx = new LetTermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_letTerm);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				_localctx = new LetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				match(LET);
				setState(20);
				match(VAR);
				setState(21);
				match(T__0);
				setState(22);
				term();
				setState(23);
				match(IN);
				setState(24);
				term();
				}
				break;
			case FIX:
				_localctx = new FixContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(FIX);
				setState(27);
				match(VAR);
				setState(28);
				term();
				}
				break;
			case IFZ:
				_localctx = new IfZeroContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				match(IFZ);
				setState(30);
				term();
				setState(31);
				match(THEN);
				setState(32);
				term();
				setState(33);
				match(ELSE);
				setState(34);
				term();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunTermContext extends ParserRuleContext {
		public FunTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funTerm; }
	 
		public FunTermContext() { }
		public void copyFrom(FunTermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends FunTermContext {
		public TerminalNode FUN() { return getToken(PcfParser.FUN, 0); }
		public TerminalNode VAR() { return getToken(PcfParser.VAR, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FunctionContext(FunTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FixFunctionContext extends FunTermContext {
		public TerminalNode FIX() { return getToken(PcfParser.FIX, 0); }
		public TerminalNode FUN() { return getToken(PcfParser.FUN, 0); }
		public List<TerminalNode> VAR() { return getTokens(PcfParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(PcfParser.VAR, i);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FixFunctionContext(FunTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterFixFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitFixFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitFixFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinTermContext extends FunTermContext {
		public AddTermContext addTerm() {
			return getRuleContext(AddTermContext.class,0);
		}
		public BinTermContext(FunTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterBinTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitBinTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitBinTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunTermContext funTerm() throws RecognitionException {
		FunTermContext _localctx = new FunTermContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funTerm);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUN:
				_localctx = new FunctionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				match(FUN);
				setState(39);
				match(VAR);
				setState(40);
				match(T__1);
				setState(41);
				term();
				}
				break;
			case FIX:
				_localctx = new FixFunctionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(FIX);
				setState(43);
				match(FUN);
				setState(44);
				match(VAR);
				setState(45);
				match(VAR);
				setState(46);
				match(T__1);
				setState(47);
				term();
				}
				break;
			case T__2:
			case CONST:
			case VAR:
				_localctx = new BinTermContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				addTerm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddTermContext extends ParserRuleContext {
		public AddTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addTerm; }
	 
		public AddTermContext() { }
		public void copyFrom(AddTermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubContext extends AddTermContext {
		public List<MulTermContext> mulTerm() {
			return getRuleContexts(MulTermContext.class);
		}
		public MulTermContext mulTerm(int i) {
			return getRuleContext(MulTermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(PcfParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(PcfParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(PcfParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(PcfParser.MINUS, i);
		}
		public AddSubContext(AddTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddTermContext addTerm() throws RecognitionException {
		AddTermContext _localctx = new AddTermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_addTerm);
		int _la;
		try {
			_localctx = new AddSubContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			mulTerm();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(52);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(53);
				mulTerm();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulTermContext extends ParserRuleContext {
		public MulTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulTerm; }
	 
		public MulTermContext() { }
		public void copyFrom(MulTermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivContext extends MulTermContext {
		public List<AppTermContext> appTerm() {
			return getRuleContexts(AppTermContext.class);
		}
		public AppTermContext appTerm(int i) {
			return getRuleContext(AppTermContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(PcfParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(PcfParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(PcfParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(PcfParser.DIV, i);
		}
		public MulDivContext(MulTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulTermContext mulTerm() throws RecognitionException {
		MulTermContext _localctx = new MulTermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mulTerm);
		int _la;
		try {
			_localctx = new MulDivContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			appTerm();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				{
				setState(60);
				_la = _input.LA(1);
				if ( !(_la==MULT || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(61);
				appTerm();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AppTermContext extends ParserRuleContext {
		public AppTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_appTerm; }
	 
		public AppTermContext() { }
		public void copyFrom(AppTermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ApplicationContext extends AppTermContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public ApplicationContext(AppTermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterApplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitApplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitApplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AppTermContext appTerm() throws RecognitionException {
		AppTermContext _localctx = new AppTermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_appTerm);
		int _la;
		try {
			_localctx = new ApplicationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67);
				atom();
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 196616L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends AtomContext {
		public TerminalNode VAR() { return getToken(PcfParser.VAR, 0); }
		public VariableContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends AtomContext {
		public TerminalNode CONST() { return getToken(PcfParser.CONST, 0); }
		public ConstantContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesesContext extends AtomContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ParenthesesContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PcfListener ) ((PcfListener)listener).exitParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PcfVisitor ) return ((PcfVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atom);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				_localctx = new VariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(VAR);
				}
				break;
			case CONST:
				_localctx = new ConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(CONST);
				}
				break;
			case T__2:
				_localctx = new ParenthesesContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				match(T__2);
				setState(75);
				term();
				setState(76);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0012Q\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000\u0012\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001%\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u00022\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u00037\b\u0003\n\u0003\f\u0003:\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004?\b\u0004\n\u0004\f\u0004B\t"+
		"\u0004\u0001\u0005\u0004\u0005E\b\u0005\u000b\u0005\f\u0005F\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"O\b\u0006\u0001\u0006\u0000\u0000\u0007\u0000\u0002\u0004\u0006\b\n\f"+
		"\u0000\u0002\u0001\u0000\f\r\u0001\u0000\u000e\u000fT\u0000\u0011\u0001"+
		"\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000\u00041\u0001\u0000\u0000"+
		"\u0000\u00063\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000\nD\u0001"+
		"\u0000\u0000\u0000\fN\u0001\u0000\u0000\u0000\u000e\u0012\u0003\u0002"+
		"\u0001\u0000\u000f\u0012\u0003\u0004\u0002\u0000\u0010\u0012\u0003\u0006"+
		"\u0003\u0000\u0011\u000e\u0001\u0000\u0000\u0000\u0011\u000f\u0001\u0000"+
		"\u0000\u0000\u0011\u0010\u0001\u0000\u0000\u0000\u0012\u0001\u0001\u0000"+
		"\u0000\u0000\u0013\u0014\u0005\n\u0000\u0000\u0014\u0015\u0005\u0011\u0000"+
		"\u0000\u0015\u0016\u0005\u0001\u0000\u0000\u0016\u0017\u0003\u0000\u0000"+
		"\u0000\u0017\u0018\u0005\u000b\u0000\u0000\u0018\u0019\u0003\u0000\u0000"+
		"\u0000\u0019%\u0001\u0000\u0000\u0000\u001a\u001b\u0005\t\u0000\u0000"+
		"\u001b\u001c\u0005\u0011\u0000\u0000\u001c%\u0003\u0000\u0000\u0000\u001d"+
		"\u001e\u0005\u0006\u0000\u0000\u001e\u001f\u0003\u0000\u0000\u0000\u001f"+
		" \u0005\u0007\u0000\u0000 !\u0003\u0000\u0000\u0000!\"\u0005\b\u0000\u0000"+
		"\"#\u0003\u0000\u0000\u0000#%\u0001\u0000\u0000\u0000$\u0013\u0001\u0000"+
		"\u0000\u0000$\u001a\u0001\u0000\u0000\u0000$\u001d\u0001\u0000\u0000\u0000"+
		"%\u0003\u0001\u0000\u0000\u0000&\'\u0005\u0005\u0000\u0000\'(\u0005\u0011"+
		"\u0000\u0000()\u0005\u0002\u0000\u0000)2\u0003\u0000\u0000\u0000*+\u0005"+
		"\t\u0000\u0000+,\u0005\u0005\u0000\u0000,-\u0005\u0011\u0000\u0000-.\u0005"+
		"\u0011\u0000\u0000./\u0005\u0002\u0000\u0000/2\u0003\u0000\u0000\u0000"+
		"02\u0003\u0006\u0003\u00001&\u0001\u0000\u0000\u00001*\u0001\u0000\u0000"+
		"\u000010\u0001\u0000\u0000\u00002\u0005\u0001\u0000\u0000\u000038\u0003"+
		"\b\u0004\u000045\u0007\u0000\u0000\u000057\u0003\b\u0004\u000064\u0001"+
		"\u0000\u0000\u00007:\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u0000"+
		"89\u0001\u0000\u0000\u00009\u0007\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000;@\u0003\n\u0005\u0000<=\u0007\u0001\u0000\u0000=?\u0003\n"+
		"\u0005\u0000><\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\t\u0001\u0000\u0000\u0000"+
		"B@\u0001\u0000\u0000\u0000CE\u0003\f\u0006\u0000DC\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000"+
		"\u0000G\u000b\u0001\u0000\u0000\u0000HO\u0005\u0011\u0000\u0000IO\u0005"+
		"\u0010\u0000\u0000JK\u0005\u0003\u0000\u0000KL\u0003\u0000\u0000\u0000"+
		"LM\u0005\u0004\u0000\u0000MO\u0001\u0000\u0000\u0000NH\u0001\u0000\u0000"+
		"\u0000NI\u0001\u0000\u0000\u0000NJ\u0001\u0000\u0000\u0000O\r\u0001\u0000"+
		"\u0000\u0000\u0007\u0011$18@FN";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}