import java.util.*;

class PRO_49994_방문길이 {
        public static class Loc {
        int x1, x2, y1, y2;

        public Loc(int x1, int x2, int y1, int y2){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        @Override
        // 같은 객체인지 체크
        public int hashCode() {
            return x1 + y1 + x2 + y2;
        }

        @Override
        // 같은 내용인지 체크
        public boolean equals(Object obj) {
            if(this == obj) return true;

            Loc loc = (Loc) obj;
            if(this.x1 == loc.x1 && this.y1 == loc.y1){
                if(this.x2 == loc.x2 && this.y2 == loc.y2){
                    return true;
                }
            }
            else if(this.x2 == loc.x1 && this.y2 == loc.y1){
                if(this.x1 == loc.x2 && this.y1 == loc.y2){
                    return true;
                }
            }

            return false;
        }
    }
    
    public int solution(String dirs) {
        int cx = 0;
        int cy = 0;

        HashSet<Loc> hs = new HashSet<Loc>();
        for(int i = 0; i < dirs.length(); i++){
            char ch = dirs.charAt(i);
            if(ch == 'U'){
                if(checkRange(cx - 1, cy)) continue;
                hs.add(new Loc(cx, --cx, cy, cy));
            }
            else if(ch == 'D'){
                if(checkRange(cx + 1, cy)) continue;
                hs.add(new Loc(cx, ++cx, cy, cy));
            }
            else if(ch == 'L'){
                if(checkRange(cx, cy - 1)) continue;
                hs.add(new Loc(cx, cx, cy, --cy));
            }
            else {
                if(checkRange(cx, cy + 1)) continue;
                hs.add(new Loc(cx, cx, cy, ++cy));
            }
        }

        return hs.size();
    }

    public static boolean checkRange(int x, int y){
        return x < -5 || x > 5 || y < -5 || y > 5;
    }
}
