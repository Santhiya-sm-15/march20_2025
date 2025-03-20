# march20_2025
The problem that i solved today in leetcode

1.There is an undirected weighted graph with n vertices labeled from 0 to n - 1.

You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.

A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk may visit the same edge or vertex more than once.

The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.

You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.

Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.

Code:
class Solution {
    int[] parent,rank;
    public int find(int x)
    {
        if(parent[x]==x)
            return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int u,int v)
    {
        if(u==v) return;
        int pu=find(u);
        int pv=find(v);
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else if(rank[pv]<rank[pu])
            parent[pv]=pu;
        else
        {
            parent[pu]=pv;
            rank[pu]++;
        }
    }
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        parent=new int[n];
        rank=new int[n];
        int[] cost=new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        for(int i=0;i<n;i++)
            parent[i]=i;
        for(int i=0;i<n;i++)
            rank[i]=0;
        for(int[] e:edges)
            union(e[0],e[1]);
        for(int[] e:edges)
        {
            int root=find(e[0]);
            cost[root]&=e[2];
        }
        int[] res=new int[query.length];
        int i=0;
        for(int[] q:query)
        {
            int u=q[0];
            int v=q[1];
            if(find(u)!=find(v))
                res[i++]=-1;
            else
            {
                int root=find(u);
                res[i++]=cost[root];
            }
        }
        return res;
    }
}
