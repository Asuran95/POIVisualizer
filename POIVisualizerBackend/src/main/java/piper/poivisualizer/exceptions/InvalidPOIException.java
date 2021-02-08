package piper.poivisualizer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPOIException extends RuntimeException {

	private static final long serialVersionUID = 2379708181543366889L;

}
