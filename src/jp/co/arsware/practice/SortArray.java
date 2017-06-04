package jp.co.arsware.practice;

public class SortArray {

	static String makeArrayString(int []ary) {
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<ary.length ; i++)
			sb.append(",").append(ary[i]);
		sb.setCharAt(0, '[');
		sb.append("]");
		return sb.toString();
	}


	public static void main(String[] args) {
		int[] aryNum = {7,1,5,2,3,4,8,9};
		int[] aryNumDesc = aryNum.clone();
		int[] aryNumAsc = aryNum.clone();

		System.out.println("aryNum : " + makeArrayString(aryNum));

		for(int i=0 ; i<aryNumDesc.length ; i++) {
			for(int j=i+1 ; j<aryNumDesc.length ; j++) {
				if(aryNumDesc[i]<aryNumDesc[j]) {
					int tmp = aryNumDesc[i];
					aryNumDesc[i] = aryNumDesc[j];
					aryNumDesc[j] = tmp;
				}
			}
		}
		System.out.println("aryNumDesc : " + makeArrayString(aryNumDesc));

		for(int i=0 ; i<aryNumAsc.length ; i++) {
			for(int j=i+1 ; j<aryNumAsc.length ; j++) {
				if(aryNumAsc[i]>aryNumAsc[j]) {
					int tmp = aryNumAsc[i];
					aryNumAsc[i] = aryNumAsc[j];
					aryNumAsc[j] = tmp;
				}
			}
		}
		System.out.println("aryNumAsc : " + makeArrayString(aryNumAsc));

		return;
	}
}
