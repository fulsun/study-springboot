package pers.sfl.exception.handler.exception;

import lombok.Getter;
import pers.sfl.exception.handler.constant.Status;

/**
 * @description: 页面异常
 */
@Getter
public class PageException extends BaseException {

	public PageException(Status status) {
		super(status);
	}

	public PageException(Integer code, String message) {
		super(code, message);
	}
}
