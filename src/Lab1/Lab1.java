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
             int pointer = i - 1;

             // If value in the array on current iteration, IE greatest elem in array > Unsorted Elem -> Then move to the right to make a hole
             // Then update value for holding iteration, so that it also checks the previous values and compares to unsorted elem
             while (pointer >= 0 && array[pointer] > unsortedElem){
                 array[pointer+1] = array[pointer];
                 pointer = pointer -1;
             }
             // Place unsorted element to the position where unsorted >= array[pointer] && unsorted < array[pointer + 2]
             array[pointer + 1] = unsortedElem;
         }
     }


    // Quicksort.
     public static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
     }

    /**
     * Usage: Recursily quicksort the array
     * 1) If elements still left to sort
     * 2) Assign new pivot
     * 3) Quicksort left part
     * 4) Quicksort right part
     */
     private static void quickSort(int[] array, int begin, int end) {
         if(begin < end){
             int pivot = partition(array, begin, end);
             quickSort(array, begin, pivot - 1);
             quickSort(array, pivot + 1, end);
         }
     }

    /**
     * Usage: Partition the array and return index of pivot element
     * 1) Create a pointer 1 Index to the right of pivot
     * 2) Traverse each item in partition
     * 3) If element < pivot? swap with pointer and increment pointer
     * 4) Finally swap pivot back to it's correct position, and return pivot index.
     */
     private static int partition(int[] array, int begin, int end) {
         // Creating the pointer
         int pointer = begin + 1;

         //Traverse each elem in partition
         for (int i = pointer; i <= end ; i++) {
             //Compare element with pivot, if elem < pivot, swap elem with elem to the right of pivot, and increment pointer.
             if(array[i] < array[begin]){
                 swap(array, i, pointer++);
             }
         }
         //Swap back pivot to correct place
         swap(array, begin, pointer-1);

         //Return pivot index
         return pointer-1;
     }

    // Swap two elements in an array
     private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
     }


    // Mergesort.
     public static int[] mergeSort(int[] inArray) {
         // Recursive basecase where array has 1 Elem
         if(inArray.length <= 1){
             return inArray;
         }

         // Mid index of array
         int midVal = inArray.length / 2;

         //Defining left and Rightside of array
         int[] left = new int[midVal];
         int[] right = new int[inArray.length % 2 == 0? midVal : midVal+1];

         //Populate left array
         for (int i = 0; i < midVal; i++) {
             left[i] = inArray[i];
         }

         //Populate right array with remaining values
         for (int i = 0; i < right.length; i++) {
             right[i] = inArray[i + midVal];
         }

         // The recursive call to split the arrays
         left = mergeSort(left);
         right = mergeSort(right);

         // merge the arrays into one and return
         return merge(left, right);
     }

    // Merge two sorted arrays into one
     private static int[] merge(int[] left, int[] right) {
         // initialize array to hold values for merged array
         int[] resultArray = new int[left.length + right.length];

         // initialize pointers for keeping track of elements in arrays
         int leftPointer = 0;
         int rightPointer = 0;
         int resultPointer = 0;

         // While elements still in left or right array we want to merge into resultArray
         while (leftPointer < left.length || rightPointer < right.length){

             // If both Arrays have elements left
             if(leftPointer < left.length && rightPointer < right.length){

                 //val in left array lowest
                 if(left[leftPointer] < right[rightPointer]){
                     resultArray[resultPointer++] = left[leftPointer++];
                 }
                 else{
                     resultArray[resultPointer++] = right[rightPointer++];
                 }
             }
             //only values remaining is in left array
             else if(leftPointer < left.length){
                 resultArray[resultPointer++] = left[leftPointer++];
             }
             //only values remaining is in right array
             else{
                 resultArray[resultPointer++] = right[rightPointer++];
             }
         }
         return resultArray;
     }
}
