//�ҳ�����ظ����ַ��� �������Ӵ�����
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int max = 0;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j <= len; j++){
                if(allUnique(s, i, j)){
                    max = Math.max(max, j - i);
                }
                // else{
                //     break;
                // }
            }
        }
        return max;
    }
    
    public boolean allUnique(String s, int start, int end){
        Set<Character> set = new HashSet<Character>();
        for(int i = start; i < end; i++){
            Character ch = s.charAt(i);
            if(set.contains(ch))
                return false;
            set.add(ch);
        }
        return true;
    }
}
