package test.org.FirstBlockChain;

import java.util.ArrayList;

import org.FirstBlockChain.Block;
import org.FirstBlockChain.FirstBlockChain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBlock {

	private ArrayList<Block> blockchain;
	
	@Before
	public void setup() {
		blockchain = new ArrayList<Block>();
	}
	
	@Test
	public void gerenateChainTest() {
		boolean onlyBlocksInBlockchain = true;
		blockchain = FirstBlockChain.generateChain(blockchain);
		
		Assert.assertTrue(blockchain.size() > 0);
		
		for(int i = 0; i < blockchain.size(); i++) {
			if(!(blockchain.get(i) instanceof Block)) {
				onlyBlocksInBlockchain = false;
			}
		}
		
		Assert.assertTrue(onlyBlocksInBlockchain);
	}
	
	@Test
	public void isChainValidTest() {
		Assert.assertTrue(FirstBlockChain.isChainValid());
	}
}
