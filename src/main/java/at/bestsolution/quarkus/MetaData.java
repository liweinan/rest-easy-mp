package at.bestsolution.quarkus;

public class MetaData implements IMetaData {
    public String metaInfo;

    public MetaData() {

    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getMetaInfo() {
        return this.metaInfo;
    }

    @Override
    public String toString() {
        return "MetaData [metaInfo=" + metaInfo + "]";
    }
}