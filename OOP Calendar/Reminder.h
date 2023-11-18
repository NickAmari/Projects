#ifndef REMINDER_H
#define REMINDER_H 
#include <iostream>
#include <string>
#include <vector>
using namespace std;
		
class Reminder {

	private:
		string description;
	public:
		Reminder();
		Reminder(string desc);
		void setDesc(string desc);
		string getDesc();
		virtual string toString();
};

#endif
