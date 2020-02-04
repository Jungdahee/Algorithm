#include <iostream>
#include <queue>
using namespace std;

int n, m, k;
int cnt;
int map[50][50];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

bool checkRange(int x, int y){
    return x < 0 || x >= n || y < 0 || y >= m;
}

void bfs(int i, int j){
    queue<pair<int, int>> q;
    q.push(make_pair(i, j));
    map[i][j] = 2;

    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for(int k = 0; k < 4; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(checkRange(nx, ny)) continue;
            if(map[nx][ny] == 1){
                q.push(make_pair(nx, ny));
                map[nx][ny] = 2;
            }
        }
    }
}

int main(){ //유기농 배추
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    
    int tc;
    cin >> tc;

    while(tc--){
        cin >> m >> n >> k;
        
        //배추 위치 설정하기
        int x, y;
        for(int i = 0; i < k; i++){
            cin >> y >> x;
            map[x][y] = 1;
        }

        cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 1) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        cout << cnt << '\n';
    }
}
