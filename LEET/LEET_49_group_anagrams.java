class Solution {
    public List<List<String>> groupAnagrams(String[] input) {
        LinkedList<List<String>> result = new LinkedList<List<String>>();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        for(int i = 0; i < input.length; i++) {
            char chArr[] = input[i].toCharArray();

            Arrays.sort(chArr);
            String tmp = new String(chArr);
            
            if (!hm.containsKey(tmp)) hm.put(tmp, hm.keySet().size());
            int index = hm.get(tmp);

            if (result.size() != 0 && result.size() - 1 >= index) result.get(index).add(input[i]);
            else {
                List<String> list = new LinkedList<String>();
                list.add(input[i]);
                result.add(list);
            }
        }
        
        return result;
    }
}
