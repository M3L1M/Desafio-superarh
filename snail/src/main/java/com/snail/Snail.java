package com.snail;

import java.util.ArrayList;
import java.util.List;

public class Snail {
	public static List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;

        int n = matrix[0].length;

        List<Integer> result = new ArrayList<>();

        if (m == 0)
            return result;

        boolean[][] seen = new boolean[m][n];
        int[] dr = {0, 1, 0, -1};

        int[] dc = {1, 0, -1, 0};

        int r = 0, c = 0;

        int di = 0;

        for (int i = 0; i < m * n; ++i) {

            result.add(matrix[r][c]);

            seen[r][c] = true;

            int newR = r + dr[di];
            int newC = c + dc[di];

            if (0 <= newR && newR < m && 0 <= newC && newC < n
                    && !seen[newR][newC]) {
                r = newR;
                c = newC;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3 },
                { 5, 6, 7},
                { 13, 14, 15 }
        };

        List<Integer> result = spiralOrder(matrix);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
