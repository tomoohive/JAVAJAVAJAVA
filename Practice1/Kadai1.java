/*
2018/10/9
Problem 1
Extraction of two or more Katakana words in a file and printing of frequent occurrences
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.StringBuilder;

public class Kadai1{

  private static final char[] ZENKAKU_KATAKANA = { 'ァ', 'ア', 'ィ', 'イ', 'ゥ',
      'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ', 'ギ', 'ク', 'グ', 'ケ', 'ゲ',
      'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ', 'ス', 'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ',
      'ダ', 'チ', 'ヂ', 'ッ', 'ツ', 'ヅ', 'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ',
      'ネ', 'ノ', 'ハ', 'バ', 'パ', 'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ', 'ヘ', 'ベ',
      'ペ', 'ホ', 'ボ', 'ポ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ャ', 'ヤ', 'ュ', 'ユ',
      'ョ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン',
      'ヴ', 'ヵ', 'ヶ'};

  private static final String[] HANKAKU_KATAKANA = { "ｧ", "ｱ", "ｨ", "ｲ", "ｩ",
      "ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ", "ｷ", "ｷﾞ", "ｸ", "ｸﾞ", "ｹ",
      "ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ", "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ",
      "ｿﾞ", "ﾀ", "ﾀﾞ", "ﾁ", "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ",
      "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ", "ﾋﾟ", "ﾌ",
      "ﾌﾞ", "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ",
      "ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ",
      "ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ"};

  private static final char ZENKAKU_KATAKANA_FIRST_CHAR = ZENKAKU_KATAKANA[0];

  private static final char ZENKAKU_KATAKANA_LAST_CHAR = ZENKAKU_KATAKANA[ZENKAKU_KATAKANA.length - 1];
  
  public static String zenkakuKatakanaToHankakuKatakana(char c) {
    if (c >= ZENKAKU_KATAKANA_FIRST_CHAR && c <= ZENKAKU_KATAKANA_LAST_CHAR) {
      return HANKAKU_KATAKANA[c - ZENKAKU_KATAKANA_FIRST_CHAR];
    } else if(c == 'ー'){
      return "-";
    }else {
      return String.valueOf(c);
    }
  }

  public static String zenkakuKatakanaToHankakuKatakana(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      char originalChar = s.charAt(i);
      String convertedChar = zenkakuKatakanaToHankakuKatakana(originalChar);
      sb.append(convertedChar);
    }
    return sb.toString();
  }
	
	public static void main(String[] args){
    if(args.length > 0){
      String fileName = args[0];
      Map<String, Integer> map = new HashMap<>();
      try{
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder word = new StringBuilder();
        word.setLength(0);
        while((line = input.readLine()) != null){
          for(int i=0; i < line.length(); i++){
            char x = line.charAt(i);
            if(x == 'ー' || (x >='ァ' && x <= 'ヶ')){
              word.append(x);
              if(word.charAt(0) == 'ー'){
                word.setLength(0);
              }
            }else if((word.length()) != 0){
              String katakana = zenkakuKatakanaToHankakuKatakana(word.toString());
              if(map.containsKey(katakana)){
                int count = map.get(katakana) + 1;
                map.put(katakana, count);
              }else {
                map.put(katakana, 1);
              }
              word.setLength(0);
            }
          }
        }
      } catch(FileNotFoundException e){
        System.out.println("ファイルが見つかりませんでした。");
      } catch(IOException e){
        System.out.println("読み取りに失敗しました。");
      }

      List<String> list = new ArrayList<>();
      int maxLengthOfSpelling = 0;
      for(String key : map.keySet()){
        list.add(key);
        if(maxLengthOfSpelling < key.length()){
          maxLengthOfSpelling = key.length();
        }
      }
      Collections.sort(list, (o1, o2) -> {
        return - map.get(o1) + map.get(o2);
      });
      System.out.println("出現回数");
      String format = "%-" + maxLengthOfSpelling + "s %3d%s";
      for(String word : list){
        int count = map.get(word);
        String kai = "回";
        System.out.printf(format, word, count, kai);
        System.out.println();
      }
    } else {
      System.out.println("ファイル名を入力してください。");
    }
	}
}
