import java.util.*;

/*
    각 cell은 하나의 idx로 표현할 수 있음 ((r-1) * 50) + c
    
    각 cell에 필요한 정보
        row, col
        value
        merged[idx]={parentIdx} // idx가 병합된 부모가 누구인지
        
    HashMap< Value, List<Cell> >
*/

class Solution {
    
    int N = 51; // 표의 크기는 50x50으로 고정 (1 indexing)
    int[] parent = new int[(51*51) +1]; // cell 병합한 경우, 부모 cell을 기록
    String[] cells = new String[(51*51) + 1]; // 각 cell의 value를 기록
    List<String> result = new ArrayList<>(); // PRINT 명령어 값을 저장할 리스트
    
    public String[] solution(String[] commands) {
        
        init(); // 전역 값 초기화
        
        
        // 명령어를 순차적으로 실행한다
        for(int i = 0; i < commands.length; i++) {
            String[] cmds = commands[i].split(" ");
            
            String op = cmds[0]; // 명령어 종류
            
            switch(op) {
                case "UPDATE" :
                    if(cmds.length == 4) { // 셀의 위치를 통해 UPDATE
                        int uR = Integer.parseInt(cmds[1]);
                        int uC = Integer.parseInt(cmds[2]);
                        String val = cmds[3];
                        
                        cellUpdate(uR, uC, val);
                    }
                    else { // 셀의 값을 통해 UPDATE
                        String v1 = cmds[1];
                        String v2 = cmds[2];
                        
                        cellUpdate(v1, v2);
                    }
                    
                    break;
                    
                case "MERGE" : // 셀 두개를 병합
                    int mR1 = Integer.parseInt(cmds[1]);
                    int mC1 = Integer.parseInt(cmds[2]);
                    int mR2 = Integer.parseInt(cmds[3]);
                    int mC2 = Integer.parseInt(cmds[4]);
                    
                    cellMerge(mR1, mC1, mR2, mC2);
                    
                    break;
                    
                case "UNMERGE" : // 셀의 병합을 풀어버림
                    int umR = Integer.parseInt(cmds[1]);
                    int umC = Integer.parseInt(cmds[2]);
                    
                    cellUnMerge(umR, umC);
                    
                    break;
                    
                case "PRINT" : // 해당 셀의 값을 읽어서 저장
                    int pR = Integer.parseInt(cmds[1]);
                    int pC = Integer.parseInt(cmds[2]);
                    
                    cellPrint(pR, pC);
                    
                    break;
                    
                default :
                    System.out.println("INPUT ERROR");
                    break;
            } // switch
        } // for
        
        // answer 배열로 옮겨서 반환
        int size = result.size();
        String[] answer = new String[size];
        for(int i = 0; i < size; i++) {
            answer[i] = result.get(i);
        }
        return answer;
        
    } // solution()
    
    public void cellUpdate(int r, int c, String v) {
        int idx = (r-1)*50 + c;
        
        cells[parent[idx]] = v; // 해당 위치의 값을 value로 바꿔준다
        
    } // update with idx
    
    public void cellUpdate(String v1, String v2) {
        for(int i = 1; i < cells.length; i++) { // 모든 cell을 탐색
            if(v1.equals(cells[i])) cells[i] = v2; // 값 변경
        }
    } // update with value
    
    public void cellMerge(int r1, int c1, int r2, int c2) {
        int idx1 = (r1-1)*50 + c1;
        int idx2 = (r2-1)*50 + c2;
        int parentIdx1 = parent[idx1]; // 1번 셀의 부모 idx 기록
        int parentIdx2 = parent[idx2]; // 2번 셀의 부모 idx 기록
        
        if(parentIdx1 == parentIdx2) return; // 같은 셀인 경우 무시
        
        String val = cells[parentIdx1]; // 기본적으로 r1,c1위치의 셀 값을 받고
        
        if("".equals(val)) val = cells[parentIdx2]; // 빈 값이면 r2,c2 위치 값으로 변경
        
        cells[parentIdx1] = cells[parentIdx2] = ""; // 두 셀 다 비워둔다
        
        int smaller = Math.min(parentIdx1, parentIdx2); // 둘 중 더 작은 idx를 부모로
        
        for(int i = 1; i < cells.length; i++) {
            if(parent[i] == parentIdx1 || parent[i] == parentIdx2) { // 병합될 셀을 발견했다면
                parent[i] = smaller; // 부모 cell을 설정해준다
            }
        }
        
        cells[smaller] = val; // 아까 받아놨던 값을 반영
        
    } // merge()
    
    public void cellUnMerge(int r, int c) {        
        int idx = (r-1)*50 + c;
        int parentIdx = parent[idx]; // 병합된 부모 cell의 idx 기억
        String val = cells[ parent[idx] ];
        
        // idx와 함께 merge된 애들을 모두 풀어줌
        for(int i = 1; i < cells.length; i++) { // 모든 cell을 탐색
            if(parent[i] == parentIdx) { // 같은 부모로 merge된 cell이라면
                parent[i] = i; // merge 상태를 풀어주고
                cells[i] = ""; // 값을 "" 으로 변경
            }
        }
        
        cells[idx] = val; // idx 위치에만 기존 val을 반영
        
    } // unmerge()
    
    public void cellPrint(int r, int c) {
        int idx = (r-1)*50 + c;
        String val = cells[ parent[idx] ];
        
        if("".equals( val )) result.add("EMPTY"); // 빈 cell이면 EMPTY
        else result.add(val); // 값이 있는 cell이면, 값을 추가
    } // print()
    
    public void init() {
        for(int i = 0; i < cells.length; i++) { 
            parent[i] = i; // 각 셀은 시작 시 자기 자신을 병합 부모로
            cells[i] = ""; // 각 셀의 초기 값을 빈칸으로
        }
    } // init()
    
} // class Solution