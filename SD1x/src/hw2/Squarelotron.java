package hw2;

public class Squarelotron {
	int[][] squarelotron;
	int size;
	

	public Squarelotron(int n) {
		this.size = n;
		int count = 1;
		squarelotron = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.squarelotron[i][j] = count;
				count++;
			}
		}
	}
	
	public boolean onRing(int i, int j, int ring) {
		boolean on = (i == ring - 1 || j == ring - 1 
				|| i == size - ring || j == size - ring); 
		boolean outside = (i <= ring - 2 || i >= size + 1 - ring 
				|| j <= ring - 2 || j >= size + 1 - ring); 
		return on && !outside; 
	}


	public Squarelotron upsideDownFlip(int ring) {
		Squarelotron s = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (onRing(i, j, ring)) {
					s.squarelotron[i][j] = this.squarelotron[size - 1 - i][j]; // flip pattern
				}
			}
		}
		return s;
		
	}
	
	public Squarelotron mainDiagonalFlip(int ring) {
		Squarelotron s = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (onRing(i, j, ring)) {
					s.squarelotron[i][j] = this.squarelotron[j][i]; // flip pattern
				}
			}
		}
		return s;
	}

	public void rotateRight(int numberOfTurns) {
		numberOfTurns = (numberOfTurns % 4 + 4) % 4; 
		for (int n = 0; n < numberOfTurns; n++) {
			Squarelotron s = new Squarelotron(size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					s.squarelotron[j][size - 1 - i] = squarelotron[i][j]; 
				}
			}
			this.squarelotron = s.squarelotron;
		}
	}
	
}