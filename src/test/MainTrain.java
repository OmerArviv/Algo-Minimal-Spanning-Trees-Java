// Omer Arviv 205967847

package test;

import java.beans.BeanDescriptor;
import java.util.ArrayList;
import java.util.Random;

public class MainTrain {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static int sizeOfGraph=10;
    public static void main(String[] args) {

        Graph graph=new Graph();

        Random rand = new Random();
        int temp = (int)rand.nextInt(10)+20;
        int a,b,c,i;

        Vertex[] ver_arr=new Vertex[temp];
        for (i=0;i<temp;i++){
            ver_arr[i]=new Vertex(i);
        }
        int tempVer=(int)rand.nextInt(10)+80;

        ArrayList<Edge> edgeList=new ArrayList<>();

        for(i=0;i<tempVer;i++){
            a=(int)rand.nextInt(temp);
            b=(int)rand.nextInt(temp);
            c=(int)rand.nextInt(9)+1;
            if (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), c))) {
                while (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), c))) {
                    a = (int) rand.nextInt(temp);
                    b = (int) rand.nextInt(temp);
                    c = (int) rand.nextInt(9) + 1;
                }
            }
            edgeList.add(new Edge(new Vertex(a),new Vertex(b),c));
        }

        Edge[] edge_arr=new Edge[tempVer];
        for(i=0;i<tempVer;i++){
            edge_arr[i]=new Edge(edgeList.get(i).source,edgeList.get(i).dest,edgeList.get(i).weight);
        }


        int[][] temparr = graph.findMinSpanTree(ver_arr,edge_arr);

        a=(int)rand.nextInt(temp);
        b=(int)rand.nextInt(temp);
        if (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), 10))) {
            while (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), 10))) {
                a = (int) rand.nextInt(temp);
                b = (int) rand.nextInt(temp);
            }
        }
        Edge tempEdge=new Edge(new Vertex(a),new Vertex(b),10);

        System.out.println();
        System.out.println(ANSI_CYAN+"The new randomized edge is:\tVertex 1: "+ANSI_YELLOW+tempEdge.source.getVerNum()+ANSI_CYAN+"\tVertex 2: "+ANSI_YELLOW+tempEdge.dest.getVerNum()+ANSI_CYAN+"\tWeight: "+ANSI_YELLOW+tempEdge.weight+ANSI_CYAN);
        System.out.println();
        System.out.println("The minimal spanning tree with the new "+ANSI_YELLOW+"BIG"+ANSI_CYAN+" edge after NewEdgeToTree:");
        System.out.println();

        temparr= graph.NewEdgeToTree(temparr,tempEdge);

        a=(int)rand.nextInt(temp);
        b=(int)rand.nextInt(temp);
        if (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), 1))) {
            while (a == b || edgeList.contains(new Edge(new Vertex(a), new Vertex(b), 1))) {
                a = (int) rand.nextInt(temp);
                b = (int) rand.nextInt(temp);
            }
        }
        tempEdge=new Edge(new Vertex(a),new Vertex(b),1);
        System.out.println();
        System.out.println(ANSI_CYAN+"The new randomized edge is:\tVertex 1: "+ANSI_YELLOW+tempEdge.source.getVerNum()+ANSI_CYAN+"\tVertex 2: "+ANSI_YELLOW+tempEdge.dest.getVerNum()+ANSI_CYAN+"\tWeight: "+ANSI_YELLOW+tempEdge.weight+ANSI_CYAN);
        System.out.println();
        System.out.println("The minimal spanning tree with the new "+ANSI_YELLOW+"SMALL"+ANSI_CYAN+" edge after NewEdgeToTree:");
        System.out.println();
        temparr= graph.NewEdgeToTree(temparr,tempEdge);
    }
}
