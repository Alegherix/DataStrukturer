package Lab1;

public class Lab1 {
    /** Sorting algorithms **/

    /**
     * Steps
     * 1) Select the first unsorted elem
     * 2) Swap all the other elements to the right,
     * 3) Place our unsorted element to the right position
     * 4) Move marker to next elem and repeat
     * @param array
     */
    // Insertion sort.
     public static void insertionSort(int[] array) {
         int n = array.length;

         for(int i = 1; i < n; i++){
             int unsortedElem = array[i];
             int marker = i - 1;

             // Uses a while loop cause loop iterations is unknown
             // If value in the array on current iteration, IE greatest elem in array > Unsorted Elem -> Then move to the right to make a hole
             // Then update value for holding iteration, so that it also checks the previous values and compares to unsorted elem
             while (marker >= 0 && array[marker] > unsortedElem){
                 array[marker+1] = array[marker];
                 marker = marker -1;
             }
             // Place unsorted element to the position where unsorted >= array[marker] && unsorted < array[marker + 2]
             array[marker + 1] = unsortedElem;
         }
     }


    // Quicksort.
     public static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
     }

    /**
     * Usage: Recursily quicksort the array
     * 1) If elements still left to sort
     * 2) Extract new pivot
     * 3) Quicksort left
     * 4) Quicksort right
     */
     private static void quickSort(int[] array, int begin, int end) {
         if(begin < end +1){
             int pivot = partition(array, begin, end);
             quickSort(array, begin, pivot - 1);
             quickSort(array, pivot + 1, end);
         }
     }

    /**
     * Usage: Partition the array and return index of pivot element
     * 1) Create a pointer 1 Index to the right of pivot
     * 2) Traverse each item in partition
     * 3) If element < pivot? swap with marker and increment marker
     * 4) Finally swap pivot back to it's correct position, and return pivot index.
     */
     private static int partition(int[] array, int begin, int end) {
         // Creating the pointer
         int pointer = begin + 1;

         //Traverse each elem in partition
         for (int i = pointer; i <= end ; i++) {
             //Compare element with pivot, if elem < pivot, swap elem with elem to the right of pivot, and increment marker.
             if(array[i] < array[begin]){
                 swap(array, i, pointer++);
             }
         }
         //Swap back pivot to correct place
         swap(array, begin, pointer-1);
         return pointer-1;
     }

    // Swap two elements in an array
     private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
     }


    // Mergesort.
     public static int[] mergeSort(int[] array) {
        throw new UnsupportedOperationException();
     }

    // Mergesort part of an array
     private static int[] mergeSort(int[] array, int begin, int end) {
        throw new UnsupportedOperationException();
     }

    // Merge two sorted arrays into one
     private static int[] merge(int[] left, int[] right) {
        throw new UnsupportedOperationException();
     }
}
