package br.com.gilmariosoftware.security;

public class CurrentUser {

    private String token;
    private String usuario;

    public CurrentUser() {
    }

    public CurrentUser(String token, String usuario) {
        super();
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
