package qlcoder;

/**
 * @auther xzl on 13:36 2018/5/9
 */
public class CharacterSort {

    public   int getCount(String code){
        int num = 0;
        if(code == null ||code.length() ==0) return 0;
        char ch = 'A';
        for(int i=0;i<26;i++){
            ch = (char)(ch+i);
            if (code.indexOf(ch)>-1){
                num++;
            }
            if (num>2){
                return  0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String code = "AABBCC";
    }
}
