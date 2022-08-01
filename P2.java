import org.junit.Test;

import java.io.File;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 2、文件数量
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算。
 * @create 2022-08-01 10:13
 */
public class P2 {

    public int getFileNum(String path) {
        File file = new File(path);
        if (!file.isDirectory() && !file.isFile()) {
            return 0;
        }
        if (file.isFile())return 1;
        Stack<File> stack = new Stack<>();
        stack.push(file);
        int res = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next:folder.listFiles()){
                if (next.isFile()){
                    res++;
                }else if (next.isDirectory()){
                    stack.push(next);
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        String path = "F:\\DataSet\\svm_training_imgs";
        System.out.println(getFileNum(path));
    }


}
