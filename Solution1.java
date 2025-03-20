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