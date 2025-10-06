package parserANTLR;// Generated from /home/jperezr/Documents/LOGIN/Compilation et Interpretation/TP - Interpreter/src/Pcf.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PcfParser}.
 */
public interface PcfListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code App}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterApp(PcfParser.AppContext ctx);
	/**
	 * Exit a parse tree produced by the {@code App}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitApp(PcfParser.AppContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterFunction(PcfParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitFunction(PcfParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PcfParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PcfParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Fix}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterFix(PcfParser.FixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Fix}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitFix(PcfParser.FixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterConstant(PcfParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitConstant(PcfParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperation(PcfParser.BinaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperation(PcfParser.BinaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Let}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterLet(PcfParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Let}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitLet(PcfParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterIfZero(PcfParser.IfZeroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitIfZero(PcfParser.IfZeroContext ctx);
}