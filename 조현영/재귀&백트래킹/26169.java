import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int [][] board = new int [5][5];
	public static int [][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	public static boolean flag = false;
	public static boolean [][] visited = new boolean [5][5];
	public static void dfs(int r, int c, int move, int apple) {
		if(move == 3) {
			if(apple >= 2) {
				flag = true;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				if(board[nr][nc] == 1) {
					dfs(nr, nc, move+1, apple+1);
				}
				else if(board[nr][nc] == 0) {
					dfs(nr, nc, move+1, apple);
				}
				visited[nr][nc] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		visited[r][c] = true;
		if(board[r][c] == 1) {
			dfs(r, c, 1, 0);
		}
		else{
			dfs(r, c, 0, 0);
		}
		
		if(flag) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}

}