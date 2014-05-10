package otumin.com;

/**
 * ニムトのゲームルール
 *
 * @author tksmaru
 */
public class Rule {

	/** 最小プレーヤー人数 */
	public static final int MIN_NUMBER_OF_PLAYER = 2;

	/** 最大プレーヤー人数 */
	public static final int MAX_NUMBER_OF_PLAYER = 10;

	/**
	 * プレーヤー人数が正しいかを判定します。<br />
	 *
	 * @param number プレーヤー人数
	 * @return 2-10人であればtrue、それ以外の場合はfalseを返します。
	 */
	public static boolean isValidNumberOfPlayers(int number) {
		return MIN_NUMBER_OF_PLAYER <= number && number <= MAX_NUMBER_OF_PLAYER ? true : false;
	}
}
