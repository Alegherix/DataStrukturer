package Lab2;

import java.util.Comparator;

/**
 * Used as a comperator for Buyers
 * We want the buyers with the Higest Bid at front of priority queue
 */
public class BuyerComperator implements Comparator<Bid> {

    @Override
    public int compare(Bid o1, Bid o2) {
        if(o1.bid > o2.bid){
            return 1;
        }
        else if(o1.bid == o2.bid){
            return 0;
        }
        else{
            return -1;
        }
    }
}
