package cn.edu.ustc.xk.exception;

/**
 * Created by 徐科 on 2018/11/28.
 */
public class UserNotExistException extends RuntimeException
{
    public UserNotExistException()
    {
        super("用户不存在");
    }
}
