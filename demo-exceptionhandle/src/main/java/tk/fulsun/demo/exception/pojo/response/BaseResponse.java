package tk.fulsun.demo.exception.pojo.response;

import lombok.Data;
import tk.fulsun.demo.exception.constant.IResponseEnum;
import tk.fulsun.demo.exception.constant.enums.CommonResponseEnum;

/**
 * @author fulsun
 * @date 7/1/2021
 */

@Data
public class BaseResponse {
    /** 返回码 */
    protected int code;
    /** 返回消息 */
    protected String message;

    public BaseResponse() {
        // 默认创建成功的回应
        this(CommonResponseEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}