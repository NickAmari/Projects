#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <iostream>
using namespace std;
template <typename T> class Node;

template <typename T> class LinkedList {
	
	public:
		LinkedList();
		~LinkedList();
		void AddToFront(T); 		// adds a new node to the front of the linked list
		void AddToBack(T); 		// adds a new node to the back of the linked list
		bool AddAtIndex(T, int);	// fucntion used to insert new node in order in the list
		Node<T>* RemoveFromFront();
		Node<T>* RemoveFromEnd();
		void RemoveTheFirst(T);
		void RemoveAllOf(T);
		bool ElementExists(T); 		// searches for a value in the linked list and returns the point to object that contains that value
		int IndexOf(T);
		Node<T>* RetrieveFront();
		Node<T>* RetrieveEnd();
		Node<T>* Retrieve(int);
		int Length();
		void PrintList();		// prints the contents of the linked list
		void Empty();

	private:
		Node<T> *frontPtr; 	// pointer to the first node in the list
		Node<T> *backPtr; 	// pointer to the last node in the list
		bool isEmpty();
		Node<T>* Find(T);

};



template <typename T> LinkedList<T>::LinkedList() { // constructor for list of of nodes

	frontPtr = NULL;
	backPtr = NULL;
}

template <typename T> LinkedList<T>::~LinkedList() {
	
	if ( !isEmpty() ) {    			// if the list is not already empty
		Node<T> *Ptr = frontPtr;	// get the first node
		Node<T> *tempPtr;
		while ( Ptr != 0 ) {  		// while the first node is not null
	     		tempPtr = Ptr; 		// temp = the current node
   			Ptr = Ptr->nextNodePtr; // current node = next node
	  		delete tempPtr; 	// delete current node
    		}
	}
}

template <typename T> void LinkedList<T>::Empty() {

	Node<T>* curr = frontPtr;
	Node<T>* second;

	while (curr != NULL) {
		second = curr->nextNodePtr;
		delete curr;
		curr = second;
	}

}

template <typename T> void LinkedList<T>::AddToFront(T nodeData) {

	if(isEmpty()) { 				
		Node<T> * newPtr = new Node<T>(nodeData); 
		frontPtr = newPtr; 					// if there are no nodes, this new node is front and back
		backPtr = newPtr;
	}
	else { 	
		Node<T> * newPtr = new Node<T>(nodeData);
		newPtr->nextNodePtr = frontPtr;				// if there are nodes, this node becomes the front, and points to the old front
		frontPtr = newPtr;
	}
}

template <typename T> void LinkedList<T>::AddToBack(T nodeData) {

	if(isEmpty()) {
		Node<T> * newPtr2 = new Node<T>(nodeData);
		frontPtr = newPtr2;					// same as AddToFront, if theres nothing, its both
		backPtr = newPtr2;
	}
	else {
		Node<T> * newPtr2 = new Node<T>(nodeData);
		backPtr->nextNodePtr = newPtr2;				// same as AddToBack, but modifying the back node instad of front
		backPtr = newPtr2;
	}
}

template <typename T> bool LinkedList<T>::AddAtIndex(T nodeData, int index) {

	Node<T>* prev = new Node<T>(nodeData);
  	Node<T>* curr = new Node<T>(nodeData);
  	Node<T>* newNode = new Node<T>(nodeData);
	bool success = false;
  	int count = 0;

  	curr = frontPtr;

	if(frontPtr != NULL) {
		while(curr->nextNodePtr != NULL && count != index) {
			prev = curr;
			curr = curr->nextNodePtr;
			count += 1;
		}
		if(index == 0) {
			AddToFront(nodeData);
			success = true;
		}
	       	else if(curr->nextNodePtr == NULL && index == count+1) {
			AddToBack(nodeData);
			success = true;
		}
		else if(index > count+1) {
			cout << " Position is out of bounds " << endl;
		}
	       	else {
			prev->nextNodePtr = newNode;
			newNode->nextNodePtr = curr;
			success = true;
		}
	}
	else {
		frontPtr = newNode;
		newNode->nextNodePtr = NULL;
		cout << "Added to the front; list was empty." << endl;
		success = true;
	}
	return success;	
}

template <typename T> Node<T>* LinkedList<T>::RemoveFromFront() {
	
	if (frontPtr == NULL) {
		return NULL;
	}

       	if (frontPtr->nextNodePtr == NULL) {
	       	delete (frontPtr);
		frontPtr = NULL;
		backPtr = NULL;
		return NULL;
	}

	Node<T>* tempPtr = frontPtr;
	frontPtr = frontPtr->nextNodePtr;
 	delete tempPtr;
	return frontPtr;	
}

template <typename T> Node<T>* LinkedList<T>::RemoveFromEnd() {

	if (frontPtr == NULL) {
		return NULL;		  
	}

       	if (frontPtr->nextNodePtr == NULL) {
	       	delete (frontPtr);
		frontPtr = NULL;
		backPtr = NULL;
		return NULL;
	}	    
       
	Node<T>* secondLastPtr = frontPtr;
	while (secondLastPtr->nextNodePtr->nextNodePtr != NULL) {
		secondLastPtr = secondLastPtr->nextNodePtr;
	}
	delete (secondLastPtr->nextNodePtr);

	secondLastPtr->nextNodePtr = NULL;
	backPtr = secondLastPtr;
	return backPtr;
}

template <typename T> void LinkedList<T>::RemoveTheFirst(T nodeData) {

	Node<T> *newNodePtr = frontPtr;
	Node<T> *followPtr = frontPtr;
	Node<T> *tempPtr;
	if ( !isEmpty() && (ElementExists(nodeData)) ) {	
	if (newNodePtr->data == nodeData) {
		tempPtr = newNodePtr;
		newNodePtr = newNodePtr->nextNodePtr;
		delete tempPtr;
		delete followPtr;		
	}
		

       	if (newNodePtr->data != nodeData) {
		newNodePtr = newNodePtr->nextNodePtr; // now newNodePtr is the second node, and followPtr is the first, so one behind newNodePtr       	
	}
      
	while (newNodePtr->data != nodeData) {
		 followPtr = followPtr->nextNodePtr;
		 newNodePtr = newNodePtr->nextNodePtr; // if current node data isnt corrent, move to next node, and followNode moves to current node
	}
	// When while loop exits, newNodePtr will be the node to delete, and followPtr will be the one behind it
	followPtr->nextNodePtr = (newNodePtr->nextNodePtr);
	delete (newNodePtr);
	}
}

template <typename T> void LinkedList<T>::RemoveAllOf(T nodeData) {
	
	Node<T> *tempPtr = frontPtr;
	if ( !isEmpty() && (ElementExists(nodeData))) {
	while (frontPtr->data == nodeData) { // parses through entire list of nodes until it hits a node where the data is =
		frontPtr = frontPtr->nextNodePtr; // front pointer will now point to first node without that data
	}

	while (tempPtr->nextNodePtr != NULL) { // while there is more in the list
		if (tempPtr->nextNodePtr->data == nodeData) { // if the next nodes data is = nodeData
			tempPtr->nextNodePtr = tempPtr->nextNodePtr->nextNodePtr; // move pointer to next node and recheck until ptr is at a node withoout matching data
		}
		else {
			tempPtr = tempPtr->nextNodePtr;
		}
	}
	backPtr = tempPtr;
	}
}

template <typename T> bool LinkedList<T>::ElementExists(T nodeData) {
	
	Node<T>* findNode;
	findNode = Find(nodeData);
	bool exists = false;
	if ( (findNode != NULL) ) {
		exists = true;
	}
	return exists;
}

template <typename T> Node<T>* LinkedList<T>::Find(T nodeData) {
		
	Node<T>* newPtr; 				// creating a new pointer to move through list
	bool foundNode = false;				// using boolean becasue I didnt get to in C
	newPtr = frontPtr;

	while( ( !foundNode ) && ( newPtr != NULL ) ) { // runs through list until data is found within a node or end of list is reacheh
		if( (newPtr->data == nodeData) ) {
			foundNode = true;
		}
		else {		
			newPtr = newPtr->nextNodePtr;
		}				// returns pointer to the datas node
	}
	if ((foundNode = true)) {
		return newPtr;
	}
	return frontPtr;
}

template <typename T> int LinkedList<T>::IndexOf(T nodeData) {

	Node<T>* tempNode = frontPtr;
	int count = 0;
	while ( (tempNode != NULL) ) {
		if ( (tempNode->data == nodeData) ) {
			return count;
		}
		else {
			count += 1;
			tempNode = tempNode->nextNodePtr;
		}
	}
	return -1;
}

template <typename T> Node<T>* LinkedList<T>::RetrieveFront() {

	if ( !isEmpty() ) {

		return frontPtr;
	}
	return NULL;
}

template <typename T> Node<T>* LinkedList<T>::RetrieveEnd() {
	
	if ( !isEmpty() ) {
		
		return backPtr;
	}
	return NULL;

}

template <typename T> Node<T>* LinkedList<T>::Retrieve(int index) {
	
	if ( !isEmpty() ) {
	if (index >= Length() ) {
		cout << "Index out of bounds, returning last node." << endl;
		return backPtr;
	}
	int count = 0;
	Node<T> *tempPtr = frontPtr;
	while ( count != index ) {
		tempPtr = tempPtr->nextNodePtr;
		count += 1;
	}
	//Now tempPtr is pointing to the node at index required
	return (tempPtr);
	}
	return NULL;
}

template <typename T> void LinkedList<T>::PrintList() {

	if(isEmpty()) {
		cout << "The list is empty" << endl;
	}
	else {
		Node<T> * Ptr = frontPtr;			//if the list isnt empty print the contents of the current node, and then move to the next node and repeat on new lines
		cout << "The list is: ";
		while(Ptr != NULL) {
			cout << Ptr->data << " ";
			Ptr = Ptr->nextNodePtr;
		}
	}
	cout << "\n";
}

template <typename T> bool LinkedList<T>::isEmpty() {

	if( (frontPtr == NULL) && (backPtr == NULL)) { // if front and back are NULL the list is empty
		return 1;
	}	
	else {
		return 0;
	}
}

template <typename T> int LinkedList<T>::Length() {
	
	int count = 0;
	Node<T> *currentPtr = frontPtr;
	while (currentPtr != NULL) {
		count += 1;
		currentPtr = currentPtr->nextNodePtr;
	}
	return count;
}

#endif

#ifndef NODE_H
#define NODE_H

template <typename T> class Node {
	
	friend class LinkedList<T>;

	private:
		T data; // templated data storage, this is what the node contains
		Node<T> *nextNodePtr; //pointer to next node
	public:
		Node(T); // constructor
		T getData();
};

template <typename T> Node<T>::Node(T nodeData) {

	data = nodeData;
	nextNodePtr = NULL; // pointer is set to null until a new node is added
}

template <typename T> T Node<T>::getData() //returns whatever data is stored
{
	return data;
}

#endif


