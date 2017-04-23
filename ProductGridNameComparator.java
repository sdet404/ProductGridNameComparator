package org.fanniemae.com.SampleProject;

import java.util.Comparator;

public class ProductGridNameComparator implements Comparator<String> {

	public int compare(final String prodNameA, final String prodNameB) {
		if (prodNameA == null || prodNameB == null)
			throw new NullPointerException("Comparing String that is set to null.");

		int indexNonDigitA = getIndexOfFirstNonDigit(prodNameA);
		int indexNonDigitB = getIndexOfFirstNonDigit(prodNameB);

		// non-null values assigned only when applicable to facilitate sort
		String numPrefixA = null;
		String numPrefixB = null;
		String alphaSuffixA = null;
		String alphaSuffixB = null;

		if (indexNonDigitA > 0) {
			numPrefixA = prodNameA.substring(0, indexNonDigitA);
			alphaSuffixA = prodNameA.substring(indexNonDigitA);
		} else if (indexNonDigitA == -1)
			numPrefixA = prodNameA;
		else
			alphaSuffixA = prodNameA;
		if (indexNonDigitB > 0) {
			numPrefixB = prodNameB.substring(0, indexNonDigitB);
			alphaSuffixB = prodNameB.substring(indexNonDigitB);
		} else if (indexNonDigitB == -1)
			numPrefixB = prodNameB;
		else
			alphaSuffixB = prodNameB;

		if (numPrefixA != null && numPrefixB != null) {
			int numA = Integer.parseInt(numPrefixA);
			int numB = Integer.parseInt(numPrefixB);
			if (numA > numB)
				return -1;
			else if (numA < numB)
				return 1;
			else if (alphaSuffixA == null && alphaSuffixB == null)
				return 0;
			else if (alphaSuffixA == null && alphaSuffixB != null)
				return -1;
			else if (alphaSuffixA != null && alphaSuffixB == null)
				return 1;
			else if (alphaSuffixA.compareToIgnoreCase(alphaSuffixB) == 0)
				return alphaSuffixA.compareTo(alphaSuffixB);
			return alphaSuffixA.compareToIgnoreCase(alphaSuffixB);
		}
		if (numPrefixA != null && numPrefixB == null)
			return -1;
		if (numPrefixA == null && numPrefixB != null)
			return 1;
		if (alphaSuffixA.compareToIgnoreCase(alphaSuffixB) == 0)
			return alphaSuffixA.compareTo(alphaSuffixB);
		return alphaSuffixA.compareToIgnoreCase(alphaSuffixB);
	}

	private int getIndexOfFirstNonDigit(String prodName) {
		/*
		 * First index of product grid name that will be treated with alpha
		 * ascending although index may correspond to a digit in the product
		 * grid name:
		 */
		final int NUM_DIGITS = 3;
		for (int index = 0; index < NUM_DIGITS; index++) {
			char charA = charAt(prodName, index);
			if (!Character.isDigit(charA))
				return index;
		}
		if (prodName.length() == NUM_DIGITS)
			return -1;
		return NUM_DIGITS;
	}

	private char charAt(String s, int i) {
		if (i >= s.length())
			return 0;
		return s.charAt(i);
	}
}

//String[] products = new String[] { "TEAM", "ta", "TEam 1", "TA", "tab", "10-Year Fixed Rate", "8", "8", "6", "o", ",,", "z", "20-Year Fixed Rate",
//		"30-Year Fixed Rate A", "22-Year Fixed", "30-year fixed rate", "301", "303", "10", "fat", "abc", "ob",
//		"aa", "aaa", "a", "z", "c" };
// Arrays.sort(products, new ProductGridNameComparator());
// for (String product: products)
// System.out.println(product);
