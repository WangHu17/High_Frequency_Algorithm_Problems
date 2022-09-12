/**
 * @author wanghu
 * @discrption： 删除重复字符
 * 给定一个字符串，删除重复的字符让每个字符只出现一次。返回所有可能的结果中最小字典序的。
 * @create 2022-09-12 14:35
 */
public class P119_RemoveDuplicateLetters {

    public String removeDuplicateLetters(String str) {
        if (str == null || str.length() < 2)return str;
        int[] count = new int[256];
        for (int i=0; i<str.length(); i++){
            count[str.charAt(i)]++;
        }
        int minACSIndex = 0;
        for (int i=0; i<str.length(); i++) {
            minACSIndex = str.charAt(minACSIndex) > str.charAt(i) ? i : minACSIndex;
            if (--count[str.charAt(i)] == 0){
                break;
            }
        }
        return str.charAt(minACSIndex) +
                removeDuplicateLetters(str.substring(minACSIndex+1).replaceAll(String.valueOf(str.charAt(minACSIndex)), ""));
    }

}
