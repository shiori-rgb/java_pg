
// Todo（やることを表すデータ）を使うためのList（複数のデータを入れる箱）を読み込みます。
import java.util.List;

// プログラムの開始地点を持つクラスです。
public class Main {
    // Todo 1件分の情報を表すクラスです。
    static class Todo {
        // Todoのタイトル（名前）を保存します。
        String title;

        // Todoが済んだかどうかを保存します。
        boolean done;

        // タイトルと済んだかどうかを受け取ってTodoを作ります。
        Todo(String title, boolean done) {
            // 受け取ったタイトルを保存します。
            this.title = title;
            // 受け取った済んだかどうかを保存します。
            this.done = done;
        }

        // TodoをHTMLのli（箇条書き）形式の1行に変えます。
        String toItem() {
            // 済んでいれば「[済] 」を付け、済んでいなければ何も付けません。
            String mark = done ? "[済] " : "";
            // 作成した印とタイトルをliタグで囲んだ文字列を返します。
            return "<li>" + mark + title + "</li>";
        }
    }

    // Javaプログラムが最初に実行するメソッド（処理の入口）です。
    public static void main(String[] args) {
        // Todoを2件入れるList（複数のTodoを並べる箱）を作ります。
        List<Todo> todos = List.of(
                // まだ済んでいないTodoを1件入れます。
                new Todo("牛乳を買う", false),
                // 済んだTodoを1件入れます。
                new Todo("ゴミを出す", true));

        // for（繰り返し）でListのTodoを1件ずつ取り出します。
        for (Todo todo : todos) {
            // toItem()の結果をターミナル（文字を表示する画面）に1行出力します。
            System.out.println(todo.toItem());
        }
    }
}
