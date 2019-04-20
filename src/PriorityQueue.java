import java.util.*;

// A priority queue.
public class PriorityQueue<E> implements Iterator<E>{
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
		// Adds the element at the last of the list
		heap.add(x);

		// Put the element into the hashMap with it's Position aswell
		elemPosMap.put(x, heap.size()-1);

		// Sift new Element upwards
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

		// Remove first elem from map, now that we know we can remove it safely.
		elemPosMap.remove(minimum());

		//put the last elem at the top so that we can safely remove the smallest element
		heap.set(0, heap.get(heap.size()-1));

		// Updates the now top placed elem to it's proper position in the HashMap aswell
		elemPosMap.replace(minimum(), 0);

		//Removes the lowest element in Priority queue, which now resides at last position
		heap.remove(heap.size()-1);

		// If elements still remaining, send the element at the top of the queue for possible sifting downwards.
		if (heap.size() > 0) siftDown(0);

	}


	/**
	 * Used for updating the queue to
	 * @param oldE - The old element that should be updated
	 * @param newE - The new element that should be used instead
	 */
	public void updateQueue(E oldE, E newE){
		if(!elemPosMap.containsKey(oldE)) throw new NoSuchElementException();

		// Find the index of old element
		int index = elemPosMap.get(oldE);

		// Replace the old key with the new key - Keep the same Value
		elemPosMap.put(newE, index);

		// Update the heap to use the new element at the same index spot aswell
		heap.set(index, newE);

		// Compare old elem to new, to check wheter we should try to sift up or down
		if(comparator.compare(oldE, newE) > 0){
			siftUp(index);
		}
		else if(comparator.compare(oldE, newE) < 0){
			siftDown(index);
		}
	}


	// Sifts a node up.
	// siftUp(index) fixes the invariant if the element at 'index' may
	// be less than its parent, but all other elements are correct.
	private void siftUp(int index) {

		// Skapa referens till värdet vi möjligtvis vill sifta uppåt
		E insertedValue = heap.get(index);

		// Medan vi har en förälder, samt OM förälderns värde är större än värdet vi insertar
		while (parent(index)>=0){
			E parentVal = heap.get(parent(index));

			// Om objektet har mindre värde, skicka det uppåt via att swappa dessa värden
			if(comparator.compare(parentVal, insertedValue) > 0){
//				System.out.print("The current binary heap representation is: ");
//				heap.forEach(s -> System.out.print(s + " "));
//				System.out.println();
//				System.out.println("Switching the inserted Element: " + insertedValue);


				int parentIndex = parent(index);

				// Swappa nu ut objekten
				heap.set(parentIndex, insertedValue);
				heap.set(index, parentVal);

				//Uppdaterar värdet i mappen
				// FIXME: 2019-04-20 -> Möjligtvis att denna mapDel är skev
				elemPosMap.replace(parentVal, index);
				elemPosMap.replace(insertedValue, parentIndex);


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

		// TODO -- Fixa mappen korrekt

		// Hämta objektet via indexet ifrån heapen
		E value = heap.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < heap.size()) {

			// Hämta index för de olika barnen
			int left    = leftChild(index);
			int right   = rightChild(index);

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
			// Uppdatera även Mappen för att reflektera förändringarna
			if (comparator.compare(value, childValue) > 0) {

				//Uppdatera platsen på heapen
				heap.set(index, childValue);

				// Byt plats på objekten i mappen
				elemPosMap.replace(childValue, index);
				elemPosMap.replace(value, childIndex);
				index = childIndex;
			}
			else break;
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

	@Override
	public boolean hasNext() {
		return heap.size()>0;
	}

	@Override
	public E next() {
		return minimum();
	}
}
