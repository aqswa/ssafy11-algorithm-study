import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair{
	private int x;
	private int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

public class Main {
	
	public static int N, M; //보드 사이즈, 남길 치킨집 수
	public static int [][] board = new int[50][50];
	public static ArrayList<Pair> chickens = new ArrayList<Pair>();
	public static ArrayList<Pair> houses = new ArrayList<Pair>();
	public static int [] chosen = new int [13];
	public static int ans = 100000;
	
	public static int find_distance() {
		int total = 0;
		for(int i=0; i<houses.size(); i++) {
			int min = 100;
			for(int j=0; j<M; j++) {
				int dist = Math.abs(houses.get(i).getX() - chickens.get(chosen[j]).getX()) +
						Math.abs(houses.get(i).getY() - chickens.get(chosen[j]).getY());
				if(dist < min) {
					min = dist;
				}
			}
			total += min;
		}
		
		return total;
	}
	
	public static void combination(int cnt, int idx) {
		if(cnt == M) {
			int dist = find_distance();
			if(dist < ans) {
				ans = dist;
			}
			return;
		}
		
		for(int i=idx; i<chickens.size(); i++) {
			chosen[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //고를 치킨집 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) {
					chickens.add(new Pair(i, j));
				}
				else if(board[i][j] == 1) {
					houses.add(new Pair(i, j));
				}
			}
		}
		
		combination(0, 0);
		
		System.out.println(ans);
	}
}