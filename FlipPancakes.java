import java.util.Scanner;

public class FlipPancakes {
	int[] _data;
	/**
	 * represents the number of variables at the 'top' that are unsorted.
	 */
	int _unsortedLength;
	Queue<Integer> _result = new LinkedQueue<>();
	/**
	 * represents the highest magnitude of the unsorted part of the pancake stack
	 */
	int _intHigh = Integer.MIN_VALUE;
	/**
	 * represents lowest magnitude of the pancake sizes
	 */
	int _intLow = Integer.MAX_VALUE;

	/** generates our instance variables and calls calculate.
	 *
	 * @param input string of numbers. First number is number of following numbers, next numbers are unique magnitude signed integers
	 */
	public FlipPancakes(String input){
		Scanner in = new Scanner(input);
		_unsortedLength = in.nextInt();
		_data = new int[_unsortedLength];
		for(int i = 0; i < _unsortedLength; i++){
			_data[i] = in.nextInt();
			_intHigh = Math.max(_intHigh, Math.abs(_data[i]));
			_intLow = Math.min(_intLow, Math.abs(_data[i]));
		}
		calculate();
	}

	/**
	 * preforms background calculations and tracking to determine sort status of structure.
	 * @return if the array is sorted
	 */
	private boolean notSorted() {
		while(_unsortedLength > 0 && _intHigh == _data[_unsortedLength - 1]) {
			_unsortedLength--;
			_intHigh = Integer.MIN_VALUE; // reset
			for (int i = 0; i < _unsortedLength; i++) {
				_intHigh = Math.max(_intHigh, Math.abs(_data[i]));
			}
		}
		if (_result.size() >= 3*_data.length - 2){ // result flips >= worst case scenario, give up. No infinite loops ever again.
			_unsortedLength = 0;
		}
		return _unsortedLength > 0;
	}

	public void calculate(){
		int top;
		int indexhigher;
		int indexlower;
		while(notSorted()) {
			top = _data[0];
			if (Math.abs(top) == _intHigh) { //If top pancake is largest of unsorted
				if (top > 0) { // is positive
					flip(1);
				}
				flip(_unsortedLength);
				continue; // back to start
			}
			indexhigher = findHigherMagnitude(top);
			indexlower = findLowerMagnitude(top);
			if (Math.abs(top) == _intLow){
				int index = 0;
				while(Math.abs(_data[index++]) != _intHigh){}// index = position after intHigh
				flip(index);
				continue;
			}

			if (_data[indexhigher] > 0 && top > 0){
				flip(1);
				flip(indexhigher);
			} else if (_data[indexhigher] > 0 && top < 0) {
				flip(indexhigher);
			} else if (_data[indexhigher] < 0 && top > 0){
				flip(indexhigher + 1);
				flip(indexhigher);
			} else { //indexhigher < 0 && top < 0
				flip(1);
				flip(indexhigher + 1);
				flip(indexhigher);
			}
		}
	}

	/**
	 * finds number to operate on
	 * @return array index, -1 if lowest in unsorted section of array.
	 */
	private int findLowerMagnitude(int value) {
		value = Math.abs(value);
		int index = -1;
		int lower = -1;
		int curr;
		for(int i = 0; i < _unsortedLength; i++){
			curr = Math.abs(_data[i]);
			if (curr < value ){
				if (curr > lower){
					lower = curr;
					index = i;
				}
			}
		}
		return index;
	}

	/**
	 * finds number to operate on
	 * @return array index, -1 if lowest in unsorted section of array.
	 */
	private int findHigherMagnitude(int value) {
		value = Math.abs(value);
		int index = -1;
		int higher = Integer.MAX_VALUE;
		int curr;
		for(int i = 0; i < _unsortedLength; i++){
			curr = Math.abs(_data[i]);
			if (curr > value ){
				if (curr < higher){
					higher = curr;
					index = i;
				}
			}
		}
		return index;
	}

	/**
	 * changes the state of the "pancake stack" and updates result queue
	 * @param count num of pancakes to flip from top. No greater than _unsortedLength.
	 */
	private void flip(int count){
		if(count > _unsortedLength || count < 0){
			throw new IllegalArgumentException("FlipPancakes.flip() out of acceptable range");
		}
		Stack<Integer> temp = new ListStack<>();
		for(int i = 0; i < count; i++){
			temp.push(_data[i]);
		}
		for(int i = 0; i < count; i++){
			_data[i] = -1 * temp.pop();
		}
		_result.enqueue(count);
	}

	/**
	 * Safe for one call per object
	 * @return flip sequence
	 */
	public String getResult(){
		StringBuilder sb = new StringBuilder();
		sb.append(_result.size());
		while(!_result.isEmpty()){
			sb.append(' ');
			sb.append(_result.dequeue());
		}
		return sb.toString();
	}
}
