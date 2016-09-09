/**
 * This is a good easy task to learn how evolutionary algorithms work
 * @author LuxXx
 * https://github.com/LuxXx
 */
public class Evolution {
	private String goal;
	private char[] candidate;
	private int mutations = 0;
	
	public Evolution(String goal) {
		this.goal = goal;
		candidate = new char[goal.length()];
		for (int i = 0; i < candidate.length; i++) {
			candidate[i] = ' ';
		}
	}
	
	private int fitness(char[] word) {
		int fitness = 0;
		for (int i = 0; i < word.length; i++) {
			if (word[i] == goal.charAt(i))
				fitness++;
		}
		return fitness;
	}
	
	public void breed() {
		while (!new String(candidate).equals(goal)) {
			
			for (int i = 0; i < candidate.length; i++) {
				
				// reproduce / copy candidate
				char[] child = new char[candidate.length];
				for (int j = 0; j < child.length; j++) {
					child[j] = candidate[j];
				}
				mutations++;
				child[i] = mutation(); // mutation
				
				// is the mutated child fitter than the candidate?
				if (fitness(child) > fitness(candidate)) {
					//System.out.println(getCandidate());
					candidate = child;
				}
				
			}
			
		}
	}
	
	public String getCandidate() {
		return new String(candidate);
	}
	
	public int getMutations() {
		return mutations;
	}
	
	public static void main(String[] args) {
		Evolution e = new Evolution("Survival of the fittest!");
		e.breed();
		System.out.println(e.getCandidate());
		System.out.println("I needed " + e.getMutations() + " Mutations to reach the goal");
	}
	
	private static char mutation() {
		int i = 33 + (int) (Math.random()*94);
		return (char)i;
	}
}
