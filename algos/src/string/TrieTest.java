package string;

import java.util.ArrayList;

public class TrieTest {

	public static void main (String [] args) {
		String[] inputs = {"tree", "trie", "algo", "assoc", "all", "also", "tree"};

		Node root = new Node(null);
		for (int i = 0; i < inputs.length; i++) {
			root.addWord(inputs[i], 0);
		}
		
		String searchPrefix = "al";
		String searchWord = "alg";
		
		System.out.println(root);
		System.out.println("Number of prefixes \"" + searchPrefix + "\": " 
								+ root.countPrefixes(searchPrefix, 0));
		System.out.println("Number of times word \"" + searchWord + "\" appears: " 
								+ root.countWords(searchWord, 0));
	}

	static class Node {
		Character name;
		int count;
		ArrayList<Node> sub;
		
		public Node (Character i) {
			name = i;
			count = 0;
			sub = new ArrayList<Node>();
		}
		
		public void addWord (String word, int ix) {
			if (ix < word.length()) {
				int prefixAt = indexOfPrefix(word.charAt(ix));
				if (prefixAt == -1) {
					addChar(word.charAt(ix));
					prefixAt = sub.size() - 1;
				}
				sub.get(prefixAt).addWord(word, ix + 1);
			}
			else count++;
		}
		
		public int countPrefixes (String prefix, int ix) {
			if (ix == prefix.length()) return countLeaves();
			else {
				int prefixAt = indexOfPrefix(prefix.charAt(ix));
				if (prefixAt == -1) return 0;
				return sub.get(prefixAt).countPrefixes(prefix, ix + 1);
			}
		}
		
		public int countWords (String word, int ix) {
			if (ix == word.length()) {
				/*
				 * If I don't have sub nodes, then I am the word, so return my count
				 * Otherwise, the word isn't complete, so return 0.
				 */
				if (sub.isEmpty()) return count;
				else return 0;
			}
			
			int prefixAt = indexOfPrefix(word.charAt(ix));
			if (prefixAt == -1) return 0;
			
			return sub.get(prefixAt).countWords(word, ix + 1);
		}
		
		private int countLeaves () {
			int sum = 0;
			for (Node n : sub) {
				if (n.sub.size() == 0) sum += n.count;
				else sum += n.countLeaves();
			}
			return sum;
		}
		
		private int indexOfPrefix (Character j) {
			for (int i = 0; i < sub.size(); i++)
				if (sub.get(i).name.equals(j))
					return i;
			return -1;
		}
		
		private void addChar (Character j) {
			Node n = new Node(j);
			sub.add(n);
		}
		
		public String toString () {
			return name + "";
		}
	}
}
