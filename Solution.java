import java.util.Scanner;

public class Solution {
	int[] _data;
	String _result = null;
	public Solution(String input){
		Scanner in = new Scanner(input);
		int length = in.nextInt();
		_data = new int[length];
		for(int i = 0; i < length; i++){
			_data[i] = in.nextInt();
		}
	}
	public void calculate(){
		int maxInt = 0;
		for (int i : _data){
			maxInt = Math.max(maxInt, Math.abs(i));
		}
		//compare top to maxint. if maxint, preform 1-2 flips to make it bottom.
		//if not maxint, compare if positive or negative.
		//if top negative, find next larger number in array
		//if next larger number positive, flip  that position -1. 
		//if next larger number negative, flip that position number.
		//if top positive, find next lowest number in array
		//if next lowest number is positive, flip that position - 1.
		//if next lowest number is negative,
	}
	public String getResult(){
		if (_result == null){
			throw new IllegalArgumentException();
		}
		return _result;
	}
}

