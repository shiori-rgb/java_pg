import com.sun.net.httpserver.HttpServer; // 簡易Webサーバーの機能を読み込みます。
import java.net.InetSocketAddress; // 待ち受ける番号（ポート）を扱う機能を読み込みます。

public class App { // Appという名前のプログラムを定義します。
    public static void main(String[] args) throws Exception { // プログラムの開始地点を定義します。
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); // 8080番で接続を待つサーバーを用意します。
        server.createContext("/", exchange -> {
            System.out.println("ハンドラが動いた"); // トップページ（/）への処理を直接書きます。
            String message = "天津飯"; // ブラウザに返す文字を用意します。
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8"); // 返す文字がUTF-8だと伝えます。
            byte[] body = message.getBytes("UTF-8"); // 返す文字をUTF-8のデータに変換します。
            exchange.sendResponseHeaders(200, body.length); // 正常に返すこととデータの長さを伝えます。
            exchange.getResponseBody().write(body); // ブラウザへデータを送ります。
            exchange.getResponseBody().close(); // データを送り終えたので通信を閉じます。
        }); // トップページへの処理を登録します。
        server.start(); // サーバーを起動して待ち受けます。
        System.out.println("サーバー起動: http://localhost:8080 （止めるときは Ctrl+C）"); // 起動したことをターミナルに表示します。
    } // mainメソッドを終わります。
} // Appクラスを終わります。
