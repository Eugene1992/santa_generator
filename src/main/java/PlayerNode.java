import java.io.File;

public class PlayerNode {

    private int rndNumber;
    private String name;
    private File form;
    private PlayerNode giveTo;

    public PlayerNode(String name, File form, PlayerNode giveTo) {
        this.rndNumber = (int) (Math.random() * 1000);
        this.name = name;
        this.form = form;
        this.giveTo = giveTo;
    }

    public int getRndNumber() {
        return rndNumber;
    }

    public void setRndNumber(int rndNumber) {
        this.rndNumber = rndNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getForm() {
        return form;
    }

    public void setForm(File form) {
        this.form = form;
    }

    public PlayerNode getGiveTo() {
        return giveTo;
    }

    public void setGiveTo(PlayerNode giveTo) {
        this.giveTo = giveTo;
    }
}
