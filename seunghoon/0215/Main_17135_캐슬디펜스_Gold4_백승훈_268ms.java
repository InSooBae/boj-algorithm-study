package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main_17135_캐슬디펜스_Gold4_백승훈_268ms {

	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	/** 
	 * MEMO : 
	 * 격자판의 크기 N*M
	 * 턴제 게임
	 * 각 칸에있는 적의 수 : 최대 1명
	 * N+1칸에는 성이 있다
	 * 궁수가 공격하는 적은 거리가 D이하인 적 중 가까운 적 -> 여러명이면 가장 왼쪽에 있는 적을 공격
	 * 같은 적이 여러 궁수에게 공격당할 수 있다, 공격받은 적은 게임에서 제외된다.
	 * 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동하면 게임에서 제외
	 * 
	 * original Board에 입력을 받고 board에 복사를 해서 사용하는 형식으로 진행
	 * 궁수 위치를 올겨가며 시뮬레이션 해야됨
	 * 
	 * 
	 * 아이디어 	
	 * 3명의 궁수 배치 -> 조합을 통해 위치 선택
	 * N+1칸에 궁수 위치를 배치시키는데 궁수는 2로 표시
	 * BFS를 통해 적의 위치를 파악하기 위해 왼쪽, 오른쪽, 위쪽에는 벽(-1)을 표시 
	 * 공격당한 적의 인덱스를 저장(궁수 1명은 1명만 공격가능하고, 총 3번의 공격이 가능하기 때문)
	 * 궁수위치를 파악해 놓고, 해당 인덱스 부터 BFS방식을 통해 거리 D안에 있는 적 파악		
	 * 남은 적의 수 파악 -> 적이 남아있으면 아래로 한칸 이동
	 * 
	 */
	
	static int N, M, D; // 3 ≤ N, M ≤ 15, 1 ≤ D ≤ 10
	
	//bfs에 활용하기 위해 4방탐색 좌표
	// 왼쪽을 우선적으로 탐색하기 위해서 좌 상 우 순서로 배치
	private static int dr[] = {0, -1, 0}; // 좌 상 우 
	private static int dc[] = {-1, 0, 1}; // 좌 상 우
	private static int[][] board; // 격자판
	private static int[][] originalBoard;
	private static int[] archor;
	private static List<int[]> archorPosition;
	private static List<int[]> targets;
	private static List<int[]> fixedTarget;
	private static int archorKill;
	private static int moveCount;
	private static int enemyDead;
	private static int enemy;
	private static int enemycount;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		board = new int[N+2][M+2];			// N+1행에는 궁수배치, 벽테두리 설정
		originalBoard = new int[N+2][M+2];			// N+1행에는 궁수배치, 벽테두리 설정
		archor = new int[M];
		archorPosition = new LinkedList<>();
		
		// 보드판 입력
		for(int i=1; i<=N; i++) { 				
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				originalBoard[i][j] = Integer.parseInt(st.nextToken());
				if(originalBoard[i][j] == 1) enemy++;
			}
		}
		
		// 벽 설치
		for(int i=0; i<=N+1; i++) originalBoard[i][0] = originalBoard[i][M+1] = -1;
		for(int j=0; j<=M+1; j++) originalBoard[0][j] = -1;
		

		// 궁수 배치
		combination(0, 1, new int[3]);
		int result = 0;
		
		for (int[] position : archorPosition) { // 시뮬레이션 시작
			archor = position; 					// 궁수 위치 배치		
			archorKill = 0;						// 궁수가 죽인 적의 수 초기화
			moveCount = 0;
			enemyDead = 0;

			for(int i=0; i<=N+1; i++)			// copy board
				for(int j=0; j<=M+1; j++)
					board[i][j] = originalBoard[i][j];
		
												// 성벽도 -1로 초기화, 궁수가 있는 위치 이외의 장소는 -1처리 -> bfs효율 증가
			for(int block=0; block<=M+1; block++) 
				board[N+1][block] = -1;
			board[N+1][position[0]] = board[N+1][position[1]] = board[N+1][position[2]] = 2;			

			while(archorKill + enemyDead < enemy) {
				
				
				int[] t;
				t = BFS(archor[0]);
				fixedTarget = new LinkedList<>();
				if(t!=null) fixedTarget.add(t);
				t = BFS(archor[1]);
				if(t!=null) fixedTarget.add(t);
				t = BFS(archor[2]);
				if(t!=null) fixedTarget.add(t);
				
				for (int[] target : fixedTarget) {
					if(board[target[0]][target[1]] == 1) {						
						board[target[0]][target[1]] = 0;
						archorKill++;
					}
				}
				moveEnemy();
			}
			result = Math.max(result, archorKill);
		}		
		System.out.println(result);
		
		
	} // end of main

	
	private static void moveEnemy() {
		// TODO 적 아래로 한칸 이동
		// 성벽 바로 위에서부터 탐색
		for(int i=N; i>=0; i--) { 		// 체크하지 않아도 되는 부분 제외
			for(int j=1; j<=M; j++) {
				if(board[i][j] == 1 && i==N) {
					board[i][j] = 0;
					enemyDead++;
				} else if(board[i][j] == 1) {
					board[i+1][j] = board[i][j];
					board[i][j] = 0;
				}
			}
		}	
	}



	private static int[] BFS(int ac) {
		// TODO 궁수의 위치 입력, 거리안에 있는 적을 죽이는 함수
		
		if(archorKill + enemyDead >= enemy) return null;

		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N+2][M+2]; 
		queue.offer(new int[] {N+1, ac});
		visited[N+1][ac] = true;

		targets = new LinkedList<int[]>();
		int minDistance = D;
		
q:		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.peek()[1];
			queue.poll();
			for(int i=0; i<3; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(!visited[nr][nc] && board[nr][nc] != -1 && getDistance(N+1,ac,nr,nc) <= minDistance) {
					if(getDistance(N+1,ac,nr,nc) > minDistance) continue;
					if(board[nr][nc] == 1) { 			// 적을 찾은 경우
						if(minDistance == D) { // 거리가 가장 짧은 놈을 찾았을 때							
							targets.add(new int[] {nr,nc});
							minDistance = getDistance(N+1,ac,nr,nc);
						} else if(minDistance == getDistance(N+1,ac,nr,nc)) {
							targets.add(new int[] {nr,nc});
						}
					}
					visited[nr][nc] = true;
					queue.offer(new int[] {nr,nc});
				}
			}	
		}
		
		int[] returnTarget = null;
		for (int[] target : targets) {
			if(returnTarget == null) returnTarget = target;
			else if(target[1] < returnTarget[1]) {
				returnTarget = target;
			}
		}
		return returnTarget;
		
		
	}

	private static int getDistance(int r, int c, int nr, int nc) {
		// TODO Auto-generated method stub
		return Math.abs(r-nr)+Math.abs(c-nc);
	}



	// 궁수의 위치를 반환할 
	public static void combination(int cnt, int start, int[] result) {
		if(cnt == 3) {
			int[] position = new int[] {result[0], result[1], result[2] };
			archorPosition.add(position);
			return;
		}
		
		for(int i=start; i<=M; i++) {
			result[cnt] = i;
			combination(cnt+1, i+1, result);
		}
	} // end of combination
	
} // end of class















