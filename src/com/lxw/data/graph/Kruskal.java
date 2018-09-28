package com.lxw.data.graph;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/28
 *     desc   :
 * </pre>
 */

public class Kruskal {
    public Graph mGraph;
    public Edge[] mEdges;
    public int verticeSize;
    public int edgeSize;
    public int[][] matrix;

    public Kruskal(Graph graph) {
        mGraph = graph;
        verticeSize = graph.verticeSize;
        mEdges = new Edge[verticeSize * verticeSize];
        matrix = graph.matrix;
        carculate();
    }

    private void carculate() {
        mEdges = getEdges();
        Edge[] edges = mEdges;
        int[] edgTemp = new int[edgeSize];
        Edge[] results = new Edge[edgeSize];
        int index = 0;
        sort(edges);
        int temp;
        for (int i = 0; i < edgeSize; i++) {
            Edge edge = edges[i];
            //判断从该两个顶点出发，是否有回路
            int m = getEnd(edge.start, edgTemp);
            int n = getEnd(edge.end, edgTemp);
            if (m != n) {
                if (m > n) {
                    temp = m;
                    m = n;
                    n = temp;
                }
                edgTemp[m] = n;
                results[index++] = edge;
            }
        }


    }


    private int getEnd(int start, int[] edgTemp) {
        int ret = start;
        while (ret != 0) {
            ret = edgTemp[ret];
        }
        return ret;
    }

    /**
     * 根据权重进行从小到大的排序
     *
     * @param edges
     */
    private void sort(Edge[] edges) {
        if (edges == null || edges.length <= 1) {
            return;
        }

        Edge temp;
        for (int i = 0; i < edgeSize; i++) {
            for (int j = 1; j < edgeSize - i; j++) {
                if (edges[j - 1].weight > edges[j].weight) {
                    temp = edges[j - 1];
                    edges[j - 1] = edges[j];
                    edges[j] = temp;
                }
            }
        }
    }

    private Edge[] getEdges() {
        Edge[] edges = new Edge[verticeSize * verticeSize];
        int index = 0;
        for (int i = 0; i < verticeSize; i++) {
            for (int j = 0; j < verticeSize; j++) {
                if (matrix[i][j] != Graph.MAX_WEIGHT && matrix[i][j] != 0) {
                    edges[index++] = new Edge(i, j, matrix[i][j]);
                }
            }
        }
        edgeSize = index;
        return edges;
    }

    public static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
