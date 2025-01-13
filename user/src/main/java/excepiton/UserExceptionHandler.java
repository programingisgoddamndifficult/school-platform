package excepiton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:16
 */
@ControllerAdvice
@Slf4j
public class UserExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(MissingServletRequestParameterException.class)
}
