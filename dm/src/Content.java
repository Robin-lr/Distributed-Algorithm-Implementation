import io.jbotsim.core.Node;
import java.util.ArrayList;
import java.util.List;

public class Content {
    protected String type;
    protected List<Node> z = new ArrayList<>();
    protected int value;

    public Content(String type, List<Node> z, int value){
        this.type = type;
        this.z = z;
        this.value = value;
    }

    public String getType(){
        return this.type;
    }

    public List<Node> getZ() {
        return this.z;
    }

    public int getValue() {
        return this.value;
    }

    public String getContent() {
        return "(type = "+ this.getType() + ", z = " + this.getZ() + ", value = " + this.getValue() + ")"; 
    }
}
