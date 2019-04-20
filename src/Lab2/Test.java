package Lab2;

import java.util.*;

public class Test {
    List<Bid> bids = Arrays.asList(new Bid("karl", 50), new Bid("Martin", 55), new Bid("Johan", 52));


    public static void main(String[] args) {
        List<Bid> bids = Arrays.asList(new Bid("Karl", 50), new Bid("Martin", 55), new Bid("Jess", 52));
        Comparator<Bid> buyComp = (o1, o2) -> Integer.compare(o2.bid, o1.bid);
        Comparator<Bid> sellComp = Comparator.comparingInt(o -> o.bid);
        bids.sort(sellComp);

        PriorityQueue<Bid> bidPriorityQueue = new PriorityQueue<>(buyComp);
        bids.forEach(bidPriorityQueue::add);
        System.out.println("At top of priority is: " + bidPriorityQueue.minimum());

        bidPriorityQueue.updateQueue(new Bid("Martin", 55), new Bid("Martin", 52));





    }

    public static void testRegularQueue(){
        // Sorterar efter minst först
        Comparator<Integer> comparator1 = Integer::compareTo;
        List<Integer> nmbrs = Arrays.asList(6,4,12,156,15616,16,4,851,6,2);
        nmbrs.sort(comparator1);
        nmbrs.forEach(System.out::println);

    }


}




