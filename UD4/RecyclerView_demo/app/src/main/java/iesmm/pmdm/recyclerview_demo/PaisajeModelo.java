package iesmm.pmdm.recyclerview_demo;

public class PaisajeModelo {
    private String pais,ciudad;
    private int imgPaisaje;

    public PaisajeModelo() {
    }

    public PaisajeModelo(String pais, String ciudad, int imgPaisaje) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.imgPaisaje = imgPaisaje;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getImgPaisaje() {
        return imgPaisaje;
    }

    public void setImgPaisaje(int imgPaisaje) {
        this.imgPaisaje = imgPaisaje;
    }
}
