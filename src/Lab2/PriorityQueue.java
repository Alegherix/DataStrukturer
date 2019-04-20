package Lab2;

import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
	private ArrayList<E> heap = new ArrayList<E>();
	private Comparator<E> comparator;
	private Map<E, Integer> elemPosMap = new HashMap<>();

	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Returns the size of the priority queue.
	public int size() {
		return heap.size();
	}

	// Adds an item to the priority queue.
	public void add(E x)
	{
//		System.out.println("Inserting elem: " + x + "\n");

		// Lägger till elementet i slutet av array Listan
		heap.add(x);

		// Sifta de uppåt tills dess korrekta plats.
		siftUp(heap.size()- 1);
	}

	// Returns the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	public E minimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		return heap.get(0);
	}

	// Removes the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	public void deleteMinimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		//Sätter sista elementet till Högst upp
		heap.set(0, heap.get(heap.size()-1));

		//Tar bort det sista elementet i listan -> Som är det som var höst upp i Priority queuen
		heap.remove(heap.size()-1);

		// Om det finns element kvar, -> Sifta elementet vid index 0, Nedåt tills den hamnar i korrekt plats.
		if (heap.size() > 0) siftDown(0);
	}


	public void updateQueue(E oldE, E newE){
		if(!heap.contains(oldE)) throw new NoSuchElementException();

		// Hitta de gamla elementets index
		int index = heap.indexOf(oldE);

		if(comparator.compare(oldE, newE) > 0){
			System.out.println("We should sift up");
			heap.set(index, newE);
			siftUp(index);
		}
		else if(comparator.compare(oldE, newE) < 0){
			System.out.println("We should sift down");
			heap.set(index, newE);
			siftDown(index);
		}


		// Hitta Det gamla elementets index i heapen
		// Jämför det nya med det som finns för att veta om vi ska sifta upp eller ned
		// Ersätt det gamla elementet med det nya.
		// Börja göra eventuell Sifting

	}


	// Sifts a node up.
	// siftUp(index) fixes the invariant if the element at 'index' may
	// be less than its parent, but all other elements are correct.
	private void siftUp(int index) {

		// TODO: 2019-04-19 Behöver tänka ut vart vi ska placera denna Om här, eller i while loopen
		// TODO: 2019-04-19 Behöver tänka ut smartare sätt att uppdatera Index över nya placeringen för Elementet vi vill ska gå uppåt

		// Skapa referens till värdet vi möjligtvis vill sifta uppåt
		E insertedValue = heap.get(index);

		// Medan vi har en förälder, samt OM förälderns värde är större än värdet vi insertar
		while (parent(index)>=0){
			E parentVal = heap.get(parent(index));

			// Om objektet har mindre värde, skicka det uppåt via att swappa dessa värden
			if(comparator.compare(parentVal, insertedValue) > 0){
//				System.out.print("The current binary heap representation is: ");
				heap.forEach(s -> System.out.print(s + " "));
				System.out.println();
//				System.out.println("Switching the inserted Element: " + insertedValue);
				int parentIndex = parent(index);

				// Swappa nu ut objekten
				heap.set(parentIndex, insertedValue);
				heap.set(index, parentVal);

				// Byt index, så att vi kan switcha med rätt.
				index = parentIndex;
//				System.out.println("Current index of newVal is: " + index);
			}
			else{
				break;
			}
//			System.out.print("The final binary heap representation is: ");
//			heap.forEach(s -> System.out.print(s + " "));
//			System.out.println(" ");
		}
//		System.out.println("-----------------------");
	}
     
	// Sifts a node down.
	// siftDown(index) fixes the invariant if the element at 'index' may
	// be greater than its children, but all other elements are correct.
	private void siftDown(int index) {

		// Hämta objektet via indexet ifrån heapen
		E value = heap.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < heap.size()) {

			// Hämta index för de olika barnen
			int left    = leftChild(index);
			int right   = rightChild(index);


			// Work out whether the left or right child is smaller.
			// Start out by assuming the left child is smaller...

			// Assigna ett index värde, börja med vänster
			int childIndex = left;

			// Hämta barnvärdet, via vänster först
			E childValue = heap.get(left);

			// Om det finns ett höger värde, Assigna även detta objektet
			// Jämför de båda objektens värden
			// Assigna sedan om childIndex, och childValue till det som faktiskt är minst
			if (right < heap.size()) {
				E rightValue = heap.get(right);
				if (comparator.compare(childValue, rightValue) > 0) {
					childIndex = right;
					childValue = rightValue;
				}
			}

			// Jämför det ursprungliga objektets värde med barn värdet
			// Om barnets värde är mindre än objektets värde, fortsätt skicka objektet nedåt.
			if (comparator.compare(value, childValue) > 0) {
				heap.set(index, childValue);
				index = childIndex;
			} else break;
		}

		heap.set(index, value);
	}

	// Helper functions for calculating the children and parent of an index.
	private final int leftChild(int parentIndex) {
		return 2*parentIndex+1;
	}

	private final int rightChild(int parentIndex) {
		return 2*parentIndex+2;
	}

	private final int parent(int childIndex) {
		return (childIndex-1)/2;
	}
}
