public class PRO_60060_가사검색 {

    public static class Trie {
        int cnt = 0;
        Trie children[];

        public Trie(){
            this.cnt = 0;
            this.children = new Trie[26];
        }

        public void insert(char chars[]){
            Trie current = this;

            for(char ch : chars){
                ++current.cnt;
                if(current.children[ch - 'a'] == null) current.children[ch - 'a'] = new Trie();
                current = current.children[ch - 'a'];
            }
        }

        public int find(char queries[]){
            Trie current = this;

            for(char query : queries){
                if(query == '?') return current.cnt;
                if(current.children[query - 'a'] != null) current = current.children[query - 'a'];
                else return 0;
            }

            return current.cnt;
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        int answer[] = new int[queries.length];

        Trie front[] = new Trie[10001];
        Trie back[] = new Trie[10001];

        for(String word : words){
            int length = word.length();

            if(front[length] == null) front[length] = new Trie();
            front[length].insert(word.toCharArray());

            if(back[length] == null) back[length] = new Trie();
            back[length].insert((new StringBuffer(word)).reverse().toString().toCharArray());
        }

        for(int i = 0; i < queries.length; i++){
            String query = queries[i];

            if(query.indexOf('?') != 0){
                if(front[query.length()] != null)
                    answer[i] = front[query.length()].find(query.toCharArray());
            }
            else {
                if(back[query.length()] != null)
                    answer[i] = back[query.length()].find((new StringBuffer(query)).reverse().toString().toCharArray());
            }
        }

        return answer;
    }

    public static void main(String args[]){
        String words[] = {"frodo", "front", "frost", "frozen", "frame", "kakao", "friday"};
        String queries[] = {"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString(solution(words, queries)));
    }
}
