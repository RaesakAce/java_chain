//Marco Casaglia 5759711
package chain;

public class Node<T> {
	private T element;
	private int key;
	private Node<T> next;
	private Node<T> previous;
		
	//Inizializzazione del nodo vuoto
	
	public Node(){
		element=null;
		next=null;
		previous=null;
	}
	
	//Inizializzazione di nodo con contenuto e chiave
	
	public Node(T e,int k, Node<T> n, Node<T> p){
		element=e;
		key=k;
		next=n;
		previous=p;
	}
	
	//Restituisce l'elemento contenuto nel nodo
	
	public T getElement(){
		return element;
	}

	//Restituisce la chiave del nodo
	
	public int getKey(){
		return key;
	}
	
	//Restituisce il nodo seguente nella catena
	
	public Node<T> getNext(){
		return next;
	}
	
	//Restituisce il nodo precedente nella catena
	
	public Node<T> getPrevious(){
		return previous;
	}

	//Assegna il valore "e" all'elemento del nodo
	
	public void setElement(T e){
		element=e;
	}
	
	//Assegna valore "k" alla chiave del nodo
	
	public void setKey(int k){
		key=k;
	}
	
	//Assegna il valore del prossimo nodo nella catena
	
	public void setNext(Node<T> n){
		next=n;
	}
	
	//Assegna il valore del nodo precedente nella catena
	
	public void setPrevious(Node<T> p){
		previous=p;
	}

}
