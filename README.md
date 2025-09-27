Design and Analysis of Algorithms — Assignment Report
Implemented Algorithms

This repository implements four classic divide-and-conquer algorithms with safe recursion patterns and explicit metrics:

MergeSort

Linear merge, one reusable buffer per run, cutoff at n ≤ 20 to insertion sort.

Skips merging if already ordered (a[mid] ≤ a[mid+1]).

QuickSort (robust)

Randomized pivot (shuffle once at the start).

Always recurse into the smaller partition, iterate over the larger one (“smaller-first recursion”).

This ensures stack depth is typically O(log n).

Deterministic Select (Median-of-Medians, groups of 5)

Partition input into groups of 5, sort each group, collect medians, take the median of medians as pivot.

Recurse only into the side containing the k-th element, preferring the smaller side.

Guarantees O(n) worst-case time.

Closest Pair of Points (2D)

Sort points by x; recursive split.

Maintain y-order by merging.

In the “strip”, check only up to 7 subsequent neighbors for each point.

Achieves O(n log n).
