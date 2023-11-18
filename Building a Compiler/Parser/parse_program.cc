// This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <istream>
#include <vector>
#include <string>
#include <cctype>

#include "lexer.h"
#include "parser.h"

int main()
{
	LexicalAnalyzer lexer;
	Token token;
	parser test;

	test.parse_program();
	for (int i = 0; i < test.output.size(); i++) {
		std::cout << test.output[i] << std::endl;
	}
}
