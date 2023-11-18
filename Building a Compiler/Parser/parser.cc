#include "parser.h"
#include <iostream>
#include "lexer.h"

using namespace std;

void parser::parse_program() {
	table1.currScopeCount = 0;
	table1.RBC = 0;
	table1.currentScope = "global";
	t1 = lexer.GetToken();
	if (t1.token_type == ID) {
		lexer.UngetToken(t1);
		parse_global_vars();
		parse_scope();
	}
	else {
		//cout << "Syn Err1";
		syntax_error();
	}

	if (lexer.GetToken().token_type != END_OF_FILE) {
		syntax_error();
	}
}

void parser::parse_global_vars() {
	t1 = lexer.GetToken();
	bool no_globals = false;
	if (t1.token_type == ID) {
		t2 = lexer.GetToken();
		if (t2.token_type == COMMA || t2.token_type == SEMICOLON) {
			lexer.UngetToken(t2);
			lexer.UngetToken(t1);
			parse_var_list();
		}
		else if (t2.token_type == LBRAC) {
			lexer.UngetToken(t2);
			lexer.UngetToken(t1);
			no_globals = true;
		}
		else {
			//cout << "Syn Err4";
			syntax_error();
		}
	}
	else {
		//cout << "Syn Err5";
		syntax_error();
	}

	t1 = lexer.GetToken();
	if (no_globals == false) {
		if (t1.token_type == SEMICOLON) {
			//cout << "Global Vars Done2";
		}
		else {
			//cout << "Syn Err6";
			syntax_error();
		}
	}
	else {
		lexer.UngetToken(t1);
	}
}

void parser::parse_var_list() {
	t1 = lexer.GetToken();
	if (t1.token_type == ID || t1.token_type == COMMA) {
		if (t1.token_type == ID) {
			if (table1.currentScope == "global") {
				table1.global_vars.push_back(t1.lexeme);
			}
			if (table1.currentScope == "public") {
				table1.scopes.back().publics.push_back(t1.lexeme);
			}
			if (table1.currentScope == "private") {
				table1.scopes.back().privates.push_back(t1.lexeme);
			}
		}
		parse_var_list();
	}
	else if (t1.token_type == SEMICOLON) {
		lexer.UngetToken(t1);
	}
	else {
		//cout << "Syn Err7";
		syntax_error();
	}
}

void parser::parse_scope() {
	t1 = lexer.GetToken(); 
	if (t1.token_type == ID) {
		t2 = lexer.GetToken();
		if (t2.token_type == LBRAC) {
			SCOPE newScope = { t1.lexeme };
			table1.currScopeCount++;
			table1.scopes.push_back(newScope);
			parse_public_vars();
			parse_private_vars();
			parse_stmt_list();
			t3 = lexer.GetToken(); //current token after parses should be just before closing brace
			if (t3.token_type == RBRAC) {
				//cout << "scope Parsed" << endl;
				table1.currScopeCount--;
			}
			else if (t3.token_type == ID) {
				if (table1.currScopeCount == 1) { // currently in main
					t4 = lexer.GetToken();
					if (t4.token_type != EQUAL) {
						table1.RBC++;//came back to previous scope
					}
					lexer.UngetToken(t4);
				}
				lexer.UngetToken(t3);
				parse_stmt_list();
				if (table1.RBC == 1) {
					table1.RBC--;
				}
				t4 = lexer.GetToken();
				while (t4.token_type == ID) {
					t5 = lexer.GetToken();
					if (t5.token_type == LBRAC) {
						table1.RBC++;
					}
					lexer.UngetToken(t5);
					lexer.UngetToken(t4);
					parse_stmt_list();
					if (table1.RBC == 1) {
						table1.RBC--;
					}
					t4 = lexer.GetToken();
				}
				table1.currScopeCount--;
			}
			else {
				//cout << "Syn Err8";
				syntax_error();
			}
		}
		else {
			//cout << "Syn Err9";
			syntax_error();
		}
	}
	else {
		//cout << "Syn Err10";
		syntax_error();
	}


}

void parser::parse_public_vars() {
	t1 = lexer.GetToken();
	if (t1.token_type == PUBLIC) {
		t2 = lexer.GetToken();
		if (t2.token_type == COLON) {
			table1.currentScope = "public";
			parse_var_list();
			//will be 1 token before ending semi
		}
		else { // if no colon then its a syntax error
			//cout << "Syn Err11";
			syntax_error();
		}
	}
	else if (t1.token_type == PRIVATE) {
		lexer.UngetToken(t1);
		//cout << "no public vars1";
	}
	else if (t1.token_type == ID) {//its a stmt list or scope
		lexer.UngetToken(t1);
		//cout << "no public vars2";
	}
	else {
		//cout << "Syn Err12";
		syntax_error();
	}

	t1 = lexer.GetToken();
	if (t1.token_type == SEMICOLON) {
		//cout << "Public Vars Done";
	}
	else {
		lexer.UngetToken(t1);
	}
}

void parser::parse_private_vars() {
	t1 = lexer.GetToken();
	if (t1.token_type == PRIVATE) {
		t2 = lexer.GetToken();
		if (t2.token_type == COLON) {
			table1.currentScope = "private";
			parse_var_list();
			//will be 1 token before ending semi
		}
		else { // if no colon then its a syntax error
			//cout << "Syn Err13";
			syntax_error();
		}
	}
	else if (t1.token_type == ID) {//its a stmt list or scope
		lexer.UngetToken(t1);
		//cout << "no private vars";
	}
	else {
		//cout << "Syn Err14";
		syntax_error();
	}

t1 = lexer.GetToken();
if (t1.token_type == SEMICOLON) {
	//cout << "Private Vars Done";
}
else {
	lexer.UngetToken(t1);
}
}

void parser::parse_stmt_list() {
	t1 = lexer.GetToken();
	if (t1.token_type == ID) {
		t2 = lexer.GetToken();
		if (t2.token_type == EQUAL) {
			t3 = lexer.GetToken();
			if (t3.token_type == ID) {
				lexer.UngetToken(t3);
				lexer.UngetToken(t2);
				lexer.UngetToken(t1);
				parse_stmt();//statement parsed, current token is before the semicolon
			}
			else {
				//cout << "Syn Err15";
				syntax_error();
			}
		}
		else if (t2.token_type == LBRAC) {
			lexer.UngetToken(t2);
			lexer.UngetToken(t1);
			parse_scope();
		}
		else {
			//cout << "Syn Err16";
			syntax_error();
		}
	}
	else if (t1.token_type == RBRAC) {
		lexer.UngetToken(t1);
	}
	else {
		//cout << "Syn Err17";
		syntax_error();
	}

	t1 = lexer.GetToken();
	if (t1.token_type == SEMICOLON) {
		parse_stmt_list();
	}
	else {
		lexer.UngetToken(t1);
	}
}

void parser::parse_stmt() {
	t1 = lexer.GetToken();
	if (t1.token_type != ID) {//a
		//cout << "Syn Err18";
		syntax_error();
	}
	t2 = lexer.GetToken();
	if (t2.token_type != EQUAL) {//=
		lexer.UngetToken(t2);
		lexer.UngetToken(t1);
		parse_scope();
	}
	t3 = lexer.GetToken();
	if (t3.token_type != ID) {//b
		//cout << "Syn Err19";
		syntax_error();
	}
	t4 = lexer.GetToken();
	if (t4.token_type != SEMICOLON) {//;
		//cout << "Syn Err20";
		syntax_error();
	}
	//everything should be correct so now find output and print it.stack is at semi
	string temp1 = find_ID(t1.lexeme);
	string temp2 = find_ID(t3.lexeme);
	string op = temp1 + " = " + temp2;
	output.push_back(op);
	lexer.UngetToken(t4);
}

string parser::find_ID(string const& id) {
	int i;
	int j;
	if (table1.RBC == 1) {
		//cout << "here ";
		//cout << table1.currScopeCount << endl;
		for (i = 0; i < table1.scopes.back().privates.size(); i++) {
			if (id == table1.scopes.back().privates[i]) {
				return table1.scopes.back().name + "." + id;
			}
		}

		for (i = table1.scopes.size() - 1; i >= 0; i--) {
			for (j = 0; j < table1.scopes[i].publics.size(); j++) {
				if (id == table1.scopes[i].publics[j]) {
					return table1.scopes[i].name + "." + id;
				}
			}
		}
		for (i = 0; i < table1.global_vars.size(); i++) {
			if (table1.global_vars[i] == id) {
				return "::" + id;
			}
		}
	}
	else {
		//cout << "or here ";
		//cout << table1.currScopeCount << endl;
		for (i = 0; i < table1.scopes[(table1.currScopeCount - 1)].privates.size(); i++) {
			if (id == table1.scopes[(table1.currScopeCount - 1)].privates[i]) {
				return table1.scopes[(table1.currScopeCount - 1)].name + "." + id;
			}
		}

		for (i = (table1.scopes.size() - (table1.scopes.size() - table1.currScopeCount)) - 1; i >= 0; i--) {
			for (j = 0; j < table1.scopes[i].publics.size(); j++) {
				if (id == table1.scopes[i].publics[j]) {
					return table1.scopes[i].name + "." + id;
				}
			}
		}
		for (i = 0; i < table1.global_vars.size(); i++) {
			if (table1.global_vars[i] == id) {
				return "::" + id;
			}
		}
	}

	return "?." + id;
}

void parser::syntax_error() {
	cout << "Syntax Error\n";
	exit(1);
}