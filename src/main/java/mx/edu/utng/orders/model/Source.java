package mx.edu.utng.orders.model;

/**
 * Created by tacho on 7/02/17.
 */

public class Source {
    private String idSource;
    private String nameSource;
    private String typeSource;

    public Source(String idSource, String nameSource, String typeSource) {
        this.idSource = idSource;
        this.nameSource = nameSource;
        this.typeSource = typeSource;
    }

    public Source() {
        this("","", "");
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getNameSource() {
        return nameSource;
    }

    public void setNameSource(String nameSource) {
        this.nameSource = nameSource;
    }

    public String getTypeSource() {
        return typeSource;
    }

    public void setTypeSource(String typeSource) {
        this.typeSource = typeSource;
    }

    @Override
    public String toString() {
        return "Source{" +
                "idSource='" + idSource + '\'' +
                ", nameSource='" + nameSource + '\'' +
                ", typeSource='" + typeSource + '\'' +
                '}';
    }
}
