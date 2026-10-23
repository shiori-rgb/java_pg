import com.sun.net.httpserver.HttpServer; // 簡易Webサーバーの機能を読み込みます。
import java.net.InetSocketAddress;
import java.util.ArrayList; // リストを作る機能を読み込みます。
import java.util.List; // 文字列のリストを扱う機能を読み込みます。 // 待ち受ける番号（ポート）を扱う機能を読み込みます。

public class App { // Appという名前のプログラムを定義します。
    public static void main(String[] args) throws Exception { // プログラムの開始地点を定義します。
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); // 8080番で接続を待つサーバーを用意します。
        server.createContext("/", exchange -> { // すべてのパスへの処理を直接書きます。
            String path = exchange.getRequestURI().getPath(); // アクセスされたパスを取り出します。
            String message;
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8"); // 通常の返事をUTF-8のプレーンテキストにします。
            if (path.equals("/hello")) { // パスが/helloかどうかを比べます。
                String query = exchange.getRequestURI().getRawQuery();
                System.out.println("query = " + query); // URLのクエリ（追加情報）を取り出します。
                String name = query.substring(5); // name=の後ろを切り出します。
                message = "こんにちは、" + name + "さん！"; // 名前を混ぜて応答します。
            } else if (path.equals("/todos")) { // パスが/todosかどうかを比べます。
                List<String> todos = new ArrayList<>(); // Todo（やること）のリストを用意します。
                todos.add("牛乳を買う"); // Todoを1件追加します。
                todos.add("卵を買う"); // Todoを1件追加します。
                todos.add("パンを買う");
                todos.add("ITパスポートの勉強をする"); // Todoを1件追加します。
                String html = "<ul>"; // HTML（Webページの記述）のリストを始めます。
                for (String todo : todos) { // Todoを1件ずつ取り出します。
                    html += "<li>" + todo + "</li>"; // TodoをHTMLの項目として追加します。
                }
                html += "</ul>"; // HTMLのリストを閉じます。
                message = html; // 組み立てたHTMLを返す内容にします。
                exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8"); // TodoだけHTMLとして返します。
            } else if (path.equals("/bye")) { // パスが/byeかどうかを比べます。
                message = "さようなら！";
            } else if (path.equals("/menu")) { // パスが/かどうかを比べます。
                message = "Gong茶の新作は「おいもミルクティー」です！";
            } else {
                message = "ページが見つかりません";
            }
            // 返す文字がUTF-8だと伝えます。
            byte[] body = message.getBytes("UTF-8"); // 返す文字をUTF-8のデータに変換します。
            exchange.sendResponseHeaders(200, body.length); // 正常に返すこととデータの長さを伝えます。
            exchange.getResponseBody().write(body); // ブラウザへデータを送ります。
            exchange.getResponseBody().close(); // データを送り終えたので通信を閉じます。
        }); // パスごとの処理を登録します。
        server.start(); // サーバーを起動して待ち受けます。
        System.out.println("サーバー起動: http://localhost:8080 （止めるときは Ctrl+C）"); // 起動したことをターミナルに表示します。
    } // mainメソッドを終わります。
} // Appクラスを終わります。
