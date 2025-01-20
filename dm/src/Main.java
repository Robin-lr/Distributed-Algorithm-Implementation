import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

 public class Main {
    public static void main(String[] args) {
        System.out.println("----------------Implementation Algo Dist 2----------------");
        Topology tp = new Topology();
        tp.setDefaultNodeModel(Noeud.class);
        new JViewer(tp);
        tp.start();
    }    
}
