// Generated from /home/jperezr/Documents/LOGIN/Compilation et Interpretation/TP - Interpreter/src/Pcf.g4 by ANTLR 4.13.2
package parserANTLR;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PcfParser}.
 */
public interface PcfListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(PcfParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(PcfParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Let}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void enterLet(PcfParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Let}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void exitLet(PcfParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Fix}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void enterFix(PcfParser.FixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Fix}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void exitFix(PcfParser.FixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void enterIfZero(PcfParser.IfZeroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link PcfParser#letTerm}.
	 * @param ctx the parse tree
	 */
	void exitIfZero(PcfParser.IfZeroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link PcfParser#funTerm}.
	 * @param ctx the parse tree
	 */
	void enterFunction(PcfParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link PcfParser#funTerm}.
	 * @param ctx the parse tree
	 */
	void exitFunction(PcfParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinTerm}
	 * labeled alternative in {@link PcfParser#funTerm}.
	 * @param ctx the parse tree
	 */
	void enterBinTerm(PcfParser.BinTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinTerm}
	 * labeled alternative in {@link PcfParser#funTerm}.
	 * @param ctx the parse tree
	 */
	void exitBinTerm(PcfParser.BinTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link PcfParser#addTerm}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(PcfParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link PcfParser#addTerm}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(PcfParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link PcfParser#mulTerm}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(PcfParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link PcfParser#mulTerm}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(PcfParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Application}
	 * labeled alternative in {@link PcfParser#appTerm}.
	 * @param ctx the parse tree
	 */
	void enterApplication(PcfParser.ApplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Application}
	 * labeled alternative in {@link PcfParser#appTerm}.
	 * @param ctx the parse tree
	 */
	void exitApplication(PcfParser.ApplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PcfParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PcfParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterConstant(PcfParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitConstant(PcfParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(PcfParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link PcfParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(PcfParser.ParenthesesContext ctx);
}