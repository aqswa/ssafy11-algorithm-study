import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T; //사람 수, 파티의 수, 진실을 아는 사람의 수
	static boolean known [] = new boolean[51];
	static ArrayList<ArrayList<Integer>> parties;
	static int parent[] = new int[51];
	
	public static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		return find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if(known[p1] && !known[p2]) {
			parent[p2] = p1;
			return;
		}
		parent[p1] = p2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//진실을 아는 사람 입력
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int i=0; i<T; i++) {
			int a = Integer.parseInt(st.nextToken());
			known[a] = true;
		}
		
		//파티 배열 초기화
		parties = new ArrayList<>();
		for(int i=0; i<M; i++) {
			parties.add(new ArrayList<Integer>());
		}
		
		//파티 정보 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				parties.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		//유니온
		for(int i=0; i<M; i++) {
			ArrayList<Integer> party = parties.get(i);
			for(int j=0; j<party.size()-1; j++) {
				for(int k=j+1; k<party.size(); k++) {
					union(party.get(j), party.get(k));
				}
			}
		}
		
		//진실 들을 사람 있는 파티 수
		int ans = 0;
		for(int i=0; i<M; i++) {
			boolean flag = false;
			for(Integer person : parties.get(i)) {
				if(known[find(person)]) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}