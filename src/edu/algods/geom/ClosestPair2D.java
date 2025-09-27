package edu.algods.geom;

import edu.algods.metrics.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair2D {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double solve(Point[] pts) {
        Point[] px = pts.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Metrics.current().move(px.length);
        Point[] py = px.clone();
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        Metrics.current().move(py.length);
        return rec(px, py, 0, px.length - 1, 1);
    }

    private static double rec(Point[] px, Point[] py, int lo, int hi, long depth) {
        Metrics.current().enterFrame(depth);
        int n = hi - lo + 1;
        if (n <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i = lo; i <= hi; i++)
                for (int j = i + 1; j <= hi; j++)
                    best = Math.min(best, dist(px[i], px[j]));
            return best;
        }
        int mid = lo + (hi - lo) / 2;
        double xmid = px[mid].x;

        Point[] pyl = new Point[mid - lo + 1];
        Point[] pyr = new Point[hi - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= xmid && li < pyl.length) pyl[li++] = p;
            else if (p.x > xmid && ri < pyr.length) pyr[ri++] = p;
        }
        double dl = rec(px, pyl, lo, mid, depth + 1);
        double dr = rec(px, pyr, mid + 1, hi, depth + 1);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[py.length];
        int s = 0;
        for (Point p : py) if (Math.abs(p.x - xmid) <= d) strip[s++] = p;

        for (int i = 0; i < s; i++) {
            for (int j = i + 1; j < Math.min(i + 8, s); j++) {
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}
