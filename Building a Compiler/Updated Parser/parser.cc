
#include "parser.h"
#include "compiler.h"

#include <cstdlib>
#include <map>
#include <iostream>

using namespace std;

map<string,int> loc;

void parser::syntax_error() {
	cout << "syntax error\n";
	exit(1);
}

Token parser::expect(TokenType expected_type) {
    
	Token tmp1 = lexer.GetToken();

    if (tmp1.token_type != expected_type) {
        syntax_error();
	}
	
    return tmp1;
}

struct InstructionNode * parser::parse_program() {

	parse_var_section();
  
	struct InstructionNode* body = parse_body();
  
	parse_inputs();

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == END_OF_FILE){
		expect(END_OF_FILE);
	} else syntax_error();

	return body;

}

void parser::parse_var_section() {

	parse_id_list();


	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == SEMICOLON) {
		expect(SEMICOLON);
	} 
	else {
		syntax_error();
	}
}

void parser::parse_id_list() {

	Token tmp1 = lexer.peek(1);
	
	if(tmp1.token_type == ID){
		Token tmp2 = expect(ID);
		mem[next_available] = 0;
		loc.insert(pair<string, int> (tmp2.lexeme, next_available));
		next_available++;
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);

	if(tmp1.token_type == COMMA){
		expect(COMMA);
		parse_id_list();
	}

}

struct InstructionNode * parser::parse_body(){//Done

	struct InstructionNode * inst1;

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == LBRACE){
		expect(LBRACE);
	} 
	else {
		syntax_error();
	}
	
	inst1 = parse_stmt_list();


	tmp1 = lexer.peek(1);
	
	if(tmp1.token_type == RBRACE){
		expect(RBRACE);
	} 
	else {
		syntax_error();
	}
	
  return inst1;

}

struct InstructionNode * parser::parse_stmt_list() {

	struct InstructionNode * inst;
	struct InstructionNode * inst1;

	inst = parse_stmt();

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == ID || tmp1.token_type == WHILE || tmp1.token_type == IF || tmp1.token_type == SWITCH || tmp1.token_type == FOR || tmp1.token_type == INPUT || tmp1.token_type == OUTPUT) {
    inst1 = parse_stmt_list();

    struct InstructionNode * temp = inst;
    if(temp->next != NULL){
		while(temp->next != NULL){
			temp = temp->next;
		}
	}

    temp->next = inst1;
	
	}

	return inst;

}

struct InstructionNode * parser::parse_stmt() {

	struct InstructionNode * stmt = new InstructionNode;

	Token tmp1 = lexer.peek(1);

	if(tmp1.token_type == ID) {
		stmt = parse_assign_stmt();
	}
	else if(tmp1.token_type == WHILE) {
		stmt = parse_while_stmt();
	}
	else if(tmp1.token_type == IF) {
		stmt = parse_if_stmt();
	}
	else if(tmp1.token_type == SWITCH) {
		stmt = parse_switch_stmt();
	}
	else if(tmp1.token_type == FOR) {
		stmt = parse_for_stmt();
	}
	else if(tmp1.token_type == OUTPUT) {
		stmt = parse_output_stmt();
	}
	else if(tmp1.token_type == INPUT) {
		stmt = parse_input_stmt();
	}
	else {
		syntax_error();
	}

	return stmt;

}

struct InstructionNode * parser::parse_assign_stmt() {

	struct InstructionNode* stmt = new InstructionNode;
	stmt->type = ASSIGN;

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	stmt->next = no;

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == ID){
		Token p = expect(ID);
		stmt->assign_inst.left_hand_side_index = loc.find(p.lexeme)->second;
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == EQUAL){
		expect(EQUAL);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(2);
	
	if(tmp1.token_type == SEMICOLON){
		Token tmp2 = lexer.peek(1);
		parse_primary();
		stmt->assign_inst.op = OPERATOR_NONE;
		stmt->assign_inst.operand1_index = loc.find(tmp2.lexeme)->second;
	}
	else {
		struct InstructionNode * i = parse_expr();
		stmt->assign_inst.op = i->assign_inst.op;
		stmt->assign_inst.operand1_index = i->assign_inst.operand1_index;
		stmt->assign_inst.operand2_index = i->assign_inst.operand2_index;
	}

  //expect SEMICOLON Token
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == SEMICOLON){
		expect(SEMICOLON);
	} 
	else {
		syntax_error();
	}
	
  return stmt;

}

struct InstructionNode * parser::parse_expr() {

	struct InstructionNode * inst = new InstructionNode;
	inst->type = NOOP;
	inst->next = NULL;

	Token tmp1 = lexer.peek(1);
	parse_primary();
	inst->assign_inst.operand1_index = loc.find(tmp1.lexeme)->second;
	inst->assign_inst.op = parse_op();
	
	tmp1 = lexer.peek(1);
	parse_primary();
	inst->assign_inst.operand2_index = loc.find(tmp1.lexeme)->second;

	return inst;

}

void parser::parse_primary() {


	Token tmp1 = lexer.peek(1);
	if (tmp1.token_type == ID){
		expect(ID);
	}
	else if (tmp1.token_type == NUM){
		Token tmp2 = expect(NUM);
		mem[next_available] = stoi(tmp2.lexeme);
		loc.insert(pair<string, int> (tmp2.lexeme, next_available));
		next_available++;
	}
	else {
		syntax_error();
	}
	
}

ArithmeticOperatorType parser::parse_op(){//Done

  ArithmeticOperatorType op = OPERATOR_NONE;


	Token tmp1 = lexer.peek(1);
	if (tmp1.token_type == PLUS){
		expect(PLUS);
		op = OPERATOR_PLUS;
	}
	else if (tmp1.token_type == MINUS){
		expect(MINUS);
		op = OPERATOR_MINUS;
	}
	else if (tmp1.token_type == MULT){
		expect(MULT);
		op = OPERATOR_MULT;
	}
	else if (tmp1.token_type == DIV){
		expect(DIV);
		op = OPERATOR_DIV;
	}
	else {
		syntax_error();
	}
	
	return op;

}

struct InstructionNode * parser::parse_output_stmt() {

	struct InstructionNode * op = new InstructionNode;
	op->type = OUT;

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	op->next = no;

 
	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == OUTPUT) {
		expect(OUTPUT);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == ID) {
		op->output_inst.var_index = loc.find(expect(ID).lexeme)->second;
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == SEMICOLON){
		expect(SEMICOLON);
	} 
	else {
		syntax_error();
	}
	
	return op;

}

struct InstructionNode * parser::parse_input_stmt(){//Done

	struct InstructionNode * ni = new InstructionNode;
	ni->type = IN;

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	ni->next = no;

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == INPUT){
		expect(INPUT);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == ID){
		ni->input_inst.var_index = loc.find(expect(ID).lexeme)->second;
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == SEMICOLON){
		expect(SEMICOLON);
	} 
	else {
		syntax_error();
	}
	
	return ni;

}

struct InstructionNode * parser::parse_while_stmt() {

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == WHILE){
		expect(WHILE);
	}
	else {
		syntax_error();
	}
	
	struct InstructionNode * inst = parse_condition();
	inst->next = parse_body();
	struct InstructionNode * jmp = new InstructionNode;
	jmp->type = JMP;
	struct InstructionNode * temp = inst->next;
	
	if(temp->next != NULL){
		while(temp->next != NULL){
		temp = temp->next;
		}
	}

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	inst->cjmp_inst.target = no;
	jmp->next = no;
	jmp->jmp_inst.target = inst;

	temp->next = jmp;

	return inst;

}

struct InstructionNode * parser::parse_if_stmt() {

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == IF) {
		expect(IF);
	}
	else {
		syntax_error();
	}
	
	struct InstructionNode * inst = parse_condition();
	inst->next = parse_body();
	struct InstructionNode * temp = inst->next;
	if(temp->next != NULL) {
		while(temp->next != NULL) {
		temp = temp->next;
		}
	}

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	temp->next = no;

	inst->cjmp_inst.target = no;

	return inst;

}

struct InstructionNode * parser::parse_condition() {

	struct InstructionNode * cond = new InstructionNode;
	cond->type = CJMP;
	cond->cjmp_inst.target = NULL;
	
	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	cond->next = no;

	Token tmp1 = lexer.peek(1);
	parse_primary();
	cond->cjmp_inst.operand1_index = loc.find(tmp1.lexeme)->second;
	cond->cjmp_inst.condition_op = parse_relop();
	
	tmp1 = lexer.peek(1);
	parse_primary();
	cond->cjmp_inst.operand2_index = loc.find(tmp1.lexeme)->second;

	return cond;
	
}

ConditionalOperatorType parser::parse_relop() {

	ConditionalOperatorType co;

	Token tmp1 = lexer.peek(1);
	if (tmp1.token_type == GREATER){
		expect(GREATER);
		co = CONDITION_GREATER;
	}
	else if (tmp1.token_type == LESS){
		expect(LESS);
		co = CONDITION_LESS;
	}
	else if (tmp1.token_type == NOTEQUAL){
		expect(NOTEQUAL);
		co = CONDITION_NOTEQUAL;
	}

	return co;

}

struct InstructionNode * parser::parse_switch_stmt() {

	int comp;

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == SWITCH){
		expect(SWITCH);
	}
	else {
		syntax_error();
	}

	tmp1 = lexer.peek(1);
	if(tmp1.token_type == ID){
		Token tmp2 = expect(ID);
		comp = loc.find(tmp2.lexeme)->second;
	} 
	else {
		syntax_error();
	}

	tmp1 = lexer.peek(1);
	if(tmp1.token_type == LBRACE){
		expect(LBRACE);
	} 
	else {
		syntax_error();
	}
	
	struct InstructionNode * caseList = parse_case_list(comp);
	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	tmp1 = lexer.peek(1);
	if(tmp1.token_type == RBRACE) {
		expect(RBRACE);
	}
	else {
		//same as before 
		struct InstructionNode * def = parse_default_case();

		struct InstructionNode * temp = caseList;
		if(temp->next->next != NULL) {
			while(temp->next != NULL) {
				temp = temp->next;
			}
		}

		temp->type = JMP;
		temp->next = no;
		temp->jmp_inst.target = def->jmp_inst.target;
	
		tmp1 = lexer.peek(1);
		if(tmp1.token_type == RBRACE) {
			expect(RBRACE);
		}
		else {
			syntax_error();
		}
		
	}

	return caseList;

}

struct InstructionNode * parser::parse_for_stmt() {

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == FOR){
		expect(FOR);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == LPAREN){
		expect(LPAREN);
	} 
	else {
		syntax_error();
	}
	
	struct InstructionNode * a = parse_assign_stmt();
	struct InstructionNode * cond = parse_condition();

	a->next = cond;

	tmp1 = lexer.peek(1);
	if(tmp1.token_type == SEMICOLON){
		expect(SEMICOLON);
	}
	else {
		syntax_error();
	}
	
	struct InstructionNode * b = parse_assign_stmt();

	tmp1 = lexer.peek(1);
	if(tmp1.token_type == RPAREN){
		expect(RPAREN);
	} 
	else {
		syntax_error();
	}
	
	cond->next = parse_body();

	struct InstructionNode * temp = cond->next;
	if(temp->next != NULL){
		while(temp->next != NULL){
			temp = temp->next;
		}
	}

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	struct InstructionNode * jmp = new InstructionNode;
	jmp->type = JMP;
	jmp->next = no;
	jmp->jmp_inst.target = cond;

	temp->next = b;
	b->next = jmp;
	cond->cjmp_inst.target = no;

	return a;

}

struct InstructionNode * parser::parse_case_list(int x) {

	struct InstructionNode * inst;

	inst = parse_case(x);

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == CASE) {

		struct InstructionNode * temp = inst;
		if(temp->next != NULL) {
			while(temp->next != NULL) {
				temp = temp->next;
			}
		}

		temp->next = parse_case_list(x);
		inst->next = temp->next;
	}

	return inst;

}

struct InstructionNode * parser::parse_case(int x) {

	struct InstructionNode * inst = new InstructionNode;

	inst->type = CJMP;
	inst->cjmp_inst.operand1_index = x;
	inst->cjmp_inst.condition_op = CONDITION_NOTEQUAL;

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == CASE) {
		expect(CASE);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == NUM) {
		
		Token tmp2 = expect(NUM);
		mem[next_available] = stoi(tmp2.lexeme);
		loc.insert(pair<string, int> (tmp2.lexeme, next_available));
		next_available++;

		inst->cjmp_inst.operand2_index = loc.find(tmp2.lexeme)->second;

	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == COLON){
		expect(COLON);
	} 
	else {
		syntax_error();
	}
	
	struct InstructionNode * temp = parse_body();
	inst->cjmp_inst.target = temp;

	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;

	if(temp->next != NULL){
		while(temp->next != NULL){
			temp = temp->next;
		}
	}

	temp->next = no;
	inst->next = no;

	return inst;

}

struct InstructionNode * parser::parse_default_case() {

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == DEFAULT){
		expect(DEFAULT);
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == COLON){
		expect(COLON);
	} 
	else {
		syntax_error();
	}
	
	struct InstructionNode * def = parse_body();
	struct InstructionNode * no = new InstructionNode;
	no->type = NOOP;
	no->next = NULL;
	struct InstructionNode * jmp = new InstructionNode;
	jmp->type = JMP;
	jmp->next = no;

	struct InstructionNode * temp = def;
	if(temp->next != NULL){
		while(temp->next != NULL){
		temp = temp->next;
		}
	}

	temp->next = no;

	jmp->jmp_inst.target = def;

	return jmp;

}

void parser::parse_inputs() {

  parse_num_list();

}

void parser::parse_num_list() {

	Token tmp1 = lexer.peek(1);
	if(tmp1.token_type == NUM){
		inputs.push_back(stoi(expect(NUM).lexeme));
	} 
	else {
		syntax_error();
	}
	
	tmp1 = lexer.peek(1);
	if(tmp1.token_type == NUM){
		parse_num_list();
	}

}


struct InstructionNode * parse_generate_intermediate_representation() {
	parser parser;
	InstructionNode* inst  = parser.parse_program();
	return inst;
}
