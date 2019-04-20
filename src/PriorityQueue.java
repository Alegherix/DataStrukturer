import java.util.*;


/**
 * HashMaps put, get is Amortized O(1)
 * @param <E>
 */

// A priority queue.
public class PriorityQueue<E> implements Iterator<E>{
	private ArrayList<E> heap = new ArrayList<E>();
	private Comparator<E> comparator;
	private Map<E, Integer> elemPosMap = new HashMap<>();

	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}


	// Returns the size of the priority queue.
	// O(1) -> size() seems to be incremented as a variable under the hood of a ArrayList
	// If however it's calculated by traversing the Collection then returning it's O(n)
	public int size() {
		return heap.size();
	}


	/**
	 *
	 * @param x - The element to att to the Priority queue
	 */
	public void add(E x)
	{
		// Adds the element at the last of the list
		heap.add(x);

		// Put the element into the HashMap
		elemPosMap.put(x, heap.size()-1);

		// Sift new Element upwards
		siftUp(heap.size()- 1);
	}


	/**
	 *  O(1) -> Min is always as top so Constant
	 * @return - The element with the highest priority
	 */
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

		//Put the last elem at the top so that we can safely remove the smallest element
		heap.set(0, heap.get(heap.size()-1));

		// Updates HashMap value for the now top place element
		elemPosMap.replace(minimum(), 0);

		//Removes the Top priority element in queue, which now resides at last position
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

		E insertedValue = heap.get(index);

		// While we have a parent, try to sift up
		while (parent(index)>=0){

			// Assign parent for comparission
			E parentVal = heap.get(parent(index));

			// If element has higher priority, bubble it up via swaping position
			if(comparator.compare(parentVal, insertedValue) > 0){
				int parentIndex = parent(index);

				// Swap the elements
				heap.set(parentIndex, insertedValue);
				heap.set(index, parentVal);

				//Update the HashMap to represent swapping
				elemPosMap.replace(parentVal, index);
				elemPosMap.replace(insertedValue, parentIndex);

				//Update index for next iteration of loop
				index = parentIndex;
			}
			else{
				break;
			}
		}
	}
     
	// Sifts a node down.
	// siftDown(index) fixes the invariant if the element at 'index' may
	// be greater than its children, but all other elements are correct.
	private void siftDown(int index) {

		E value = heap.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < heap.size()) {

			// Get indexes for the children
			int left    = leftChild(index);
			int right   = rightChild(index);

			// Assign a childIndex, start with left
			int childIndex = left;

			// Get childElement, start with left
			E childValue = heap.get(left);

			// Om det finns ett höger värde, Assigna även detta objektet
			// Jämför de båda objektens värden
			// Assigna sedan om childIndex, och childValue till det som faktiskt är minst

			//If a right
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


	//	  O(1) -> size() seems to be incremented as a variable under the hood of a ArrayList,
	//	  IF however it's calculated by traversing then returning it's O(n)
	@Override
	public boolean hasNext() {
		return size()>0;
	}


	// O(1) -> Min is always as top so Constant
	@Override
	public E next() {
		return minimum();
	}
}
