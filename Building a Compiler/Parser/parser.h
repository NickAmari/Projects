#ifndef __PARSER__H__
#define __PARSER__H__

#include <vector>
#include <string>

#include "lexer.h"

struct SCOPE {
    std::string name;
    std::vector<std::string> privates;
    std::vector<std::string> publics;
};

struct SymTable {
    int currScopeCount;
    int RBC;
    int LBC;
    std::string currentScope;
    std::vector<SCOPE> scopes;
    std::vector<std::string> global_vars;
};



class parser {
public:
    void parse_program();
    void parse_global_vars();
    void parse_var_list();
    void parse_scope();
    void parse_public_vars();
    void parse_private_vars();
    void parse_stmt_list();
    void parse_stmt();
    std::string find_ID(std::string const& id);
    void syntax_error();
    std::vector<std::string> output;

private:
    LexicalAnalyzer lexer;
    SymTable table1;
    Token t1;
    Token t2;
    Token t3;
    Token t4;
    Token t5;

};

#endif

