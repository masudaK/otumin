package com.masudak.nimmt.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Nimmtでカードを置く場を表します。<br />
 * 場に対する操作は{@link GameMaster}を介して行います。
 *
 * @author tksmaru
 */
public class Field {

	/** 場にある列の数 */
	private static final int LINE_SIZE = Rule.FIELD_SIZE;

	/** 場が持つ列 */
	private List<Line> lines;

	Field(List<Card> cards) {
		// TODO 引数チェック
		lines = new ArrayList<Line>(LINE_SIZE);
		for (int i = 0; i < LINE_SIZE; i++) {
			Line line = new Line(i);
			line.addLast(cards.get(i));
			lines.add(line);
		}
	}

	/**
	 * フィールドを構成する列の一覧を取得しますが、削除はしません。<br />
	 * また、取得したリスト内の列に対して変更を加えても列の情報が変更されることはありません。
	 *
	 * @return フィールドを構成する列の一覧
	 */
	public List<Line> getLines() {
		List<Line> field = new ArrayList<Line>(LINE_SIZE);
		for (Line line : lines) {
			field.add(line.copy());
		}
		return field;
	}

	/**
	 * フィールドを構成する列を取得しますが、削除はしません。<br />
	 * また、取得した列に対して変更を加えても列の情報が変更されることはありません。
	 *
	 * @return フィールドを構成する列
	 */
	public Line getLine(int index) {
		return lines.get(index).copy();
	}

	/**
	 * 場の各列の最後尾のカードのうち、最小の番号を取得します。
	 *
	 * @return 各列の最後尾のカードのうち、最小の番号
	 */
	public int getSmallestInTheLast() {
		int smallest = Rule.NUMBER_OF_CARDS;
		for (Line line : lines) {
			int number = line.getLast().getNumber();
			if (smallest > number) {
				smallest = number;
			}
		}
		return smallest;
	}

	/**
	 * 指定した列をクリアし、その時点で列に並んでいた各カードの牛の数の合計を返します。
	 *
	 * @param index 列番号（0-3）
	 * @return 列に並んでいた各カードの牛の数の合計
	 */
	int clearLine(int index) {
		// TODO 引数チェック
		Line line = lines.get(index);
		int totalCows = 0;
		while(!line.isEmpty()) {
			totalCows += line.remove().getCow();
		}
		return totalCows;
	}

	/**
	 * 指定した列の最後尾にカードを配置します。
	 *
	 * @param index 列番号(0-3)
	 * @param card カード
	 */
	void put(int index, Card card) {
		// TODO
		lines.get(index).addLast(card);
	}

	/**
	 * 各列の最後尾のカードのリストを取得します。
	 *
	 * @return 各列の最後尾のカードのリスト
	 */
	public List<Card> getLasts() {
		List<Card> cards = new ArrayList<Card>();
		for (Line line : lines) {
			cards.add(line.getLast());
		}
		return cards;
	}
}
