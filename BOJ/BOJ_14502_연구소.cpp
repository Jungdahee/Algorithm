#include <iostream>
#include <algorithm>
#include <queue>
#include <cstring>
using namespace std;

int map[8][8], tMap[8][8];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int n, m;
int result = 0;

bool checkRange(int x, int y){ //맵 범위 체크 
    return (x < 0 || x >= n || y < 0 || y >= m);
}

void spread(){ //바이러스 퍼트리기
    int sMap[8][8];
    memcpy(sMap, tMap, sizeof(tMap));
    queue<pair<int, int>> q;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(sMap[i][j] == 2) q.push(make_pair(i, j));
        }
    }
    
    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for(int k = 0; k < 4; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(checkRange(nx, ny)) continue;
            if(sMap[nx][ny] == 0){
                sMap[nx][ny] = 2;
                q.push(make_pair(nx, ny));
            }
        }
    }

    //감염되지 않은 공간 카운팅
    int tmp = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(sMap[i][j] == 0) ++tmp;
        }
    }
    
    //최대값 갱신
    if(result < tmp) result = tmp;
}

void makeWall(int cnt){
    if(cnt == 3){
        spread();
        return;
    }

    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(tMap[i][j] == 0) {
                tMap[i][j] = 1; //벽 세우기 
                makeWall(cnt + 1); //재귀 호출
                tMap[i][j] = 0; //벽 없애기
            }
        }
    }
}

int main(){ //연구소
    cin >> n >> m;
    
    //#1. 입력 받기
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> map[i][j];
        }
    }
    
    //#2. 감염되지 않은 부분에 벽 세우기
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(map[i][j] == 0){
                memcpy(tMap, map, sizeof(map)); 
                tMap[i][j] = 1;
                makeWall(1);
                tMap[i][j] = 0;
            }
        }
    }
    cout << result;
    return 0;
}
