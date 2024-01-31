import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int [] given;
	public static int [] chosen;
	public static StringBuilder sb = new StringBuilder();
	public static void combination(int cnt, int idx) {
		if(cnt == 6) {
			for(int i=0; i<6; i++) {
				sb.append(chosen[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			chosen[cnt] = given[i];
			combination(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) {
				break;
			}
			
			given = new int [N];
			chosen = new int [N];
			for(int i=0; i<N; i++) {
				given[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}