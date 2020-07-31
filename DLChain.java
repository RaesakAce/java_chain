//Marco Casaglia 5759711
package chain;

public class DLChain<T> {
	
	private  Node<T> head;
	
	//Inizializza la catena vuota
	
	public DLChain(){
		emptify();
	}
	
	//Svuota la catena
	
	public void emptify(){		
		head=new Node<T>();
	}
	
	//Controlla se la catena è vuota, in caso lo sia ritorna il valore true
	
	public boolean isEmpty(){
		return head.getElement()==null;
	}

	//Restituisce il primo nodo (head)
	
	public Node<T> getFirst(){
		if (isEmpty()){
			System.out.println("The chain is empty");
			return null;
		}
		else
			return head;
	}
	
	//Restituisce l'ultimo nodo della catena
	
	public Node<T> getLast(){
		if (isEmpty()){
                        System.out.println("The chain is empty");
			return null;			
		}
		else
			return head.getPrevious();
	}
	
	//Inserisce il primo nodo della catena quando essa è vuota
	
	public void addFirst(T e,int k){
		head.setElement(e);
		head.setKey(k);
		head.setNext(head);
		head.setPrevious(head);
	}
	
	//Inserisce un nodo nella catena subito dopo a head, head in caso sia vuota
	
	public void addNode(T e, int k) {
		if (isEmpty()){
			addFirst(e,k);
			return ;			
		}
		int i=0;
		Node<T> n=new Node<T>();
		n.setElement(e);
		n.setKey(k);
		n.setNext(head.getNext());
	    n.getNext().setPrevious(n);	
		n.setPrevious(head);
		head.setNext(n);
		Node<T> t = n;
		while(t!=head && i==0) {
			t = t.getNext();
			if(t.getKey()==n.getKey()) {
				if(t.getElement().equals(n.getElement())) {
					System.out.println("Node already in chain, operation failed");
					delete(n);
					i++;
				}
			}
		}
		
	}
	
	//Rimuove il nodo di chiave k dalla catena, più vicino a head
	
	public void removeNode(int k) {
		if (isEmpty()) {
			System.out.println("The chain is empty");
			return;
		}
		if (findKeyDistance(k)==0) {
			delete(head);
		}
		else if(findKeyDistance(k)!=-1) {
			int i=0;
			Node<T> temp=head;
			while(i<findKeyDistance(k)) {
				temp=temp.getNext();
				i++;
			}
			if(temp.getKey()==k) {
				delete(temp);
			}
			i=0;
			temp=head;
			while(i<findKeyDistance(k)) {
				temp=temp.getPrevious();
				i++;
			}
			if(temp.getKey()==k) {
				delete(temp);
			}
		}
	}
		
	//Metodo di servizio che rimuove il nodo selezionato
	
	public void delete(Node<T> n) {
			if(n==head && countNodes()>1) {
				head=head.getNext();
			}
			else if(n==head) {
				emptify();
			}
		n.getPrevious().setNext(n.getNext());
		n.getNext().setPrevious(n.getPrevious());
		n.setElement(null);
	}
	
	//Trova la distanza del nodo più vicino a head
	
	public int findKeyDistance(int k){
		int i=1;
		if(head.getKey()==k){	
			return 0;
		}
		int distance=1;
		Node<T> temp=head.getNext();
		while(temp.getKey()!=k && temp!=head) {
			distance++;
			temp=temp.getNext();
		}
		if (temp==head && temp.getKey()!=k) {
			System.out.println("Chiave non trovata");
			return -1;
		}
		else{
			temp=head.getPrevious();
			while(i<distance && temp.getKey()!=k) {
				temp=temp.getPrevious();
				i++;
			}
			return i;
		}
	}

	//Restituisce la stringa [e,k] del nodo di chiave k più vicino a head
	
	public String search (int k){
		if (isEmpty()) {
			System.out.println("The chain is empty");
			return "";
		}
		if(findKeyDistance(k)!=-1) {
			String t = "";
			int j=0;
			int i=0;
			Node<T> temp=head;
			while(i!=findKeyDistance(k)) {
				temp=temp.getNext();
				i++;
			}
			if(temp.getKey()==k) {
				t=t+stringify(temp);
				j=1;
			}
			if (i==0){
				return t;
			}
			i=0;
			temp=head;
			while(i!=findKeyDistance(k)) {
				temp=temp.getPrevious();
				i++;
			}
			if(temp.getKey()==k) {
				if (j==1){
					t=t+",";
				}
				t=t+stringify(temp);
			}
			return t;
		}
		return "";
	}

	//Restituisce l'array utilizzato per ordinare i nodi col quicksort
	
	public int[][] chainKeyArray(){
		Node<T> temp = head;
		int i=0;
		int[][] a = new int[2][countNodes()];
		while (i<countNodes()){
			a[0][i]=i;
			a[1][i]=temp.getKey();
			temp=temp.getNext();
			i++;
		}
		return a;
	}

	//Restituisce la stringa [e,k] del nodo passato per argomento
	
	public String stringify (Node<T> n){
		String t="["+n.getElement().toString()+","+ String.valueOf(n.getKey())+"]";
		return t;
	}
	
	//Restituiscce il numero di nodi presenti nella catena
	
	public int countNodes() {
		int i=0;
		if(isEmpty()) {
			return i;
		}
		Node<T> temp=head.getNext();
		i=1;
		while(temp!=head) {
			temp=temp.getNext();
			i++;
		}
		return i;
	}

	//Restituisce il nodo in posizione i
	
	public Node<T> selectNode(int i){
		int j=1;
		if (i>countNodes() || i==0) {
			System.out.println("Il numero selezionato non é valido.");
			return null;
		}
		Node<T> temp=head;
		while(j<i) {
			temp=temp.getNext();
			j++;
		}
		return temp;
	}
	
	//Restituisce la stringa S=[e1,k1],[e2,k2],...,[eN,kN] con N numero di nodi
	
	public String chainToString(){
		if (isEmpty()) {
			System.out.println("The chain is empty");
			return "";
		}
		String t=stringify(head);
		Node<T> temp=head.getNext();
		while(temp!=head){
			t=t+","+stringify(temp);
			temp=temp.getNext();
		}
		return t;
	}

	//Restituisce la stringa S', ovvero S con le chiavi in ordine crescente
	
	public String keyOrderString(){
		if (isEmpty()) {
			System.out.println("The chain is empty");
			return "";
		}
		String t = "";
		int[][] arr = chainKeyArray();
		quickSort(arr, 0 , countNodes()-1);
		int i=0;
		int j=0;
		while (j<countNodes()-1) {
			j=0;
			for (i=1;i<countNodes();i++) {
				j++;
				if(arr[1][i]==arr[1][i-1]) {
					if(arr[0][i]<arr[0][i-1]) {
						j=0;
						int temp = arr[0][i];
						arr[0][i] = arr[0][i-1];
						arr[0][i-1] = temp;
					}
				}
			} 	
		}
		i=0;
		while(i<countNodes()-1){
			j=0;
			Node<T> temp = head;
			while(j<arr[0][i]){
			j++;
			temp=temp.getNext();
			}
			t=t+stringify(temp)+",";
			i++;
		}
		j=0;
		Node<T> temp = head;
		while(j<arr[0][countNodes()-1]){
			j++;
			temp=temp.getNext();
		}
		t=t+stringify(temp);
		return t;
	}

	//Algoritmo di ordinamento utilizzato per ottenere le chiavi in ordine
	
	public static void quickSort(int[][] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		int middle = low + (high - low) / 2;
		int pivot = arr[1][middle];

		int i = low, j = high;
		while (i <= j) {
			while (arr[1][i] < pivot) {
				i++;
			}
 
			while (arr[1][j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = arr[1][i];
				int t = arr[0][i];
				arr[1][i] = arr[1][j];
				arr[0][i] = arr[0][j];
				arr[1][j] = temp;
				arr[0][j] = t;
				i++;
				j--;
			}
		}
 
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
}
