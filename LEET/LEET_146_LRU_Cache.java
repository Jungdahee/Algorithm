public class LEET_146_LRU_Cache {

    public static class LRUCache {
        LinkedHashMap<Integer, Integer> hm;
        int capacity, idx;

        public LRUCache(int capacity) {
            this.hm = new LinkedHashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if(hm.get(key) == null) return -1;
            else {
                int value = hm.get(key);
                hm.remove(key);
                hm.put(key, value);
                return hm.get(key);
            }
        }

        public void put(int key, int value) {
            if(hm.containsKey(key)){
                hm.remove(key);
            }
            else {
                if(hm.size() >= capacity) {
                    int rmKey = hm.keySet().iterator().next();
                    hm.remove(rmKey);
                }
            }
            hm.put(key, value);
        }
    }
}
