package org.FirstBlockChain;

import java.util.Date;

public class Block {

	// https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa
	// https://dzone.com/articles/the-top-3-blockchain-libraries-for-java-devs
	// https://www.quora.com/What-is-the-best-programming-language-to-learn-if-you-want-to-work-on-the-blockchain

	public String hash;
	public String previousHash;

	private String data; // Just a simple message.
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		// We should apply SHA-256 into the infos that we dont want to change.
		// So, we dont want to change: previousHash, timeStamp and data.
		StringBuffer inputParameter = new StringBuffer();
		
		inputParameter.append(previousHash).append(Long.toString(timeStamp)).append(Integer.toString(nonce)).append(data);
		
		String calculatedHash = StringUtil.applySha256(inputParameter.toString());
		
		return calculatedHash;
	}
	
	public void mineBlock(int difficulty) {
		// Create a string with difficulty * "0" 
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	
}
