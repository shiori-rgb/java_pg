// Todoを3件まとめて持つ配列（複数のデータを並べたもの）を作ります。
public class Items {
    // プログラムの実行を始める場所です。
    public static void main(String[] args) {
        // Todoの文字列3件を配列（データの並び）に入れます。
        String[] todos = { "牛乳を買う", "", "パンを買う", "掃除をする" };
        boolean[] done = { true, false, false, false };
        // iを使って配列の先頭から最後まで1件ずつ繰り返します。
        for (int i = 0; i < todos.length; i++) {
            // Todoを<li>と</li>で囲み、ターミナル（文字を表示する画面）に出力します。
            if (!todos[i].isEmpty()) {
                String mark = done[i] ? "[済]" : "";
                System.out.println("<li>" + mark + todos[i] + "</li>");
            }
        }
    }
}
