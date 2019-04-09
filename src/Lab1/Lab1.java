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
     throw new UnsupportedOperationException();
     }

    // Quicksort part of an array
     private static void quickSort(int[] array, int begin, int end) {
     }

    // Partition part of an array, and return the index where the pivot
    // ended up.
     private static int partition(int[] array, int begin, int end) {
     throw new UnsupportedOperationException();
     }

    // Swap two elements in an array
     private static void swap(int[] array, int i, int j) {
     int x = array[i];array[i] = array[j];array[j] = x;
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
