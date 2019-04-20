package Lab2;

import java.io.*;
import java.util.*;

public class Lab2 {

	public static String pureMain(String[] commands) {
		StringBuilder sb = new StringBuilder();

		// Declaring Comparators
		Comparator<Bid> buyComperator = (o1, o2) -> Integer.compare(o2.bid, o1.bid);
		Comparator<Bid> sellComperator = Comparator.comparingInt(o -> o.bid);

		// Passes Comparator of higestBidder to front of queue for buyers, and lowest amount for sellers
		PriorityQueue<Bid> sell_pq = new PriorityQueue<>(sellComperator);
		PriorityQueue<Bid> buy_pq = new PriorityQueue<>(buyComperator);

		// Tar in en fil via dess namn, och börjar parsea köp/sälj kommandon
		for(int line_no=0; line_no<commands.length; line_no++){
			String line = commands[line_no];

			// Om vi inte har mer Kommandon att parsea
			if( line.equals("") ){
				continue;
			}

			//Splitta upp nuvarande rad till en Array
			String[] parts = line.split("\\s+");

			//Parsea de olika delarna ur strängen
			String name = getName(line_no, parts);
			String action = getAction(line_no, parts[1], name);
			int price = parsePrice(line_no, parts[2]);
			int sPrice = parsePrice(line_no, parts[3]);

			switch (action){
				case "K":
					buy_pq.add(new Bid(name, price));
					break;
				case "S":
					sell_pq.add(new Bid(name, price));
					break;
				case "NK":
					buy_pq.updateQueue(new Bid(name, price), new Bid(name, sPrice));
					break;
				case "NS":
					sell_pq.updateQueue(new Bid(name, price), new Bid(name, sPrice));
					break;
					default:
						throw new RuntimeException("line " + line_no + ": invalid action");
			}
//			if( action.equals("K") ) {
//				// TODO: add new buy bid
//			} else if( action.equals("S") ) {
//				// TODO: add new sell bid
//			} else if( action.equals("NK") ){
//				// TODO: update existing buy bid. use parts[3].
//			} else if( action.equals("NS") ){
//				// TODO: update existing sell bid. use parts[3].
//			} else {
//				throw new RuntimeException(
//						"line " + line_no + ": invalid action");
//			}

			if( sell_pq.size() == 0 || buy_pq.size() == 0 ){
				continue;
			}

			/*
			else if(buy_pq.minimum().equals(sell_pq.minimum())){
				buy_pq.deleteMinimum();
				sell_pq.deleteMinimum();
			}
			*/

			// TODO:
			// compare the bids of highest priority from each of
			// each priority queues.
			// if the lowest seller price is lower than or equal to
			// the highest buyer price, then remove one bid from
			// each priority queue and add a description of the
			// transaction to the output.
		}

		sb.append("Order book:\n");

		sb.append("Sellers: ");
		// TODO: print remaining sellers.
		//       can remove from priority queue until it is empty.

		sb.append("Buyers: ");
		// TODO: print remaining buyers
		//       can remove from priority queue until it is empty.

		return sb.toString();
	}

	private static String getAction(int line_no, String part, String name) {
		if( name.charAt(0) == '\0' ){
			throw new RuntimeException("line " + line_no + ": invalid name");
		}
		return part;
	}

	private static String getName(int line_no, String[] parts) {
		if( parts.length != 3 && parts.length != 4){
			throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
		}
		return parts[0];
	}

	private static int parsePrice(int line_no, String partToParce) {
		int price;
		try {
			price = Integer.parseInt(partToParce);
		}
		catch(NumberFormatException e){
			throw new RuntimeException(
					"line " + line_no + ": invalid price");
		}
		return price;
	}

	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if( args.length != 1 ){
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while(true){
			String line = actions.readLine();
			if( line == null)break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}
