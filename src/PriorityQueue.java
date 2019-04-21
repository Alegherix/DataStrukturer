import java.util.*;

/**
 * Source for Time Complexity of ArrayList operations: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * " The size, isEmpty, get, set, iterator, and listIterator operations run in constant time. The add operation runs in amortized constant time,
 *   that is, adding n elements requires O(n) time. All of the other operations run in linear time (roughly speaking). "
 *
 * Source for Time Complexity of HashMap operations: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * " This implementation provides constant-time performance for the basic operations (get and put),
 *   assuming the hash function disperses the elements properly among the buckets."
 *
 * 	~~Class Invariant~~
 * 	The class invariant for a binary heap is that each child has to be greater than it's parent (In the case of a Min Heap),
 * 	and viceversa for Max Heaps.
 */

public class PriorityQueue<E> implements Iterator<E>{
	private ArrayList<E> heap = new ArrayList<E>();
	private Comparator<E> comparator;
	private Map<E, Integer> elemPosMap = new HashMap<>();

	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}


	/**
	 * O(1) -> size() is running at Constant time.
	 * @return
	 */
	public int size() {
		return heap.size();
	}


	/**
	 * add -> amortized O(1)
	 * put -> O(1)
	 * O(log n) -> Sifting up is what defines Big O for this method
	 * @param x - The element to att to the Priority queue
	 */
	public void add(E x)
	{
		assert invariant();

		heap.add(x);

		// Put the element into the HashMap
		elemPosMap.put(x, heap.size()-1);

		// Sift new Element upwards
		siftUp(heap.size()- 1);

		assert invariant();
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

	/**
	 * Removes highest priority element
	 * remove -> O(1)
	 * set -> O(1)
	 * replace -> O(1)
	 * O(log n) -> Sifting down is what defines Big O for this method
	 */

	public void deleteMinimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		assert invariant();

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

		assert invariant();

	}


	/**
	 * Updates Queue and Map with a new element
	 * containsKey -> O(1)
	 * put -> O(1)
	 * get -> O(1)
	 * set -> O(1)
	 * O(log n) -> The sifting is what defines the O for this method
	 * @param oldE - The old element that should be updated
	 * @param newE - The new element that should be used instead
	 */
	public void updateQueue(E oldE, E newE){
		if(!elemPosMap.containsKey(oldE)) throw new NoSuchElementException();

		assert invariant();

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

		assert invariant();
	}



	/**
	 * Sifts a node up
	 * get -> O(1)
	 * compare -> O(1), Comparing bid, which has int as underlying data structure, Could be greater if other compare implementation is used
	 * set -> O(1)
	 * replace -> O(1)
	 * Best case Scenario -> O(1) == element is placed in order and should not be reprioritzed
	 * Worst case scenario -> O(log n) -> when element have to be sifted up all the way to the root
	 * @param index - Index of element in heap to possibly sift upwards.
	 */
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


	/**
	 * Sifts a node down
	 * get -> O(1)
	 * compare -> O(1), Comparing bid, which has int as underlying data structure, Could be greater if other compare implementation is used
	 * set -> O(1)
	 * replace -> O(1)
	 * Best case Scenario -> O(1) == element is placed in order and should not be reprioritzed
	 * Worst case scenario -> O(log n) == when element have to be sifted all the way down to the leaf
	 * @param index - Index of element in head to possibly sift downwards
	 */
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
			E childElem = heap.get(left);

			//If a right value exist, compare if and assign the proper childIndex, and ChildElem
			if (right < heap.size()) {
				E rightValue = heap.get(right);
				if (comparator.compare(childElem, rightValue) > 0) {
					childIndex = right;
					childElem = rightValue;
				}
			}

			// Compare the elem with child elem, if elem has lower prio, send it down and update map to reflect changes
			if (comparator.compare(value, childElem) > 0) {

				//Update place on the heap
				heap.set(index, childElem);

				//Reflect changes in the HashMap
				elemPosMap.replace(childElem, index);
				elemPosMap.replace(value, childIndex);

				index = childIndex;
			}
			else break;
		}
		heap.set(index, value);
	}


	/**
	 * Invariant that checks that each child must be greater than it's parent(Min Heap) or viceversa (Max Heap),
	 * aswell as checking the corresponding map entries.
	 * @return - Boolean of wheter or not the invariant holds
	 */
	private boolean invariant(){
		for (int i = 0; i < heap.size(); i++) {

			E parent = heap.get(parent(i));
			E child = heap.get(i);

			if(comparator.compare(parent, child)>0 || (elemPosMap.get(parent) > elemPosMap.get(child))){
				return false;
			}
		}
		return true;
	}


	/**
	 * O(1)
	 * @param parentIndex - Index of parent
	 * @return index of Left child
	 */
	private final int leftChild(int parentIndex) {
		return 2*parentIndex+1;
	}

	/**
	 * O(1)
	 * @param parentIndex - Index of parent
	 * @return index of Right child
	 */
	private final int rightChild(int parentIndex) {
		return 2*parentIndex+2;
	}

	/**
	 * O(1)
	 * @param childIndex - Index of child
	 * @return index of parent
	 */
	private final int parent(int childIndex) {
		return (childIndex-1)/2;
	}


	/**
	 * O(1)
	 * @return - Boolean wheter or not there's still elements in the heap
	 */
	@Override
	public boolean hasNext() {
		return size()>0;
	}


	/**
	 * O(1)
	 * @return - Next element in the heap
	 */
	@Override
	public E next() {
		return minimum();
	}
}
