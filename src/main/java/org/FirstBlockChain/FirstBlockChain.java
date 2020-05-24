package org.FirstBlockChain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class FirstBlockChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] main) {
		blockchain = generateChain(blockchain);

		System.out.println("\nBlockchain is valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}

	// Just to simplify tests.
	public static ArrayList<Block> generateChain(ArrayList<Block> blockchain) {
		// Adding blocks.
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		return blockchain;
	}

	public static Boolean isChainValid() {

		Block currentBlock;
		Block previousBlock;
		
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			// We re-calculate the hash to see if it is the same.
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}

			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			// Check if hash is solved
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}

		return true;
	}

}
