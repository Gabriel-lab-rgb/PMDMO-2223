package iesmm.pmdm.pmdm_t4_01;

public interface GestorApp {

    public void mandarSms(String contenido);
    public void mandarSmsTo(String contenido,String telefono);
    public void abrirMarcadorllamada();
    public void macarLlamada(String telefono);
    public void realizarLlamada(String telefono);
    public void abrirNavegador(String url);




}
