package login.crudusuarios.Excepciones;

public class UsuarioFoundException extends Exception{

	public UsuarioFoundException() {
		super("Ya existe usuario con username ingresado, ingrese otro username");
	}
	
	public UsuarioFoundException(String mensaje) {
		super(mensaje);
	}
	
}
