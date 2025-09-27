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
Architecture Notes

Metrics: every algorithm reports number of comparisons, moves, allocations, and recursion depth.

Depth control:

MergeSort uses a cutoff and buffer reuse.

QuickSort recurses only into the smaller side, so maximum depth stays close to 2*⌊log2 n⌋ + O(1).

Deterministic Select discards at least 30% of elements each step.

Closest Pair recursion depth is O(log n) by design.

Allocations: controlled by reusing arrays (merge buffer, strip arrays).

Recurrence Analysis

MergeSort:
T(n) = 2T(n/2) + Θ(n) → Master Theorem Case 2 → Θ(n log n).

QuickSort (randomized):
Expected time Θ(n log n) because each level does Θ(n) work and the expected recursion tree height is Θ(log n). Stack depth is bounded by smaller-first recursion.

Deterministic Select (MoM5):
Recurrence T(n) ≤ T(n/5) + T(7n/10) + Θ(n) → Θ(n) (by substitution or Akra–Bazzi).

Closest Pair (2D):
T(n) = 2T(n/2) + Θ(n) → Θ(n log n). The combine step is linear because of the ≤7 neighbor rule.

Akra–Bazzi intuition:
Generalizes Master Theorem to unbalanced subproblems (e.g., Select). Floors/ceilings do not affect asymptotics if f(n) is polynomially bounded.

Measurements & Plots

Time vs n:

MergeSort and QuickSort: n log n.

Select: n.

Closest Pair: n log n.

Depth vs n:

MergeSort: depth ≈ log n.

QuickSort: depth bounded by ~2 log2 n.

Select: depth ≈ log n.

Closest Pair: depth ≈ log n.

Constant-factor effects:

Cache: insertion sort cutoff improves small cases.

Memory/GC: buffer reuse reduces allocations.

Randomization: prevents adversarial inputs for QuickSort.

Testing

Sorting: correctness on random and adversarial inputs, including duplicates.

QuickSort: verify depth ≤ ~2 log2 n + O(1).

Select: compare against Arrays.sort(a)[k] for 100 random trials.

Closest Pair: validated against brute-force O(n²) for small n.
