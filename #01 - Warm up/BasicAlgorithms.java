import java.util.*;
import java.io.*;

public class BasicAlgorithms {
  public static void main(String...args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    
    long start = System.currentTimeMillis();
    
    int n = Integer.parseInt(in.readLine());
    int[] arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(in.readLine());
    }

    quickSort(arr, 0, n - 1);

    int q = (int) 1e4;
    while (q-- > 0) {
      int e = (int) 1e6 + 5;
      int idx = ternarySearch(arr, e);
    }

    //for (int i = 0; i < n; i++) {
    //  System.out.println(arr[i]);
    //}

    long end = System.currentTimeMillis();
    System.out.printf("\nDone in %dms.\n", end - start);
    
    in.close();
    out.close();
  }

  // Complete search: Time complexity O(n)

  public static int completeSearch(int[] arr, int e) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == e) return i;
    }
    return -1;
  }

  // Binary search: Time complexity O(log2(n))

  public static int binarySearch(int[] arr, int e) {
    int l = 0, r = arr.length - 1, m;
    while (l <= r) {
      m = (l + r) / 2;
      if (arr[m] == e) return m;
      else if (arr[m] > e) r = m - 1;
      else l = m + 1;
    }
    return -1;
  }

  // Ternary search: Time complexity O(log3(n))

  public static int ternarySearch(int[] arr, int e) {
    int l = 0, r = arr.length - 1, m1, m2;
    while (l <= r) {
      m1 = l + (r - l) / 3;
      m2 = r - (r - l) / 3;
      if (arr[m1] == e) return m1;
      else if (arr[m2] == e) return m2;
      else if (arr[m1] > e) r = m1 - 1;
      else if (arr[m2] < e) l = m2 + 1;
      else {
        l = m1 + 1;
        r = m2 - 1;
      }
    }
    return -1;
  }

  // Insertion sort: Time complexity O(n^2)

  public static int imin(int[] arr, int l, int r) {
    int imin = l;
    for (int i = l + 1; i <= r; i++) {
      if (arr[i] < arr[imin]) imin = i;
    }
    return imin;
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int imin = imin(arr, i, arr.length - 1);
      swap(arr, i, imin);
    }
  }

  // Merge sort: Time complexity O(nlog(n))

  public static void merge(int[] arr, int l, int r) {
    int m = (l + r) / 2;
    int i1 = l, i2 = m + 1, i = 0;
    int[] sorted = new int[r - l + 1];
    while (i1 <= m && i2 <= r) {
      if (arr[i1] < arr[i2]) {
        sorted[i++] = arr[i1++];
      } else {
        sorted[i++] = arr[i2++];
      }
    }
    while (i1 <= m) {
      sorted[i++] = arr[i1++];
    }
    while (i2 <= r) {
      sorted[i++] = arr[i2++];
    }
    for (i = 0; i < r - l + 1; i++) {
      arr[l + i] = sorted[i];
    }
  }

  public static void mergeSort(int[] arr, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;
      mergeSort(arr, l, m);
      mergeSort(arr, m + 1, r);
      merge(arr, l, r);
    }
  }

  // Quick sort: Time complexity O(nlog(n)) and O(n^2) in worst case
  
  public static int partition(int[] arr, int l, int r) {
    int iless = l - 1, pivot = arr[r];
    for (int i = l; i <= r - 1; i++) {
      if (arr[i] <= pivot) {
        iless++;
        swap(arr, i, iless);
      }
    }
    swap(arr, iless + 1, r);
    return iless + 1;
  }
  
  public static void quickSort(int[] arr, int l, int r) {
    if (l < r) {
      int pivot = partition(arr, l, r);
      quickSort(arr, l, pivot - 1);
      quickSort(arr, pivot + 1, r);
    }
  }
}
