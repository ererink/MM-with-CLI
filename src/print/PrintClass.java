package print;

import java.util.ArrayList;
import java.util.List;

public class PrintClass {
    private static PrintClass instance = new PrintClass();
    private static final int colNum = 150;
    /**
     * 현재 프린트할 STRING 정보
     */
    private static List<List<String>> row = new ArrayList<>();
    private PrintClass() {

    }
    static public PrintClass getInstance() {
        return instance;
    }

    public static String padding(String str, int n) {
        for (int i = 0; i < n; i++) {
            str += " ";
        }
        return str;
    }
    public static String paddingLeft(String str, int n) {
        for (int i = 0; i < n; i++) {
            str = " "+str;
        }
        return str;
    }

    /**
     * 새로운 줄 추가
     */
    public static void addRow() {
        row.add(new ArrayList<>());
    }

    /**
     * 현재 줄에 요소 추가
     * @param str
     */

    public static void addElement(String str) {
        row.get(row.size() - 1).add(str);
    }

    /**
     * 현재까지 추가한 STRING 모두 출력
     * row 비워짐
     */
    public static void printCurrent() {
        for (List<String> stringList : row) {
            int size = stringList.size();
            for (String str : stringList) {
                int col_size = colNum/size;
                int koreanCnt = 0;
                for (int i = 0; i < str.length(); i++) {
                    char temp = str.charAt(i);
                    if(Character.getType(temp)==5){
                        koreanCnt++;
                    }
                }
                int padding = (col_size - str.length() - koreanCnt/4*3);
                if (size == 1) {
                    System.out.print(paddingLeft(str,padding/2));
                }
                else System.out.print(padding(str,padding));
            }
            System.out.println();
        }
        row = new ArrayList<>();
    }



    public static void main(String[] args) {
        addRow();
        addElement("1 개가 조회되었습니다 ");
        addRow();
        addRow();
        addElement(" ");
        addElement("ID");
        addElement("TITLE");
        addElement("CONTENT");
        addElement("DATE");
        addRow();
        addElement("1");
        addElement("adoo24");
        addElement("오늘의 숙제");
        addElement("숙제 내용입니다");
        addElement("23-04-05");
        addRow();
        addElement("한글한글");
        addElement("한글한글한글");
        addElement("한글한굴한글한글");
        addElement("한글한굴한글한글addd");
        addElement("adsfasdasdfasd");
        printCurrent();
    }
}
