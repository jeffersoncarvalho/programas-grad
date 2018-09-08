package br.ufc.lia.jeffersoncarvalho.ordenacao;

public class SortingMethods {

	public static void selectionSort(Element[] elements) {
		int m = 0;
		for (int i = 0; i < elements.length - 1; i++) {
			m = i;
			for (int j = i + 1; j < elements.length; j++) {
				if (elements[j].getIndex() < elements[m].getIndex())
					m = j;
			}
			Element aux = elements[i];
			elements[i] = elements[m];
			elements[m] = aux;
		}

	}

	public static void mergeSort(Element[] elements, int n) {
		if (n < 2)
			return;
		int m = (int) n / 2;

		Element[] l = new Element[m + 1];
		Element[] r = new Element[n - m + 1];

		for (int i = 0; i < l.length - 1; i++)
			l[i] = elements[i];

		for (int i = 0; i < r.length - 1; i++)
			r[i] = elements[m + i];

		l[l.length - 1] = new Element(null, (Double.MAX_VALUE));
		r[r.length - 1] = new Element(null, (Double.MAX_VALUE));

		mergeSort(l, m);
		mergeSort(r, n - m);
		int i = 0;
		int j = 0;
		for (int k = 0; k < n; k++) {
			if (l[i].getIndex() < r[j].getIndex()) {
				elements[k] = l[i];
				i++;
			} else {
				elements[k] = r[j];
				j++;
			}
		}

	}

	 
	public static void quickSort(Element[] elements, int low, int high) {
		if (low >= high)
			return;
		int p = partition(elements,low, high);
		quickSort(elements,low, p);
		quickSort(elements,p + 1, high);
	}

	 

	private static int partition(Element[] elements,int low, int high) {
		// First element
		double pivot = elements[low].getIndex();

		// Middle element
		// int middle = (low + high) / 2;
		// int pivot = a[middle];

		int i = low - 1;
		int j = high + 1;
		while (i < j) {
			i++;
			while (elements[i].getIndex() < pivot)
				i++;
			j--;
			while (elements[j].getIndex() > pivot)
				j--;
			if (i < j){
				Element temp = elements[i];
				elements[i] = elements[j];
				elements[j] = temp;
			}
		}
		return j;
	}
 

	 
	
	public static void main(String args[]) {
		Element element1 = new Element(null, 7.5);
		Element element2 = new Element(null, 5);
		Element element3 = new Element(null, 12.3);
		Element element4 = new Element(null, 0.8);
		Element element5 = new Element(null, 5.2);
		Element element6 = new Element(null, 1.8);
		Element element7 = new Element(null, 1.7);
		Element element8 = new Element(null, 14.1);

		Element[] elements = new Element[8];
		elements[0] = element1;
		elements[1] = element2;
		elements[2] = element3;
		elements[3] = element4;
		elements[4] = element5;
		elements[5] = element6;
		elements[6] = element7;
		elements[7] = element8;

		print(elements);
		// selectionSort(elements);
		//mergeSort(elements, elements.length);
		quickSort(elements,0,elements.length-1);
		System.out.println();
		print(elements);

	}

	public static void print(Element[] elements) {
		for (int i = 0; i < elements.length; i++)
			System.out.print(" " + elements[i].getIndex());
	}
}
