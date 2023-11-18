#ifndef TEMPLATEINT_H
#define TEMPLATEINT_H

class TemplateInt {
	
	private:
		int storedInteger;
	public:
		TemplateInt();
		TemplateInt(int);
		int getInt();
		void setInt(int);
		friend ostream& operator<<(ostream& os, const TemplateInt& dt);
	 	bool operator==(const TemplateInt& dt);
		bool operator!=(const TemplateInt& dt);
};


TemplateInt::TemplateInt() {

	this->storedInteger = 0;
}

TemplateInt::TemplateInt( int x ) {

	this->storedInteger = x;
}

int TemplateInt::getInt() {

	return this->storedInteger;
}

void TemplateInt::setInt( int x ) {

	this->storedInteger = x;
}

ostream& operator<<(ostream& os, const TemplateInt& dt) {

	os << dt.storedInteger;
	return os;
}

bool TemplateInt::operator==(const TemplateInt& dt) {
		
	if ( (this->storedInteger == dt.storedInteger) ) {
		return true;
	}
	return false;
}

bool TemplateInt::operator!=(const TemplateInt& dt) {

	return !(this->storedInteger == dt.storedInteger);
}
#endif
