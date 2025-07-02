package pers.sfl.exception.handler.exception;

		import lombok.Getter;
		import pers.sfl.exception.handler.constant.Status;

/**
 * @description: JSON异常
 */
@Getter
public class JsonException extends BaseException {

	public JsonException(Status status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
