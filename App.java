import com.sun.net.httpserver.HttpServer; // 簡易Webサーバーの機能を読み込みます。
import java.net.InetSocketAddress; // 待ち受ける番号（ポート）を扱う機能を読み込みます。

public class App { // Appという名前のプログラムを定義します。
    public static void main(String[] args) throws Exception { // プログラムの開始地点を定義します。
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); // 8080番で接続を待つサーバーを用意します。
        server.createContext("/", exchange -> { // すべてのパスへの処理を直接書きます。
            String path = exchange.getRequestURI().getPath(); // アクセスされたパスを取り出します。
            String message;
            if (path.equals("/hello")) { // パスが/helloかどうかを比べます。
                String query = exchange.getRequestURI().getRawQuery();
                System.out.println("query = " + query); // URLのクエリ（追加情報）を取り出します。
                String name = query.substring(5); // name=の後ろを切り出します。
                message = "こんにちは、" + name + "さん！"; // 名前を混ぜて応答します。
            } else if (path.equals("/bye")) { // パスが/byeかどうかを比べます。
                message = "さようなら！";
            } else if (path.equals("/menu")) { // パスが/かどうかを比べます。
                message = "Gong茶の新作は「おいもミルクティー」です！";
            } else {
                message = "ページが見つかりません";
            }
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8"); // 返す文字がUTF-8だと伝えます。
            byte[] body = message.getBytes("UTF-8"); // 返す文字をUTF-8のデータに変換します。
            exchange.sendResponseHeaders(200, body.length); // 正常に返すこととデータの長さを伝えます。
            exchange.getResponseBody().write(body); // ブラウザへデータを送ります。
            exchange.getResponseBody().close(); // データを送り終えたので通信を閉じます。
        }); // パスごとの処理を登録します。
        server.start(); // サーバーを起動して待ち受けます。
        System.out.println("サーバー起動: http://localhost:8080 （止めるときは Ctrl+C）"); // 起動したことをターミナルに表示します。
    } // mainメソッドを終わります。
} // Appクラスを終わります。
