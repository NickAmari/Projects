#include <iostream>
#include "LinkedList.h"
#include "TemplateInt.h"
/*
Name : Nicholas Amari
Date : April 20 2021
Assignment : 6 - Templated Linked list without problem, 80% specifications
*/

int main() {
	//Quick Test of every function before stress testing
	LinkedList<TemplateInt> myList;
	TemplateInt myInt(5);
	TemplateInt myInt2(7);
	TemplateInt myInt3(9);
	myList.AddToFront( myInt );
	myList.AddToFront( myInt3 );
	myList.AddToBack( myInt3 );
	myList.PrintList(); // should be 9 5 9
	myList.RemoveFromFront();
	myList.RemoveFromFront();
	myList.AddAtIndex( myInt2, 0);
	myList.PrintList(); // should be 7 9
	myList.RemoveFromFront();
	myList.PrintList(); // 9
	myList.RemoveFromEnd();
	myList.PrintList(); // should be empty
	myList.AddToFront( myInt );
	myList.AddToFront( myInt3 );
	myList.AddToBack( myInt);
	myList.PrintList();// should be 9 5 5
	myList.RemoveTheFirst( myInt );
	myList.PrintList(); // should be 9 5
	myList.AddToFront( myInt);
	myList.AddToBack( myInt);
	myList.PrintList(); // should be 5 9 5 5
	myList.RemoveAllOf( myInt );
	myList.PrintList(); // should be 9
	cout << "\n";
	cout << myList.Length(); // should be 1
	cout << "\n";
	myList.AddToFront( myInt3 );
	myList.AddToFront (myInt2);
	myList.AddToBack( myInt2 ); // should be 7 9 9 7
	cout << myList.ElementExists( myInt ); // should be 0, Uses the Find function
	cout << "\n";
	cout << myList.IndexOf( myInt2 ); // should be 0
	cout << "\n";
	//Now stress testing time
	int i = 0;
	LinkedList<TemplateInt> newList;
	while (i <= 100) {
		TemplateInt newInt(i);
		TemplateInt newInt2(i+1);
		newList.AddToFront(newInt);
		newList.AddToBack(newInt2);
		i++;
	}
	newList.PrintList();
	newList.RemoveAllOf( 11 );
	newList.RemoveAllOf( 300 );
	int i2 = 5;
	for (i2 = 5; i2 < 15; i2++) {
	       newList.RemoveAllOf( i2 );
	}
	cout << "List after some removal\n" << endl;
	newList.PrintList();
	int i3 = 0;
	for (i3 = 0; i3 < 80; i3++) {
		newList.RemoveFromEnd();
	}
	newList.AddAtIndex( 3000, 20);
	cout << "List after more removal, and addition of 3000\n" << endl;
	newList.PrintList();
	int i4 = 0;
	while ( i4 <= 30 ) {
		TemplateInt thirtyNodes(30);
		newList.AddToFront( thirtyNodes );
		i4++;
	}
	cout << "addition of many same data nodes\n" << endl;
	newList.PrintList();
	cout << "removal of all of them with RemoveAllOf 30\n" << endl;
	newList.RemoveAllOf( 30 );
	newList.PrintList();
	cout << "testing elementexists T/F over specific section of list\n" << endl;
	int i5 = 0;
	while ( i5 < (newList.Length() / 2) ) {
		cout << newList.ElementExists( i5 ) << " ";
		i5++;
	}
	cout << "\n";
	//FIND AND ISEMPTY ARE PRIVATE AND USED IN MULTIPLE OTHER METHODS
	cout << "getting the front of the list " << newList.RetrieveFront()->getData() << endl;
	cout << "getting the end of the list " << newList.RetrieveEnd()->getData() << endl;
	cout << "getting the element at index 20 " << newList.Retrieve(20)->getData() << endl;
	newList.RemoveFromFront();
	newList.RemoveTheFirst( 92 );
	cout << "getting index of 20 " << newList.IndexOf( 20 ) << endl;
	// Now for fail condition checking, ex. RemovingFromFront on an empty list
	cout << "\n";
	newList.PrintList();
	cout << "\nAdding 10 at index 4000" << endl;
	newList.AddAtIndex( 10, 4000);
	LinkedList<TemplateInt> newList2;
	cout << "\nRemovingFromFront and back of an empty list" << endl;
	newList2.RemoveFromFront();
	newList2.RemoveFromEnd();
	cout << "No Errors" << endl;
	newList2.PrintList();
	cout << "RemovingTheFirst and RemovingAllOf and ElementExists on an empty list, and later a list without those elements" << endl;
	newList2.RemoveTheFirst( 10 );
	newList2.RemoveAllOf( 10 );
	newList2.ElementExists( 10 );
	cout << "No Errors for empty list" << endl;
	cout << "RetreivingFront and End with no elements in the list" << endl;
	newList2.RetrieveFront();
	newList2.RetrieveEnd();
	newList2.Retrieve( 5 );
	cout << "No Errors for retreivals of empty list, now filling the list" << endl;
	int i6 = 0;
	while (i6 <= 10) {
		TemplateInt newInt(i6);
		newList2.AddToFront(newInt);
		i6++;
	}
	newList2.PrintList();
	newList2.RemoveTheFirst ( 20 );
	newList2.RemoveAllOf ( 20 );
	cout << newList2.ElementExists( 20 );
	cout << "\n";
	cout << "No Errors for removing elements / finding elements that arent in the list" << endl;
	cout << "Finally, Retrieve Method with filled list but bad parameter" << endl;
	newList.Retrieve( 12 );
	cout << "No Error" << endl;
	return 0;
}
