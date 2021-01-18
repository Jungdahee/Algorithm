public class LEET_208_Implement_Trie {

    public static class Trie {
        LinkedList<String> children;

        public Trie() {
            children = new LinkedList<String>();
        }

        public void insert(String word) {
            children.add(word);
        }

        public boolean search(String word) {
            if(children.contains(word)) return true;
            else return false;
        }

        public boolean startsWith(String prefix) {
            for(String current : children){
                if(current.length() < prefix.length()) continue;
                if(current.substring(0, prefix.length()).equals(prefix)) return true;
            }

            return false;
        }
    }
    public static void main(String agrs[]){
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
