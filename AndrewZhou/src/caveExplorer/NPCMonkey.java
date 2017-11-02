package caveExplorer;

public class NPCMonkey extends NPC {

	
	
		public NPCMonkey() {
			super();
		}
		
		public int[] calculateMovement() {
			int[] moves = new int[2];
			int[][] possibleMoves = {{-1,0},{0,1},{1,0},{0,-1}};
			int rand = (int)(Math.random()*possibleMoves.length);
			moves[0] = possibleMoves[rand][0]+currentRow;
			moves[1] = possibleMoves[rand][1]+currentCol;
			while(currentRoom.getDoor(rand) == null ||
					!(CaveExplorer.caves[moves[0]][moves[1]] instanceof NPCRoom)) {
				rand = (int)(Math.random()*possibleMoves.length);
				moves[0] = possibleMoves[rand][0]+currentRow;
				moves[1] = possibleMoves[rand][1]+currentCol;
			}
			return moves;
		}

}
