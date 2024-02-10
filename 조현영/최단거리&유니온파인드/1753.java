import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair>{
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

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return this.x - o.getX();
	}
}

public class Main {
	
	static int V, E, K;
	static ArrayList<ArrayList<Pair>> graph; //연결된 정점, 가중치
	static int [] distance;
	static final int MAX_VAL = 200000;
	static boolean [] visited;
	public static void dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>(); //시작점으로부터 거리, 정점 번호
		pq.add(new Pair(0, start));
		
		while(!pq.isEmpty()) {
			Pair top = pq.poll(); //다음으로 방문처리할 노드
			int curNode = top.getY();
			int curDistance = top.getX();
			if(!visited[curNode]) { //중복으로 큐에 남긴 노드가 아니라면
				visited[curNode] = true; //방문 처리
				for(Pair p : graph.get(curNode)) { //현재 노드에 연결된 노드에 대해서
					if(visited[p.getX()]) continue; //이미 방문처리한 정점 제외
					int nextNode = p.getX();
					if(distance[nextNode] > curDistance + p.getY()) { //현재 정점을 거쳐가는 경우가 더 가깝다면
						distance[nextNode] = curDistance + p.getY(); //거리 갱신
						pq.add(new Pair(distance[nextNode], nextNode));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); //정점 개수
		E = Integer.parseInt(st.nextToken()); //간선 개수
		K = Integer.parseInt(br.readLine()); //시작 정점 번호
		
		//인접 리스트 그래프 초기화
		graph = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Pair(v, w));
			/*boolean flag = false;
			for(Pair p : graph.get(u)) { //이중 연결
				if(p.getX() == v) {
					if(p.getY() > w) {
						graph.get(u).remove(p);
						graph.get(u).add(new Pair(v, w));
					};
					flag = true;
					break;
				}
			}
			
			if(!flag)
				graph.get(u).add(new Pair(v, w));
			*/
		}
		
		visited = new boolean [V+1];
		distance = new int[V+1];
		for(int i=1; i<=V; i++) {
			distance[i] = MAX_VAL;
		}

		distance[K] = 0;
		dijkstra(K);
		
		for(int i=1; i<=V; i++) {
			if(distance[i] == MAX_VAL)
				sb.append("INF").append("\n");
			else
				sb.append(distance[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}