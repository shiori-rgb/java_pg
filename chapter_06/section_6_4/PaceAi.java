package chapter_06.section_6_4;

public class PaceAi {
    public static void main(String[] args) {
        int totalPages = 250;
        int perDay = 30;
        int days = totalPages / perDay;
        int rest = totalPages % perDay;

        System.out.println("全体のページ数: " + totalPages);
        System.out.println("1日に読むページ数: " + perDay);
        System.out.println("まるまる読める日数: " + days);
        System.out.println("最終日に残るページ数: " + rest);
    }
}
