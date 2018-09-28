package com.lxw.data.graph;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/28
 *     desc   :
 * </pre>
 */

class Dijkstra {
    public Graph mGraph;
    //源点P
    public int sourceP;
    //源点P  到其他顶点的距离
    public int[] distance;
    //记录已 计算出 最短路径的 点
    public int[] vertices;
    public int verticeSize;
    public int[][] matrix;

    public Dijkstra(Graph graph) {
        mGraph = graph;
        verticeSize = graph.verticeSize;
        distance = new int[verticeSize];
        vertices = new int[verticeSize];
        matrix = mGraph.matrix;
    }

    public void dijkstra() {
        int min;
        int minId = 0;
        //先 初始化 其他点到P的距离
        for (int i = 0; i < verticeSize; i++) {
            distance[i] = matrix[sourceP][i];
        }
        //为1则表示已经找到最短路径
        vertices[sourceP] = 1;
        for (int i = 0; i < verticeSize; i++) {
            min = Graph.MAX_WEIGHT;
            if (vertices[i] == 0 && distance[i] < min) {
                min = distance[i];
                minId = i;
            }
            if (min == Graph.MAX_WEIGHT) {
                break;
            }
            vertices[minId] = 1;
            //更新 其他未记录顶点到P的距离
            for (int j = 0; j < verticeSize; j++) {
                if (vertices[j] == 0 && distance[j] > matrix[minId][j] + distance[minId]) {
                    distance[j] = matrix[minId][j] + distance[minId];
                }
            }
        }
    }
}
