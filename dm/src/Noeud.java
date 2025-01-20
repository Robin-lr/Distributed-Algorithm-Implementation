import java.util.ArrayList;
import java.util.List;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

public class Noeud extends Node {

    private boolean initiator = false, received = false;
    private List<Node> y = new ArrayList<>(), childs = new ArrayList<>(); 
    private int nbr_ack, local;
    private Content content;
    private Node parent;

    public void onStart() {
        y.add(this);
        System.out.println(getID() + " initialized.");
    }

    public void onSelection() {
        // This is the initiator node => init()
        initiator = true;
        setColor(Color.BLACK);
        System.out.println(getID() + " selected as initiator.");

        // Get the neighbors for counting ACKs
        nbr_ack = getNeighbors().size();
        y.addAll(getInNeighbors());

        // Initialize the message
        content = new Content("exp", y, 0);
        sendAll(new Message(content));
        System.out.println(getID() + " sent exploration message to all neighbors.");
    }

    public void onMessage(Message m) {
        System.out.println(getID() + " received message: " + ((Content) (m.getContent())).getContent() + " from " + m.getSender().getID());

        if ( ( (Content) m.getContent() ).getType() == "exp"){
            y.addAll(getInNeighbors());
            recv_exp(m);
        }

        if ( ( (Content) m.getContent() ).getType() == "ack"){
            recv_ack(m);
        }

        if (this.nbr_ack == 0 ) {
            System.out.println(getID() + " nbr_ack = 0");
            if (this.initiator) {
                System.out.println("Algo finished, value = " + this.local);
            } else {
                content = new Content("ack", null, this.local);
                send(this.parent, new Message(content));
                System.out.println(getID() + " sent: " + content.getContent() + " to " + this.parent); 
            }
        }

    }

    public void recv_exp (Message m) {

        if (this.received == false){

            this.received = true;
            this.parent = m.getSender();
            this.local = this.getID();
            y.removeAll(((Content) m.getContent() ).getZ());
            nbr_ack = y.size();
            if (nbr_ack == 0) return;
            System.out.println(getID() + " y = " + this.y);

            System.out.println(getID() + ": nbr_ack = "+ this.nbr_ack);
            List<Node> z = new ArrayList<>(y);

            z.addAll(((Content) m.getContent() ).getZ());
            System.out.println(getID() + " z = " + z);

            System.out.println( getID() + " sending exp to " + y);
            for (Node element: y){
                content = new Content("exp", z, local);
                send(element, new Message(content));
                System.out.println( getID() + " sent: " + content.getContent() + " to " + element.getID());
            }
        } else{
            content = new Content("ack", null, -1);
            send(m.getSender(), new Message(content));
            System.out.println(getID() + " sent: " + content.getContent() + " to " + m.getSender());

        }

    }

    public void recv_ack (Message m) {
        
        int msgValue = ( (Content) m.getContent() ).getValue();
        nbr_ack--;
        System.out.println( getID() + ": nbr_ack = "+ this.nbr_ack);
        if ( msgValue > 0){
            this.childs.add(m.getSender());
            System.out.println( getID() + ": childs = "+ this.childs);
            local += msgValue;
            System.out.println( getID() + ": local = "+ this.local);
        }
    }

}
