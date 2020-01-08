package perpusoft;

public class GlobalConfig {

    private String currentIdPetugas;

    //  Class Konstruktor
    public GlobalConfig() {
        this.currentIdPetugas = "1";
    }

    //  Getter ID Petugas yang sedang login
    public String getCurrentIdPetugas() {
        return currentIdPetugas;
    }

    //  Setter ID Petugas yang sedang login
    public void setCurrentIdPetugas(String currentIdPetugas) {
        this.currentIdPetugas = currentIdPetugas;
    }






}
