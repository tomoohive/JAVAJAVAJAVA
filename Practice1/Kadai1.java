/*
********************************************************
作成者：■ 日付：2018年10月10日 14:30:15 JST
入力ファイル名：jnews4.txt
ファイル内の2文字以上のカタカナ語の抽出と頻度のプリント
********************************************************

全角カタカナを半角カタカナに変換してあるのは、コンソール出力した際に、出現回数の
数字列を揃える為です。エディタによっては出力結果がずれて見えると思います。
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.StringBuilder;

public class Kadai1{

  // 出力する際のヘッダー
  private static final String header = "********************************************************\n作成者：■ 日付：2018年10月10日 14:30:15 JST\n入力ファイル名：jnews4.txt\nファイル内の2文字以上のカタカナ語の抽出と頻度のプリント\n********************************************************";

  // 全角カタカナのリスト
  private static final char[] ZENKAKU_KATAKANA = { 'ァ', 'ア', 'ィ', 'イ', 'ゥ',
      'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ', 'ギ', 'ク', 'グ', 'ケ', 'ゲ',
      'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ', 'ス', 'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ',
      'ダ', 'チ', 'ヂ', 'ッ', 'ツ', 'ヅ', 'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ',
      'ネ', 'ノ', 'ハ', 'バ', 'パ', 'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ', 'ヘ', 'ベ',
      'ペ', 'ホ', 'ボ', 'ポ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ャ', 'ヤ', 'ュ', 'ユ',
      'ョ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン',
      'ヴ', 'ヵ', 'ヶ'};

  // 半角カタカナのリスト
  private static final String[] HANKAKU_KATAKANA = { "ｧ", "ｱ", "ｨ", "ｲ", "ｩ",
      "ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ", "ｷ", "ｷﾞ", "ｸ", "ｸﾞ", "ｹ",
      "ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ", "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ",
      "ｿﾞ", "ﾀ", "ﾀﾞ", "ﾁ", "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ",
      "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ", "ﾋﾟ", "ﾌ",
      "ﾌﾞ", "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ",
      "ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ",
      "ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ"};

  // カタカナのリストの最初の位置
  private static final char ZENKAKU_KATAKANA_FIRST_CHAR = ZENKAKU_KATAKANA[0];

  // カタカナのリストの最後の位置
  private static final char ZENKAKU_KATAKANA_LAST_CHAR = ZENKAKU_KATAKANA[ZENKAKU_KATAKANA.length - 1];
  
  // 全角カタカナ1文字を半角に変換
  public static String zenTohan_character(char c) {
    if (c >= ZENKAKU_KATAKANA_FIRST_CHAR && c <= ZENKAKU_KATAKANA_LAST_CHAR) {
      return HANKAKU_KATAKANA[c - ZENKAKU_KATAKANA_FIRST_CHAR];
    } else if(c == 'ー'){
      return "-";
    }else {
      return String.valueOf(c);
    }
  }

  // 全角カタカナの単語を半角に変換
  public static String zenTohan_characters(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      char originalChar = s.charAt(i);

      // 1文字変換関数に送る
      String convertedChar = zenTohan_character(originalChar);

      // 文字を追加する
      sb.append(convertedChar);
    }
    return sb.toString();
  }
	
	public static void main(String[] args){
    if(args.length > 0){  //参照先ファイルが指定されているかを判定

      String fileName = args[0];  // 入力されたファイル名を代入
      Map<String, Integer> map = new HashMap<>();  // 単語の管理を行うMapを生成

      try{
        // ファイルを読み込む
        BufferedReader input = new BufferedReader(new FileReader(fileName));

        // テキストの行を読み込む変数
        String line;

        // カタカナ単語の結合をStringBuilderで行う
        StringBuilder word = new StringBuilder();

        // word変数の初期化
        word.setLength(0);

        // テキストを一行ずつ読み込んで単語を抽出する
        while((line = input.readLine()) != null){
          for(int i=0; i < line.length(); i++){
            char x = line.charAt(i);
            // カタカナの判定を行う
            if(x == 'ー' || (x >='ァ' && x <= 'ヶ')){
              word.append(x);

              // 一文字目が'ー'の場合リセットする
              if(word.charAt(0) == 'ー'){
                word.setLength(0);
              }

            // word変数に単語が入力された場合にMapに出力する
            }else if((word.length()) != 0){

              // StringBuilder word を Stringに変換して半角カナに変更
              String katakana = zenTohan_characters(word.toString());

              // Mapにすでに同じ単語が入っている場合、keyカウンタを増やす
              if(map.containsKey(katakana)){
                int count = map.get(katakana) + 1;
                map.put(katakana, count);

              // 入っていない場合初期値1で入力
              }else {
                map.put(katakana, 1);
              }

              // wordを初期化してループを再開
              word.setLength(0);
            }
          }
        }
        // BufferReaderを閉じる
        input.close();
      } catch(FileNotFoundException e){  // 指定ファイルが見つからなかった場合エラーを返す
        System.out.println("ファイルが見つかりませんでした。");
      } catch(IOException e){  // 指定ファイルの読み取りに失敗した場合エラーを返す
        System.out.println("読み取りに失敗しました。");
      }

      // 結果をコンソールに出力&テキストファイルに書き込み
      try{
        // Writerクラスを宣言する
        FileWriter outFile = new FileWriter("Kadai1.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(outFile));

        // MapをListに出力するための変数
        List<String> list = new ArrayList<>();

        // 最大文字長を格納するための変数
        int maxLengthOfSpelling = 0;

        // listにmapデータを格納、最大文字長取得
        for(String key : map.keySet()){
          list.add(key);
          if(maxLengthOfSpelling < key.length()){
            maxLengthOfSpelling = key.length();
          }
        }

        // listを単語の出現回数でソートする
        Collections.sort(list, (o1, o2) -> {
          return - map.get(o1) + map.get(o2);
        });

        // 出力フォーマットを設定
        String format = "%-" + maxLengthOfSpelling + "s %3d%s";

        // ヘッダー出力
        System.out.println(header);
        System.out.println();
        pw.println(header);
        pw.println();

        // 結果を出力する
        for(String word : list){
          int count = map.get(word);
          String kai = "回";
          System.out.printf(format, word, count, kai);
          pw.printf(format, word, count, kai);
          System.out.println();
          pw.println();
        }

        // PrintWriterを閉じる
        pw.close();

      // 読み込めなかったときのエラー
      }catch(IOException e){
        System.out.println(e);
      }
    } else {  //入力されていない場合入力するように促す
      System.out.println("ファイル名を入力してください。");
    }
	}
}
