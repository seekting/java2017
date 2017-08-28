/**
 * Created by seekting on 17-5-5.
 */

public class Sort2 {

    public static void main(String args[]) {

        int[] a = new int[]{4, 7, 3, 8, 9, 1, 2, 6, 5};
        mergeSort(a);
        print(a);
    }

    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");

        }
        System.out.println();
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;

    }

    public static void bubbleSort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }

            }

        }

    }

    public static void selectSort(int a[]) {

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }


            }
            if (minIndex != i) {
                swap(a, minIndex, i);
            }

        }
    }

    public static void insertSort(int a[]) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }

            }

        }
    }

    public static void shellSort(int a[]) {
        int d = a.length;
        while (true) {
            d = d / 2;
            if (d == 0) {
                return;
            }
            shellSortD(a, d);

        }

    }

    private static void shellSortD(int a[], int d) {
        for (int x = 0; x < d; x++) {
            for (int i = x; i + d < a.length; i = i + d) {
                for (int j = i + d; j - d >= x; j = j - d) {
                    if (a[j] < a[j - d]) {
                        swap(a, j, j - d);
                    } else {
                        break;
                    }

                }

            }
        }
    }

    public static void heapSort(int a[]) {

        for (int i = a.length / 2 - 1; i >= 0; i--) {
            createMaxHeap(a, i, a.length - 1);
        }
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            createMaxHeap(a, 0, i - 1);
        }


    }

    private static void createMaxHeap(int a[], int head, int to) {
        if (head >= to) {
            return;
        }
        int left = head * 2 + 1;
        int right = head * 2 + 2;
        int maxIndex = head;
        if (left <= to && a[left] > a[maxIndex]) {
            maxIndex = left;
        }
        if (right <= to && a[right] > a[maxIndex]) {
            maxIndex = right;

        }
        if (maxIndex != head) {
            swap(a, maxIndex, head);
            createMaxHeap(a, maxIndex, to);
        }

    }

    public static void mergeSort(int a[]) {
        int d = 1;
        while (true) {
            for (int i = 0; i < a.length; i = i + 2 * d) {
                merge(a, i, i + d - 1, i + d, (i + d) + (d - 1));
            }
            d = d * 2;
            if (d >= a.length) {
                break;
            }
        }

    }

    private static void merge(int a[], int leftF, int leftT, int rightF, int rightT) {
        if (rightF >= a.length) {
            return;
        }
        rightT = Math.min(rightT, a.length - 1);
        int leftIndex = leftF;
        int rightIndex = rightF;
        int[] temp = new int[rightT - leftF + 1];

        for (int i = 0; i < temp.length; i++) {
            if (leftIndex <= leftT) {
                if (rightIndex > rightT) {
                    temp[i] = a[leftIndex];
                    leftIndex++;
                } else {
                    if (a[leftIndex] <= a[rightIndex]) {
                        temp[i] = a[leftIndex];
                        leftIndex++;
                    } else {
                        temp[i] = a[rightIndex];
                        rightIndex++;
                    }
                }
            } else {
                if (rightIndex > rightT) {
                    break;
                } else {
                    temp[i] = a[rightIndex];
                    rightIndex++;
                }
            }


        }

        arrayCopy(a, leftF, temp, 0, temp.length);

    }

    private static void arrayCopy(int[] a, int begin, int[] b, int from, int to) {
        for (int i = 0; i < to; i++) {
            a[begin] = b[i];
            begin++;

        }
    }
    public static void quickSort(int a[]){
        
    }
}
