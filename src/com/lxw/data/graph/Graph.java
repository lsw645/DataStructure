package com.lxw.data.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/26
 *     desc   :
 * </pre>
 */

public class Graph {
    public int[] vertices;
    public int[][] matrix;
    public boolean[] isVisited;
    public static final int MAX_WEIGHT = Integer.MAX_VALUE;
    public int verticeSize;

    public Graph(int verticeSize) {
        this.verticeSize = verticeSize;
        this.vertices = new int[verticeSize];
        this.matrix = new int[verticeSize][verticeSize];
        isVisited = new boolean[verticeSize];
    }

    /**
     * v1到v2的权重
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        int weight = matrix[v1][v2];
        return weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight);
    }


    public int[] getVertices() {
        return vertices;
    }


    public int getOutDegree(int v1) {
        int count = 0;
        for (int i = 0; i < verticeSize; i++) {
            if (matrix[v1][i] != 0 && matrix[v1][i] != MAX_WEIGHT) {
                count++;
            }
        }
        return count;
    }


    public int getInDegree(int v1) {
        int count = 0;
        for (int i = 0; i < verticeSize; i++) {
            if (matrix[i][v1] != 0 && matrix[i][v1] != MAX_WEIGHT) {
                count++;
            }
        }
        return count;
    }

    public int getFirstNeighbor(int v1) {
        for (int i = 0; i < verticeSize; i++) {
            if (matrix[v1][i] != 0 && matrix[v1][i] != MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int index) {
        for (int i = index + 1; i < verticeSize; i++) {
            if (matrix[v1][i] != 0 && matrix[v1][i] != MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }


    private static int[][] h = new int[][]{
            {1, 2, 3, 4, 5, 6},
            {6, 5, 4, 3, 2, 1},
    };

    public static void main(String[] args) {
//        System.out.println();
        for (int i = 0; i < h[0].length; i++) {
            System.out.print(h[0][i] + "，");
        }
    }

    public void bfs() {


        for (int i = 0; i < verticeSize; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = true;
            System.out.println("isVisited: " + i);
            bfs(i);
        }

    }

    private void bfs(int index) {
        Queue<Integer> queue = new LinkedList();
        int fn = getFirstNeighbor(index);
        if (fn == -1) {
            return;
        }
        if (!isVisited[fn]) {
            isVisited[fn] = true;
            System.out.println("isVisited: " + fn);
            queue.offer(fn);
        }
        int nextNeighbor = getNextNeighbor(index, fn);
        while (nextNeighbor != -1) {
            if (!isVisited[nextNeighbor]) {
                isVisited[nextNeighbor] = true;
                System.out.println("isVisited: " + fn);
                queue.offer(nextNeighbor);
            }

            nextNeighbor = getNextNeighbor(index, nextNeighbor);
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            bfs(poll);
        }
    }

    public void prim() {
        int[] lowcost = new int[verticeSize];
        //lowcost 的值为0 ,即代表该点已走过， 其他不为0的值，代表该顶点 到其他未访问顶点的 最短距离
        //当顶点为第一个的时候，则是顶点到 其他顶点的距离
        for (int i = 0; i < verticeSize; i++) {
            lowcost[i] = matrix[0][i];
        }
        int min = 0;
        int minId = 0;
        int sum = 0;
        for (int i = 1; i < verticeSize; i++) {
            min = MAX_WEIGHT;
            for (int j = 1; j < verticeSize; j++) {
                if (lowcost[j] < min) {
                    min = lowcost[j];
                    minId = j;
                }
            }
            if (min == MAX_WEIGHT) {
                break;
            }
            lowcost[minId] = 0;
            sum += min;

            for (int j = 1; j < verticeSize; j++) {
                if (lowcost[j] != 0 && matrix[minId][j] < lowcost[j]) {
                    lowcost[j] = matrix[minId][j];
                }
            }
        }
        System.out.println("最小生成树代价：" + sum);
    }
}
