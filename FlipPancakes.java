import java.util.Scanner;

public class FlipPancakes {
	/**
	 * java FlipPancakes array/list N [pancakes]
	 * @param args
	 */
	public static void main(String[] args){
		if (args.length < 2){
			System.out.println("You need more arguments");
		}
		Stack<Integer> stack;
		if (args[0].equals("array")){
			stack = new ArrayStack<>();
		} else if(args[0].equals("list")) {
			stack = new ListStack<>();
		} else {
			System.out.println("First argument invalid");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(args[1]);
		for(int i = 2; i < args.length; i++){
			sb.append(" ");
			sb.append(args[i]);
		}
		FlipPancakes solution = new FlipPancakes(sb.toString(), stack);
		System.out.println(solution.getResult());
	}
	private int[] _data;
	/**
	 * represents the number of variables at the 'top' that are unsorted.
	 */
	private int _unsortedLength;
	private Stack<Integer> _stack;
	private Queue<Integer> _result = new LinkedQueue<>();
	/**
	 * represents the highest magnitude of the unsorted part of the pancake stack
	 */
	private int _intHigh = Integer.MIN_VALUE;
	/**
	 * represents lowest magnitude of the pancake sizes
	 */
	private int _intLow = Integer.MAX_VALUE;

	/** generates our instance variables and calls calculate.
	 *
	 * @param input string of numbers. First number is number of following numbers, next numbers are unique magnitude signed integers
	 */
	public FlipPancakes(String input, Stack<Integer> st){
		_stack = st;//used in private void flip(int count)
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
			if(_data[_unsortedLength - 1] == -_intHigh){ //optimize bottom element if inverted first
				flip(_unsortedLength);// this makes question 2 answer better
				flip(1);
				flip(_unsortedLength);
				continue; // back to start
			}
			indexhigher = findHigherMagnitude(top);
			indexlower = findLowerMagnitude(top);
			if(indexhigher == 1){
				if(_data[indexhigher] < 0 && top < 0){
					flip(1);
					flip(2);
					flip(1);
					continue;// back to start
				}
				int index = 0;
				while(Math.abs(_data[index++]) != _intHigh){}// index = position after intHigh
				if(_data[index-1] != _intHigh){// target burnt side up, get to top preserveing relative order
					flip(_unsortedLength);
					flip(_unsortedLength - index + 1);
					continue;// back to start
				}
				flip(index);
				continue;// back to start
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
				indexhigher += 1;
				int ts = topSorted();
				flip(ts);//flip the top sorted section
				flip(indexhigher);//good
				flip(indexhigher-ts);// flip (indexhigher + 1 - ts)
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
	 * returns number of indexis from the top that are sorted in reletive order
	 */
	private int topSorted(){
		int index = 0;
		int temp = _data[index++];
		if (temp > 0){
			while (_data[index] > 0 && _data[index] < temp){
				temp = _data[index];
				index+=1;
			}
		} else {
			while (_data[index] < 0 && _data[index] > temp){
				temp = _data[index];
				index+=1;
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
		for(int i = 0; i < count; i++){
			_stack.push(_data[i]);
		}
		for(int i = 0; i < count; i++){
			_data[i] = -1 * _stack.pop();
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
