#ifndef __PARSER__H__
#define __PARSER__H__

#include "lexer.h"
#include "compiler.h"


class parser {
public:
	
	
    struct InstructionNode * parse_program();
    void parse_var_section();
    void parse_id_list();
    struct InstructionNode * parse_body();
    struct InstructionNode * parse_stmt_list();
    struct InstructionNode * parse_assign_stmt();
    void parse_primary();
	struct InstructionNode * parse_expr();
	struct InstructionNode * parse_stmt();
    ArithmeticOperatorType parse_op();
    struct InstructionNode * parse_output_stmt();
    struct InstructionNode * parse_input_stmt();
    struct InstructionNode * parse_while_stmt();
    struct InstructionNode * parse_if_stmt();
    struct InstructionNode * parse_condition();
    ConditionalOperatorType parse_relop();
    struct InstructionNode * parse_switch_stmt();
    struct InstructionNode * parse_for_stmt();
    struct InstructionNode * parse_case_list(int c);
    struct InstructionNode * parse_case(int c);
    struct InstructionNode * parse_default_case();
	void parse_inputs();
	void parse_num_list();
	struct InstructionNode * parse_generate_intermediate_representation();
	
private:
    LexicalAnalyzer lexer;
	Token expect(TokenType expected_type);
	void syntax_error();
};

#endif