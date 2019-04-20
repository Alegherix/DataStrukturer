import java.io.*;
import java.util.*;


public class Lab2 {

	public static String pureMain(String[] commands) {
		StringBuilder sb = new StringBuilder();

		// Adds a stringJoiner to get rid of last comma when parsing OrderBook data
		StringJoiner joiner = new StringJoiner(", ");

		// Declaring Comparators
		Comparator<Bid> buyComperator = (o1, o2) -> Integer.compare(o2.bid, o1.bid);
		Comparator<Bid> sellComperator = Comparator.comparingInt(o -> o.bid);

		//Initialization of queues, where seller queue prioritises lowest asking price, and buy queue prioritze highest bid price
		PriorityQueue<Bid> sellQueue = new PriorityQueue<>(sellComperator);
		PriorityQueue<Bid> buyQueue = new PriorityQueue<>(buyComperator);


		for(int line_no=0; line_no<commands.length; line_no++){
			String line = commands[line_no];

			if( line.equals("") ){
				continue;
			}

			//Parsing Bid data
			String[] parts = line.split("\\s+");
			String name = parseName(line_no, parts);
			String action = parseAction(line_no, parts[1], name);
			int price = parsePrice(line_no, parts[2]);
			int sPrice = 0;
			if(parts.length>=4){
				sPrice = parsePrice(line_no, parts[3]);
			}

			//Updating queues depending on the parsed input data
			switch (action){
				case "K":
					buyQueue.add(new Bid(name, price));
					break;
				case "S":
					sellQueue.add(new Bid(name, price));
					break;
				case "NK":
					buyQueue.updateQueue(new Bid(name, price), new Bid(name, sPrice));
					break;
				case "NS":
					sellQueue.updateQueue(new Bid(name, price), new Bid(name, sPrice));
					break;
					default:
						throw new RuntimeException("line " + line_no + ": invalid action");
			}

			if( sellQueue.size() == 0 || buyQueue.size() == 0 ){
				continue;
			}

			//Assign the top of the queues to check if we should execute a market order
			Bid topBuyer = buyQueue.minimum();
			Bid topSeller = sellQueue.minimum();

			//Executing market order if the bid of buyer >= sellers asking price
			if(topBuyer.bid >= topSeller.bid){
				buyQueue.deleteMinimum();
				sellQueue.deleteMinimum();

				sb.append(topBuyer.name)
						.append(" buys from ")
						.append(topSeller.name)
						.append(" for ")
						.append(topBuyer.bid)
						.append("kr")
						.append("\n");
			}
		}

		//Append text for the order Book
		sb.append("\nOrder book:\n");

		//Append text for Sellers in Orderbook
		sb.append("Sellers: ");
		while (sellQueue.hasNext()){
			joiner.add(sellQueue.next().toString());
			sellQueue.deleteMinimum();
		}
		sb.append(joiner);

		// Reassign joiner, as there is no proper way to clear it's entries
		joiner = new StringJoiner(", ");

		//Append text for Buyers in Orderbook
		sb.append("\nBuyers: ");
		while (buyQueue.hasNext()){
			joiner.add(buyQueue.next().toString());
			buyQueue.deleteMinimum();
		}
		sb.append(joiner);

		return sb.toString();
	}


	/**
	 * Used for parcing action I.E Buy/Sell or Update
	 * @param line_no - Line number used for Debugging the stacktrace
	 * @param part
	 * @param name
	 * @return
	 */
	private static String parseAction(int line_no, String part, String name) {
		if( name.charAt(0) == '\0' ){
			throw new RuntimeException("line " + line_no + ": invalid name");
		}
		return part;
	}

	/**
	 * Used for parsing the name or throwing Exception
	 * @param line_no - Line number used for Debugging the stacktrace
	 * @param parts
	 * @return
	 */
	private static String parseName(int line_no, String[] parts) {
		if( parts.length != 3 && parts.length != 4){
			throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
		}
		return parts[0];
	}

	/**
	 * Used for parcing the price or throwing an Exception
	 * @param line_no - Line number used for Debugging the stacktrace
	 * @param partToParce
	 * @return
	 */
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
