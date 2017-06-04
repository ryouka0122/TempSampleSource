package jp.co.arsware.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchWord {

	public static void main(String[] args) throws IOException {


		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.print("ファイルを指定してください > ");
		String filename = br.readLine();

		System.out.print("検索文字を指定してください > ");
		String keyword = br.readLine();

		File file = new File(filename);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));

			int count = 0;		// 検出件数
			int lineNo = 0;		// 読み込んだ行数

			while(true) {
				String text = reader.readLine(); // １行分読み込み

				if(text==null) {
					// ファイルを最後まで読み切った
					break;
				}
				lineNo += 1; // 行数更新

				// チェック
				if(text.contains(keyword)) {
					count += 1; // 検出件数更新
					System.out.println(lineNo + "行目：" + text);
				}
			}
			System.out.println("検出した件数は"+count+"件です．");

		} catch (FileNotFoundException e) {
			System.out.println("ファイルがありません");
		} finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					;
				}
			}
		}




	}
}
