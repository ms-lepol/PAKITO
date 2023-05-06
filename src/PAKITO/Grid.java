package PAKITO;
import java.util.*;

public class Grid {
	Map<Position,List<Piece>> Grid;

	public Grid(){
		Grid = new HashMap<Position,List<Piece>>();
		initGrid();
	}

	public void initGrid(){
		for(int row = 0; row < Position.MAX_HEIGHT; row++){
			for(int col = 0; col < Position.MAX_WIDTH; col++){
				List<Piece> li = new ArrayList<Piece>();
				li.add(0, new Free());
				Grid.put(new Position(col, row), li);
			}
		} 
	}

	@Override
	public String toString() {
		String str = "";
		for(int row = 0; row < Position.MAX_HEIGHT; row++){
			for(int col = 0; col < Position.MAX_WIDTH; col++){
				str += Grid.get(new Position(col, row)).get(0).toString();
			}
		}
		return str;
	}
}
