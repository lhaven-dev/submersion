package fr.lhaven.submersion;


import java.util.ArrayList;
import java.util.List;

public class Host {

    private static Host instance;

    private String hostName;
    private List<String> coHosts;
    private Host() {
        hostName = "lhaven";
        coHosts = new ArrayList<>();
    }

    public static Host getInstance() {
        if (instance == null) {
            instance = new Host();
        }
        return instance;
    }

    public void setCoHosts(String coHosts) {
    this.coHosts.add(coHosts);
    }

    public List<String> getCoHosts() {
       return coHosts;
    }

    public boolean isCoHost(String coHost) {
        return coHosts.contains(coHost);
    }
    public boolean isHost(String host) {
        return hostName.equals(host);
    }

}

