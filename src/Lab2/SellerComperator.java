package Lab2;

import java.util.Comparator;

/**
 * Used for Sellers
 * We want the sellers with the Lowest selling price at the Front on the Priority queue
 */
public class SellerComperator implements Comparator<Bid> {

    @Override
    public int compare(Bid o1, Bid o2) {
        if(o1.bid > o2.bid){
            return -1;
        }
        else if(o1.bid == o2.bid){
            return 0;
        }
        else{
            return 1;
        }
    }
}
