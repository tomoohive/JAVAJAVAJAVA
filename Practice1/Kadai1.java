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
              String katakana = word.toString();
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
      String format = "%-" + maxLengthOfSpelling + "s %3d";
      for(String word : list){
        int count = map.get(word);
        System.out.printf(format, word, count);
        System.out.println();
      }
    } else {
      System.out.println("ファイル名を入力してください。");
    }
	}
}
